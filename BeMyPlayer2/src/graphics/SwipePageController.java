package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import model.InformationExpert;
import model.MatchType;
import model.Profile;

// TODO: Auto-generated Javadoc
/**
 * The Class SwipePageController.
 */
public class SwipePageController extends PageController {
	
	/** The model. */
	SwipePageModel model;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(SwipePageController.class.getName());
	
	/**
	 * Instantiates a new swipe page controller.
	 */
	public SwipePageController() {
	}

	/* (non-Javadoc)
	 * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
	 */
	@Override
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		boolean invalid = false;
		//	load first matches
		
		if(InformationExpert.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
			try {
			if(InformationExpert.getClientModel().getFriendProfileFront() == null || InformationExpert.getClientModel().getFriendMatches().isEmpty()) {
				//	1 iteration of import, next import grows size
				InformationExpert.importFriendMatchBatch();
			}
			
			if(InformationExpert.getClientModel().getFriendProfileFront() == null) {
				throw new DBFailureException();
			}
			
				InformationExpert.setOtherProfile(InformationExpert.getClientModel().getFriendProfileFront().getUserId());
				//InformationExpert.getClientModel().dequeueFriendProfile();
			} catch (DBFailureException e1) {
				invalid = true;
				logger.severe("Ran out of matches");
				InvalidPopup p = new InvalidPopup(new JPanel(),"Ran out of matches for today. Please come back tomorrow");
				GraphicsController.processPage(PageCreator.HOME_PAGE, PageController.backPage);
			}
		}
		else if(InformationExpert.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())){
			try {
				if(InformationExpert.getClientModel().getLoveProfileFront() == null || InformationExpert.getClientModel().getLoveMatches().isEmpty()) {
					//	1 iteration of import, next import grows size
					InformationExpert.importLoveMatchBatch();
				}
				if(InformationExpert.getClientModel().getLoveProfileFront() == null) {
					throw new DBFailureException();
				}
				
					InformationExpert.setOtherProfile(InformationExpert.getClientModel().getLoveProfileFront().getUserId());
					//InformationExpert.getClientModel().dequeueFriendProfile();
				} catch (DBFailureException e1) {
					invalid = true;
					logger.severe("Ran out of matches");
					InvalidPopup p = new InvalidPopup(new JPanel(),"Ran out of matches for today. Please come back tomorrow");
					GraphicsController.processPage(PageCreator.HOME_PAGE, PageController.backPage);
				}
		}
		if(InformationExpert.getActiveAccount().getAccountProfile().getMute()) {
			AccountIsMuted.Warning(mainFrame);
			invalid = true;
		}
		if(!invalid) {
		this.model = new SwipePageModel(mainFrame, InformationExpert.getOtherProfile(), this);
		model.backButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    			logger.info("back");
	    			GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
	    	}
	    });
		}
	}

	/**
	 * Sets the profile.
	 *
	 * @param profile the new profile
	 */
	public void setProfile(Profile profile) {
		this.model.ChangeProfile(profile);
	}
	
	/* (non-Javadoc)
	 * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
