package model;

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
	private static List<Profile> allProfiles = null;
	private static List<Match> allMatches = null;
	private static FireBaseAdapter databaseAdapter = null;
	
	
	
	public static void initializeAdapter() {
		databaseAdapter = new FireBaseAdapter();
		
		if(!databaseAdapter.initializeDBConnection()){
			//error, could not initialize database
		}
		
		//should database dump these, also other account should be null until needed
		activeUserAccount = new Account();/*
		try {
			activeUserAccount = getUserAccount("21R6vA3D6LtA7ilWsprZ");
		} catch (DBFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			otherAccount = getUserAccount("86DV4wIRNJ393akBJbmA");
		} catch (DBFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
	
	public static String authenticateUserAccount(String userEmail, String passwordHash) throws DBFailureException {
		return databaseAdapter.authenticateUserAccount(userEmail, passwordHash);
	}
	
	public static Account getUserAccountNoProfile(String userId) throws DBFailureException{
		return databaseAdapter.getUserAccountNoProfile(userId);
	}
	
	public static Account getUserAccountWithProfile(String userId) throws DBFailureException{
		return databaseAdapter.getUserAccountWithProfile(userId);
	}
	
}
