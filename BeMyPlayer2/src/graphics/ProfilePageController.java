package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProfilePageController implements ActionListener {
	
	//init command constants
	public static final String BACK = "back";
	public static final String EDIT_ACCOUNT = "edit";
	public static final String BLOCK = "block";
	public static final String MESSAGE = "message";
	
	// get view and jframe
	private ProfilePageModel profileModel = null;
	private JPanel profilePanel = null;
	private Account a;
	public void launchProfilePage(JFrame j,Account a) {
		this.a = a;
		ProfilePageView.startProfilePage(this,j);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case BACK:
				System.out.println("back");
				GraphicsController.launchHomePage();
				break;
			case EDIT_ACCOUNT:
				GraphicsController.launchEditPage();
				break;
			case BLOCK:
				System.out.println("block");
				break;
			case MESSAGE:
				System.out.println("message");
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
