package firebase;

import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Account;
import model.Profile;
import model.ResourceManager;

// TODO: Auto-generated Javadoc
/**
 * The Class FirebaseTestConnector.
 */
public class FirebaseTestConnector {
	

	/** The Constant LOGGER. */
	public final static Logger LOGGER = Logger.getLogger(FireBaseAdapter.class.getName());
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
			LOGGER.log(Level.WARNING, "ParseExceptio: ", e);
		}
		acc.setAccountProfile(prof);
		
		try {
			
			if(adapter.attemptAddNewAccount(acc)) {
				LOGGER.info("ADDED ACCOUNT");
			}else {
				LOGGER.info("ACCOUNT EXISTS");
				LOGGER.info("Authenticating/Fetching UID...");
				String uid = adapter.authenticateUserAccount("myOtherEmail@myDomain.org","12345");
				LOGGER.info("UID: " + uid);
				
				LOGGER.info("Fetching account...");
				Account newacc = adapter.getUserAccountNoProfile(uid);
				LOGGER.info(newacc.getSecurityQ2());
				
				LOGGER.info("Fetching full account...");
				Account fullacc = adapter.getUserAccountWithProfile(uid);
				LOGGER.info(fullacc.getSecurityQ2());
				LOGGER.info(fullacc.getAccountProfile().getDescription());
				
				fullacc.setSecurityQ1("IS THIS A NEW SECURITY QUESTION?");
				fullacc.getAccountProfile().setDescription("This is an account description modified again.");
				LOGGER.info("Updating account with changes...");
				adapter.updateUserAccount(fullacc);
				
				LOGGER.info("Updating Profile to have new description...");
				fullacc.getAccountProfile().setDescription("THIS IS A TOTALLY NEW DESCRIPTION!");
				adapter.updateProfile(fullacc.getAccountProfile());
				LOGGER.info("Done!");
				
				LOGGER.info("Uploading picture...");
				
				BufferedImage testImage = ResourceManager.loadImage("heart.png");
				adapter.addProfileImage(testImage, uid);
				
			}
			
		} catch (DBFailureException e) {
			LOGGER.log(Level.WARNING, "Databse Failure Exception: ", e);
		}
		
	}
}
