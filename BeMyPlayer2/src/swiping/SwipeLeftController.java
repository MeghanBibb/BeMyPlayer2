package swiping;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.JPanel;

import firebase.DBFailureException;
import graphics.GraphicsController;
import graphics.InvalidPopup;
import graphics.PageController;
import graphics.PageCreator;
import match.Match;
import match.MatchStatus;
import match.MatchType;
import model.*;

/**
 * The Class SwipeLeftController.
 */
public class SwipeLeftController extends SwipeButtonController{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(SwipeLeftController.class.getName());
	/** The controller. */
	SwipePageController controller;
	
	/**
	 * Instantiates a new swipe left controller.
	 *
	 * @param controller the controller
	 */
	public SwipeLeftController(SwipePageController controller) {
		super("Left");
		this.controller = controller;
		
	}

	/* (non-Javadoc)
	 * @see graphics.SwipeButtonController#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Match thisMatch;
			if((thisMatch = ClientManager.getMatch(ClientManager.getActiveAccount().getAccountProfile(), ClientManager.getOtherProfile())) != null) {
				
				if(ClientManager.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
					thisMatch.setType(MatchType.FRIEND_MATCH);
				}
				else if(ClientManager.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())) {
					thisMatch.setType(MatchType.LOVE_MATCH);
				}
				
				thisMatch.setClientMatchStatus(MatchStatus.SWIPE_LEFT);	
				
				//update match
				ClientManager.updateMatch(thisMatch);
				
			} else {
				//create new match
				thisMatch = new Match(ClientManager.getActiveAccount().getAccountProfile(), ClientManager.getOtherProfile());
				thisMatch.setClientMatchStatus(MatchStatus.SWIPE_LEFT);
				thisMatch.setOtherMatchStatus(MatchStatus.SWIPE_LEFT);
				if(ClientManager.getCurrentSwipePage().equals(MatchType._TYPE_FRIEND_MATCH)) {
					thisMatch.setType(MatchType.FRIEND_MATCH);
				}
				else if(ClientManager.getCurrentSwipePage().equals(MatchType._TYPE_LOVE_MATCH)) {
					thisMatch.setType(MatchType.LOVE_MATCH);
				}
				//add match
				ClientManager.addMatch(thisMatch);
			}
		} catch (DBFailureException e1) {
			logger.severe("Failed to load matches");
			InvalidPopup p = new InvalidPopup(new JPanel(), "Database error, please try again later");
		} finally {

			try {
				//	get next profile
				
				if(ClientManager.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
					ClientManager.getClientModel().dequeueFriendProfile();
					if(ClientManager.getClientModel().getFriendProfileFront() == null) {
						ClientManager.importFriendMatchBatch();
					}	
					if(ClientManager.getClientModel().getFriendProfileFront() == null) {
						throw new DBFailureException();
					}
					ClientManager.setOtherProfile(ClientManager.getClientModel().getFriendProfileFront().getUserId());
				}
				else if (ClientManager.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())) {
					ClientManager.getClientModel().dequeLoveProfile();
					if(ClientManager.getClientModel().getLoveProfileFront() == null) {
						ClientManager.importLoveMatchBatch();
					}	
					if(ClientManager.getClientModel().getLoveProfileFront() == null) {
						throw new DBFailureException();
					}
					ClientManager.setOtherProfile(ClientManager.getClientModel().getLoveProfileFront().getUserId());
					
				}
				
				controller.setProfile(ClientManager.getOtherProfile());
			} catch (DBFailureException e1) {
				logger.severe("Ran out of matches");
				InvalidPopup p = new InvalidPopup(new JPanel(),"Ran out of matches for today. Please come back tomorrow");
				GraphicsController.processPage(PageCreator.HOME_PAGE, null);
			}
		}
	}

}