package model;

import java.util.List;

import firebase.DBFailureException;
import firebase.FireBaseAdapter;

public class InformationExpert {
	
	private static Account activeUserAccount = null;
	private static Account otherAccount = null;
	private static List<Match> potentialMatches = null;
	private static FireBaseAdapter databaseAdapter = null;
	
	public static void initializeAdapter() {
		databaseAdapter = new FireBaseAdapter();
		
		if(!databaseAdapter.initializeDBConnection()){
			//error, could not initialize database
		}
		
		//should database dump these, also other account should be null until needed
		activeUserAccount = new Account();
		try {
			activeUserAccount = getUserAccount("4WlKcGHoFUfuyxqQCB7y");
		} catch (DBFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		otherAccount = new Account();
		try {
			otherAccount = getUserAccount("86DV4wIRNJ393akBJbmA");
		} catch (DBFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public static Account getOtherAccount() {
		return otherAccount;
	}
	
	public static boolean isActiveUser(Account a) {
		if(a == activeUserAccount) {
			return true;
		}
		return false;
	}
}
