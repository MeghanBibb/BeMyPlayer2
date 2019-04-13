package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.InformationExpert;
import model.Profile;
import model.ResourceManager;

public class SwipePageModel {
	
	SwipeButton left;
	SwipeButton right;
	BorderLayout layout = new BorderLayout();
	JFrame frame = null;
	JButton backButton = new JButton("Back");
	Profile currentProfile;
	Rectangle briefSize = new Rectangle(150,100,230,200);
	
	public SwipePageModel(JFrame t_frame, Profile profile){
		
		this.frame = t_frame;
		
		this.currentProfile = profile;
		
		this.frame.setContentPane(new BackgroundPanel(null));
		
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		frame.getContentPane().setLayout(layout);
		backButton.setBackground(Colors.Yellow);
		backButton.setFont(Fonts.getFont((float) 12));
		backButton.setForeground(Colors.Red);
		
		
		this.left = new SwipeButton(new SwipeLeftController());
		this.right = new SwipeButton(new SwipeRightController());
		
		layout.setHgap(70);
		layout.setVgap(40);
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(Fonts.getFont((float) 30));
		lblBeMyPlayer.setForeground(Colors.Yellow);
		lblBeMyPlayer.setBounds(160,10,204,69);
		
		frame.getContentPane().add(lblBeMyPlayer, BorderLayout.NORTH);
		frame.getContentPane().add(this.left, BorderLayout.LINE_START);
		frame.getContentPane().add(this.right, BorderLayout.LINE_END);
		frame.getContentPane().add(this.backButton, BorderLayout.PAGE_END);
		frame.getContentPane().add(new ProfileBriefModel(profile, briefSize, PageCreator.SWIPE_PAGE), BorderLayout.CENTER);
	}
	public void ChangeProfile(Profile profile) {
		this.currentProfile = profile;
		frame.getContentPane().add(new ProfileBriefModel(profile, briefSize, PageCreator.SWIPE_PAGE), BorderLayout.CENTER);
		frame.getContentPane().repaint();
	}
}