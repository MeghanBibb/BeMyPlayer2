package graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

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
	
	
	/*    MAIN METHOD   */
	
	public static void main(String[] args) {
		/*
		try {
			 System.setProperty("os.name", "Windows");
			 System.setProperty("os.version", "5.1");
			 UIManager.setLookAndFeel(
			   "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			 } 
			catch (Exception ex) {}*/
		
		// See the updated Account object in the model package...
		InformationExpert.initializeAdapter();
		GraphicsController g = new GraphicsController();
		
	}
	
}
