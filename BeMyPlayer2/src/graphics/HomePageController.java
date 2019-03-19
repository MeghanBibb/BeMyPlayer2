package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HomePageController implements ActionListener{
	
	// set constant actions
	public static final String ACCOUNT = "account";
	public static final String SUPPORT = "support";
	public static final String FIND_FRIENDS = "friends";
	public static final String FIND_LOVE = "love";
	public static final String VIEW_MATCHES = "matches";
	public static final String LOGOUT = "logout";
	
	// get view and jframe
	private HomePageModel homePageModel = null;
	private JPanel homePanel = null;
	
	public void launchHomePage(JFrame j) {
		HomePageView.launchHomePage(this,j);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case ACCOUNT: 
				System.out.println("Account");
				GraphicsController.launchProfilePage();
				break;
			case SUPPORT: 
				System.out.println("Support");
				break;
			case FIND_FRIENDS: 
				System.out.println("Find Friends");
				GraphicsController.launchSwipePage();
				break;
			case FIND_LOVE: 
				System.out.println("Find Love");
				break;
			case VIEW_MATCHES: 
				System.out.println("View Matches");
				GraphicsController.launchViewMatches();
				break;
			case LOGOUT: 
				System.out.println("Logout");
				GraphicsController.launchLoginPage();
				break;
		}
	}

	public HomePageModel getHomePageModel() {
		return homePageModel;
	}

	public void setHomePageModel(HomePageModel homePageModel) {
		this.homePageModel = homePageModel;
	}

	public JPanel getHomePanel() {
		return homePanel;
	}

	public void setHomePanel(JPanel homePanel) {
		this.homePanel = homePanel;
	}

	
	
}
