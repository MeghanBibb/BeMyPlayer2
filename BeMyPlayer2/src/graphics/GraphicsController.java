package graphics;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import firebase.DBFailureException;
import model.Account;
import model.InformationExpert;
import model.ResourceManager;


// TODO: Auto-generated Javadoc
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
	
	static {
		try {
			InputStream configFile = GraphicsController.class.getClassLoader().getResourceAsStream("logger.properties");
			LogManager.getLogManager().readConfiguration(configFile);
			configFile.close();
		} catch (IOException ex) {
			System.out.println("WARNING: Could not open configuration file");
		    System.out.println("WARNING: Logging not configured (console output only)");
		}
		logger.info("running graphic controller");
	}
	
	/**
	 * Instantiates a new graphics controller.
	 */
	GraphicsController() {
		
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
			    exc.printStackTrace();
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
	
	/*    MAIN METHOD   */
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		}
		catch (Exception ex) {logger.warning("Failed to load look and feel for main");}
		
		// See the updated Account object in the model package...
		InformationExpert.initialize();
		GraphicsController g = new GraphicsController();
		
	}
	
}
