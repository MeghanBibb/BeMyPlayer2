package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditAccountPageController implements ActionListener{
	
	public static final String BACK = "back";
	public static final String CANCEL = "cancel";
	public static final String SUBMIT = "submit";
	public static final String PROFILE = "profile";
	public static final String ACCOUNT = "account";
	public static final String QUESTIONNAIRE = "questionnaire";
	public static final String UPGRADE = "upgrade";
	
	private JFrame copyFrame = null;
	private EditAccountPageModel editAccountModel = null;
	private JPanel editAccountPanel = null;
	
	public void launchEditPage(JFrame j) {
		this.copyFrame = j;
		EditAccountPageView.launchEditPage(this,j);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case BACK:	//go back to profile page
				this.editAccountPanel.removeAll();
				GraphicsController.launchProfilePage();
				break;
			case CANCEL://go back to edit page
				GraphicsController.launchEditPage();
				break;
			case SUBMIT: //save info and go back to edit page
				System.out.println("Submit");
				GraphicsController.launchEditPage();
				break;
			case PROFILE://go to edit profile page
				this.editAccountPanel.removeAll();
				GraphicsController.getMainFrame().repaint();
				EditAccountPageView.launchEditProfilePage(this, copyFrame);
				break;
			case ACCOUNT: //go to edit account page
				this.editAccountPanel.removeAll();
				GraphicsController.getMainFrame().repaint();
				EditAccountPageView.launchEditAccountPage(this, copyFrame);

				break;
			case QUESTIONNAIRE: //go to questionnaire page
				this.editAccountPanel.removeAll();
				GraphicsController.getMainFrame().repaint();
				EditAccountPageView.launchEditQuestionnairePage(this, copyFrame);
				break;
			case UPGRADE:		//go to upgrade account page
				GraphicsController.launchPaymentPage();
				break;
		}
		
	}

	public EditAccountPageModel getEditAccountModel() {
		return editAccountModel;
	}

	public void setEditAccountModel(EditAccountPageModel editAccountModel) {
		this.editAccountModel = editAccountModel;
	}

	public JPanel getEditAccountPanel() {
		return editAccountPanel;
	}

	public void setEditAccountPanel(JPanel editAccountPanel) {
		this.editAccountPanel = editAccountPanel;
	}
	
	
	
}
