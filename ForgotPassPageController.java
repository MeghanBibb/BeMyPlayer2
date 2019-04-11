package graphics;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import firebase.Hasher;
import model.Account;

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
				if(validateInfo()) {
					Account account;
					try {
						account = GraphicsController.getUserAccount(forgotPasswordModel.getFrmtdtextfldEnterUsername().getText());
						account.setPasswordHash(forgotPasswordModel.getPwdEnterPass().getText());
						GraphicsController.updateUserAccount(account);
						GraphicsController.processPage(PageCreator.LOGIN_PAGE,backPage);
					} catch (DBFailureException e1) {
						e1.printStackTrace();
					}
				}
				break;
			case BACK:
				System.out.println("Back");
				GraphicsController.processPage(PageCreator.LOGIN_PAGE,backPage);
		}
	}

	public boolean validateInfo() {
		boolean valid = true;
		Account account;
		try {
			account = GraphicsController.getUserAccount(forgotPasswordModel.getFrmtdtextfldEnterUsername().getText());
			
			//2 entered passwords do not match
			if(!(forgotPasswordModel.getPwdEnterPass().getText().contentEquals(forgotPasswordModel.getFrmtdtextfldEnterNewPassword().getText()))) {
				valid = false;
			}
			
			
			// security answer is invalid
			if(forgotPasswordModel.getSecurityQ().getSelectedIndex() == 1) {
				if(!Hasher.hashString(forgotPasswordModel.getSecurityQuestions()[0]).equals(account.getSecurityQ1AnsHash())){
					valid = false;	
				}
			}

			if(forgotPasswordModel.getSecQA().getText().contentEquals(account.getSecurityQ1AnsHash()))
			account.setPasswordHash(forgotPasswordModel.getPwdEnterPass().getText());
			
			GraphicsController.updateUserAccount(account);
			GraphicsController.processPage(PageCreator.LOGIN_PAGE,backPage);
			
		} catch (DBFailureException e1) {
			//e1.printStackTrace();
			valid = false;
		}
		
		
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
