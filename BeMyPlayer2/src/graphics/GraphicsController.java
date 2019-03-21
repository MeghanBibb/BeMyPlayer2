package graphics;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GraphicsController {
	private static JFrame mainFrame;
	private static Account temp;
	GraphicsController(Account a) {
//			init default jframe as base frame
			temp = a;
			this.mainFrame = (new JFrame("BeMyPlayer2"));
			this.mainFrame.setSize(400, 300);
			this.mainFrame.setMaximumSize(new Dimension(500,400));
			this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.mainFrame.getContentPane().setLayout(null);
			this.mainFrame.setResizable(false);
			
			this.launchLoginPage();
	}
	public static void launchLoginPage() {
		LoginPageController controller = new LoginPageController();
		controller.launchLoginPage(mainFrame);
	}
	
	public static void launchForgotPasswordPage() {
		ForgotPassPageController forgotController = new ForgotPassPageController();
		forgotController.launchForgotPasswordPage(mainFrame);
	}
	
	public static void launchCreateAccountPage() {
		CreateAccountPageController createPageController = new CreateAccountPageController();
		createPageController.launchCreateAccountPage(mainFrame,temp);
	}
	public static void launchHomePage() {
		HomePageController homeController = new HomePageController();
		homeController.launchHomePage(mainFrame);
	}
	public static void launchProfilePage() {
		ProfilePageController profileController = new ProfilePageController();
		profileController.launchProfilePage(mainFrame);
	}
	public static void launchEditPage() {
		EditAccountPageController editController = new EditAccountPageController();
		editController.launchEditPage(mainFrame);
	}
	public static void launchPaymentPage() {
		PaymentPageController paymentController = new PaymentPageController();
		paymentController.launchPaymentPage(mainFrame);
	}
	public static void launchViewMatches() {
		ViewMatchesController viewMatchController = new ViewMatchesController();
		viewMatchController.launchViewMatches(mainFrame, temp);
	}
	public static void launchSwipePage() {
		SwipePageView.launchSwipePage(mainFrame);
	}
	public static void launchSupportPage() {
		SupportController supportController = new SupportController();
		supportController.launchSupportPage(mainFrame);
	}
	public static void main(String[] args) {
		
		try {
			 System.setProperty("os.name", "Windows");
			 System.setProperty("os.version", "5.1");
			 UIManager.setLookAndFeel(
			   "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			 } 
			catch (Exception ex) {}
		
		Account a = new Account();
		GraphicsController g = new GraphicsController(a);
	}
	public static JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	
}
