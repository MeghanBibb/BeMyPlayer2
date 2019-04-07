package graphics;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import firebase.DBFailureException;
import model.Account;
import model.InformationExpert;

public class GraphicsController {

	private static final String ACTIVE_ACCOUNT = "active account";
	private static final String OTHER_ACCOUNT = "other account";
	
	private static JFrame mainFrame;
	private static String profileAccount;
	
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
	

	public static Account getProfileAccount() {
		if(profileAccount == ACTIVE_ACCOUNT) {
			return getActiveAccount();
		} else {
			return getOtherAccount();
		}
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
	public static boolean attemptAddNewAccount(Account a) throws DBFailureException {
		return InformationExpert.attemptAddNewAccount(a);
	}
	
	public static Account getUserAccount(String userId) throws DBFailureException {
		return InformationExpert.getUserAccount(userId);
	}
	
	public static boolean updateUserAccount(Account a) throws DBFailureException {
		return InformationExpert.updateUserAccount(a);
	}
	
	public static boolean updateUserProfile(Account a) throws DBFailureException {
		return InformationExpert.updateUserProfile(a);
	}
	
	public static Account getActiveAccount() {
		return InformationExpert.getActiveAccount();
	}
	
	public static Account getOtherAccount() {
		return InformationExpert.getOtherAccount();
	}
	
	public static boolean isActiveAccount(Account a) {
		return InformationExpert.isActiveUser(a);
	}
	
	public static String getActiveID() {
		return InformationExpert.getActiveUserID();
	}
	
	public static String getOtherID() {
		return InformationExpert.getOtherUserID();
	}
	
	public static BufferedImage getProfileImage(String userID) throws DBFailureException {
		return InformationExpert.getProfileImage(userID);
	}
	
	public static void uploadProfileImage(BufferedImage pic, String userID) throws DBFailureException {
		InformationExpert.addProfileImage(pic, userID);
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
		InformationExpert.initializeAdapter();
		GraphicsController g = new GraphicsController();
		
	}
	
}
