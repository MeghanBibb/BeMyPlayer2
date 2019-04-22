package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Profile;
import model.ResourceManager;

/**
 * The Class SwipePageModel.
 */
public class SwipePageModel {
	
	/** The left. */
	SwipeButton left;
	
	/** The right. */
	SwipeButton right;
	
	/** The layout. */
	BorderLayout layout = new BorderLayout();
	
	/** The frame. */
	JFrame frame = null;
	
	/** The back button. */
	ImgButton backButton = new ImgButton("Back");
	
	/** The current profile. */
	Profile currentProfile;
	
	/** The brief size. */
	Rectangle briefSize = new Rectangle(150,100,230,200);
	
	/** The controller. */
	SwipePageController controller;
	
	/** The lbl be my player. */
	JLabel lblBeMyPlayer;
	
	/** The heart image. */
	JLabel heartImage;
	
	/** The img 1. */
	BufferedImage img1 = ResourceManager.loadImage("splash_heart.png");
	
	/**
	 * Instantiates a new swipe page model.
	 *
	 * @param t_frame the t frame
	 * @param profile the profile
	 * @param c the c
	 */
	public SwipePageModel(JFrame t_frame, Profile profile, SwipePageController c){
		this.controller = c;
		this.frame = t_frame;
		heartImage = new JLabel();
		
		this.currentProfile = profile;
		
		this.frame.setContentPane(new BackgroundPanel(null));
		
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		frame.getContentPane().setLayout(layout);
		backButton.setPreferredSize(new Dimension(300, 30));
		backButton.setFont(Fonts.getFont((float) 12));
		backButton.setForeground(Colors.Red);
		
		heartImage.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		heartImage.setBounds(350, 1, 50, 50);
		
		this.left = new SwipeButton(new SwipeLeftController(this.controller));
		
		this.left.setPreferredSize(new Dimension(65, 250));
		
		this.right = new SwipeButton(new SwipeRightController(this.controller));
		this.right.setPreferredSize(new Dimension(65, 250));
		
		layout.setHgap(70);
		layout.setVgap(40);
		lblBeMyPlayer = new JLabel("         Be My Player 2");
		lblBeMyPlayer.setFont(Fonts.getFont((float) 30));
		lblBeMyPlayer.setForeground(Colors.Yellow);
		lblBeMyPlayer.setBounds(200,10,204,69);
		
		frame.getContentPane().add(heartImage, BorderLayout.NORTH);
		frame.getContentPane().add(lblBeMyPlayer, BorderLayout.NORTH);
		frame.getContentPane().add(this.left, BorderLayout.LINE_START);
		frame.getContentPane().add(this.right, BorderLayout.LINE_END);
		frame.getContentPane().add(this.backButton, BorderLayout.PAGE_END);
		frame.getContentPane().add(new ProfileBriefModel(profile, briefSize, PageCreator.SWIPE_PAGE), BorderLayout.CENTER);
	}
	
	/**
	 * Change profile.
	 *
	 * @param profile the profile
	 */
	public void ChangeProfile(Profile profile) {
		this.currentProfile = profile;
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		frame.getContentPane().setLayout(layout);
		layout.setHgap(70);
		layout.setVgap(40);
		frame.getContentPane().add(heartImage, BorderLayout.NORTH);
		frame.getContentPane().add(this.lblBeMyPlayer, BorderLayout.NORTH);
		frame.getContentPane().add(this.left, BorderLayout.LINE_START);
		frame.getContentPane().add(this.right, BorderLayout.LINE_END);
		frame.getContentPane().add(this.backButton, BorderLayout.PAGE_END);
		frame.getContentPane().add(new ProfileBriefModel(currentProfile, briefSize, PageCreator.SWIPE_PAGE), BorderLayout.CENTER);
		frame.getContentPane().repaint();
	}
}