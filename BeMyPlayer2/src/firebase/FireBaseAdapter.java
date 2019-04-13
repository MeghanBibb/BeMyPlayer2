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

import model.Account;
import model.DBDocumentPackage;
import model.InformationExpert;
import model.Match;
import model.MatchStatus;
import model.MatchType;
import model.Profile;

import com.google.cloud.Service;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseOptions;

public class FireBaseAdapter {
	
	public final static Logger LOGGER = Logger.getLogger(FireBaseAdapter.class.getName());
	
	private static final String FIREBASE_TOKEN_PATH = "db/bemyplayer2-e65fc-dca2d3903ee3.json";
	private static final String DB_URL = "https://bemyplayer2-e65fc.firebaseio.com";
	private static final String DB_BUCKET_NAME = "bemyplayer2-e65fc.appspot.com";
	private static final int MAX_NUM_PROFILES_RETRIEVED = 10;
	
	public static final String LOVE_MATCHES = "LOVE_MATCHES";
	public static final String FRIEND_MATCHES = "FRIEND_MATCHES";
	
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
	
	public boolean resetUserAccountPassword(String userEmail, int securityQ, String ansHash, String passwordHash) throws DBFailureException {
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
		
		//query if user account exists:
		ApiFuture<QuerySnapshot> fetchUser = 
				db.collection(FireBaseSchema.ACCOUNTS_TABLE)
				.whereEqualTo(Account._EMAIL, userEmail)
				.whereEqualTo(securityQName, ansHash)
				.get();
		
		try {
			QuerySnapshot authUser = fetchUser.get();
			if(authUser.isEmpty()) {
				LOGGER.log(Level.WARNING, "[FIREBASE] Error- query returned duplicate users for user email: " + userEmail);
				return false;
			}else if(authUser.size() > 1) {
				LOGGER.log(Level.FINE, "Error- Query returned empty for user: " + userEmail);
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
				.filter(p -> p != null)
				.collect(Collectors.toList());
			
		}
		
		return profList;
	}
	
	public List<Profile> getUnmatchedProfiles(String userId, String matchType) throws DBFailureException{
		return getUnmatchedProfiles(userId, matchType,1);
	}
	
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
		
		ApiFuture<QuerySnapshot> getBatchProfiles = 
				db.collection(FireBaseSchema.PROFILES_TABLE)
					.offset(iterationNumber * MAX_NUM_PROFILES_RETRIEVED)
					.limit(MAX_NUM_PROFILES_RETRIEVED)
					.get();
					
		try {
			
			QuerySnapshot clientMatches = getTypeMatches.get();
			Set<String> clientMatchIds = clientMatches
				.getDocuments().stream()
				.map(m -> m.getId())
				.collect(Collectors.toCollection(HashSet::new));
					
			
			QuerySnapshot profileBatch = getBatchProfiles.get();
			if(profileBatch.isEmpty()) {
				LOGGER.log(Level.FINE, "Query for new Profiles returned empty.");
				return new ArrayList<Profile>();
			}else {
				
				//parallelize package conversion to list of profiles:
				batch = profileBatch.getDocuments().parallelStream()
					.filter(p -> !clientMatchIds.contains(p.getId()))
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
	
	public Match getMatch(Profile clientProfile, Profile otherProfile) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
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
	
	public void addProfileImage(BufferedImage pic, String userId) throws DBFailureException {
		
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		Bucket defaultBucket = StorageClient.getInstance().bucket(DB_BUCKET_NAME);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(pic, "jpg", baos);
			byte[] binData = baos.toByteArray();
			Blob writtenPic = defaultBucket.create(FireBaseSchema.toProfileImageIndex(userId), 
											binData, Bucket.BlobTargetOption.doesNotExist());
			LOGGER.log(Level.FINE, "Added a Profile Image.");
		}catch(Exception exc) {
			LOGGER.log(Level.SEVERE, "Error- Image upload failed!");
			throw new DBFailureException();
		}
	}
	
	public void updateProfileImage(BufferedImage pic, String userId) throws DBFailureException {
		if(this.db == null) {
			LOGGER.log(Level.WARNING, "Error- no database connection");
			throw new DBFailureException();
		}
		
		Storage storage = StorageOptions.getDefaultInstance().getService();
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(pic, "jpg", baos);
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
			exc.printStackTrace();
			LOGGER.log(Level.SEVERE, "Error- Image upload failed-- Profile picture may have been lost!");
			throw new DBFailureException();
		}
	}
	
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


}
