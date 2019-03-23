package firebase;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Date;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.cloud.FirestoreClient;

import model.Account;

import com.google.cloud.Service;

public class FirebaseTestConnector {
	
	
	//initialize app connector
	public static void main(String[] args) {
		
		
		FireBaseAdapter adapter = new FireBaseAdapter();
		adapter.initializeDBConnection();
		
		System.out.println("CONNECTED");
		Account acc = new Account("myEmail@myDomain.org",
								  "12345",
								  "Q1","A1",
								  "Q2","A2");
		adapter.addNewAccount(acc);
		
		/*
		try {
			
			// Note: this is a reference to a private key- I cannot put this on the repo!
			
			FileInputStream serviceAccount = new FileInputStream("C:/Users/colin/firebase/ServiceAccountKey/bemyplayer2-e65fc-dca2d3903ee3.json");
			
			FirebaseOptions options = new FirebaseOptions.Builder()
			    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			    .setDatabaseUrl("https://bemyplayer2-e65fc.firebaseio.com")
			    .build();
			
			FirebaseApp.initializeApp(options);
			System.out.println("Connection initialized.\nQuerying db...");
			
			Firestore db = FirestoreClient.getFirestore();
			
			
			// asynchronously retrieve all users
			ApiFuture<QuerySnapshot> query = db.collection("users").get();
			
			// query.get() blocks on response
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			
			for (QueryDocumentSnapshot document : documents) {
				  System.out.println("ID: " + document.getId());
				  System.out.println("username: " + document.getString("username"));
				  
				  if (document.contains("password")) {
				    System.out.println("password: " + document.getString("password"));
				  }
			}
			
			System.out.println("Creating new user...");
			
			FirebaseAuth auth = FirebaseAuth.getInstance();
			
			
			CreateRequest newReq = new CreateRequest();
			
			newReq.setEmail("myNewEmail@someDomain.com");
			newReq.setPassword("password");
			newReq.setDisplayName("user_123");
			
			auth.createUser(newReq);
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
