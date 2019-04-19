package firebase;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Blob.BlobSourceOption;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobGetOption;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.*;

import com.google.cloud.Service;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseOptions;

// TODO: Auto-generated Javadoc
/**
 * The Class FireBaseAdapter.
 */
public class FireBaseAdapter {
	
	/** The Constant LOGGER. */
	public final static Logger LOGGER = Logger.getLogger(FireBaseAdapter.class.getName());
	
	/** The Constant FIREBASE_TOKEN_PATH. */
	private static final String FIREBASE_TOKEN_PATH = "db/bemyplayer2-e65fc-dca2d3903ee3.json";
	
	/** The Constant DB_URL. */
	private static final String DB_URL = "https://bemyplayer2-e65fc.firebaseio.com";
	
	/** The Constant DB_BUCKET_NAME. */
	private static final String DB_BUCKET_NAME = "bemyplayer2-e65fc.appspot.com";
	
	/** The Constant MAX_NUM_PROFILES_RETRIEVED. */
	private static final int MAX_NUM_PROFILES_RETRIEVED = 10;
	
	/** The Constant LOVE_MATCHES. */
	public static final String LOVE_MATCHES = "LOVE_MATCHES";
	
	/** The Constant FRIEND_MATCHES. */
	public static final String FRIEND_MATCHES = "FRIEND_MATCHES";
	
	/** The db. */
	private Firestore db = null;
	
	/**
	 * Initialize DB connection.
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Adds the new payment info.
	 *
	 * @param payment the payment
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
	public boolean addNewPaymentInfo(PaymentInfo payment) throws DBFailureException {
		
		if(this.db == null) {
			LOGGER.warning("Error- no database connection");
			throw new DBFailureException();
		}
		

		DBDocumentPackage payPackage = payment.toDBPackage();
		
		db.collection(FireBaseSchema.PAYMENT_TABLE)
		        .document(payment.getID())
		        .set(payPackage.getValues());
		
		
		return true;
	}
	
	/**
	 * Removes the payment info.
	 *
	 * @param userID the user ID
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
	public boolean removePaymentInfo(String userID) throws DBFailureException {
		
		if(this.db == null) {
			LOGGER.warning("Error- no database connection");
			throw new DBFailureException();
		}
		
		
		ApiFuture<DocumentSnapshot> payment = 
				db.collection(FireBaseSchema.PAYMENT_TABLE)
				.document(userID)
				.get();
				
		
		ApiFuture<QuerySnapshot> paymentQ = 
				db.collection(FireBaseSchema.PAYMENT_TABLE)
				.whereEqualTo(PaymentInfo._USER_ID, userID).get();
		
		try {
			if(paymentQ.get().isEmpty()) {
				return false;
			}
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Match query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Match query failed.");
			throw new DBFailureException();
		}
		
		ApiFuture<WriteResult> deletePayment = db.collection(FireBaseSchema.PAYMENT_TABLE)
				.document(userID)
				.delete();
		
		return true;
	}
	
	/**
	 * Gets the payment info.
	 *
	 * @param userID the user ID
	 * @return the payment info
	 * @throws DBFailureException the DB failure exception
	 */
	public PaymentInfo getPaymentInfo(String userID) throws DBFailureException {
		
		if(this.db == null) {
			LOGGER.warning("Error- no database connection");
			throw new DBFailureException();
		}
		
		ApiFuture<DocumentSnapshot> getPayment = 
				db.collection(FireBaseSchema.PAYMENT_TABLE)
					.document(userID)
					.get();
		
		DocumentSnapshot payment;
		
		try {
			
			payment = getPayment.get();
			if(!payment.exists()) {
				return null;
			}
			
			DBDocumentPackage pkg = new DBDocumentPackage(payment.getId(), payment.getData());
			PaymentInfo info = new PaymentInfo(userID);
			info.initializeFromPackage(pkg);
			
			return info;
			
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Match query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Match query failed.");
			throw new DBFailureException();
		}
		
	}
	
