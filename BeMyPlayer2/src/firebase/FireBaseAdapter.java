package firebase;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
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
			System.err.println("Error- unable to locate FIREBASE_TOKEN_PATH");
			return false;
		}
		
		System.out.println("Connected to database.");
		return true;
	}
	
	public void addNewAccount(Account account) {
		if(this.db == null) {
			System.err.println("Error- no database connection.");
			return;
		}
		
		DBDocumentPackage p = account.toDBPackage();
		// asynchronously retrieve all users
		ApiFuture<QuerySnapshot> update = db.collection(FireBaseSchema.ACCOUNTS_TABLE).get();
		this.db.collection("users").add(p.getValues());
		
		try {
			QuerySnapshot updateRef = update.get();
			DocumentSnapshot newUserDoc = updateRef.getDocumentChanges().get(0).getDocument();
			account.setUserId(newUserDoc.getId());
			System.out.println("ADDED USER WITH ID: " + account.getUserId());
		} catch (InterruptedException e1) {
			System.err.println("Error- update query interrupted.");
		} catch (ExecutionException e1) {
			System.err.println("Error- Execution Exception thrown.");
		}
		
	}
	
	public boolean AuthenticateUserAccount(String user, String passwordHash) {
		
		return true;
	}
	
	public Account queryUserAccount(String userId) {
		return null;
	}
	
}
