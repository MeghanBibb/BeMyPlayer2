package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ForgotPassPageController extends PageController {
	
	// set constant actions
	public static final String SUBMIT = "submit";
	public static final String BACK = "back";

	private ForgotPassPageModel forgotPasswordModel = null;
	private JPanel forgotPasswordPanel = null;

	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		ForgotPassPageView.startForgotPasswordPage(this,mainFrame);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case SUBMIT: 
				System.out.println("Submit");
				GraphicsController.processPage(PageCreator.LOGIN_PAGE,backPage);
				break;
			case BACK:
				System.out.println("Back");
				GraphicsController.processPage(PageCreator.LOGIN_PAGE,backPage);
		}
	}

	public boolean validateInfo() {
		boolean valid = true;
		
		//	CHECK FIELDS ARE NOT EMPTY OR SQL COMMANDS TO DELETE OUR TABLES
		//	VALIDATION FROM CREATE ACCOUNT PAGE + DATABASE VALIDATION
		
		return valid;
	}

	public ForgotPassPageModel getForgotPasswordPageModel() {
		return forgotPasswordModel;
	}

	public void setForgotPasswordPageModel(ForgotPassPageModel forgotPageModel) {
		this.forgotPasswordModel = forgotPageModel;
	}

	public JPanel getForgotPasswordPanel() {
		return forgotPasswordPanel;
	}

	public void setForgotPasswordPanel(JPanel forgotPanel) {
		this.forgotPasswordPanel = forgotPanel;
	}
}
