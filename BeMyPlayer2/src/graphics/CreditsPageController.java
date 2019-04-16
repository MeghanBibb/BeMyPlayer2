package graphics;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import model.InformationExpert;

public class CreditsPageController extends PageController{
	public static final String BACK = "back";
	public static final String PROFILE = "profileclick";
	private static Logger logger = Logger.getLogger(CreditsPageController.class.getName());
	
	private JFrame copyFrame = null;
	private JPanel creditsPanel = null;
	private ProfileBriefModel brief = null;
	
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		this.copyFrame = mainFrame;
		CreditsPageView.launchPage(this,mainFrame);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == BACK) {
			logger.info("Returning to home page");
			GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
		}
		else if(e.getActionCommand() == PROFILE){
			String text = ((JButton) e.getSource()).getName();
			logger.info("launch profile brief for: " + text);
			try {
				InformationExpert.setOtherProfile(text);
			} catch (DBFailureException e1) {
				// TODO Auto-generated catch block
				logger.warning("database failed to load profile");
			}
			if(brief == null) {
				brief = new ProfileBriefModel(InformationExpert.getOtherProfile(),new Rectangle(250,120,215,245),PageCreator.MATCHES_PAGE);
			}
			else {
				this.copyFrame.remove(brief);
				brief = new ProfileBriefModel(InformationExpert.getOtherProfile(),new Rectangle(250,120,215,245),PageCreator.MATCHES_PAGE);
			}
			this.copyFrame.add(brief);
			this.copyFrame.repaint();
		}
	}
	public JPanel getCreditsPanel() {
		return creditsPanel;
	}
	public void setCreditsPanel(JPanel creditsPanel) {
		this.creditsPanel = creditsPanel;
	}
	protected List<String> getCreators(){
		List<String> creators = new ArrayList<>();
		creators.add("Colin");
		creators.add("Meghan");
		creators.add("Ben");
		creators.add("David");
		creators.add("Jackson");
		creators.add("Sam");
		
		return creators;
	}
}
