package firebase;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.imageio.ImageIO;

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
import model.Profile;

import com.google.cloud.Service;

public class FirebaseTestConnector {
	
	
	//initialize app connector
	public static void main(String[] args) {
		
		FireBaseAdapter adapter = new FireBaseAdapter();
		adapter.initializeDBConnection();
		
		Account acc = new Account("myOtherEmail@myDomain.org",
								  "12345",
								  "Q1","A1",
								  "Q2","A2");
		Profile prof = null;
		try {
			prof = new Profile("user_name",
										Profile.DOB_FORMAT.parse("1/1/2000"),
										"male",
										"This is a profile description");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		acc.setAccountProfile(prof);
		
		try {
			
			if(adapter.attemptAddNewAccount(acc)) {
				System.out.println("ADDED ACCOUNT");
			}else {
				System.out.println("ACCOUNT EXISTS");
				System.out.println("Authenticating/Fetching UID...");
				String uid = adapter.authenticateUserAccount("myOtherEmail@myDomain.org","12345");
				System.out.println("UID: " + uid);
				
				System.out.println("Fetching account...");
				Account newacc = adapter.getUserAccountNoProfile(uid);
				System.out.println(newacc.getSecurityQ2());
				
				System.out.println("Fetching full account...");
				Account fullacc = adapter.getUserAccountWithProfile(uid);
				System.out.println(fullacc.getSecurityQ2());
				System.out.println(fullacc.getAccountProfile().getDescription());
				
				fullacc.setSecurityQ1("IS THIS A NEW SECURITY QUESTION?");
				fullacc.getAccountProfile().setDescription("This is an account description modified again.");
				System.out.println("Updating account with changes...");
				adapter.updateUserAccount(fullacc);
				
				System.out.println("Updating Profile to have new description...");
				fullacc.getAccountProfile().setDescription("THIS IS A TOTALLY NEW DESCRIPTION!");
				adapter.updateProfile(fullacc.getAccountProfile());
				System.out.println("Done!");
				
				System.out.println("Uploading picture...");
				try {
					BufferedImage testImage = ImageIO.read(new File("img/booth1.jpg"));
					adapter.addProfileImage(testImage, uid);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (DBFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
