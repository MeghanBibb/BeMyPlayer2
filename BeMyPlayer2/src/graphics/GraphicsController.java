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


public class GraphicsController {

	private static final String ACTIVE_ACCOUNT = "active account";
	private static final String OTHER_ACCOUNT = "other account";
	
	private static JFrame mainFrame;
	private static String profileAccount;
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

	public static void main(String[] args) {
		Account a = new Account();
		GraphicsController g = new GraphicsController();
	}
	
	public static JFrame getMainFrame() {
		return mainFrame;
	}
	
	public void setMainFrame(JFrame frame) {
		mainFrame = frame;
	}
	
}
