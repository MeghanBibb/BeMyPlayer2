package graphics;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.JPanel;

import firebase.DBFailureException;
import model.*;

// TODO: Auto-generated Javadoc
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
			if((thisMatch = InformationExpert.getMatch(InformationExpert.getActiveAccount().getAccountProfile(), InformationExpert.getOtherProfile())) != null) {
				/* CHECK TO MAKE SURE MATCH TYPE IS THE SAME IE: loveType on loveSwipePage*/
				//if(thisMatch.getType == controller.getType){
				thisMatch.setClientMatchStatus(MatchStatus.SWIPE_LEFT);	
				//}
				    
				//update match
				InformationExpert.updateMatch(thisMatch);
				
			} else {
				//create new match
				thisMatch = new Match(InformationExpert.getActiveAccount().getAccountProfile(), InformationExpert.getOtherProfile());
				thisMatch.setClientMatchStatus(MatchStatus.SWIPE_LEFT);
				thisMatch.setOtherMatchStatus(MatchStatus.SWIPE_LEFT);
				if(InformationExpert.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH)) {
					thisMatch.setType(MatchType.FRIEND_MATCH);
				}
				else if(InformationExpert.getCurrentSwipePage().equals(MatchType.LOVE_MATCH)) {
					thisMatch.setType(MatchType.LOVE_MATCH);
				}
				//add match
				InformationExpert.addMatch(thisMatch);
			}
		} catch (DBFailureException e1) {
			// TODO Auto-generated catch block
			logger.severe("Failed to load matches");
			InvalidPopup p = new InvalidPopup(new JPanel(), "Database error, please try again later");
		} finally {

			try {
				//	get next profile
				
				if(InformationExpert.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
					InformationExpert.getClientModel().dequeueFriendProfile();
					if(InformationExpert.getClientModel().getFriendProfileFront() == null) {
						InformationExpert.importFriendMatchBatch();
					}	
					if(InformationExpert.getClientModel().getFriendProfileFront() == null) {
						throw new DBFailureException();
					}
					InformationExpert.setOtherProfile(InformationExpert.getClientModel().getFriendProfileFront().getUserId());
					//InformationExpert.getClientModel().dequeueFriendProfile();
				}
				else if (InformationExpert.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())) {
					InformationExpert.getClientModel().dequeLoveProfile();
					if(InformationExpert.getClientModel().getLoveProfileFront() == null) {
						InformationExpert.importLoveMatchBatch();
					}	
					if(InformationExpert.getClientModel().getLoveProfileFront() == null) {
						throw new DBFailureException();
					}
					InformationExpert.setOtherProfile(InformationExpert.getClientModel().getLoveProfileFront().getUserId());
					
				}
				
				controller.setProfile(InformationExpert.getOtherProfile());
				//controller.setProfile(InformationExpert.getUserAccountWithProfile("LfiDeQ0WNQEnNyZ1c94J").getAccountProfile());
			} catch (DBFailureException e1) {
				// TODO Auto-generated catch block
				logger.severe("Ran out of matches");
				InvalidPopup p = new InvalidPopup(new JPanel(),"Ran out of matches for today. Please come back tomorrow");
				GraphicsController.processPage(PageCreator.HOME_PAGE, PageController.backPage);
			}
		}
	}

}