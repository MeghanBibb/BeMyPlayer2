package blacklist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public void launchCreateAccountPage(JFrame j,Account a) {
		this.a = a;
		this.pageNum = 0;
		//CreateAccountPageController controller = new CreateAccountPageController();
		CreateAccountPageView.startCreateAccountPage(this,j);
	}
	//	check command 
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == NEXT) {
			pageNum++;
			switch(pageNum) {
			case 1: 
				break;
			case 2: 
				break;
				
			}
		}
		else if (e.getActionCommand() == BACK) {
			pageNum--;
			switch(pageNum) {
			case -1:
				break;
			case 0:
				break;
			case 1: 
				break;
			}
		}
	}
	public CreateAccountPageModel getCreateAccountPageModel() {
		return createAccountPageModel;
	}
	public void setCreateAccountPageModel(CreateAccountPageModel createAccountPageModel) {
		this.createAccountPageModel = createAccountPageModel;
	}
	public JPanel getCreateAccountPanel() {
		return createAccountPanel;
	}
	public void setCreateAccountPanel(JPanel createAccountPanel) {
		this.createAccountPanel = createAccountPanel;
	}

}
