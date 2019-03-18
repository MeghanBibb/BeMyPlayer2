package firebase;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
