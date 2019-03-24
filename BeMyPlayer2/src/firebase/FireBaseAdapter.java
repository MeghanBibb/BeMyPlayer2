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
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Account;
import model.DBDocumentPackage;

import com.google.cloud.Service;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseOptions;

public class FireBaseAdapter {
	
	public final static Logger LOGGER = Logger.getLogger(FireBaseAdapter.class.getName());
	
	private static String FIREBASE_TOKEN_PATH = "C:/Users/colin/firebase/ServiceAccountKey/bemyplayer2-e65fc-dca2d3903ee3.json";
	private Firestore db = null;
	
	public boolean initializeDBConnection() {
	
		FirebaseOptions options = null;
		try {
			FileInputStream clientKeyToken = new FileInputStream(FIREBASE_TOKEN_PATH);
			options = new FirebaseOptions.Builder()
			    .setCredentials(GoogleCredentials.fromStream(clientKeyToken))
			    .setDatabaseUrl("https://bemyplayer2-e65fc.firebaseio.com")
			    .build();
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
	
	public boolean AuthenticateUserAccount(String user, String passwordHash) {
	
		return true;
	}
	
	public Account queryUserAccount(String userId) {
		return null;
	}
	
}
