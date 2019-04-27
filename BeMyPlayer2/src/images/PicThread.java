package images;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.RepaintManager;

import credits.CreditsPageController;
import firebase.DBFailureException;
import graphics.Colors;
import graphics.Fonts;
import model.ClientManager;
import model.Profile;
import model.ResourceManager;
import viewMatches.ViewMatchesController;

public class PicThread extends Thread{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(PicThread.class.getName());	
	private String uid;
	private List<JButton> buttons;
	private int id;
	private List<JLabel> texts;
	private ViewMatchesController c = null;
	private CreditsPageController d = null;
	public static final BufferedImage DEFAULT_PIC = ResourceManager.loadImage("defaultIcon.png");
	PicThread(String userid,List<JButton> g,int id,List<JLabel> texts,ViewMatchesController c){
		this.uid = userid;
		this.buttons = g;
		this.id = id;
		this.texts = texts;
		this.c = c;
	}
	PicThread(String userid,List<JButton> g,int id,List<JLabel> texts,CreditsPageController d){
		this.uid = userid;
		this.buttons = g;
		this.id = id;
		this.texts = texts;
		this.d = d;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
//		query db for name and get images 
		Image img1;
		Profile temp =  null;
		try {
			img1 = ClientManager.getProfileImage(this.uid);
		} catch (DBFailureException e) {
			img1 = DEFAULT_PIC;
			logger.warning("database failed to load pic for " + this.uid);
		}
		try {
			temp = ClientManager.getUserAccountWithProfile(this.uid).getAccountProfile();
		}
		catch(Exception b) {
			logger.warning("Failed to load profile");
			temp  = null;
		}
		if(temp != null) {
		JButton setIcon = new JButton();
		setIcon.setLayout(new FlowLayout());
		CircularImage ci = new CircularImage((new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH))));
		//RepaintManager.currentManager(ci).markCompletelyClean(ci);
		//ci.setIgnoreRepaint(true);
		setIcon.add(ci);
		
		setIcon.setFont(Fonts.getFont(12f));
		setIcon.setForeground(Colors.Yellow);
		setIcon.setBackground(Colors.Red);
		setIcon.setName(this.uid);
		setIcon.setPreferredSize(new Dimension(75,50));
		setIcon.add(this.texts.get(this.id), BorderLayout.PAGE_END);
		setIcon.setActionCommand("profileclick");
		if(this.c == null) {
			setIcon.addActionListener(d);
		}
		else {
			setIcon.addActionListener(c);
		}
		
		this.buttons.add(setIcon);
		}
		else {
			JButton trash  =new JButton();
			trash.setEnabled(false);
			trash.setVisible(false);
			this.buttons.add(trash);
		}
	}
}


