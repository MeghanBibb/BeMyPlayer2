package graphics;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import firebase.DBFailureException;
import firebase.Hasher;
import model.ClientManager;
import model.PaymentInfo;
import model.ResourceManager;

/**
 * The Class EditAccountPageController.
 */
public class EditAccountPageController extends PageController{
	
	/** The Constant BACK. */
	public static final String BACK = "back";
	
	/** The Constant CANCEL. */
	public static final String CANCEL = "cancel";
	
	/** The Constant SUBMIT. */
	public static final String SUBMIT = "submit";
	
	/** The Constant SUBMITEDITPROFILE. */
	public static final String SUBMITEDITPROFILE = "subeditprof";
	
	/** The Constant SUBMITEDITACCOUNT. */
	public static final String SUBMITEDITACCOUNT = "subeditacc";
	
	/** The Constant SUBMITEDITQUESTIONAIRE. */
	public static final String SUBMITEDITQUESTIONAIRE = "subeditq";
	
	/** The Constant PROFILE. */
	public static final String PROFILE = "profile";
	
	/** The Constant ACCOUNT. */
	public static final String ACCOUNT = "account";
	
	/** The Constant QUESTIONNAIRE. */
	public static final String QUESTIONNAIRE = "questionnaire";
	
	/** The Constant UPGRADE. */
	public static final String UPGRADE = "upgrade";
	
	/** The Constant MUTE. */
	public static final String MUTE = "mute";
	
	/** The Constant DELETE. */
	public static final String DELETE = "delete";
	
	/** The Constant END_PAYMENT. */
	public static final String END_PAYMENT = "cancel payment";
	
	/** The Constant MAXLENGTH. */
	public static final int MAXLENGTH = 250;
	
	/** The copy frame. */
	private JFrame copyFrame = null;
	
	/** The edit account model. */
	private EditAccountPageModel editAccountModel = null;
	
	/** The edit account panel. */
	private JPanel editAccountPanel = null;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(EditAccountPageController.class.getName());
	
