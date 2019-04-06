package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import model.Account;
import model.InformationExpert;

public class LoginPageController extends PageController{

	//	default frame size
	//public static final int APP_WINDOW_WIDTH = 300;
	//public static final int APP_WINDOW_HEIGHT = 400;
	//	set action commands
	public static final String LOGIN="login";
	public static final String CREATE_ACCOUNT="create_account";
	public static final String FORGOT_PASSWORD = "forgot_password";
	public static final String EXIT = "exit";
	
	//	table model, db pull, table model, jtable, jframe
	private LoginPageModel loginPageModel = null;
	private JPanel loginPanel = null;
	
	//private JFrame loginFrame = null;
	//	init controller 
	
	//	launch 
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		
		LoginPageView.startLoginPage(this,mainFrame);
	}
	//	validate login (should check db)
	public static boolean validateLogin(String user,String pass) {
		boolean valid = true;
		if(user.equalsIgnoreCase("") || pass.equalsIgnoreCase("")) {
			valid = false;
		}
		try {
			String userHash = InformationExpert.authenticateUserAccount(user, pass);
			InformationExpert.setActiveAccount(InformationExpert.getUserAccountWithProfile(userHash));
			
		} catch (DBFailureException e) {
			// TODO Auto-generated catch block
			valid = false;
		}
		
		return valid;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == CREATE_ACCOUNT) {
			GraphicsController.processPage(PageCreator.CREATE_ACCOUNT_PAGE,backPage);
		}
		else if(e.getActionCommand() == LOGIN) {
		//	if(validateLogin(this.getLoginPageModel().getFrmtdtxtfldEnterUsername().getText(),
			//		this.getLoginPageModel().getPwdEnterPass().getText()) == true){
				GraphicsController.processPage(PageCreator.HOME_PAGE, backPage);
				
		//	}
			
		}
		else if(e.getActionCommand() == FORGOT_PASSWORD) {
			GraphicsController.processPage(PageCreator.FORGOT_PASSWORD_PAGE,backPage);
		} else if(e.getActionCommand() == EXIT) {
			System.exit(0);
		}
	}
	public LoginPageModel getLoginPageModel() {
		return loginPageModel;
	}
	public void setLoginPageModel(LoginPageModel loginPageModel) {
		this.loginPageModel = loginPageModel;
	}
	public JPanel getLoginPanel() {
		return loginPanel;
	}
	public void setLoginPanel(JPanel loginPanel) {
		this.loginPanel = loginPanel;
	}
	
}
