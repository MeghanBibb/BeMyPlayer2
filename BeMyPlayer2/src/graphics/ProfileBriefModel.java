package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProfileBriefModel extends JPanel{
	ViewMatchesController viewMatchController = new ViewMatchesController();
	ProfileBriefModel(){
		//Color yellow = new Color(254, 195, 123);
		Color black = new Color(204, 255, 255);
		Color yellow = new Color(254, 195, 123);
		Color red = new Color(134,48,111);
		Color white = new Color(255, 255, 255);
		
		Image img1 = new ImageIcon(viewMatchController.getClass().getResource("/defaultIcon.png")).getImage();
		JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
		JLabel username = new JLabel("Username");
		JLabel age = new JLabel("Age");
		JLabel gender = new JLabel("Gender");
		JButton viewProfile = new JButton("View Profile");
		
		this.setLayout(null);
		this.setBackground(yellow);
		
		viewProfile.setBackground(red);
		viewProfile.setBounds(40, 140, 150, 60);
		viewProfile.setForeground(white);
		
		username.setFont(new Font("Monospaced", Font.BOLD, 20));
		username.setForeground(red);
		username.setBounds(105,7,150,69);
		
		age.setFont(new Font("Monospaced", Font.BOLD, 20));
		age.setForeground(red);
		age.setBounds(105,27,150,69);
		
		gender.setFont(new Font("Monospaced", Font.BOLD, 20));
		gender.setForeground(red);
		gender.setBounds(105,47,150,69);
		
		this.add(username);
		this.add(age);
		this.add(gender);
		this.add(viewProfile);
		setIcon.setBounds(17, 17, 75, 75);
		this.add(setIcon);
	}

}
