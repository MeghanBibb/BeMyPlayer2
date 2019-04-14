package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JFrame;

import firebase.DBFailureException;
import model.InformationExpert;
import model.MatchType;
import model.Profile;

public class SwipePageController extends PageController {
	SwipePageModel model;
	private static Logger logger = Logger.getLogger(SwipePageController.class.getName());
	public SwipePageController() {
	}

	@Override
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		
		//	load first matches
		if(InformationExpert.getCurrentSwipePage().equals(MatchType.FRIEND_MATCH.getStatusString())) {
			if(InformationExpert.getClientModel().getFriendProfileFront() == null) {
				//	1 iteration of import, next import grows size
				InformationExpert.importFriendMatchBatch();
			}
			
			try {
				InformationExpert.setOtherProfile(InformationExpert.getClientModel().getFriendProfileFront().getUserId());
				//InformationExpert.getClientModel().dequeueFriendProfile();
			} catch (DBFailureException e1) {
				// TODO Auto-generated catch block
				logger.warning("database failed to load matches");
			}
		}
		else if(InformationExpert.getCurrentSwipePage().equals(MatchType.LOVE_MATCH.getStatusString())){
			if(InformationExpert.getClientModel().getLoveProfileFront() == null) {
				InformationExpert.importLoveMatchBatch();
			}

			try {
				InformationExpert.setOtherProfile(InformationExpert.getClientModel().getLoveProfileFront().getUserId());
				//InformationExpert.getClientModel().dequeLoveProfile();
			} catch (DBFailureException e1) {
				// TODO Auto-generated catch block
				logger.warning("database failed to load matches");
			}
		}
		if(InformationExpert.getOtherProfile() == null) {
			logger.warning("no matches for you loser");
		}
		this.model = new SwipePageModel(mainFrame, InformationExpert.getOtherProfile(), this);
		model.backButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    			logger.info("back");
	    			GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
	    	}
	    });
		
	}

	public void setProfile(Profile profile) {
		this.model.ChangeProfile(profile);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
