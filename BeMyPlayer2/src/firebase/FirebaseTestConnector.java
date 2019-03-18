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
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.Service;

public class FirebaseTestConnector {
	
	
	//initialize app connector
	public static void main(String[] args) {
		
		try {
			
			// Note: this is a reference to a private key- I cannot put this on the repo!
			FileInputStream serviceAccount = new FileInputStream("C:/Users/colin/firebase/bemyplayer2-e65fc-firebase-adminsdk-7fn94-829084ec19.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
			    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			    .setDatabaseUrl("https://bemyplayer2-e65fc.firebaseio.com")
			    .build();

			FirebaseApp.initializeApp(options);
			
			System.out.println("Connection initialized.\nQuerying db...");
			
			Firestore db = FirestoreClient.getFirestore();
			
			// asynchronously retrieve all users
			ApiFuture<QuerySnapshot> query = db.collection("users").get();
			// ...
			// query.get() blocks on response
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			
			for (QueryDocumentSnapshot document : documents) {
			  System.out.println("User: " + document.getId());
			  System.out.println("First: " + document.getString("first"));
			  if (document.contains("middle")) {
			    System.out.println("Middle: " + document.getString("middle"));
			  }
			  System.out.println("Last: " + document.getString("last"));
			  System.out.println("Born: " + document.getLong("born"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
