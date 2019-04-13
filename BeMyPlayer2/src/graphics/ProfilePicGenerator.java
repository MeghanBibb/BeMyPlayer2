package graphics;
import model.Profile;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private static Logger logger = Logger.getLogger(ProfilePageController.class.getName());
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
				Profile temp = InformationExpert.getOtherProfile();
				//Image img1 = new ImageIcon("filepathfromDB").getImage();//	add try catch and dont add if invalid file path
				JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 50, Image.SCALE_DEFAULT)));
				setIcon.setLayout(new BorderLayout());
				setIcon.setName(name);
				setIcon.setSize(50, 50);
				setIcon.add(new JLabel(temp.getUsername()),BorderLayout.PAGE_END);
				setIcon.setActionCommand("profileclick");
				setIcon.addActionListener(b);
				loveList.add(setIcon);
			} catch (DBFailureException e) {
				// TODO Auto-generated catch block
				logger.warning("database failure");
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
				Profile temp = InformationExpert.getOtherProfile();
				//Image img1 = new ImageIcon("filepathfromDB").getImage();//	add try catch and dont add if invalid file path
				JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 50, Image.SCALE_DEFAULT)));
				setIcon.setLayout(new BorderLayout());
				setIcon.setName(name);
				setIcon.setSize(50, 50);
				setIcon.add(new JLabel(temp.getUsername()),BorderLayout.PAGE_END);
				setIcon.setActionCommand("profileclick");
				setIcon.addActionListener(b);
				friendList.add(setIcon);
			} catch (DBFailureException e) {
				// TODO Auto-generated catch block
				logger.warning("database failure");
			}
			
		}
		return friendList;
	}
	
}
