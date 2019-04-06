package graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import firebase.DBFailureException;
import model.Account;
import model.InformationExpert;

public class GraphicsController {
	
	private static JFrame mainFrame;
	private static Account temp;
	
	GraphicsController() {
//			init default jframe as base frame
			//temp = a;
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
		GraphicsController g = new GraphicsController();
	}
	
}
