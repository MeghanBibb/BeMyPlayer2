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

// TODO: Auto-generated Javadoc
/**
 * The Class InformationExpert.
 */
public class InformationExpert {
	
	/** The active user account. */
	private static Account activeUserAccount = null;
	
	/** The client model. */
	private static ClientModel clientModel = null;
	
	/** The database adapter. */
	private static FireBaseAdapter databaseAdapter = null;
	
	/** The other profile. */
	private static Profile otherProfile = null;
	
	/** The current swipe page. */
	private static MatchType currentSwipePage = null;
	
	/**
	 * Initialize.
	 */
	public static void initialize() {
		databaseAdapter = new FireBaseAdapter();
		if(!databaseAdapter.initializeDBConnection()){
			//error, could not initialize database
		}
	}
	
	/**
	 * Reset client model.
	 */
	public static void resetClientModel() {
		//should database dump these, also other account should be null until needed
		clientModel = new ClientModel(activeUserAccount.getAccountProfile());
		
		//import user matches (This should be made asynchronous in the future):
		loadAccountMatches();
	}
	
	/**
	 * Adds the payment info.
	 *
	 * @param payment the payment
	 * @throws DBFailureException the DB failure exception
	 */
	public static void addPaymentInfo(PaymentInfo payment) throws DBFailureException {
		databaseAdapter.addNewPaymentInfo(payment);
	}
	
	/**
	 * Gets the payment info.
	 *
	 * @param userID the user ID
	 * @return the payment info
	 * @throws DBFailureException the DB failure exception
	 */
	public static PaymentInfo getPaymentInfo(String userID) throws DBFailureException {
		return databaseAdapter.getPaymentInfo(userID);
	}
	
	/**
	 * Delete payment info.
	 *
	 * @param userID the user ID
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
	public static boolean deletePaymentInfo(String userID) throws DBFailureException {
		return databaseAdapter.removePaymentInfo(userID);
	}
	
	/**
	 * Sets the curretn swipe page.
	 *
	 * @param type the new curretn swipe page
	 */
	public static void setCurretnSwipePage(String type) {
		if(type.equalsIgnoreCase("love")) {
			currentSwipePage = MatchType.LOVE_MATCH;
		}
		else if(type.equalsIgnoreCase("friend")) {
			currentSwipePage = MatchType.FRIEND_MATCH;
		}
	}
	
	/**
	 * Adds the issue.
	 *
	 * @param issue the issue
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
	public static boolean addIssue(Issue issue) throws DBFailureException {
		return databaseAdapter.addNewIssue(issue);
	}
	
	/**
	 * Gets the current swipe page.
	 *
	 * @return the current swipe page
	 */
	public static String getCurrentSwipePage() {
		return currentSwipePage.getStatusString();
	}
	
	/**
	 * Gets the other profile.
	 *
	 * @return the other profile
	 */
	public static Profile getOtherProfile() {
		return otherProfile;
	}
	
