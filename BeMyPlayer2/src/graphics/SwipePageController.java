package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JFrame;

import model.InformationExpert;
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

		this.model = new SwipePageModel(mainFrame, InformationExpert.getActiveAccount().getAccountProfile(), this);
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
