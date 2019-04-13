package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Account;
import model.InformationExpert;
import model.Profile;

public class ProfilePageController extends PageController {
	
	
	//init command constants
	public static final String BACK = "back";
	public static final String EDIT_ACCOUNT = "edit";
	public static final String BLOCK = "block";
	public static final String MESSAGE = "message";
	private static Logger logger = Logger.getLogger(ProfilePageController.class.getName());
	// get view and jframe
	private ProfilePageModel profileModel = null;
	private JPanel profilePanel = null;
	private Profile a;
	
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		if(GraphicsController.getProfileString().equalsIgnoreCase("active account")){
			a = InformationExpert.getActiveAccount().getAccountProfile();
		}
		else if(GraphicsController.getProfileString().equalsIgnoreCase("other account")){
			System.out.println("other account");
			a = InformationExpert.getOtherProfile();
		}
		ProfilePageView.startProfilePage(this,mainFrame);
	}
	
	public boolean isActiveAccount() {
		return InformationExpert.isActiveUser(a);
	}
	
	public Profile getProfile() {
		return a;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case BACK:
				logger.info("back");
				GraphicsController.setProfileAccountActive();
				GraphicsController.processPage(backPage,backPage);
				break;
			case EDIT_ACCOUNT:
				GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE,backPage);
				break;
			case BLOCK:
				logger.info("block");
				break;
			case MESSAGE:
				logger.info("message");
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
