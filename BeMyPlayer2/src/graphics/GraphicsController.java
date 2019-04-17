package graphics;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
//import firebase.DBFailureException;
import model.Account;
import model.InformationExpert;


public class GraphicsController {

	private static final String ACTIVE_ACCOUNT = "active account";
	private static final String OTHER_ACCOUNT = "other account";
	
	private static JFrame mainFrame;
	private static String profileAccount;
	private static Logger logger = Logger.getLogger(GraphicsController.class.getName());	
	static {
		/*try {
			InputStream configFile = GraphicsController.class.getClassLoader().getResourceAsStream("logger.properties");
			LogManager.getLogManager().readConfiguration(configFile);
			configFile.close();
		} catch (IOException ex) {
			System.out.println("WARNING: Could not open configuration file");
		    System.out.println("WARNING: Logging not configured (console output only)");
		}*/
		logger.info("running graphic controller");
	}
	GraphicsController() {
		
//			init default jframe as base frame
			mainFrame = (new JFrame("BeMyPlayer2"));
			mainFrame.setSize(400, 300);
			mainFrame.setMaximumSize(new Dimension(500,400));
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.getContentPane().setLayout(null);
			mainFrame.setResizable(false);
			
			processPage(PageCreator.LOGIN_PAGE, null);
			
	}
	
	public static void processPage(String page, String backPage) {
		PageController newPage = PageCreator.getPage(page);
		newPage.launchPage(mainFrame, backPage);
	}
	
	
	public static void setProfileAccountActive() {
		profileAccount = ACTIVE_ACCOUNT;
	}
	
	public static void setProfileAccountOther() {
		profileAccount = OTHER_ACCOUNT;
	}
	
	public static JFrame getMainFrame() {
		return mainFrame;
	}
	
	public void setMainFrame(JFrame frame) {
		mainFrame = frame;
	}
	
	
	/* Info Expert Calls */
	public static boolean attemptAddNewAccount(Account a)  {
		return InformationExpert.attemptAddNewAccount(a);
	}
	
	public static Account getUserAccount(String userId) {
		return InformationExpert.getUserAccount(userId);
	}
	
	public static boolean updateUserAccount(Account a)  {
		return InformationExpert.updateUserAccount(a);
	}
	
	public static boolean updateUserProfile(Account a)  {
		return InformationExpert.updateUserProfile(a);
	}
	
	public static Account getActiveAccount() {
		return InformationExpert.getActiveAccount();
	}
	
	
	public static boolean isActiveAccount(Account a) {
		return InformationExpert.isActiveUser(a);
	}
	
	public static String getActiveID() {
		return InformationExpert.getActiveUserID();
	}
	
	
	public static ImageIcon getProfileImage(String userID)  {
		return InformationExpert.getProfileImage(userID);
	}
	
	public static void uploadProfileImage(BufferedImage pic, String userID)  {
		InformationExpert.addProfileImage(pic, userID);
	}
	
	public static void updateAccount(Account a)  {
		InformationExpert.updateAccount(a);
	}
	
	public static void updateProfile(Account a)  {
		InformationExpert.updateProfile(a.getAccountProfile());
	}
	
	/*    MAIN METHOD   */
	
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
			 } 
			catch (Exception ex) {ex.printStackTrace();}
		
		// See the updated Account object in the model package...
		BackgroundMusic.playSong();
		InformationExpert.initializeAdapter();
		GraphicsController g = new GraphicsController();
		
	}
	
}
