package graphics;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import firebase.Hasher;
import model.Account;
import model.ClientManager;
import model.Profile;

/**
 * The Class CreateAccountPageController.
 */
public class CreateAccountPageController extends PageController{
	
	/** The Constant MAXLENGTH. */
	//	action commands
	public static final int MAXLENGTH = 250;
	
	/** The Constant NEXT. */
	public static final String NEXT = "next";
	
	/** The Constant BACK. */
	public static final String BACK="back";
	
	/** The Constant SUBMIT. */
	public static final String SUBMIT = "submit";
	
	/** The create account page model. */
	private CreateAccountPageModel createAccountPageModel;
	
	/** The create account panel. */
	private JPanel createAccountPanel;
	
	/** The page num. */
	private int pageNum;
	
	/** The a. */
	private Account a;
	
	/** The copy frame. */
	private JFrame copyFrame;
	
	/** The visited P 1. */
	private boolean visitedP1;
	
	/** The visited P 2. */
	private boolean visitedP2;
	
	/** The visited P 3. */
	private boolean visitedP3;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(CreateAccountPageController.class.getName());
	
	/* (non-Javadoc)
	 * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
	 */
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		this.pageNum = 0;
		this.copyFrame = mainFrame;
		visitedP1 = true;
		visitedP2 = false;
		visitedP3 = false;
		CreateAccountPageView.startCreateAccountPage(this,mainFrame,false);
	}
	
	/* (non-Javadoc)
	 * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
	 */
	//	check command 
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == NEXT) {
			pageNum++;
			logger.info("page number " + pageNum);
			switch(pageNum) {
			case 1: if(validateCreatePage1()) {
						this.createAccountPanel.removeAll();
						GraphicsController.getMainFrame().repaint();
						CreateAccountPageView.startQuestionaire(this, GraphicsController.getMainFrame(),visitedP2);
					}
					else {
						pageNum--;
					}
				break;
			case 2: if(validateCreatePage2()) {
						this.createAccountPanel.removeAll();
						GraphicsController.getMainFrame().repaint();
						visitedP2= true;
						CreateAccountPageView.startProfileForm(this, GraphicsController.getMainFrame(),visitedP3);
					}
					else {
						pageNum--;
					}
				break;
			}
		}
		else if (e.getActionCommand() == BACK) {
			pageNum--;
			logger.info("page number " + pageNum);
			switch(pageNum) {
			case -1: visitedP1 = false;
					 visitedP2 = false;
					 visitedP3 = false;
					GraphicsController.processPage(PageCreator.LOGIN_PAGE,backPage);
					break;
			case 0: this.createAccountPanel.removeAll();
					visitedP2 = true;
					CreateAccountPageView.startCreateAccountPage(this,copyFrame,visitedP1);
				break;
			case 1: visitedP3 = true;
					this.createAccountPanel.removeAll();
					CreateAccountPageView.startQuestionaire(this,copyFrame,visitedP2);
				break;
			}
		}
		else if (e.getActionCommand() == SUBMIT) {
			
			if(validateCreatePage3()) {
				a = new Account();
				a.setEmail(this.getCreateAccountPageModel().getEnterEmail().getText());
				a.setPasswordHash(Hasher.hashString(this.getCreateAccountPageModel().getPwdEnterPass().getText()));
				a.setSecurityQ1(this.getCreateAccountPageModel().getSecurityQuestions());
				a.setSecurityQ1AnsHash(Hasher.hashString(this.getCreateAccountPageModel().getSecQA().getText()));
				
				//	set account fields
				//	set profile fields
				Profile p = new Profile();
				p.setUsername(this.getCreateAccountPageModel().getFrmtdtxtfldEnterUsername().getText());
				String desc = "<HTML>";
				String area = this.formatText(this.createAccountPageModel.getCharDescription());
				desc += area;
				desc = desc.replace("\n","<br>");
				desc += "<HTML>";
				
				p.setDescription(desc);
				p.setGender(this.getCreateAccountPageModel().getGender());
				try {
					p.setDateOB(this.getCreateAccountPageModel().getDob());
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					//	error
				}
				p.setPlatforms(this.getCreateAccountPageModel().getPlatforms());
				p.setGenres(this.getCreateAccountPageModel().getGenres());
				p.setProfilePicture(this.getCreateAccountPageModel().getProfileImg());
				a.setAccountProfile(p);
				boolean valid = true;
				try {
					if(ClientManager.attemptAddNewAccount(a)) {
						ClientManager.setActiveAccount(a);
						ClientManager.addProfileImage(p.getProfilePicture(), a.getUserId());
					
						ClientManager.resetClientModel();
					}else {
						throw new RuntimeException();
					}
				} catch (Exception e1) {
					valid = false;
					logger.warning("failed to add new account");
					List<String> warnings = new ArrayList<>();
					warnings.add("Failed to create new account\n");
					warnings.add("User already exists\n");
					InvalidPopup d = new InvalidPopup(this.createAccountPanel,warnings);
					GraphicsController.processPage(PageCreator.LOGIN_PAGE, backPage);
					//	must be a connection issue
				}
				if(valid == true) {
					GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
				}
				
			}
			
		}
	}
	
	/**
	 * Validate create page 1.
	 *
	 * @return true, if successful
	 */
	public boolean validateCreatePage1() {
		boolean valid = true;
		List<String> warnings = new ArrayList<>();
		//	VALIDATIONS
		try {
			if(this.createAccountPageModel.getFrmtdtxtfldEnterUsername().getText().length() > 8) {
				valid = false;
				warnings.add("Character limit 8 exceeded for username\n");
			}
			if(this.createAccountPageModel.getFrmtdtxtfldEnterUsername().getText().equalsIgnoreCase("")) {
				valid = false;
				warnings.add("Invalid username\n");
			}
			Pattern ptr = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");
			
			if(this.getCreateAccountPageModel().getEnterEmail().getText().equals("") ||
					ptr.matcher(this.getCreateAccountPageModel().getEnterEmail().getText()).matches() == false) {
				valid = false;
				warnings.add("Invalid email\n");
			}
			if(this.createAccountPageModel.getPwdEnterPass().getText().equalsIgnoreCase("")) {
				valid = false;
				warnings.add("Password cannot be empty\n");
			}
			
			if(this.createAccountPageModel.getPwdValidatePass().getText().equalsIgnoreCase("")) {
				valid = false;
				warnings.add("Password confirmation cannot be empty\n");
			}
			
			if(!this.createAccountPageModel.getPwdEnterPass().getText().equals(this.createAccountPageModel.getPwdValidatePass().getText())) {
				valid = false;
				warnings.add("Passwords must be identical\n");
			}
			if(this.createAccountPageModel.getSecQA().getText().equalsIgnoreCase("")) {
				valid = false;
				warnings.add("Please provide answer to a security question\n");
			}
			try {
				Date bday = this.createAccountPageModel.getDob();
				
			} catch (ParseException e) {
				valid = false;
				warnings.add("invalid date: please enter dd/mm/yyyy\n");
			}
			if(this.createAccountPageModel.getAge().getText().equalsIgnoreCase("")) {
				valid = false;
				warnings.add("invalid date:\n");
			}
			if(valid == false) {
				InvalidPopup p  = new InvalidPopup(this.getCreateAccountPanel(),warnings);
			}
		}catch(NullPointerException ex) {
			valid = false;
			InvalidPopup p = new InvalidPopup(this.getCreateAccountPanel(),warnings);
		}catch(NumberFormatException ex) {
			valid = false;
			InvalidPopup p = new InvalidPopup(this.getCreateAccountPanel(),warnings);
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
		List<String> warnings = new ArrayList<>();
		try{
			this.createAccountPageModel.setCheckLister(new ArrayList<>());
			for(int i = 0; i < this.createAccountPageModel.getCheckList().size(); i++) {
				this.createAccountPageModel.getCheckLister().add(this.createAccountPageModel.getCheckList().get(i).isSelected());
			}
			for(int i = 0; i < 6; i++) {
				if(this.createAccountPageModel.getCheckLister().get(i).booleanValue() == true) {
					countPlat++;
				}
			}
			for(int i = 6; i < this.getCreateAccountPageModel().getCheckList().size(); i++) {
				if(this.createAccountPageModel.getCheckLister().get(i).booleanValue() == true) {
					countGenre++;
				}
			}

			if(countPlat == 0) {
				valid = false;
				warnings.add("Please select a favorite platform\n");
			}
			if(countGenre == 0) {
				valid = false;
				warnings.add("Please select some of your favorite genres\n");
			}
			if(valid == false) {
				InvalidPopup p = new InvalidPopup(this.getCreateAccountPanel(),warnings);
			}
		}catch(NullPointerException ex) {
			valid = false;
			InvalidPopup p = new InvalidPopup(this.getCreateAccountPanel(),warnings);
			
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
		if(this.getCreateAccountPageModel().getCharDescription().getText().equals("")) {
			valid = false;
			warnings.add("Please enter a description\n");
		}
		if(this.getCreateAccountPageModel().getProfileImg() == null) {
			valid = false;
			warnings.add("Please select a profile picture");
		}
		if(this.getCreateAccountPageModel().getCharDescription().getText().length() > MAXLENGTH) {
			valid = false;
			warnings.add("Character limit exceeded\n");
		}
		if(valid == false) {
			InvalidPopup p = new InvalidPopup(this.getCreateAccountPanel(),warnings);
		}
		
		//	send to temp account and populate db
		return valid;
	}
	
	/**
	 * Gets the creates the account page model.
	 *
	 * @return the creates the account page model
	 */
	public CreateAccountPageModel getCreateAccountPageModel() {		
		return this.createAccountPageModel;
	}
	
	/**
	 * Sets the creates the account page model.
	 *
	 * @param createAccountPageModel the new creates the account page model
	 */
	public void setCreateAccountPageModel(CreateAccountPageModel createAccountPageModel) {
		this.createAccountPageModel = createAccountPageModel;
	}
	
	/**
	 * Gets the creates the account panel.
	 *
	 * @return the creates the account panel
	 */
	public JPanel getCreateAccountPanel() {
		return this.createAccountPanel;
	}
	
	/**
	 * Sets the creates the account panel.
	 *
	 * @param createAccountPanel the new creates the account panel
	 */
	public void setCreateAccountPanel(JPanel createAccountPanel) {
		this.createAccountPanel = createAccountPanel;
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
