package graphics;
import model.Profile;
import model.ResourceManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import firebase.DBFailureException;
import model.Account;
import model.InformationExpert;

public class ProfilePicGenerator {
	private static Logger logger = Logger.getLogger(ProfilePicGenerator.class.getName());
	public static final BufferedImage DEFAULT_PIC = ResourceManager.loadImage("defaultIcon.png");
	public static List<JButton> getLoveList(ViewMatchesController b){
		List<JButton> loveList = new ArrayList<>();
		List<Profile> profiles= InformationExpert.getClientModel().getLoveMatches();
		List<String> userIds = new ArrayList<>();
		for(int i = 0; i < profiles.size(); i++) {
			userIds.add(profiles.get(i).getUserId());
		}
		for(String name: userIds) {
			//	query db for name and get images 
			Image img1;
			try {
				img1 = InformationExpert.getProfileImage(name);
				InformationExpert.setOtherProfile(name);
				
			} catch (DBFailureException e) {
				// TODO Auto-generated catch block
				img1 = DEFAULT_PIC;
				logger.warning("database failed to load pic for " + name);
			}
			Profile temp = InformationExpert.getOtherProfile();
			//Image img1 = new ImageIcon("filepathfromDB").getImage();//	add try catch and dont add if invalid file path
			JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 50, Image.SCALE_SMOOTH)));
			setIcon.setLayout(new BorderLayout());
			setIcon.setName(name);
			setIcon.setPreferredSize(new Dimension(75,50));
			setIcon.add(new JLabel(temp.getUsername()),BorderLayout.PAGE_END);
			setIcon.setActionCommand("profileclick");
			setIcon.addActionListener(b);
			loveList.add(setIcon);
		}
		if(userIds.size() < 3) {
			int i = userIds.size();
			while(i < 3) {
				JButton temp  =new JButton();
				temp.setEnabled(false);
				temp.setVisible(false);
				loveList.add(temp);
				i++;
			}
		}
		return loveList;
	}
	public static List<JButton> getFriendList(ViewMatchesController b){
		List<JButton> friendList = new ArrayList<JButton>();
		List<Profile> profiles= InformationExpert.getClientModel().getFriendMatches();
		List<String> userIds = new ArrayList<>();
		for(int i = 0; i < profiles.size(); i++) {
			userIds.add(profiles.get(i).getUserId());
		}
		for(String name: userIds) {
			//	query db for name and get images 
			Image img1;
			try {
				img1 = InformationExpert.getProfileImage(name);
				InformationExpert.setOtherProfile(name);
			} catch (DBFailureException e) {
				// TODO Auto-generated catch block
				img1 = DEFAULT_PIC;
				logger.warning("database failed to load profile pic for " + name);
			}
			
			Profile temp = InformationExpert.getOtherProfile();
			JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 50, Image.SCALE_SMOOTH)));
			setIcon.setLayout(new BorderLayout());
			setIcon.setName(name);
			setIcon.setPreferredSize(new Dimension(75,50));
			setIcon.add(new JLabel(temp.getUsername()),BorderLayout.PAGE_END);
			setIcon.setActionCommand("profileclick");
			setIcon.addActionListener(b);
			friendList.add(setIcon);
			
		}
		if(userIds.size() < 3) {
			int i = userIds.size();
			while(i < 3) {
				JButton temp  =new JButton();
				temp.setEnabled(false);
				temp.setVisible(false);
				friendList.add(temp);
				i++;
			}
		}
		return friendList;
	}
	
}
