package graphics;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import model.ClientManager;
import model.ResourceManager;


/**
 * The Class GraphicsController.
 */
public class GraphicsController {

	/** The Constant ACTIVE_ACCOUNT. */
	private static final String ACTIVE_ACCOUNT = "active account";
	
	/** The Constant OTHER_ACCOUNT. */
	private static final String OTHER_ACCOUNT = "other account";
	
	/** The main frame. */
	private static JFrame mainFrame;
	
	/** The profile account. */
	private static String profileAccount;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(GraphicsController.class.getName());

	/**
	 * Instantiates a new graphics controller.
	 */
	public GraphicsController() {
		
			//init default jframe as base frame
			mainFrame = (new JFrame("BeMyPlayer2"));
			mainFrame.setSize(400, 300);
			mainFrame.setMaximumSize(new Dimension(500,400));
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.getContentPane().setLayout(null);
			mainFrame.setResizable(false);
			
			try {
			
				ImageIcon icon = new ImageIcon(ResourceManager.loadImage("app_icon.png"));
				mainFrame.setIconImage(icon.getImage());
			}
			catch (Exception exc) {
			    logger.warning("Failed to load app icon");
			}
			processPage(PageCreator.LOGIN_PAGE, null);
			
	}
	
	/**
	 * Process page.
	 *
	 * @param page the page
	 * @param backPage the back page
	 */
	public static void processPage(String page, String backPage) {
		PageController newPage = PageCreator.getPage(page);
		newPage.launchPage(mainFrame, backPage);
	}
	
	/**
	 * Sets the profile account active.
	 */
	public static void setProfileAccountActive() {
		profileAccount = ACTIVE_ACCOUNT;
	}
	
	/**
	 * Sets the profile account other.
	 */
	public static void setProfileAccountOther() {
		profileAccount = OTHER_ACCOUNT;
	}
	
	/**
	 * Gets the profile string.
	 *
	 * @return the profile string
	 */
	public static String getProfileString() {
		return profileAccount;
	}
	
	/**
	 * Gets the main frame.
	 *
	 * @return the main frame
	 */
	public static JFrame getMainFrame() {
		return mainFrame;
	}
	
	/**
	 * Sets the main frame.
	 *
	 * @param frame the new main frame
	 */
	public void setMainFrame(JFrame frame) {
		mainFrame = frame;
	}
	
}
