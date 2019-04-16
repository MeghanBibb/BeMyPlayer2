package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.InformationExpert;

public class HomePageController extends PageController{
	
	// set constant actions
	public static final String ACCOUNT = "account";
	public static final String SUPPORT = "support";
	public static final String FIND_FRIENDS = "friends";
	public static final String FIND_LOVE = "love";
	public static final String VIEW_MATCHES = "matches";
	public static final String LOGOUT = "logout";
	public static final String LAUNCH_WEB_PAGE = "launchsite";
	// get view and jframe
	private HomePageModel homePageModel = null;
	private JPanel homePanel = null;
	private static Logger logger = Logger.getLogger(HomePageController.class.getName());
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
				logger.info("Account");
				GraphicsController.setProfileAccountActive();
				GraphicsController.processPage(PageCreator.PROFILE_PAGE,PageCreator.HOME_PAGE);
				break;
			case SUPPORT: 
				logger.info("Support");
				GraphicsController.processPage(PageCreator.SUPPORT_PAGE,backPage);
				break;
			case FIND_FRIENDS: 
				logger.info("Find Friends");
				InformationExpert.setCurretnSwipePage("friend");
				GraphicsController.processPage(PageCreator.SWIPE_PAGE,backPage);
				break;
			case FIND_LOVE: 
				logger.info("Find Love");
				InformationExpert.setCurretnSwipePage("love");
				GraphicsController.processPage(PageCreator.SWIPE_PAGE,backPage);
				break;
			case VIEW_MATCHES: 
				logger.info("View Matches");
				GraphicsController.processPage(PageCreator.MATCHES_PAGE,backPage);
				break;
			case LOGOUT: 
				logger.info("Logout of user " + InformationExpert.getActiveUserID());
				GraphicsController.processPage(PageCreator.LOGIN_PAGE,backPage);
				break;
			case LAUNCH_WEB_PAGE:
				logger.info("Launching webpage");
				web.WebHandler.openWebPage("https://mpbibb7.wixsite.com/be-my-player-2",this.homePanel);
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
