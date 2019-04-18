package graphics;
import model.Profile;
import model.ResourceManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

// TODO: Auto-generated Javadoc
/**
 * The Class ProfilePicGenerator.
 */
public class ProfilePicGenerator {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(ProfilePicGenerator.class.getName());
	
	/** The Constant DEFAULT_PIC. */
	public static final BufferedImage DEFAULT_PIC = ResourceManager.loadImage("defaultIcon.png");
	
	/**
	 * Gets the love list.
	 *
	 * @param b the b
	 * @return the love list
	 */
	public static List<JButton> getLoveList(ViewMatchesController b){
		List<JButton> loveList = new ArrayList<>();
		List<Profile> profiles= InformationExpert.getClientModel().getLoveMatches();
		List<String> userIds = new ArrayList<>();
		List<JLabel> nameLabel = new ArrayList<>();
		for(int i = 0; i < profiles.size();i++) {
			JLabel nameLabel1 = new JLabel();
			nameLabel1.setFont(Fonts.getFont(12f));
			nameLabel1.setForeground(Colors.Yellow);
			nameLabel.add(nameLabel1);
		}
		
		for(int i = 0; i < profiles.size(); i++) {
			userIds.add(profiles.get(i).getUserId());
			nameLabel.get(i).setText(profiles.get(i).getUsername());
		}
		int j = 0;
		List<PicThread> threads = new ArrayList<>();
	
		for(String name: userIds) {
			PicThread d = new PicThread(name,loveList,j,nameLabel,b);
			threads.add(d);
			j++;
		}
		for(PicThread l : threads) {
			l.start();
		}
		for(int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.severe("Join failure");
				InvalidPopup p = new InvalidPopup(b.getViewMatchesPanel(),"Synchronization issue with threads");
				
			}
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
	
	/**
	 * Gets the friend list.
	 *
	 * @param b the b
	 * @return the friend list
	 */
	public static List<JButton> getFriendList(ViewMatchesController b){
		List<JButton> friendList = new ArrayList<JButton>();
		List<Profile> profiles= InformationExpert.getClientModel().getFriendMatches();
		List<String> userIds = new ArrayList<>();
		List<JLabel> nameLabel = new ArrayList<>();
		for(int i = 0; i < profiles.size();i++) {
			JLabel nameLabel1 = new JLabel();
			nameLabel1.setFont(Fonts.getFont(12f));
			nameLabel1.setForeground(Colors.Yellow);
			nameLabel.add(nameLabel1);
		}
		for(int i = 0; i < profiles.size(); i++) {
			userIds.add(profiles.get(i).getUserId());
			nameLabel.get(i).setText(profiles.get(i).getUsername());
		}
		List<PicThread> threads = new ArrayList<>();
		int j = 0;
		for(String name: userIds) {
			PicThread d = new PicThread(name,friendList,j,nameLabel,b);
			threads.add(d);
			j++;
		}
		for(PicThread l : threads) {
			l.start();
		}
		for(int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.severe("Join failure");
				InvalidPopup p = new InvalidPopup(b.getViewMatchesPanel(),"Synchronization issue with threads");
				
			}
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
	
	/**
	 * Gets the creators list.
	 *
	 * @param p the p
	 * @return the creators list
	 */
	public static List<JButton> getCreatorsList(CreditsPageController p){
		
		List<JButton> creators = new ArrayList<JButton>();
		List<String> userIds = p.getCreators();
		List<JLabel> nameLabel = new ArrayList<>();
		
		for(String name : userIds) {
			JLabel nameLabel1 = new JLabel();
			nameLabel1.setFont(Fonts.getFont(12f));
			nameLabel1.setForeground(Colors.Yellow);
			try {
				nameLabel1.setText(InformationExpert.getUserAccountWithProfile(name).getAccountProfile().getUsername());
			} catch (DBFailureException e) {
				// TODO Auto-generated catch block
				logger.warning("User does not exist");
				
			}
			nameLabel.add(nameLabel1);
		}
		
		List<PicThread> threads = new ArrayList<>();
		int j = 0;
		for(String name: userIds) {
			PicThread d = new PicThread(name,creators,j,nameLabel,p);
			threads.add(d);
			j++;
		}
		for(PicThread l : threads) {
			l.start();
		}
		for(int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.severe("Join failure");
			}
		}
		
		return creators;
	}
}