	/* (non-Javadoc)
	 * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
	 */
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		this.copyFrame = mainFrame;
		EditAccountPageView.launchEditPage(this,mainFrame);
	}

	/* (non-Javadoc)
	 * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
	 */
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
					ClientManager.getActiveAccount().getAccountProfile().setUsername(this.getEditAccountModel().getFrmtdtxtfldEnterUsername().getText());
					ClientManager.getActiveAccount().setSecurityQ1(this.getEditAccountModel().getSecurityQ().getSelectedItem().toString());
					ClientManager.getActiveAccount().getAccountProfile().setGender(this.getEditAccountModel().getGenderBox().getSelectedItem().toString());
					if(!this.getEditAccountModel().getPwdEnterPass().getText().isEmpty()) {
						ClientManager.getActiveAccount().setPasswordHash(Hasher.hashString(this.getEditAccountModel().getPwdEnterPass().getText()));
					}
					try {
						ClientManager.getActiveAccount().getAccountProfile().setDateOB(this.getEditAccountModel().getDob());
					} catch (ParseException e1) {
						logger.warning("invalid date");
					}
					try {
						ClientManager.updateAccount(ClientManager.getActiveAccount());
						ClientManager.updateProfile(ClientManager.getActiveAccount().getAccountProfile());
					} catch (DBFailureException e1) {
						logger.warning("failed to save");
					}
					if(!this.getEditAccountModel().getSecQA().getText().isEmpty()) {
						ClientManager.getActiveAccount().setSecurityQ1AnsHash(Hasher.hashString(this.getEditAccountModel().getSecQA().getText()));
					}
					
					logger.info("Submit");
					GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE, backPage);
				}
				break;
			case SUBMITEDITQUESTIONAIRE:
				if(validateCreatePage2() == true) {
					ClientManager.getActiveAccount().getAccountProfile().setPlatforms(this.getEditAccountModel().getPlatforms());
					ClientManager.getActiveAccount().getAccountProfile().setGenres(this.getEditAccountModel().getGenres());
					try {
						ClientManager.updateAccount(ClientManager.getActiveAccount());
						ClientManager.updateProfile(ClientManager.getActiveAccount().getAccountProfile());
					} catch (DBFailureException e1) {
						logger.warning("Database failed to update questionaire for " + ClientManager.getActiveUserID());
					}
									
					logger.info("Submit");
					GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE, backPage);
				}
				break;
			case SUBMITEDITPROFILE:
				if(validateCreatePage3() == true) {
					String desc = "<HTML>";
					String area = this.formatText(this.editAccountModel.getCharDescription());
					desc += area;
					desc = desc.replace("\n","<br>");
					desc += "<HTML>";
					ClientManager.getActiveAccount().getAccountProfile().setProfilePicture(this.editAccountModel.getProfileImg());
					ClientManager.getActiveAccount().getAccountProfile().setDescription(desc);
					
					try {
						ClientManager.updateAccount(ClientManager.getActiveAccount());
						ClientManager.updateProfile(ClientManager.getActiveAccount().getAccountProfile());
						if(ClientManager.getActiveAccount().getAccountProfile().getProfilePicture() == null) {
							ClientManager.updateProfileImage(ResourceManager.loadImage("defaultIcon.png"), ClientManager.getActiveUserID());
						} else {
							ClientManager.updateProfileImage(ClientManager.getActiveAccount().getAccountProfile().getProfilePicture(), ClientManager.getActiveUserID());
						}
					} catch (Exception e1) {
						logger.warning("Failed to update profile for " + ClientManager.getActiveUserID());
						InvalidPopup p =  new InvalidPopup(this.getEditAccountPanel(),"Failed to update profile");
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
			try {
				PaymentInfo p = ClientManager.getPaymentInfo(ClientManager.getActiveUserID());
				if(p != null) {
					logger.info("GOT PAYMENT INFO FROM DB\n");
				}
			} catch (DBFailureException e1) {
				logger.severe("Database failed to pull payment info for " + ClientManager.getActiveUserID());
				InvalidPopup p = new InvalidPopup(this.getEditAccountPanel(),"Error loading payment info from database");
			}
				
				GraphicsController.processPage(PageCreator.PAYMENT_PAGE,backPage);
				break;
			case END_PAYMENT:
				
				/* make popup warning*/
				int dialogButtonP = JOptionPane.YES_NO_OPTION;
				int dialogResultP= JOptionPane.showConfirmDialog(this.editAccountPanel, "Are you sure you want to cancel your payment plan?\n You will no longer benefit from being a pro account.","Unsubscribe from pro features?", dialogButtonP);
				if(dialogResultP == 0) {
					  logger.info("removing payment info for "  + ClientManager.getActiveAccount().getEmail());
					  try {
							ClientManager.deletePaymentInfo(ClientManager.getActiveUserID());
						} catch (DBFailureException e1) {
							logger.log(Level.SEVERE, "Databse Failure on end_payment: ", e1);
						}
					  this.getEditAccountModel().getBtnUpgrade().setText("Upgrade Account!");
					  this.getEditAccountModel().getBtnUpgrade().setActionCommand(UPGRADE);
				} else {
				  logger.info("Not removing payment info for " + ClientManager.getActiveAccount().getEmail());
				}
				break;
			case MUTE:
				//	remove from match queues
				int dialogButton = JOptionPane.YES_NO_OPTION;
				boolean muteStatus = ClientManager.getActiveAccount().getAccountProfile().isMute();
				String msg = muteStatus? "Want your account unmuted?" : "Want your account muted?";
				int dialogResult = JOptionPane.showConfirmDialog(this.editAccountPanel, msg,"Account Muting", dialogButton);
				
				if(dialogResult == 0) {
					
					if(muteStatus) {
						try {
							ClientManager.getActiveAccount().getAccountProfile().setMute(false);
							ClientManager.updateProfile(ClientManager.getActiveAccount().getAccountProfile());
							logger.info("Un-muted account " + ClientManager.getActiveAccount().getAccountProfile().getUsername());
							editAccountModel.getBtnMute().setText("Mute Account");
						} catch (DBFailureException e1) {
							logger.warning("Failed to update unmuted profile");
						}
					}else {
						try {
							ClientManager.getActiveAccount().getAccountProfile().setMute(true);
							ClientManager.updateProfile(ClientManager.getActiveAccount().getAccountProfile());
							logger.info("Muted account " + ClientManager.getActiveAccount().getAccountProfile().getUsername());
							editAccountModel.getBtnMute().setText("Unmute Account");
						} catch (DBFailureException e1) {
							logger.warning("Failed to update muted profile");
						}
					}
				}
				
				break;
			case DELETE:
				
				JPasswordField pf = new JPasswordField();
				int opt = JOptionPane.showConfirmDialog(null, pf, "Type your password to delete account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				//String m = JOptionPane.showInputDialog("Type your password to delete account");
				//dialogButton.int dialogResult2= JOptionPane.showConfirmDialog(this.editAccountPanel, "Are you sure you want to delete your account?","Delete account?", dialogButton2);
				String m = pf.getText();
				m = Hasher.hashString(m);
				if(m.equals(ClientManager.getActiveAccount().getPasswordHash()) && opt == JOptionPane.OK_OPTION) {
					  logger.info("attempting to delete account "  + ClientManager.getActiveUserID());
					  
					  //attempt to delete account:
					  try {
						  ClientManager.deleteUserAccount();
						  GraphicsController.processPage(PageCreator.LOGIN_PAGE,backPage);
					  }catch(DBFailureException dbexc){
						  logger.warning("Failed to delete account: " + ClientManager.getActiveUserID());
					  }
					  
					  
					  GraphicsController.processPage(PageCreator.LOGIN_PAGE,backPage);
				} else if(opt == JOptionPane.OK_OPTION){
					 InvalidPopup p = new InvalidPopup(this.editAccountPanel, "Invalid Password");
				  logger.info("Not deleting account " + ClientManager.getActiveUserID());
				} 
				break;
		}
		
	}
	
	/**
	 * Validate create page 1.
	 *
	 * @return true, if successful
	 */
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
		if(this.editAccountModel.getFrmtdtxtfldEnterUsername().getText().equals("")) {
			valid = false;
			warnings.add("Please enter a username\n");
		}
		if(this.editAccountModel.getFrmtdtxtfldEnterUsername().getText().length() > 8) {
			valid = false;
			warnings.add("Character limit 8 exceeded\n");
		}
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
		if(this.editAccountModel.getSecQA().getText().equalsIgnoreCase("") && !this.editAccountModel.getSecurityQ().getSelectedItem().equals(ClientManager.getActiveAccount().getSecurityQ1())) {
			valid = false;
			warnings.add("Please provide answer to a security question\n");
		}
		
		try {
			this.editAccountModel.getDob();
		} catch (ParseException e) {
			valid = false;
			warnings.add("invalid date: please enter dd/mm/yyyy\n");
		}
		if(valid == false) {
			InvalidPopup p  = new InvalidPopup(this.getEditAccountPanel(),warnings);
		}
		
		return valid;
	}
	
	/**
	 * Validate create page 2.
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Validate create page 3.
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Gets the edits the account model.
	 *
	 * @return the edits the account model
	 */
	public EditAccountPageModel getEditAccountModel() {
		return editAccountModel;
	}

	/**
	 * Sets the edits the account model.
	 *
	 * @param editAccountModel the new edits the account model
	 */
	public void setEditAccountModel(EditAccountPageModel editAccountModel) {
		this.editAccountModel = editAccountModel;
	}

	/**
	 * Gets the edits the account panel.
	 *
	 * @return the edits the account panel
	 */
	public JPanel getEditAccountPanel() {
		return editAccountPanel;
	}

	/**
	 * Sets the edits the account panel.
	 *
	 * @param editAccountPanel the new edits the account panel
	 */
	public void setEditAccountPanel(JPanel editAccountPanel) {
		this.editAccountPanel = editAccountPanel;
	}
	
	public String formatText(JTextArea textArea)
	{
	    StringBuilder text = new StringBuilder( textArea.getText() );
	    int lineHeight = textArea.getFontMetrics( textArea.getFont() ).getHeight();
	    Point view = new Point(textArea.getWidth(), textArea.getInsets().top);
	    int length = textArea.getDocument().getLength();
	    int endOfLine = textArea.viewToModel(view);
	    int lines = 0;

	    while (endOfLine < length)
	    {
	        int adjustedEndOfLine = endOfLine + lines;

	        if (text.charAt(adjustedEndOfLine) == ' ')
	        {
	            text.insert(adjustedEndOfLine + 1, '\n');
	            lines++;
	        }

	        view.y += lineHeight;
	        endOfLine = textArea.viewToModel(view);
	    }

	    return text.toString();
	}
	
	
	
}
