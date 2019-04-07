package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Account;

public class ProfilePageController extends PageController {
	
	
	//init command constants
	public static final String BACK = "back";
	public static final String EDIT_ACCOUNT = "edit";
	public static final String BLOCK = "block";
	public static final String MESSAGE = "message";
	
	// get view and jframe
	private ProfilePageModel profileModel = null;
	private JPanel profilePanel = null;
	private Account a;
	
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		
		a = GraphicsController.getActiveAccount();
		ProfilePageView.startProfilePage(this,mainFrame);
	}
	
	public boolean isActiveAccount() {
		return GraphicsController.isActiveAccount(a);
	}
	
	public Account getAccount() {
		return a;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case BACK:
				System.out.println("back");
				GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
				break;
			case EDIT_ACCOUNT:
				GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE,backPage);
				break;
			case BLOCK:
				System.out.println("block");
				break;
			case MESSAGE:
				System.out.println("message");
				GraphicsController.processPage(PageCreator.MESSAGE_PAGE,backPage);
				break;
		}
		
	}

	public ProfilePageModel getProfileModel() {
		return profileModel;
	}

	public void setProfileModel(ProfilePageModel profileModel) {
		this.profileModel = profileModel;
	}

	public JPanel getProfilePanel() {
		return profilePanel;
	}

	public void setProfilePanel(JPanel profilePanel) {
		this.profilePanel = profilePanel;
	}

	
	
}
