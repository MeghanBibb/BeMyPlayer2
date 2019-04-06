package firebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Account;
import model.DBDocumentPackage;
import model.Profile;

import com.google.cloud.Service;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseOptions;

public class FireBaseAdapter {
	
	public final static Logger LOGGER = Logger.getLogger(FireBaseAdapter.class.getName());
	
	private static final String FIREBASE_TOKEN_PATH = "C:/Users/colin/firebase/ServiceAccountKey/bemyplayer2-e65fc-dca2d3903ee3.json";
	private static final String DB_URL = "https://bemyplayer2-e65fc.firebaseio.com";
	private Firestore db = null;
	
	public boolean initializeDBConnection() {
	
		FirebaseOptions options = null;
		try {
			FileInputStream clientKeyToken = new FileInputStream(FIREBASE_TOKEN_PATH);
			options = new FirebaseOptions.Builder()
			    .setCredentials(GoogleCredentials.fromStream(clientKeyToken))
			    .setDatabaseUrl(DB_URL)
			    .build();
			clientKeyToken.close();
			FirebaseApp.initializeApp(options);
			this.db = FirestoreClient.getFirestore();
				
		} catch (Exception e) {
			options = null;
			LOGGER.log(Level.SEVERE,"Error- unable to locate FIREBASE_TOKEN_PATH");
			return false;
		}
		LOGGER.log(Level.FINE, "Connected to database");
		return true;
	}
	
	public boolean attemptAddNewAccount(Account account) throws DBFailureException {
		
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		//query if user exists:
		ApiFuture<QuerySnapshot> userExists = 
				db.collection(FireBaseSchema.ACCOUNTS_TABLE)
				.whereEqualTo(Account._EMAIL, account.getEmail()).get();
		
		try {
			if(!userExists.get().isEmpty()) {
				return false;
			}
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- new Account existence query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Execution Exception thrown while querying new Account existence.");
			throw new DBFailureException();
		}
		
		DBDocumentPackage accPackage = account.toDBPackage();
		DBDocumentPackage profPackage = null;
		ApiFuture<DocumentReference> newAccountDoc;
		
		
		if(account.getAccountProfile() == null) {
			LOGGER.log(Level.WARNING, "Error- no profile specified for account;\n" +
									  "Only account information will be written...");
		}else {
			profPackage = account.getAccountProfile().toDBPackage();
		}
		
		newAccountDoc = this.db.collection(FireBaseSchema.ACCOUNTS_TABLE)
				.add(accPackage.getValues());
		
		try {
			String newId = newAccountDoc.get().getId();
			account.setUserId(newId);
			
			if(profPackage != null) {
				db.collection(FireBaseSchema.PROFILES_TABLE)
				.document(newId)
				.set(profPackage.getValues());
			}
			LOGGER.log(Level.FINE,"Added user with ID: " + newId);
			
		} catch (InterruptedException e1) {
			LOGGER.log(Level.SEVERE,"Error- Account addition interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e1) {
			LOGGER.log(Level.SEVERE,"Error- Execution Exception thrown while adding account.");
			throw new DBFailureException();
		}
		return true; 
	}
	
	public String authenticateUserAccount(String userEmail, String passwordHash) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		//query if user exists:
		ApiFuture<QuerySnapshot> fetchUser = 
				db.collection(FireBaseSchema.ACCOUNTS_TABLE)
				.whereEqualTo(Account._EMAIL, userEmail)
				.whereEqualTo(Account._PASSWORD_HASH, passwordHash)
				.get();
		
		try {
			QuerySnapshot authUser = fetchUser.get();
			if(authUser.isEmpty()) {
				LOGGER.log(Level.WARNING, "[FIREBASE] Error- query returned duplicate users for user email: " + userEmail);
				return null;
			}else if(authUser.size() > 1) {
				LOGGER.log(Level.FINE, "Error- Query returned empty for user: " + userEmail);
				return null;
			}else {
				return authUser.getDocuments().get(0).getId();
			}
			
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Account authorization query failed.");
			throw new DBFailureException();
		}
	}
	
