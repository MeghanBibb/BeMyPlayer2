package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import firebase.Hasher;
import model.Account;
import model.InformationExpert;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginPageController.
 */
public class LoginPageController extends PageController{

	//	default frame size
	//public static final int APP_WINDOW_WIDTH = 300;
	//public static final int APP_WINDOW_HEIGHT = 400;
	/** The Constant LOGIN. */
	//	set action commands
	public static final String LOGIN="login";
	
	/** The Constant CREATE_ACCOUNT. */
	public static final String CREATE_ACCOUNT="create_account";
	
	/** The Constant FORGOT_PASSWORD. */
	public static final String FORGOT_PASSWORD = "forgot_password";
	
	/** The Constant EXIT. */
	public static final String EXIT = "exit";
	
	/** The login page model. */
	//	table model, db pull, table model, jtable, jframe
	private LoginPageModel loginPageModel = null;
	
	/** The login panel. */
	private BackgroundPanel loginPanel = null;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(LoginPageController.class.getName());
	//private JFrame loginFrame = null;
	//	init controller 
	
	/* (non-Javadoc)
	 * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
	 */
	//	launch 
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		
		LoginPageView.startLoginPage(this,mainFrame);
	}
	
	/**
	 * Validate login.
	 *
	 * @param user the user
	 * @param pass the pass
	 * @return true, if successful
	 */
	//	validate login (should check db)
	public boolean validateLogin(String user,String pass) {
		boolean valid = true;
		List<String> warnings = new ArrayList<>();
		
		if(user.equalsIgnoreCase("") || pass.equalsIgnoreCase("")) {
			warnings.add("Please enter username and password\n");
			valid = false;
		}else {
			try {
				String userHash = InformationExpert.authenticateUserAccount(user, Hasher.hashString(pass));

				if(userHash == null) {
					valid = false;
					warnings.add("invalid username or password\n");
				}
				else {
					InformationExpert.setActiveAccount(InformationExpert.getUserAccountWithProfile(userHash));
				}
				
			} catch (DBFailureException e) {
				// TODO Auto-generated catch block
				warnings.add("Database failed to load user\n");
				valid = false;
			}
		}
		if(valid == false) {
			InvalidPopup p = new InvalidPopup(this.getLoginPanel(),warnings);
		}
		return valid;
	}
	
	/* (non-Javadoc)
	 * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == CREATE_ACCOUNT) {
			GraphicsController.processPage(PageCreator.CREATE_ACCOUNT_PAGE,backPage);
		}
		else if(e.getActionCommand() == LOGIN) {
			if(validateLogin(this.getLoginPageModel().getFrmtdtxtfldEnterUsername().getText(),
					this.getLoginPageModel().getPwdEnterPass().getText()) == true){
				
				InformationExpert.resetClientModel();
				GraphicsController.processPage(PageCreator.HOME_PAGE, backPage);
				
			}
			
		}
		else if(e.getActionCommand() == FORGOT_PASSWORD) {
			GraphicsController.processPage(PageCreator.FORGOT_PASSWORD_PAGE,backPage);
		} else if(e.getActionCommand() == EXIT) {
			System.exit(0);
		}
	}
	
	/**
	 * Gets the login page model.
	 *
	 * @return the login page model
	 */
	public LoginPageModel getLoginPageModel() {
		return loginPageModel;
	}
	
	/**
	 * Sets the login page model.
	 *
	 * @param loginPageModel the new login page model
	 */
	public void setLoginPageModel(LoginPageModel loginPageModel) {
		this.loginPageModel = loginPageModel;
	}
	
	/**
	 * Gets the login panel.
	 *
	 * @return the login panel
	 */
	public JPanel getLoginPanel() {
		return loginPanel;
	}
	
	/**
	 * Sets the login panel.
	 *
	 * @param loginPanel the new login panel
	 */
	public void setLoginPanel(BackgroundPanel loginPanel) {
		this.loginPanel = loginPanel;
	}
	
}
