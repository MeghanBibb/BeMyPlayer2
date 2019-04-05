package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HomePageController extends PageController{
	
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
	
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		HomePageView.launchHomePage(this,mainFrame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case ACCOUNT: 
				System.out.println("Account");
				GraphicsController.processPage(PageCreator.PROFILE_PAGE,PageCreator.HOME_PAGE);
				break;
			case SUPPORT: 
				System.out.println("Support");
				GraphicsController.processPage(PageCreator.SUPPORT_PAGE,backPage);
				break;
			case FIND_FRIENDS: 
				System.out.println("Find Friends");
				GraphicsController.processPage(PageCreator.SWIPE_PAGE,backPage);
				break;
			case FIND_LOVE: 
				System.out.println("Find Love");
				GraphicsController.processPage(PageCreator.SWIPE_PAGE,backPage);
				break;
			case VIEW_MATCHES: 
				System.out.println("View Matches");
				GraphicsController.processPage(PageCreator.MATCHES_PAGE,backPage);
				break;
			case LOGOUT: 
				System.out.println("Logout");
				GraphicsController.processPage(PageCreator.LOGIN_PAGE,backPage);
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