	/**
	 * Sets the other profile.
	 *
	 * @param userID the new other profile
	 * @throws DBFailureException the DB failure exception
	 */
	public static void setOtherProfile(String userID) throws DBFailureException {
		otherProfile = databaseAdapter.getProfile(userID);
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
	public static boolean resetUserAccountPassword(String userEmail, int securityQ, String ansHash, String passwordHash, String username) throws DBFailureException {
		return databaseAdapter.resetUserAccountPassword(userEmail, securityQ, ansHash, passwordHash, username);
	}
	
	
	/**
	 * Attempt add new account.
	 *
	 * @param a the a
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
	public static boolean attemptAddNewAccount(Account a) throws DBFailureException {
		return databaseAdapter.attemptAddNewAccount(a);
	}
	
	/**
	 * Gets the user account.
	 *
	 * @param userId the user id
	 * @return the user account
	 * @throws DBFailureException the DB failure exception
	 */
	public static Account getUserAccount(String userId) throws DBFailureException {
		return databaseAdapter.getUserAccountWithProfile(userId);
	}
	
	/**
	 * Update user account.
	 *
	 * @param a the a
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
	public static boolean updateUserAccount(Account a) throws DBFailureException {
		return databaseAdapter.updateUserAccount(a);
	}
	
	/**
	 * Update user profile.
	 *
	 * @param a the a
	 * @return true, if successful
	 * @throws DBFailureException the DB failure exception
	 */
	public static boolean updateUserProfile(Account a) throws DBFailureException {
		return databaseAdapter.updateProfile(a.getAccountProfile());
	}
	
	/**
	 * Gets the active account.
	 *
	 * @return the active account
	 */
	public static Account getActiveAccount() {
		return activeUserAccount;
	}
	
	/**
	 * Sets the active account.
	 *
	 * @param a the new active account
	 */
	public static void setActiveAccount(Account a) {
		System.out.println("setting active account " + a.getEmail());
		activeUserAccount = a;
	}
	
	/**
	 * Checks if is active user.
	 *
	 * @param a the a
	 * @return true, if is active user
	 */
	public static boolean isActiveUser(Account a) {
		if(a == activeUserAccount) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if is active user.
	 *
	 * @param a the a
	 * @return true, if is active user
	 */
	public static boolean isActiveUser(Profile a) {
		if (a == activeUserAccount.getAccountProfile()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Authenticate user account.
	 *
	 * @param userEmail the user email
	 * @param passwordHash the password hash
	 * @return the string
	 * @throws DBFailureException the DB failure exception
	 */
	public static String authenticateUserAccount(String userEmail, String passwordHash) throws DBFailureException {
		return databaseAdapter.authenticateUserAccount(userEmail, passwordHash);
	}
	
	/**
	 * Gets the user account no profile.
	 *
	 * @param userId the user id
	 * @return the user account no profile
	 * @throws DBFailureException the DB failure exception
	 */
	public static Account getUserAccountNoProfile(String userId) throws DBFailureException{
		return databaseAdapter.getUserAccountNoProfile(userId);
	}
	
	/**
	 * Gets the user account with profile.
	 *
	 * @param userId the user id
	 * @return the user account with profile
	 * @throws DBFailureException the DB failure exception
	 */
	public static Account getUserAccountWithProfile(String userId) throws DBFailureException{
		return databaseAdapter.getUserAccountWithProfile(userId);
	}
	
	/**
	 * Gets the active user ID.
	 *
	 * @return the active user ID
	 */
	public static String getActiveUserID() {
		return activeUserAccount.getUserId();
	}
	
	/**
	 * Update profile image.
	 *
	 * @param pic the pic
	 * @param userId the user id
	 * @throws DBFailureException the DB failure exception
	 */
	public static void updateProfileImage(BufferedImage pic, String userId) throws DBFailureException {
		
		databaseAdapter.updateProfileImage(pic, userId);
		
	}
	
	/**
	 * Gets the profile image.
	 *
	 * @param userID the user ID
	 * @return the profile image
	 * @throws DBFailureException the DB failure exception
	 */
	public static BufferedImage getProfileImage(String userID) throws DBFailureException {
		return databaseAdapter.getProfileImage(userID);
	}
	
	/**
	 * Adds the profile image.
	 *
	 * @param pic the pic
	 * @param userID the user ID
	 * @throws DBFailureException the DB failure exception
	 */
	public static void addProfileImage(BufferedImage pic, String userID) throws DBFailureException {
		databaseAdapter.addProfileImage(pic, userID);
	}
	
	/**
	 * Update account.
	 *
	 * @param a the a
	 * @throws DBFailureException the DB failure exception
	 */
	public static void updateAccount(Account a) throws DBFailureException {
		databaseAdapter.updateUserAccount(a);
	}
	
	/**
	 * Update profile.
	 *
	 * @param p the p
	 * @throws DBFailureException the DB failure exception
	 */
	public static void updateProfile(Profile p) throws DBFailureException {
		databaseAdapter.updateProfile(p);
	}

	/**
	 * Gets the client model.
	 *
	 * @return the client model
	 */
	public static ClientModel getClientModel() {
		return clientModel;
	}

	/**
	 * Sets the client model.
	 *
	 * @param clientModel the new client model
	 */
	public static void setClientModel(ClientModel clientModel) {
		InformationExpert.clientModel = clientModel;
	}
	
	/**
	 * Load account matches.
	 */
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
	
	/**
	 * Import friend match batch.
	 *
	 * @return true, if successful
	 */
	public static boolean importFriendMatchBatch() {
		if(activeUserAccount != null && activeUserAccount.getAccountProfile() != null) {
			String uid = activeUserAccount.getUserId();
			try {
				List<Profile> importedProfs = 
				databaseAdapter.getUnmatchedProfiles(uid, FireBaseAdapter.FRIEND_MATCHES, clientModel.getFriendMatchBatch());
				
				if(importedProfs.isEmpty())
					return false;

				//add match batch from database:

				clientModel.importUnmatchedFriendBatch(importedProfs);
				
				//add any partial matches as well that may exist:
				/*
				List<Profile> partialProfs =
				databaseAdapter.getOtherMatchedProfiles(activeUserAccount.getAccountProfile(), FireBaseAdapter.FRIEND_MATCHES);
				clientModel.importPartialFriendBatch(partialProfs);
				*/
				
				return true;
				
			} catch (DBFailureException e) {
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * Import love match batch.
	 *
	 * @return true, if successful
	 */
	public static boolean importLoveMatchBatch() {
		if(activeUserAccount != null && activeUserAccount.getAccountProfile() != null) {
			String uid = activeUserAccount.getUserId();
			
			try {
				List<Profile> importedProfs = 
				databaseAdapter.getUnmatchedProfiles(uid, FireBaseAdapter.LOVE_MATCHES, clientModel.getLoveMatchBatch());
				if(importedProfs.isEmpty())
					return false;
				
				clientModel.importUnmatchedLoveBatch(importedProfs);
				
				/*
				List<Profile> partialProfs =
				databaseAdapter.getOtherMatchedProfiles(activeUserAccount.getAccountProfile(), FireBaseAdapter.LOVE_MATCHES);
				clientModel.importPartialLoveBatch(partialProfs);
				*/
				return true;
				
			} catch (DBFailureException e) {
				return false;
			}
		}
		
		return false;

	}
	
	/**
	 * Gets the message thread.
	 *
	 * @param userId the user id
	 * @param otherUserId the other user id
	 * @return the message thread
	 * @throws DBFailureException the DB failure exception
	 */
	public static MessageThread getMessageThread(String userId, String otherUserId) throws DBFailureException {
		return databaseAdapter.getMessageThread(userId, otherUserId);
	}

	/**
	 * Send issue.
	 *
	 * @param issueType the issue type
	 * @param desc the desc
	 */
	public static void sendIssue(String issueType, String desc){
		//TODO: implement this for issue tracking in the database
	}
	
	/**
	 * Gets the match.
	 *
	 * @param clientProfile the client profile
	 * @param otherProfile the other profile
	 * @return the match
	 * @throws DBFailureException the DB failure exception
	 */
	public static Match getMatch(Profile clientProfile, Profile otherProfile) throws DBFailureException {
		return databaseAdapter.getMatch(clientProfile, otherProfile);
	}
	
	/**
	 * Adds the match.
	 * 
	 * @param match the match
	 * @throws DBFailureException the DB failure exception
	 */
	public static void addMatch(Match match) throws DBFailureException {
		databaseAdapter.addMatch(match);
	}
	
	public static void deleteUserAccount() throws DBFailureException {
		databaseAdapter.deleteAccount(getActiveUserID());
	}
	
	/**
	 * Update match.
	 *
	 * @param match the match
	 * @throws DBFailureException the DB failure exception
	 */
	public static void updateMatch(Match match) throws DBFailureException {
		databaseAdapter.updateMatch(match);
	}
	
	public static boolean addMessage(String userId, String otherUserId, Message message) throws DBFailureException {
		return databaseAdapter.addMessage(userId, otherUserId, message);
	}
}
