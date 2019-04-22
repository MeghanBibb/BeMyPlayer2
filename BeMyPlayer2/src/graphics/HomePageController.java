package graphics;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.InformationExpert;

/**
 * The Class HomePageController.
 */
public class HomePageController extends PageController{
	
	/** The Constant ACCOUNT. */
	// set constant actions
	public static final String ACCOUNT = "account";
	
	/** The Constant SUPPORT. */
	public static final String SUPPORT = "support";
	
	/** The Constant FIND_FRIENDS. */
	public static final String FIND_FRIENDS = "friends";
	
	/** The Constant FIND_LOVE. */
	public static final String FIND_LOVE = "love";
	
	/** The Constant VIEW_MATCHES. */
	public static final String VIEW_MATCHES = "matches";
	
	/** The Constant LOGOUT. */
	public static final String LOGOUT = "logout";
	
	/** The Constant LAUNCH_WEB_PAGE. */
	public static final String LAUNCH_WEB_PAGE = "launchsite";
	
	/** The Constant CREDITS. */
	public static final String CREDITS = "credits";
	
	/** The Constant SOUND. */
	public static final String SOUND = "sound";

	
	/** The home page model. */
	
	// get view and jframe
	private HomePageModel homePageModel = null;
	
	/** The home panel. */
	private JPanel homePanel = null;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(HomePageController.class.getName());
	
	/* (non-Javadoc)
	 * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
	 */
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		HomePageView.launchHomePage(this,mainFrame);
	}

	/* (non-Javadoc)
	 * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
	 */
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
			case CREDITS:
				logger.info("Launching credits page");
				GraphicsController.processPage(PageCreator.CREDITS_PAGE, PageCreator.HOME_PAGE);
				break;
			case SOUND:
				logger.info("Modifying the sound settings");
				BackgroundMusic.getInstance().music();
				JButton temp = homePageModel.getBtnSound();
				if(BackgroundMusic.isPlaying()) {
					temp.setText("<HTML><U>Turn Music Off</U></HTML>");
				}else {
					temp.setText("<HTML><U>Turn Music On</U></HTML>");
				}
				homePageModel.setBtnSound(temp);
				break;
		}
	}

	/**
	 * Gets the home page model.
	 *
	 * @return the home page model
	 */
	public HomePageModel getHomePageModel() {
		return homePageModel;
	}
	
	/**
	 * Sets the home page model.
	 *
	 * @param homePageModel the new home page model
	 */
	public void setHomePageModel(HomePageModel homePageModel) {
		this.homePageModel = homePageModel;
	}

	/**
	 * Gets the home panel.
	 *
	 * @return the home panel
	 */
	public JPanel getHomePanel() {
		return homePanel;
	}

	/**
	 * Sets the home panel.
	 *
	 * @param homePanel the new home panel
	 */
	public void setHomePanel(JPanel homePanel) {
		this.homePanel = homePanel;
	}

	
	
}
