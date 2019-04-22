package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import firebase.DBFailureException;

import model.Profile;
import model.InformationExpert;
import model.ResourceManager;

/**
 * The Class ProfileBriefModel.
 */
public class ProfileBriefModel extends JPanel{
	
	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;

	/** The view match controller. */
	ViewMatchesController viewMatchController = new ViewMatchesController();
	
	/** The Constant DEFAULT_PIC. */
	public static final BufferedImage DEFAULT_PIC = ResourceManager.loadImage("defaultIcon.png");
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(ProfileBriefModel.class.getName());
	
	/**
	 * Instantiates a new profile brief model.
	 *
	 * @param profile the profile
	 * @param rect the rect
	 * @param backPage the back page
	 */
	public ProfileBriefModel(Profile profile, Rectangle rect, String backPage){

		try {
			InformationExpert.setOtherProfile(profile.getUserId());
		} catch (DBFailureException e2) {
			logger.severe("Database failed to load other profile" + profile.getUserId());
		}
			
		CircularImage setIcon = null;
		Image img1;
		try {
			img1 = InformationExpert.getProfileImage(profile.getUserId());
			setIcon = new CircularImage(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
		} catch (Exception e1) {
			logger.warning("Failed to load profile picture for " + profile.getUserId()) ;
			img1 = DEFAULT_PIC;
			setIcon = new CircularImage(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
		}
			

			
			JLabel username = new JLabel(profile.getUsername());
			JLabel age = new JLabel();
			JLabel gender = new JLabel(profile.getGender());
			JButton viewProfile = new JButton("View Profile");
			this.setBounds(rect);
			this.setLayout(null);

			
			viewProfile.setBackground(Colors.Red);
			viewProfile.setBounds(this.getWidth()/4,120, this.getWidth()/2, 75);
			viewProfile.setForeground(Colors.Yellow);
			viewProfile.setFont(Fonts.getFont((float) 12));
			viewProfile.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					GraphicsController.setProfileAccountOther();
					 
					try {
						InformationExpert.setOtherProfile(profile.getUserId());
					} catch (DBFailureException e1) {
						logger.severe("Failed to load user profile for " + profile.getUserId());
					}
					 
					GraphicsController.processPage(PageCreator.PROFILE_PAGE, backPage);
				}
				
			});
			username.setFont(Fonts.getFont((float) 20));
			username.setForeground(Colors.Red);
			username.setBounds(105,7,150,69);
			
			age.setFont(Fonts.getFont((float) 15));
			
		    LocalDate now = LocalDate.now();
			Date nowDate = java.sql.Date.valueOf(now);
			Calendar cnow = Calendar.getInstance();
			cnow.setTime(nowDate);
			Date bday = profile.getDateOB();
			Calendar cbday = Calendar.getInstance();
			cbday.setTime(bday);
			int diff = cnow.get(Calendar.YEAR) - cbday.get(Calendar.YEAR);
			if(cnow.get(Calendar.MONTH) == cbday.get(Calendar.MONTH) && cnow.get(Calendar.DATE) > cbday.get(Calendar.DATE) ) {
				diff--;
			}
			age.setText(Integer.toString(diff) + " years old");
		    age.setForeground(Colors.Yellow);
			
			age.setForeground(Colors.Red);
			age.setBounds(105,27,150,69);
			
			gender.setFont(Fonts.getFont((float) 15));
			gender.setForeground(Colors.Red);
			gender.setBounds(105,47,150,69);
			
			
			this.add(username);
			this.add(age);
			this.add(gender);
			this.add(viewProfile);
			setIcon.setBounds(17, 17, 75, 75);
			this.add(setIcon);
			this.setVisible(true);
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	  protected void paintComponent(Graphics g) {
		RoundRectangle2D r = new RoundRectangle2D.Float(this.getAlignmentX(), this.getAlignmentY(), this.getWidth(), this.getHeight(), 7, 7);
		g.setClip(r);
	    super.paintComponent(g);
	    
		Image backgroundImage;
		backgroundImage = ResourceManager.loadImage("profile_brief_background.png");
		g.drawImage(backgroundImage.getScaledInstance(this.getWidth()+20, this.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);	
		
	}
}
