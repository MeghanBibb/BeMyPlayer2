package graphics;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import model.Account;
import model.ClientManager;

/**
 * The Class ViewMatchesController.
 */
public class ViewMatchesController extends PageController{

/** The Constant NEXT. */
//	action commands 	
	public static final String NEXT = "next";
	
	/** The Constant BACK. */
	public static final String BACK="back";
	
	/** The Constant SUBMIT. */
	public static final String SUBMIT = "submit";
	
	/** The Constant PROFILE. */
	public static final String PROFILE = "profileclick";
	
	/** The view matches model. */
	private ViewMatchesModel viewMatchesModel;
	
	/** The view matches panel. */
	private JPanel viewMatchesPanel;
	
	/** The a. */
	private Account a;
	
	/** The copy frame. */
	private JFrame copyFrame;
	
	/** The brief. */
	private ProfileBriefModel brief = null;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(ViewMatchesController.class.getName());
	
	/* (non-Javadoc)
	 * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
	 */
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}
		this.a = new Account();
		this.copyFrame = mainFrame;
		InformationExpert.loadAccountMatches();
		ViewMatchesView.startViewMatches(this,mainFrame,a);
	}
	
	/* (non-Javadoc)
	 * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
	 */
	//	check command 
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == BACK) {
			logger.info("back");
			GraphicsController.processPage(PageCreator.HOME_PAGE, backPage);
		}
		else if(e.getActionCommand() == PROFILE) {
			String text = ((JButton) e.getSource()).getName();
			logger.info("launch profile brief for: " + text);
			try {
				ClientManager.setOtherProfile(text);
			} catch (DBFailureException e1) {
				logger.warning("database failed to load profile");
			}
			if(brief == null) {
				brief = new ProfileBriefModel(ClientManager.getOtherProfile(),new Rectangle(250,120,215,245),PageCreator.MATCHES_PAGE);
			}
			else {
				this.copyFrame.remove(brief);
				brief = new ProfileBriefModel(ClientManager.getOtherProfile(),new Rectangle(250,120,215,245),PageCreator.MATCHES_PAGE);
			}
			this.copyFrame.add(brief);
			this.copyFrame.repaint();
		}
	}
	
	/**
	 * Gets the view matches panel.
	 *
	 * @return the view matches panel
	 */
	public JPanel getViewMatchesPanel() {
		return viewMatchesPanel;
	}
	
	/**
	 * Sets the view matches panel.
	 *
	 * @param viewMatchesPanel the new view matches panel
	 */
	public void setViewMatchesPanel(JPanel viewMatchesPanel) {
		this.viewMatchesPanel = viewMatchesPanel;
	}
	
	/**
	 * Gets the view matches model.
	 *
	 * @return the view matches model
	 */
	public ViewMatchesModel getViewMatchesModel() {
		return viewMatchesModel;
	}
	
	/**
	 * Sets the view matches model.
	 *
	 * @param viewMatchesModel the new view matches model
	 */
	public void setViewMatchesModel(ViewMatchesModel viewMatchesModel) {
		this.viewMatchesModel = viewMatchesModel;
	}
	
}