	/**
	 * Adds the new issue.
	 *
	 * @param issue the issue
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
	public boolean addNewIssue(Issue issue) throws DBFailureException {
		
		if(this.db == null) {
			LOGGER.warning("Error- no database connection");
			throw new DBFailureException();
		}
		
		DBDocumentPackage issPackage = issue.toDBPackage();
		ApiFuture<DocumentReference> newIssueDoc;
		
		newIssueDoc = this.db.collection(FireBaseSchema.ISSUES_TABLE)
				.add(issPackage.getValues());
		
		return true;
	}
	
	/**
	 * Attempt add new account.
	 *
	 * @param account the account
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
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
				account.getAccountProfile().setUserId(newId);
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
	
	/**
	 * Authenticate user account.
	 *
	 * @param userEmail the user email
	 * @param passwordHash the password hash
	 * @return the string
	 * @throws DBFailureException the DB failure exception
	 */
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
				LOGGER.log(Level.FINE, "Error- Query returned empty for user: " + userEmail);
				return null;
			}else if(authUser.size() > 1) {
				LOGGER.log(Level.WARNING, "[FIREBASE] Error- query returned duplicate users for user email: " + userEmail);
				return null;
			}else {
				return authUser.getDocuments().get(0).getId();
			}
			
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Account authorization query failed.");
			throw new DBFailureException();
		}
	}
	
	/**
	 * Reset user account password.
	 *
	 * @param userEmail the user email
	 * @param securityQ the security Q
	 * @param ansHash the ans hash
	 * @param passwordHash the password hash
	 * @param username the username
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
	public boolean resetUserAccountPassword(String userEmail, int securityQ, String ansHash, String passwordHash, String username) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		String securityQName;
		String ansName;
		if(securityQ == 1) {
			securityQName = Account._SECURITY_Q1;
			ansName = Account._SECURITY_Q1A;
		}else if(securityQ == 2) {
			securityQName = Account._SECURITY_Q2;
			ansName = Account._SECURITY_Q2A;
		}else {
			//invalid security Q number:
			throw new DBFailureException();
		}
		
		//System.out.println(ansHash);
		//System.out.println(userEmail);
		
		//query if user account exists:
		ApiFuture<QuerySnapshot> fetchUser = 
				db.collection(FireBaseSchema.ACCOUNTS_TABLE)
				.whereEqualTo(Account._EMAIL, userEmail)
				.whereEqualTo(ansName, ansHash)
				.get();
		
		//TODO: VALIDATE USERNAME
		
		
		try {
			QuerySnapshot authUser = fetchUser.get();
			if(authUser.isEmpty()) {
				LOGGER.log(Level.INFO, "Error- Query returned empty for user: " + userEmail);
				return false;
			}else if(authUser.size() > 1) {
				LOGGER.log(Level.WARNING, "[FIREBASE] Error- query returned duplicate users for user email: " + userEmail);
				return false;
			}else {
				//reset password:
				DocumentReference accRef = db.collection(FireBaseSchema.ACCOUNTS_TABLE)
												.document(authUser.getDocuments().get(0).getId());
				
				ApiFuture<WriteResult> updatePasswordRes = accRef.update(Account._PASSWORD_HASH, passwordHash);
				updatePasswordRes.get();
				return true;
			}
			
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Account password reset failed.");
			throw new DBFailureException();
		}
		
	}
	
	/**
	 * Gets the user account no profile.
	 *
	 * @param userId the user id
	 * @return the user account no profile
	 * @throws DBFailureException the DB failure exception
	 */
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
	
	/**
	 * Gets the user account with profile.
	 *
	 * @param userId the user id
	 * @return the user account with profile
	 * @throws DBFailureException the DB failure exception
	 */
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
	
	/**
	 * Update user account.
	 *
	 * @param account the account
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
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
					
				}
				DBDocumentPackage profWrite = account.getAccountProfile().toDBPackage();
				ApiFuture<WriteResult> profUpdateResult = profRef.set(profWrite.getValues());
				profUpdateResult.get();
			}
			accUpdateResult.get();
				
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Account update query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Execution Exception thrown while updating Account.");
			throw new DBFailureException();
		}
		LOGGER.log(Level.FINE,"Updated an Account.");
		return true;
	}
	
	/**
	 * Update profile.
	 *
	 * @param profile the profile
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
	public boolean updateProfile(Profile profile) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		ApiFuture<DocumentSnapshot> profileExists = 
				db.collection(FireBaseSchema.PROFILES_TABLE)
				.document(profile.getUserId())
				.get();
		DocumentReference profRef = db.collection(FireBaseSchema.PROFILES_TABLE)
				.document(profile.getUserId());
		Profile userProf = null;
		
		if(profile == null || profile.getUserId() == null) {
			LOGGER.log(Level.WARNING,"Profile has no initialized userId; cannot update");
			throw new DBFailureException();
		}
		
		
		try {
			DocumentSnapshot profSnapshot = profileExists.get();
			if(!profSnapshot.exists()) {
				LOGGER.log(Level.WARNING,"Error- Profile does not exist in database.");
				
			}else {
				DBDocumentPackage profWrite = profile.toDBPackage();
				ApiFuture<WriteResult> profUpdateResult = profRef.set(profWrite.getValues());
				profUpdateResult.get();
			}
			
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Profile update query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Execution Exception thrown while updating Profile.");
			throw new DBFailureException();
		}
		LOGGER.log(Level.FINE,"Updated an Account.");
		return true;
	}
	
	/**
	 * Gets the profile.
	 *
	 * @param userId the user id
	 * @return the profile
	 * @throws DBFailureException the DB failure exception
	 */
	public Profile getProfile(String userId) throws DBFailureException {
		
		ApiFuture<DocumentSnapshot> fetchProfile = 
				db.collection(FireBaseSchema.PROFILES_TABLE)
				.document(userId)
				.get();
		
		Profile prof = new Profile();
		try {
			DocumentSnapshot profResult = fetchProfile.get();
			if(!profResult.exists()){
				LOGGER.log(Level.FINE,"Could not find profile with given user id");
				return null;
			} else {
				DBDocumentPackage profilePackage = new DBDocumentPackage(userId, profResult.getData());
				prof.initializeFromPackage(profilePackage);
			}
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Profile query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Profile query failed.");
			throw new DBFailureException();
		}
		
		return prof;
	}
	
	/**
	 * Gets the fully matched profiles.
	 *
	 * @param userProfile the user profile
	 * @param matchType the match type
	 * @return the fully matched profiles
	 * @throws DBFailureException the DB failure exception
	 */
	public List<Profile> getFullyMatchedProfiles(Profile userProfile, String matchType) throws DBFailureException{
		
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		String mStringType = MatchType._TYPE_LOVE_MATCH;
		if(matchType != LOVE_MATCHES && matchType != FRIEND_MATCHES) {
			LOGGER.log(Level.WARNING, "Error- Invalid call argument: " + matchType);
			return null;
		}else if(matchType == FRIEND_MATCHES) {
			mStringType = MatchType._TYPE_FRIEND_MATCH;
		}
		
		List<Profile> profList = null;
		ApiFuture<QuerySnapshot> mBatchQuery = 
				db.collection(FireBaseSchema.MATCHES_TABLE)
					.document(userProfile.getUserId())	
					.collection(FireBaseSchema.MATCHES_TABLE_COLLECTION)
					.whereEqualTo(Match._TYPE, mStringType)
					.whereEqualTo(Match._CLIENT_MATCH_STATUS, MatchStatus._STATUS_SWIPE_RIGHT)
					.whereEqualTo(Match._OTHER_MATCH_STATUS, MatchStatus._STATUS_SWIPE_RIGHT)
					.get();
		
		QuerySnapshot matchBatch;
		try {
			matchBatch = mBatchQuery.get();
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Match batch retrieval query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Match batch retrieval query failed.");
			throw new DBFailureException();
		}
		
		if(matchBatch.isEmpty()) {
			LOGGER.log(Level.FINE, "Query for matches returned empty.");
			return new ArrayList<Profile>();
		}else {
			
			//parallelize package conversion to list of profiles:
			profList = matchBatch.getDocuments().parallelStream()
				.map(m -> {
					try {
						return this.getProfile(m.getId());
					} catch (Exception e) {
						LOGGER.log(Level.INFO, "Error- a Profile retrieval query failed");
						return null;
					} 
				})
				.filter(p -> p != null)
				.collect(Collectors.toList());
			
		}
		
		return profList;
	}
	
	/**
	 * Gets the other matched profiles.
	 *
	 * @param userProfile the user profile
	 * @param matchType the match type
	 * @return the other matched profiles
	 * @throws DBFailureException the DB failure exception
	 */
	public List<Profile> getOtherMatchedProfiles(Profile userProfile, String matchType) throws DBFailureException{
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		String mStringType = MatchType._TYPE_LOVE_MATCH;
		if(matchType != LOVE_MATCHES && matchType != FRIEND_MATCHES) {
			LOGGER.log(Level.WARNING, "Error- Invalid call argument: " + matchType);
			return null;
		}else if(matchType == FRIEND_MATCHES) {
			mStringType = MatchType._TYPE_FRIEND_MATCH;
		}
		
		List<Profile> profList = null;
		ApiFuture<QuerySnapshot> mBatchQuery = 
				db.collection(FireBaseSchema.MATCHES_TABLE)
					.document(userProfile.getUserId())
					.collection(FireBaseSchema.MATCHES_TABLE_COLLECTION)
					.whereEqualTo(Match._TYPE, mStringType)
					.whereEqualTo(Match._CLIENT_MATCH_STATUS, MatchStatus._STATUS_NO_MATCH)
					.whereEqualTo(Match._OTHER_MATCH_STATUS, MatchStatus._STATUS_SWIPE_RIGHT)
					.get();
		
		QuerySnapshot matchBatch;
		try {
			matchBatch = mBatchQuery.get();
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Match batch retrieval query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Match batch retrieval query failed.");
			throw new DBFailureException();
		}
		
		if(matchBatch.isEmpty()) {
			LOGGER.log(Level.FINE, "Query for matches returned empty.");
			return new ArrayList<Profile>();
		}else {
			
			//parallelize package conversion to list of profiles:
			profList = matchBatch.getDocuments().parallelStream()
				.map(m -> {
					try {
						return this.getProfile(m.getId());
					} catch (Exception e) {
						LOGGER.log(Level.INFO, "Error- a Profile retrieval query failed");
						return null;
					} 
				})
				.filter(p -> p != null && p.getUserId() != userProfile.getUserId())
				.collect(Collectors.toList());
			
		}
		
		return profList;
	}
	
	/**
	 * Gets the unmatched profiles.
	 *
	 * @param userId the user id
	 * @param matchType the match type
	 * @return the unmatched profiles
	 * @throws DBFailureException the DB failure exception
	 */
	public List<Profile> getUnmatchedProfiles(String userId, String matchType) throws DBFailureException{
		return getUnmatchedProfiles(userId, matchType,1);
	}
	
	/**
	 * Gets the unmatched profiles.
	 *
	 * @param userId the user id
	 * @param matchType the match type
	 * @param iterationNumber the iteration number
	 * @return the unmatched profiles
	 * @throws DBFailureException the DB failure exception
	 */
	public List<Profile> getUnmatchedProfiles(String userId, String matchType, int iterationNumber) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		String mStringType = MatchType._TYPE_LOVE_MATCH;
		if(matchType != LOVE_MATCHES && matchType != FRIEND_MATCHES) {
			LOGGER.log(Level.WARNING, "Error- Invalid call argument: " + matchType);
			return null;
		}else if(matchType == FRIEND_MATCHES) {
			mStringType = MatchType._TYPE_FRIEND_MATCH;
		}
		
		List<Profile> batch = null;
		ApiFuture<QuerySnapshot> getTypeMatches = 
				db.collection(FireBaseSchema.MATCHES_TABLE)
					.document(userId)
					.collection(FireBaseSchema.MATCHES_TABLE_COLLECTION)
					.whereEqualTo(Match._TYPE, mStringType)
					.whereEqualTo(Match._CLIENT_MATCH_STATUS, MatchStatus._STATUS_SWIPE_RIGHT)
					.get();
		
		ApiFuture<QuerySnapshot> getBlockedMatches = 
				db.collection(FireBaseSchema.MATCHES_TABLE)
				.document(userId)
				.collection(FireBaseSchema.MATCHES_TABLE_COLLECTION)
				.whereEqualTo(Match._TYPE, MatchType._TYPE_BLOCKED)
				.get();
		
		ApiFuture<QuerySnapshot> getBatchProfiles = 
				db.collection(FireBaseSchema.PROFILES_TABLE)
					.offset(iterationNumber * MAX_NUM_PROFILES_RETRIEVED)
					.limit(MAX_NUM_PROFILES_RETRIEVED)
					.get();
		
		try {
			//create set from profiles swiped right on:
			QuerySnapshot clientMatches = getTypeMatches.get();
			Set<String> filteredIds = clientMatches
				.getDocuments().parallelStream()
				.map(m -> {
					//System.out.println(m.getId());
					return m.getId();
				})
				.collect(Collectors.toCollection(HashSet::new));
			QuerySnapshot blockedMatches = getBlockedMatches.get();
			
			//augment with blocked profiles and user's Id:
			blockedMatches.getDocuments().stream()
				.forEach(p -> filteredIds.add(p.getId()));
				
			filteredIds.add(userId);
			
			QuerySnapshot profileBatch = getBatchProfiles.get();
			if(profileBatch.isEmpty()) {
				LOGGER.log(Level.FINE, "Query for new Profiles returned empty.");
				return new ArrayList<Profile>();
			}else {
				
				//parallelize package conversion to list of profiles:
				batch = profileBatch.getDocuments().parallelStream()
					.filter(p -> !(filteredIds.contains(p.getId())))
					.map(p -> {
							Profile newProf = new Profile();
							newProf.initializeFromPackage(new DBDocumentPackage(p.getId(), p.getData()));
							return newProf;
						})
					.collect(Collectors.toList());
			}
			
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Profile batch retrieval query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Profile batch retrieval query failed.");
			throw new DBFailureException();
		}
		
		return batch;
	}
	
	/**
	 * Gets the match.
	 *
	 * @param clientProfile the client profile
	 * @param otherProfile the other profile
	 * @return the match
	 * @throws DBFailureException the DB failure exception
	 */
	public Match getMatch(Profile clientProfile, Profile otherProfile) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		//System.out.println("UID: " + clientProfile.getUserId());
		ApiFuture<DocumentSnapshot> getClientMatch = 
				db.collection(FireBaseSchema.MATCHES_TABLE)
					.document(clientProfile.getUserId())	
					.collection(FireBaseSchema.MATCHES_TABLE_COLLECTION)
					.document(otherProfile.getUserId())
					.get();
		
		DocumentSnapshot clientMatch;
		try {
			
			clientMatch = getClientMatch.get();
			if(!clientMatch.exists()) {
				return null;
			}
			
			DBDocumentPackage pkg = new DBDocumentPackage(clientMatch.getId(), clientMatch.getData());
			Match newMatch = new Match(clientProfile, otherProfile);
			newMatch.initializeFromPackage(pkg);
			
			return newMatch;
			
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Match query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Match query failed.");
			throw new DBFailureException();
		}
	}
	
	/**
	 * Adds the match.
	 *
	 * @param match the match
	 * @throws DBFailureException the DB failure exception
	 */
	public void addMatch(Match match) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		DocumentReference clientMatchRef = db.collection(FireBaseSchema.MATCHES_TABLE)
				.document(match.getClientProfile().getUserId())
				.collection(FireBaseSchema.MATCHES_TABLE_COLLECTION)
				.document(match.getOtherProfile().getUserId());
		
		DocumentReference otherMatchRef = db.collection(FireBaseSchema.MATCHES_TABLE)
				.document(match.getOtherProfile().getUserId())
				.collection(FireBaseSchema.MATCHES_TABLE_COLLECTION)
				.document(match.getClientProfile().getUserId());
		
		DBDocumentPackage clientPackage = match.toDBPackage();
		DBDocumentPackage othPackage = match.converseMatchToDBPackage();
		ApiFuture<WriteResult> clientResult = clientMatchRef.set(clientPackage.getValues());
		ApiFuture<WriteResult> otherResult = otherMatchRef.set(othPackage.getValues());
		
		try {
			clientResult.get();
			otherResult.get();
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Match addition query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Match addition query failed.");
			throw new DBFailureException();
		}
		
	}
	
	/**
	 * Update match.
	 *
	 * @param match the match
	 * @throws DBFailureException the DB failure exception
	 */
	public void updateMatch(Match match) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		DocumentReference clientMatchRef = db.collection(FireBaseSchema.MATCHES_TABLE)
											.document(match.getClientProfile().getUserId())
											.collection(FireBaseSchema.MATCHES_TABLE_COLLECTION)
											.document(match.getOtherProfile().getUserId());
		
		DocumentReference otherMatchRef = db.collection(FireBaseSchema.MATCHES_TABLE)
											.document(match.getOtherProfile().getUserId())
											.collection(FireBaseSchema.MATCHES_TABLE_COLLECTION)
											.document(match.getClientProfile().getUserId());
		
		DBDocumentPackage clientPackage = match.toDBPackage();
		DBDocumentPackage othPackage = match.converseMatchToDBPackage();
		ApiFuture<WriteResult> clientResult = clientMatchRef.update(clientPackage.getValues());
		ApiFuture<WriteResult> otherResult = otherMatchRef.update(othPackage.getValues());
		
		try {
			clientResult.get();
			otherResult.get();
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE,"Error- Match update query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e) {
			LOGGER.log(Level.SEVERE,"Error- Match update query failed.");
			throw new DBFailureException();
		}
	}
	
	/**
	 * Adds the profile image.
	 *
	 * @param pic the pic
	 * @param userId the user id
	 * @throws DBFailureException the DB failure exception
	 */
	public void addProfileImage(BufferedImage pic, String userId) throws DBFailureException {
		
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		if(pic == null) {
			LOGGER.log(Level.WARNING, "Error- Image to be added is null");
			throw new DBFailureException();
		}
		
		BufferedImage convertedPic = ImageConverter.convertToJPG(pic);
		Bucket defaultBucket = StorageClient.getInstance().bucket(DB_BUCKET_NAME);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(convertedPic, "jpg", baos);
			byte[] binData = baos.toByteArray();
			Blob writtenPic = defaultBucket.create(FireBaseSchema.toProfileImageIndex(userId), 
											binData, Bucket.BlobTargetOption.doesNotExist());
			LOGGER.log(Level.FINE, "Added a Profile Image.");
			
		}catch(Exception exc) {
			//exc.printStackTrace();
			LOGGER.log(Level.SEVERE, "Error- Image upload failed!");
			throw new DBFailureException();
		}
	}
	
	/**
	 * Update profile image.
	 *
	 * @param pic the pic
	 * @param userId the user id
	 * @throws DBFailureException the DB failure exception
	 */
	public void updateProfileImage(BufferedImage pic, String userId) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		if(pic == null) {
			LOGGER.log(Level.WARNING, "Error- Image to be updated is null");
			throw new DBFailureException();
		}
		
		Storage storage = StorageOptions.getDefaultInstance().getService();
		BufferedImage convertedPic = ImageConverter.convertToJPG(pic);
		try {
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(convertedPic, "jpg", baos);
			byte[] binData = baos.toByteArray();
			
			Bucket defaultBucket = StorageClient.getInstance().bucket(DB_BUCKET_NAME);
			Blob picBlob = defaultBucket.get(FireBaseSchema.toProfileImageIndex(userId));
			
			if(picBlob.delete()) {
				LOGGER.log(Level.FINE, "Deleted old Profile Image.");
			}else {
				LOGGER.log(Level.SEVERE, "Error- Profile image for given userID does not exist.");
				throw new DBFailureException();
			}
			
			Blob writtenPic = defaultBucket.create(FireBaseSchema.toProfileImageIndex(userId), binData, Bucket.BlobTargetOption.doesNotExist());
			LOGGER.log(Level.FINE, "Updated Profile Image to new Image.");
			
		}catch(Exception exc) {
			//exc.printStackTrace();
			LOGGER.log(Level.SEVERE, "Error- Image upload failed-- Profile picture may have been lost!");
			throw new DBFailureException();
		}
	}
	
	/**
	 * Gets the profile image.
	 *
	 * @param userId the user id
	 * @return the profile image
	 * @throws DBFailureException the DB failure exception
	 */
	public BufferedImage getProfileImage(String userId) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		try {
			Bucket defaultBucket = StorageClient.getInstance().bucket(DB_BUCKET_NAME);
			Blob imgBlob = defaultBucket.get(FireBaseSchema.toProfileImageIndex(userId));
			byte [] bytes = imgBlob.getContent(BlobSourceOption.generationMatch());
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
			return img;
		}catch(Exception exc){
			LOGGER.log(Level.SEVERE, "Error- Image retrieval failed.");
			throw new DBFailureException();
		}
	}

	/**
	 * Gets the message thread.
	 *
	 * @param userId the user id
	 * @param otherUserId the other user id
	 * @return the message thread
	 * @throws DBFailureException the DB failure exception
	 */
	public MessageThread getMessageThread(String userId, String otherUserId) throws DBFailureException{
		//TODO: Fix this
		
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		

		MessageThread msgThread = new MessageThread();
		
		String msgId = FireBaseSchema.toMessageThreadIndex(userId, otherUserId);
		ApiFuture<QuerySnapshot> fetchThread =
				db.collection(FireBaseSchema.MESSAGE_THREADS_TABLE)
						.document(msgId)
						.collection(FireBaseSchema.MESSAGE_THREADS_TABLE_COLLECTION)
						.get();
		
		QuerySnapshot threadResult;

		try {
			threadResult = fetchThread.get();
			if(!threadResult.isEmpty()) {
				LOGGER.log(Level.WARNING,"Could not find match Thread");
				return null;
			}else {
				List<Message> messageList;
				messageList = threadResult.getDocuments().parallelStream()
						.map(m -> {
							try {
								DBDocumentPackage dbpck = new DBDocumentPackage(m.getId(),m.getData());
								Message msg = new Message();
								msg.initializeFromPackage(dbpck);
								return msg;
								
							} catch(Exception e) {
								LOGGER.log(Level.INFO, "Error- a Message retrieval query failed");
								return null;
							}
						})
						.filter(p -> p !=null)
						.collect(Collectors.toList());
				msgThread.setMessages(messageList);
				
				
			}

		} catch (InterruptedException e1) {
			LOGGER.log(Level.SEVERE,"Error- Thread query interrupted.");
			throw new DBFailureException();
		} catch (ExecutionException e1) {
			LOGGER.log(Level.SEVERE,"Error- Execution Exception thrown while querying thread.");
			throw new DBFailureException();
		}

		return msgThread;
	}
	
	public boolean addMessage(String userId, String otherUserId, Message message) throws DBFailureException {
		
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		String msgId = FireBaseSchema.toMessageThreadIndex(userId, otherUserId);
		
		DBDocumentPackage pck = message.toDBPackage();
		
		db.collection(FireBaseSchema.MESSAGE_THREADS_TABLE)
			.document(msgId)
			.collection(FireBaseSchema.MESSAGE_THREADS_TABLE_COLLECTION)
			.document(message.getTimestamp().toString())
			.set(pck.getValues());
		
		
		return true;
		
	}


}
