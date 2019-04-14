package model;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import firebase.DBFailureException;
import firebase.FireBaseAdapter;
import firebase.FireBaseSchema;

public class InformationExpert {
	
	private static Account activeUserAccount = null;
	private static ClientModel clientModel = null;
	private static FireBaseAdapter databaseAdapter = null;
	private static Profile otherProfile = null;
	private static MatchType currentSwipePage = null;
	
	public static void initialize() {
		databaseAdapter = new FireBaseAdapter();
		
		if(!databaseAdapter.initializeDBConnection()){
			//error, could not initialize database
		}
		
		//should database dump these, also other account should be null until needed
		
		activeUserAccount = new Account();
		clientModel = new ClientModel(activeUserAccount.getAccountProfile());
		
		//import user matches (This should be made asynchronous in the future):
		loadAccountMatches();
	}
	
	public static void setCurretnSwipePage(String type) {
		if(type.equalsIgnoreCase("love")) {
			currentSwipePage = MatchType.LOVE_MATCH;
		}
		else if(type.equalsIgnoreCase("friend")) {
			currentSwipePage = MatchType.FRIEND_MATCH;
		}
	}
	
	public static String getCurrentSwipePage() {
		return currentSwipePage.getStatusString();
	}
	
	public static Profile getOtherProfile() {
		return otherProfile;
	}
	
	public static void setOtherProfile(String userID) throws DBFailureException {
		otherProfile = databaseAdapter.getProfile(userID);
	}
	
	public static boolean resetUserAccountPassword(String userEmail, int securityQ, String ansHash, String passwordHash) throws DBFailureException {
		return databaseAdapter.resetUserAccountPassword(userEmail, securityQ, ansHash, passwordHash);
	}
	
	
	public static boolean attemptAddNewAccount(Account a) throws DBFailureException {
		return databaseAdapter.attemptAddNewAccount(a);
	}
	
	public static Account getUserAccount(String userId) throws DBFailureException {
		return databaseAdapter.getUserAccountWithProfile(userId);
	}
	
	public static boolean updateUserAccount(Account a) throws DBFailureException {
		return databaseAdapter.updateUserAccount(a);
	}
	
	public static boolean updateUserProfile(Account a) throws DBFailureException {
		return databaseAdapter.updateProfile(a.getAccountProfile());
	}
	
	public static Account getActiveAccount() {
		return activeUserAccount;
	}
	
	public static void setActiveAccount(Account a) {
		System.out.println("setting active account " + a.getEmail());
		activeUserAccount = a;
	}
	
	public static boolean isActiveUser(Account a) {
		if(a == activeUserAccount) {
			return true;
		}
		return false;
	}
	
	public static boolean isActiveUser(Profile a) {
		if (a == activeUserAccount.getAccountProfile()) {
			return true;
		}
		return false;
	}
	
	public static String authenticateUserAccount(String userEmail, String passwordHash) throws DBFailureException {
		return databaseAdapter.authenticateUserAccount(userEmail, passwordHash);
	}
	
	public static Account getUserAccountNoProfile(String userId) throws DBFailureException{
		return databaseAdapter.getUserAccountNoProfile(userId);
	}
	
	public static Account getUserAccountWithProfile(String userId) throws DBFailureException{
		return databaseAdapter.getUserAccountWithProfile(userId);
	}
	
	public static String getActiveUserID() {
		return activeUserAccount.getUserId();
	}
	
	public static void updateProfileImage(BufferedImage pic, String userId) throws DBFailureException {
		
		databaseAdapter.updateProfileImage(pic, userId);
		
	}
	
	public static BufferedImage getProfileImage(String userID) throws DBFailureException {
		return databaseAdapter.getProfileImage(userID);
	}
	
	public static void addProfileImage(BufferedImage pic, String userID) throws DBFailureException {
		databaseAdapter.addProfileImage(pic, userID);
	}
	
	public static void updateAccount(Account a) throws DBFailureException {
		databaseAdapter.updateUserAccount(a);
	}
	
	public static void updateProfile(Profile p) throws DBFailureException {
		databaseAdapter.updateProfile(p);
	}

	public static ClientModel getClientModel() {
		return clientModel;
	}

	public static void setClientModel(ClientModel clientModel) {
		InformationExpert.clientModel = clientModel;
	}
	
	public static void loadAccountMatches() {
		if(activeUserAccount != null && activeUserAccount.getAccountProfile() != null) {
			Profile userProf = activeUserAccount.getAccountProfile();
			List<Profile> friendMatches;
			List<Profile> loveMatches;
			try {
				friendMatches = databaseAdapter.getFullyMatchedProfiles(userProf, FireBaseAdapter.FRIEND_MATCHES);
				loveMatches = databaseAdapter.getFullyMatchedProfiles(userProf, FireBaseAdapter.LOVE_MATCHES);
			} catch (DBFailureException e) {
				return;
			}
			clientModel.setFriendMatches(friendMatches);
			clientModel.setLoveMatches(loveMatches);
		}
	}
	
	public static boolean importFriendMatchBatch() {
		if(activeUserAccount != null && activeUserAccount.getAccountProfile() != null) {
			String uid = activeUserAccount.getUserId();
			try {
				List<Profile> importedProfs = 
				databaseAdapter.getUnmatchedProfiles(uid, FireBaseAdapter.FRIEND_MATCHES, clientModel.getFriendMatchBatch());
				if(importedProfs.isEmpty())
					return false;
				
				clientModel.importUnmatchedFriendBatch(importedProfs);
				return true;
				
			} catch (DBFailureException e) {
				return false;
			}
		}
		
		return false;
	}
	
	public static boolean importLoveMatchBatch() {
		if(activeUserAccount != null && activeUserAccount.getAccountProfile() != null) {
			String uid = activeUserAccount.getUserId();
			try {
				List<Profile> importedProfs = 
				databaseAdapter.getUnmatchedProfiles(uid, FireBaseAdapter.LOVE_MATCHES, clientModel.getFriendMatchBatch());
				if(importedProfs.isEmpty())
					return false;
				
				clientModel.importUnmatchedLoveBatch(importedProfs);
				return true;
				
			} catch (DBFailureException e) {
				return false;
			}
		}
		
		return false;

	}
	
	public static MessageThread getMessageThread(String userId, String otherUserId) throws DBFailureException {
		//TODO: impliment getMessageThread
		//return databaseAdapter.getMessageThread(userId, otherUserId);
		return null;
	}

	public static void sendIssue(String issueType, String desc){
		//TODO: implement this for issue tracking in the database
	}
	
	public static Match getMatch(Profile clientProfile, Profile otherProfile) throws DBFailureException {
		return databaseAdapter.getMatch(clientProfile, otherProfile);
	}
	
	public static void addMatch(Match match) throws DBFailureException {
		databaseAdapter.addMatch(match);
	}
	
	public static void updateMatch(Match match) throws DBFailureException {
		databaseAdapter.updateMatch(match);
	}
}
