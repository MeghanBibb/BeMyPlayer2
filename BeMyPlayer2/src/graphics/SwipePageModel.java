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
	SwipePageController controller;
	JLabel lblBeMyPlayer;
	JLabel heartImage;
	
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
		backButton.setBackground(Colors.Yellow);
		backButton.setFont(Fonts.getFont((float) 12));
		backButton.setForeground(Colors.Red);
		
		BufferedImage img1 = ResourceManager.loadImage("splash_heart.png");
		//img1 = new ImageIcon("C:\\Backup of student files\\Spring 2019\\BeMyPlayer2\\BeMyPlayer2\\BeMyPlayer2\\img\\hearts.png").getImage();
		heartImage.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		heartImage.setBounds(350, 1, 50, 50);
		
		this.left = new SwipeButton(new SwipeLeftController(this.controller));
		this.right = new SwipeButton(new SwipeRightController(this.controller));
		
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
	public void ChangeProfile(Profile profile) {
		this.currentProfile = profile;
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		frame.getContentPane().setLayout(layout);
		layout.setHgap(70);
		layout.setVgap(40);
		frame.getContentPane().add(this.lblBeMyPlayer, BorderLayout.NORTH);
		frame.getContentPane().add(this.left, BorderLayout.LINE_START);
		frame.getContentPane().add(this.right, BorderLayout.LINE_END);
		frame.getContentPane().add(this.backButton, BorderLayout.PAGE_END);
		frame.getContentPane().add(new ProfileBriefModel(currentProfile, briefSize, PageCreator.SWIPE_PAGE), BorderLayout.CENTER);
		frame.getContentPane().repaint();
	}
}