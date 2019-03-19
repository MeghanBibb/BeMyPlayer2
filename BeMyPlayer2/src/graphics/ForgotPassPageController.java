package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ForgotPassPageController implements ActionListener {
	
	// set constant actions
	public static final String SUBMIT = "submit";
	public static final String BACK = "back";
	
	private ForgotPassPageModel forgotPasswordModel = null;
	private JPanel forgotPasswordPanel = null;

	public void launchForgotPasswordPage(JFrame j) {
		ForgotPassPageView.startForgotPasswordPage(this,j);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case SUBMIT: 
				if(validateInfo()) {
					System.out.println("Submit");
					//	logic for updating information here
					GraphicsController.launchLoginPage();
				}
				break;
			case BACK:
				System.out.println("Back");
				GraphicsController.launchLoginPage();
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