	public Account getUserAccountNoProfile(String userId) throws DBFailureException{
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		//query if user exists:
		ApiFuture<DocumentSnapshot> fetchAccount = 
				db.collection(FireBaseSchema.ACCOUNTS_TABLE)
				.document(userId)
				.get();
		Account userAccount = null;
		
		try {
			
			DocumentSnapshot result = fetchAccount.get();
			if(!result.exists()) {
				LOGGER.log(Level.FINE,"Could not find account with given user id");
			}else {
				DBDocumentPackage accountPackage = new DBDocumentPackage(userId, result.getData());
				userAccount = new Account();
				userAccount.initializeFromPackage(accountPackage);
			}
			
		} catch (InterruptedException e1) {
			LOGGER.log(Level.SEVERE,"Error- Account query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e1) {
			LOGGER.log(Level.SEVERE,"Error- Execution Exception thrown while querying account.");
			throw new DBFailureException();
		}
		
		return userAccount;
	}
	
	public Account getUserAccountWithProfile(String userId) throws DBFailureException{
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		//query if user exists:
		ApiFuture<DocumentSnapshot> fetchAccount = 
				db.collection(FireBaseSchema.ACCOUNTS_TABLE)
				.document(userId)
				.get();
		ApiFuture<DocumentSnapshot> fetchProfile = 
				db.collection(FireBaseSchema.PROFILES_TABLE)
				.document(userId)
				.get();
		Account userAccount = new Account();
		
		try {
			
			DocumentSnapshot accResult = fetchAccount.get();
			if(!accResult.exists()) {
				LOGGER.log(Level.FINE,"Could not find account with given user id");
				return null;
			}else {
				DBDocumentPackage accountPackage = new DBDocumentPackage(userId, accResult.getData());
				userAccount.initializeFromPackage(accountPackage);
			}
			
			Profile userProfile = new Profile();
			DocumentSnapshot profResult = fetchProfile.get();
			if(!profResult.exists()){
				LOGGER.log(Level.FINE,"Could not find profile with given user id");
				return null;
			} else {
				DBDocumentPackage profilePackage = new DBDocumentPackage(userId, profResult.getData());
				userProfile.initializeFromPackage(profilePackage);
				userAccount.setAccountProfile(userProfile);
			}
			
		} catch (InterruptedException e1) {
			LOGGER.log(Level.SEVERE,"Error- Account query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e1) {
			LOGGER.log(Level.SEVERE,"Error- Execution Exception thrown while querying account.");
			throw new DBFailureException();
		}
		
		return userAccount;
	}
	
	public boolean updateUserAccount(Account account) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		ApiFuture<DocumentSnapshot> accountExists = 
				db.collection(FireBaseSchema.ACCOUNTS_TABLE)
				.document(account.getUserId())
				.get();
		
		ApiFuture<DocumentSnapshot> profileExists = 
				db.collection(FireBaseSchema.PROFILES_TABLE)
				.document(account.getUserId())
				.get();
		DocumentReference accRef = db.collection(FireBaseSchema.ACCOUNTS_TABLE)
										.document(account.getUserId());
		DocumentReference profRef = db.collection(FireBaseSchema.PROFILES_TABLE)
				.document(account.getUserId());
		Profile userProf = null;
		
		if(account == null || account.getUserId() == null) {
			LOGGER.log(Level.WARNING,"Account has no initialized userId; cannot update");
			throw new DBFailureException();
		}
		
		
		try {
			DocumentSnapshot accSnapshot = accountExists.get();
			if(!accSnapshot.exists()) {
				LOGGER.log(Level.FINE,"Account not found in database; cannot update");
				return false;
			}
			DBDocumentPackage accWrite = account.toDBPackage();
			ApiFuture<WriteResult> accUpdateResult = accRef.update(accWrite.getValues());
			userProf = account.getAccountProfile();
			
			if(userProf != null) {
				DocumentSnapshot profSnapshot = profileExists.get();
				if(!profSnapshot.exists()) {
					LOGGER.log(Level.WARNING,"Error- Account does not have corresponding profile");
					LOGGER.log(Level.WARNING,"Creating Profile...");
					
				}else {
					DBDocumentPackage profWrite = account.getAccountProfile().toDBPackage();
					ApiFuture<WriteResult> profUpdateResult = profRef.set(profWrite.getValues());
					profUpdateResult.get();
				}
			}
			accUpdateResult.get();
				
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- new Account update query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Execution Exception thrown while updating Account.");
			throw new DBFailureException();
		}
		LOGGER.log(Level.FINE,"Updated an Account.");
		return true;
	}
	
}
