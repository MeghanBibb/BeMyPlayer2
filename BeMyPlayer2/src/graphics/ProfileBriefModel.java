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
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import firebase.DBFailureException;

import model.Profile;
import model.InformationExpert;
import model.ResourceManager;

public class ProfileBriefModel extends JPanel{
	ViewMatchesController viewMatchController = new ViewMatchesController();
	private String backPage;
	
	public ProfileBriefModel(Profile profile, Rectangle rect){

			
			CircularImage setIcon = null;
			Image img1;
			try {
				img1 = InformationExpert.getProfileImage(profile.getUserId());
				setIcon = new CircularImage(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
			} catch (DBFailureException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	
	public ProfileBriefModel(String s,Rectangle r, String page){
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
				try {
					 HARD CODED, NEED TO LOAD THE NECESSARY PROFILE HERE
					InformationExpert.setOtherProfile("LfiDeQ0WNQEnNyZ1c94J");
				} catch (DBFailureException e1) {
					System.out.println("NEED LOGGER: CANT LOAD PROFILE");
				}
				 
				GraphicsController.processPage(PageCreator.PROFILE_PAGE, backPage);
				*/
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
