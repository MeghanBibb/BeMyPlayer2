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

/**
 * The Class CreditsPageController.
 */
public class CreditsPageController extends PageController{
	
	/** The Constant BACK. */
	public static final String BACK = "back";
	
	/** The Constant PROFILE. */
	public static final String PROFILE = "profileclick";
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(CreditsPageController.class.getName());
	
	/** The copy frame. */
	private JFrame copyFrame = null;
	
	/** The credits panel. */
	private JPanel creditsPanel = null;
	
	/** The brief. */
	private ProfileBriefModel brief = null;
	
	/* (non-Javadoc)
	 * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
	 */
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		this.copyFrame = mainFrame;
		CreditsPageView.launchPage(this,mainFrame);
	}
	
	/* (non-Javadoc)
	 * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
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
				logger.severe("database failed to load profile");
				InvalidPopup p = new InvalidPopup(this.getCreditsPanel(),"Database failed to load profiles");
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
	
	/**
	 * Gets the credits panel.
	 *
	 * @return the credits panel
	 */
	public JPanel getCreditsPanel() {
		return creditsPanel;
	}
	
	/**
	 * Sets the credits panel.
	 *
	 * @param creditsPanel the new credits panel
	 */
	public void setCreditsPanel(JPanel creditsPanel) {
		this.creditsPanel = creditsPanel;
	}
	
	/**
	 * Gets the creators.
	 *
	 * @return the creators
	 */
	protected List<String> getCreators(){
		List<String> creators = new ArrayList<>();
		creators.add("sBMUWYKVuwXMHUhURkgZ");
		creators.add("ye3oAXBbQIGaCkG3XNIW");
		creators.add("xIjqV0bMIM6hGW3TAsvr");
		creators.add("SaZIwE9M21H5eAc4L4qz");
		creators.add("M2npjd5rVBlQaWRd8sBs");
		creators.add("PBvdWFjjvC1FhFEmjovx");
		
		return creators;
	}
}
