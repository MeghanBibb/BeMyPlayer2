package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ResourceManager;

public class ProfileBriefModel extends JPanel{
	ViewMatchesController viewMatchController = new ViewMatchesController();
	private String backPage;
	
	ProfileBriefModel(String s,Rectangle r, String page){
		this.backPage = page;

		//Color yellow = new Color(254, 195, 123);
		Color black = new Color(204, 255, 255);
		Color yellow = new Color(254, 195, 123);
		Color red = new Color(134,48,111);
		Color white = new Color(255, 255, 255);
		//	TEMPORARY FOR PROFILE IMAGE SWAPPING
		String temploc = null;
		switch(s) {
		case "Dr.Booth": temploc = "/booth1.jpg";
			break;
		case "Dr.Cerny": temploc = "/cerny1.png";
			break;
		case "Dr.Fendt": temploc = "/fendt.jpg";
			break;
		case "Dr.Hammerly": temploc = "/hammerly1.jpg";
		break;
		case "Prof.Fry": temploc = "/fry1.jpg";
			break;
		case "Prof.Aars":temploc = "/maars1.jpg";	
			break;
		}
		Image img1 = new ImageIcon(viewMatchController.getClass().getResource(temploc)).getImage();
		CircularImage setIcon = new CircularImage(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));

		
		JLabel username = new JLabel(s);
		JLabel age = new JLabel();
		JLabel gender = new JLabel("Gender");
		JButton viewProfile = new JButton("View Profile");
		this.setBounds(r);
		this.setLayout(null);
		this.setBackground(yellow);
		
		viewProfile.setBackground(red);
		viewProfile.setBounds(this.getWidth()/4,120, this.getWidth()/2, 75);
		viewProfile.setForeground(Colors.Yellow);
		viewProfile.setFont(Fonts.getFont((float) 12));
		viewProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// set the other account to selected account
				GraphicsController.setProfileAccountOther();
				/* 
				 * InformationExpert.setOtherAccount( ACCOUNT THIS PROFILE REPRESENTS);
				 */
				GraphicsController.processPage(PageCreator.PROFILE_PAGE, backPage);
			}
			
		});
		username.setFont(Fonts.getFont((float) 20));
		username.setForeground(Colors.Red);
		username.setBounds(105,7,150,69);
		
		age.setFont(Fonts.getFont((float) 15));
		age.setText("Age");
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
