package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import firebase.FireBaseAdapter;
import firebase.Hasher;
import model.Account;
import model.InformationExpert;
import model.Profile;

public class CreateAccountPageController extends PageController{

	//	action commands
	public static final int MAXLENGTH = 250;
	public static final String NEXT = "next";
	public static final String BACK="back";
	public static final String SUBMIT = "submit";
	
	private CreateAccountPageModel createAccountPageModel;
	private JPanel createAccountPanel;
	private int pageNum;
	private Account a;
	private JFrame copyFrame;
	private boolean visitedP1;
	private boolean visitedP2;
	private boolean visitedP3;
	
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
	//	check command 
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == NEXT) {
			pageNum++;
			System.out.println("page number " + pageNum);
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
			System.out.println("page number " + pageNum);
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
				//	CATCH FILE DUPLICATE
				/*
				File temp = new File(this.createAccountPageModel.getImagePath());
				
				File t2 = new File("bin\\dskfjlslkdjf");
				
				try {
					Files.copy(temp.toPath(), new File(t2.getParent()+ "\\..\\img\\"+temp.getName() ).toPath());
					this.createAccountPageModel.setImagePath(t2.getParent()+ "\\..\\img\\"+temp.getName() );
				} catch (FileAlreadyExistsException dup) {
					try {
						Files.copy(temp.toPath(), new File(t2.getParent()+ "\\..\\img\\"+"cpy"+temp.getName() ).toPath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					this.createAccountPageModel.setImagePath(t2.getParent()+ "\\..\\img\\"+temp.getName() );
				}
				catch(IOException e1) {
					
				}
				*/
				a = new Account();
				a.setEmail(this.getCreateAccountPageModel().getEnterEmail().getText());
				a.setPasswordHash(this.getCreateAccountPageModel().getPwdEnterPass().getText());
				a.setSecurityQ1(this.getCreateAccountPageModel().getSecurityQuestions());
				a.setSecurityQ1AnsHash(Hasher.hashString(this.getCreateAccountPageModel().getSecQA().getText()));
				
				//	set account fields
				//	set profile fields
				Profile p = new Profile();
				p.setUsername(this.getCreateAccountPageModel().getFrmtdtxtfldEnterUsername().getText());
				String htmlDescription = "<HTML>";
				htmlDescription += this.getCreateAccountPageModel().getCharDescription().getText();
				
				htmlDescription = htmlDescription.replace("\n", "<br>");
				htmlDescription += "</HTML>";
				
				p.setDescription(htmlDescription);
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
				
				
				try {
					GraphicsController.attemptAddNewAccount(a);
					InformationExpert.setActiveAccount(a);
					GraphicsController.uploadProfileImage(p.getProfilePicture(), GraphicsController.getActiveID());
				} catch (DBFailureException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					//	must be a connection issue
				}
				GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
			}
			
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
		if(this.createAccountPageModel.getFrmtdtxtfldEnterUsername().getText().equalsIgnoreCase("")) {
			valid = false;
			warnings.add("Invalid username\n");
		}
		Pattern ptr = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");
		String[] emails = { "\"Fred Bloggs\"@example.com", "user@.invalid.com", "Chuck Norris <gmail@chucknorris.com>", "webmaster@müller.de", "matteo@78.47.122.114" };
		for (String email : emails) {
		    System.out.println(email + " is " + (ptr.matcher(email).matches() ? "valid" : "invalid"));
		}
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
			this.createAccountPageModel.getDob();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
		
		return valid;
	}
	public boolean validateCreatePage2() {
		boolean valid = true;
		
		int countPlat = 0;
		int countGenre = 0;
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
			InvalidPopup p = new InvalidPopup(this.getCreateAccountPanel(),warnings);
		}
		
		return valid;
	}
	public boolean validateCreatePage3() {
		boolean valid = true;
		//	need to store profile pic in new location to pull from
		List<String >warnings = new ArrayList<>();
		
		if(this.getCreateAccountPageModel().getCharDescription().getText().equals("")) {
			valid = false;
			warnings.add("Please enter a description\n");
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
	public CreateAccountPageModel getCreateAccountPageModel() {		
		return this.createAccountPageModel;
	}
	public void setCreateAccountPageModel(CreateAccountPageModel createAccountPageModel) {
		this.createAccountPageModel = createAccountPageModel;
	}
	public JPanel getCreateAccountPanel() {
		return this.createAccountPanel;
	}
	public void setCreateAccountPanel(JPanel createAccountPanel) {
		this.createAccountPanel = createAccountPanel;
	}

}
