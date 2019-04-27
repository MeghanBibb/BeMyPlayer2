package graphics;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import model.ClientManager;
import model.Match;
import model.MatchStatus;
import model.MatchType;
import model.Profile;

/**
 * The Class ProfilePageController.
 */
public class ProfilePageController extends PageController {
	
	
	/** The Constant BACK. */
	//init command constants
	public static final String BACK = "back";
	
	/** The Constant EDIT_ACCOUNT. */
	public static final String EDIT_ACCOUNT = "edit";
	
	/** The Constant BLOCK. */
	public static final String BLOCK = "block";
	
	/** The Constant MESSAGE. */
	public static final String MESSAGE = "message";
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(ProfilePageController.class.getName());
	
	/** The profile model. */
	// get view and jframe
	private ProfilePageModel profileModel = null;
	
	/** The profile panel. */
	private JPanel profilePanel = null;
	
	/** The a. */
	private Profile a;
	
	/* (non-Javadoc)
	 * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
	 */
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		if(GraphicsController.getProfileString().equalsIgnoreCase("active account")){
			a = ClientManager.getActiveAccount().getAccountProfile();
		}
		else if(GraphicsController.getProfileString().equalsIgnoreCase("other account")){
			a = ClientManager.getOtherProfile();
		}
		ProfilePageView.startProfilePage(this,mainFrame);
	}
	
	/**
	 * Checks if is active account.
	 *
	 * @return true, if is active account
	 */
	public boolean isActiveAccount() {
		return ClientManager.isActiveUser(a);
	}
	
	/**
	 * Gets the profile.
	 *
	 * @return the profile
	 */
	public Profile getProfile() {
		return a;
	}

	/* (non-Javadoc)
	 * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case BACK:
				logger.info("back");
				GraphicsController.setProfileAccountActive();
				GraphicsController.processPage(backPage,backPage);
				break;
			case EDIT_ACCOUNT:
				GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE,backPage);
				break;
			case BLOCK:
				logger.info("User " + ClientManager.getActiveUserID() + " has blocked user" + ClientManager.getOtherProfile().getUsername());
				
				try {
					Match thisMatch;
					
					if((thisMatch = ClientManager.getMatch(ClientManager.getActiveAccount().getAccountProfile(), ClientManager.getOtherProfile())) != null) {
						thisMatch.setClientMatchStatus(MatchStatus.NO_MATCH);
						thisMatch.setOtherMatchStatus(MatchStatus.NO_MATCH);
						thisMatch.setType(MatchType.BLOCKED);
						    
						//update match
						ClientManager.updateMatch(thisMatch);
						
					} else {
						//create new match
						thisMatch = new Match(ClientManager.getActiveAccount().getAccountProfile(), ClientManager.getOtherProfile());
						thisMatch.setClientMatchStatus(MatchStatus.NO_MATCH);
						thisMatch.setOtherMatchStatus(MatchStatus.NO_MATCH);
						thisMatch.setType(MatchType.FRIEND_MATCH);
						
						//add match
						ClientManager.addMatch(thisMatch);
					}
					
					/*REMOVE FROM MATCH LISTS*/
					if(ClientManager.getClientModel().getFriendMatches().contains(ClientManager.getOtherProfile())) {
						ClientManager.getClientModel().getFriendMatches().remove(ClientManager.getOtherProfile());
					}
					if(ClientManager.getClientModel().getLoveMatches().contains(ClientManager.getOtherProfile())) {
						ClientManager.getClientModel().getLoveMatches().remove(ClientManager.getOtherProfile());
					}
					
					if(backPage.equalsIgnoreCase("swipe page") && ClientManager.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
						ClientManager.getClientModel().dequeueFriendProfile();
					}
					else if(backPage.equalsIgnoreCase("swipe page") && ClientManager.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())){
						ClientManager.getClientModel().dequeLoveProfile();
					}
				} catch (DBFailureException e1) {
					logger.log(Level.SEVERE,"Database failure for blocking user: ", e1);
				} 
				
				GraphicsController.processPage(backPage, backPage);
				
				break;
			case MESSAGE:
				logger.info("message");
				GraphicsController.processPage(PageCreator.MESSAGE_PAGE,backPage);
				break;
		}
		
	}

	/**
	 * Gets the profile model.
	 *
	 * @return the profile model
	 */
	public ProfilePageModel getProfileModel() {
		return profileModel;
	}

	/**
	 * Sets the profile model.
	 *
	 * @param profileModel the new profile model
	 */
	public void setProfileModel(ProfilePageModel profileModel) {
		this.profileModel = profileModel;
	}

	/**
	 * Gets the profile panel.
	 *
	 * @return the profile panel
	 */
	public JPanel getProfilePanel() {
		return profilePanel;
	}

	/**
	 * Sets the profile panel.
	 *
	 * @param profilePanel the new profile panel
	 */
	public void setProfilePanel(JPanel profilePanel) {
		this.profilePanel = profilePanel;
	}

	
	
}
