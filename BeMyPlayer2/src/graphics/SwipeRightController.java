package graphics;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import firebase.DBFailureException;
import model.InformationExpert;
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
			if((thisMatch = InformationExpert.getMatch(InformationExpert.getActiveAccount().getAccountProfile(), InformationExpert.getOtherProfile())) != null) {
			
				
				if(InformationExpert.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
					thisMatch.setType(MatchType.FRIEND_MATCH);
				}
				else if(InformationExpert.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())) {
					thisMatch.setType(MatchType.LOVE_MATCH);
				}
				

				thisMatch.setClientMatchStatus(MatchStatus.SWIPE_RIGHT);	
				    
				//update match
				InformationExpert.updateMatch(thisMatch);
				
			} else {
				//create new match
				thisMatch = new Match(InformationExpert.getActiveAccount().getAccountProfile(), InformationExpert.getOtherProfile());
				thisMatch.setClientMatchStatus(MatchStatus.SWIPE_RIGHT);
				thisMatch.setOtherMatchStatus(MatchStatus.NO_MATCH);
				if(InformationExpert.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
					thisMatch.setType(MatchType.FRIEND_MATCH);
				}
				else if(InformationExpert.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())) {
					thisMatch.setType(MatchType.LOVE_MATCH);
				}
				//add match
				InformationExpert.addMatch(thisMatch);
			}
			
			if(thisMatch.getClientMatchStatus().equals(thisMatch.getOtherMatchStatus()) && thisMatch.getClientMatchStatus().equals(MatchStatus.SWIPE_RIGHT)) {
				JOptionPane.showConfirmDialog(new JPanel(), "You have a succesful match with " + InformationExpert.getOtherProfile().getUsername(), "Match", JOptionPane.DEFAULT_OPTION);
				if(InformationExpert.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
					InformationExpert.getClientModel().addFriendMatch(InformationExpert.getOtherProfile());
				}
				else if(InformationExpert.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())) {
					InformationExpert.getClientModel().addLoveMatch(InformationExpert.getOtherProfile());
				}
			}
		} catch (DBFailureException e1) {
			logger.severe("Database error occured");
			InvalidPopup p = new InvalidPopup(new JPanel(),"Database error, please try again later");
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
			} catch (DBFailureException e1) {
				logger.severe("Ran out of matches");
				InvalidPopup p = new InvalidPopup(new JPanel(),"Ran out of matches for today. Please come back tomorrow");
				GraphicsController.processPage(PageCreator.HOME_PAGE, PageController.backPage);
			}
		}
		
	}

}