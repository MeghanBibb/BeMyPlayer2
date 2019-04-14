package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import firebase.Hasher;
import model.Account;
import model.InformationExpert;
import model.Profile;
import model.ResourceManager;

public class EditAccountPageController extends PageController{
	
	public static final String BACK = "back";
	public static final String CANCEL = "cancel";
	public static final String SUBMIT = "submit";
	public static final String SUBMITEDITPROFILE = "subeditprof";
	public static final String SUBMITEDITACCOUNT = "subeditacc";
	public static final String SUBMITEDITQUESTIONAIRE = "subeditq";
	public static final String PROFILE = "profile";
	public static final String ACCOUNT = "account";
	public static final String QUESTIONNAIRE = "questionnaire";
	public static final String UPGRADE = "upgrade";
	public static final int MAXLENGTH = 250;
	private JFrame copyFrame = null;
	private EditAccountPageModel editAccountModel = null;
	private JPanel editAccountPanel = null;
	private static Logger logger = Logger.getLogger(EditAccountPageController.class.getName());
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		this.copyFrame = mainFrame;
		EditAccountPageView.launchEditPage(this,mainFrame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case BACK:	//go back to profile page
				this.editAccountPanel.removeAll();
				GraphicsController.processPage(PageCreator.PROFILE_PAGE, backPage);
				break;
			case CANCEL://go back to edit page
				GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE, backPage);
				break;
			case SUBMIT: //save info and go back to edit page
				logger.info("Submit");
				GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE, backPage);
				break;
			case SUBMITEDITACCOUNT:
				if(validateCreatePage1() == true) {
					InformationExpert.getActiveAccount().getAccountProfile().setUsername(this.getEditAccountModel().getFrmtdtxtfldEnterUsername().getText());
					InformationExpert.getActiveAccount().setSecurityQ1(this.getEditAccountModel().getSecurityQ().getSelectedItem().toString());
					InformationExpert.getActiveAccount().setSecurityQ1AnsHash(Hasher.hashString(this.getEditAccountModel().getSecQA().getText()));
					InformationExpert.getActiveAccount().getAccountProfile().setGender(this.getEditAccountModel().getGenderBox().getSelectedItem().toString());
					if(!this.getEditAccountModel().getPwdEnterPass().getText().isEmpty()) {
						InformationExpert.getActiveAccount().setPasswordHash(Hasher.hashString(this.getEditAccountModel().getPwdEnterPass().getText()));
					}
					try {
						InformationExpert.getActiveAccount().getAccountProfile().setDateOB(this.getEditAccountModel().getDob());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						logger.warning("invalid date");
					}
					try {
						InformationExpert.updateAccount(InformationExpert.getActiveAccount());
						InformationExpert.updateProfile(InformationExpert.getActiveAccount().getAccountProfile());
					} catch (DBFailureException e1) {
						// TODO Auto-generated catch block
						logger.warning("failed to save");
					}
					
					logger.info("Submit");
					GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE, backPage);
				}
				break;
			case SUBMITEDITQUESTIONAIRE:
				if(validateCreatePage2() == true) {
					InformationExpert.getActiveAccount().getAccountProfile().setPlatforms(this.getEditAccountModel().getPlatforms());
					InformationExpert.getActiveAccount().getAccountProfile().setGenres(this.getEditAccountModel().getGenres());
					try {
						InformationExpert.updateAccount(InformationExpert.getActiveAccount());
						InformationExpert.updateProfile(InformationExpert.getActiveAccount().getAccountProfile());
					} catch (DBFailureException e1) {
						// TODO Auto-generated catch block
						logger.warning("Database failed to update questionaire for " + InformationExpert.getActiveUserID());
					}
									
					logger.info("Submit");
					GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE, backPage);
				}
				break;
			case SUBMITEDITPROFILE:
				if(validateCreatePage3() == true) {
					String desc = "<HTML>";
					desc += this.editAccountModel.getCharDescription().getText();
					desc = desc.replace("\n","<br>");
					desc += "<HTML>";
					InformationExpert.getActiveAccount().getAccountProfile().setProfilePicture(this.editAccountModel.getProfileImg());
					InformationExpert.getActiveAccount().getAccountProfile().setDescription(desc);
					
					try {
						InformationExpert.updateAccount(InformationExpert.getActiveAccount());
						InformationExpert.updateProfile(InformationExpert.getActiveAccount().getAccountProfile());
						if(InformationExpert.getActiveAccount().getAccountProfile().getProfilePicture() == null) {
							InformationExpert.updateProfileImage(ResourceManager.loadImage("defaultIcon.png"), InformationExpert.getActiveUserID());
						} else {
							InformationExpert.updateProfileImage(InformationExpert.getActiveAccount().getAccountProfile().getProfilePicture(), InformationExpert.getActiveUserID());
						}
					} catch (DBFailureException e1) {
						logger.warning("Failed to update profile for " + InformationExpert.getActiveUserID());
					}
					logger.info("Submit");
					GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE, backPage);
				}
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
				GraphicsController.processPage(PageCreator.PAYMENT_PAGE,backPage);
				break;
		}
		
	}
	public boolean validateCreatePage1() {
		boolean valid = true;
		
		//	gamer tag, password, revalidate password, validate security question, validate answer, validate gender and dob
		//	validation needed
		/*
		 * no sql commands
		 * no empty 
		 * limit size
		 * 
		 * age > 0  && less than 100
		 * 
		 *//*
		 */
		//	VALIDATIONS
		List<String> warnings = new ArrayList<>();
		
		if(this.editAccountModel.getPwdEnterPass().getText().equalsIgnoreCase("")) {
			if(!this.editAccountModel.getPwdValidatePass().getText().equalsIgnoreCase("")) {
				valid = false;
				warnings.add("Empty password field but non-empty validation field\n");
			}
		} else {
			if(this.editAccountModel.getPwdValidatePass().getText().equals("")) {
				valid = false;
				warnings.add("Need to validate password\n");
			} else if(!this.editAccountModel.getPwdValidatePass().getText().equals(this.editAccountModel.getPwdEnterPass().getText())) {
				valid = false;
				warnings.add("Passwords do not match\n");
			}
		}
		if(this.editAccountModel.getSecQA().getText().equalsIgnoreCase("")) {
			valid = false;
			warnings.add("Please provide answer to a security question\n");
		}
		try {
			this.editAccountModel.getDob();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			valid = false;
			warnings.add("invalid date: please enter dd/mm/yyyy\n");
		}
		if(valid == false) {
			InvalidPopup p  = new InvalidPopup(this.getEditAccountPanel(),warnings);
		}
		
		return valid;
	}
	public boolean validateCreatePage2() {
		boolean valid = true;
		
		int countPlat = 0;
		int countGenre = 0;
		this.editAccountModel.setCheckLister(new ArrayList<>());
		for(int i = 0; i < this.editAccountModel.getCheckList().size(); i++) {
			this.editAccountModel.getCheckLister().add(this.editAccountModel.getCheckList().get(i).isSelected());
		}
		for(int i = 0; i < 6; i++) {
			if(this.editAccountModel.getCheckLister().get(i).booleanValue() == true) {
				countPlat++;
			}
		}
		for(int i = 6; i < this.editAccountModel.getCheckList().size(); i++) {
			if(this.editAccountModel.getCheckLister().get(i).booleanValue() == true) {
				countGenre++;
			}
		}
		List<String> warnings = new ArrayList<>();
		if(countPlat == 0) {
			valid = false;
			warnings.add("Please select a favorite platform\n");
		}
		if(countGenre == 0) {
			valid = false;
			warnings.add("Please select some of your favorite genres\n");
		}
		if(valid == false) {
			InvalidPopup p = new InvalidPopup(this.getEditAccountPanel(),warnings);
		}
		
		return valid;
	}
	public boolean validateCreatePage3() {
		boolean valid = true;
		//	need to store profile pic in new location to pull from
		List<String >warnings = new ArrayList<>();
		
		if(this.editAccountModel.getCharDescription().getText().equals("")) {
			valid = false;
			warnings.add("Please enter a description\n");
		}
		if(this.editAccountModel.getCharDescription().getText().length() > MAXLENGTH) {
			valid = false;
			warnings.add("Character limit exceeded\n");
		}
		if(valid == false) {
			InvalidPopup p = new InvalidPopup(this.getEditAccountPanel(),warnings);
		}
		
		//	send to temp account and populate db
		return valid;
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
