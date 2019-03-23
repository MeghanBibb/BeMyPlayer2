package firebase;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Date;
import java.text.ParseException;
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
			}
		} catch (DBFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
