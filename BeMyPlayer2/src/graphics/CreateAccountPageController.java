package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreateAccountPageController implements ActionListener{

	//	action commands 	
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
	
	public void launchCreateAccountPage(JFrame j,Account a) {
		this.a = a;
		this.pageNum = 0;
		this.copyFrame = j;
		visitedP1 = true;
		visitedP2 = false;
		visitedP3 = false;
		CreateAccountPageView.startCreateAccountPage(this,j,false);
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
			case 2: this.createAccountPanel.removeAll();
					GraphicsController.getMainFrame().repaint();
					visitedP2= true;
					CreateAccountPageView.startProfileForm(this, GraphicsController.getMainFrame(),visitedP3);
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
					GraphicsController.launchLoginPage();
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
			if(validateCreatePage2()) {
				System.out.println("submit");
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
		System.out.println("gamer tag " + this.createAccountPageModel.getFrmtdtxtfldEnterUsername().getText());
		
		System.out.println("password " + this.createAccountPageModel.getPwdEnterPass().getText());
		
		System.out.println("validated password " + this.createAccountPageModel.getPwdValidatePass().getText());

		System.out.println("gender " + this.createAccountPageModel.getGender());
	
		System.out.println("security question " + this.createAccountPageModel.getSecurityQuestions());
		System.out.println("security qA " + this.createAccountPageModel.getSecQA().getText());
		System.out.println("age " + this.createAccountPageModel.getAge().getText());
		*/
		return valid;
	}
	public boolean validateCreatePage2() {
		boolean valid = true;
		//	need to store profile pic in new location to pull from
		System.out.println("Profile pic "+ this.getCreateAccountPageModel().getImagePath());
		System.out.println("description " + this.getCreateAccountPageModel().getCharDescription().getText());
		
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
