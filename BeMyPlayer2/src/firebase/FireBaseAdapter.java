package firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseOptions;

import graphics.Account;

public class FireBaseAdapter {
	
	private Firestore db;
	
	public boolean initializeDBConnection() {
		//FirebaseOptions options = new FirebaseOptions.Builder()
		
		return false;
	}
	
	public boolean AuthenticateUserAccount(String user, String password) {
		return true;
	}
	
	public Account queryUserAccount(String userId) {
		return null;
	}
	
}
