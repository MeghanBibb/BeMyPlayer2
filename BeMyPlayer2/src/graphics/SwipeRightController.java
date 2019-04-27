package graphics;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import firebase.DBFailureException;
import model.ClientManager;
import model.Match;
import model.MatchStatus;
import model.MatchType;

/**
 * The Class SwipeRightController.
 */
public class SwipeRightController extends SwipeButtonController{
	
	/** The controller. */
	SwipePageController controller;
	
	/**
	 * Instantiates a new swipe right controller.
	 *
	 * @param controller the controller
	 */
	public SwipeRightController(SwipePageController controller) {
		super("Right");
		this.controller = controller;
	}
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(SwipeRightController.class.getName());
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
				

				thisMatch.setClientMatchStatus(MatchStatus.SWIPE_RIGHT);	
				    
				//update match
				ClientManager.updateMatch(thisMatch);
				
			} else {
				//create new match
				thisMatch = new Match(ClientManager.getActiveAccount().getAccountProfile(), ClientManager.getOtherProfile());
				thisMatch.setClientMatchStatus(MatchStatus.SWIPE_RIGHT);
				thisMatch.setOtherMatchStatus(MatchStatus.NO_MATCH);
				if(ClientManager.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
					thisMatch.setType(MatchType.FRIEND_MATCH);
				}
				else if(ClientManager.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())) {
					thisMatch.setType(MatchType.LOVE_MATCH);
				}
				//add match
				ClientManager.addMatch(thisMatch);
			}
			
			if(thisMatch.getClientMatchStatus().equals(thisMatch.getOtherMatchStatus()) && thisMatch.getClientMatchStatus().equals(MatchStatus.SWIPE_RIGHT)) {
				JOptionPane.showConfirmDialog(new JPanel(), "You have a succesful match with " + ClientManager.getOtherProfile().getUsername(), "Match", JOptionPane.DEFAULT_OPTION);
				if(ClientManager.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
					ClientManager.getClientModel().addFriendMatch(ClientManager.getOtherProfile());
				}
				else if(ClientManager.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())) {
					ClientManager.getClientModel().addLoveMatch(ClientManager.getOtherProfile());
				}
			}
		} catch (DBFailureException e1) {
			logger.severe("Database error occured");
			InvalidPopup p = new InvalidPopup(new JPanel(),"Database error, please try again later");
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
				GraphicsController.processPage(PageCreator.HOME_PAGE, PageController.backPage);
			}
		}
		
	}

}