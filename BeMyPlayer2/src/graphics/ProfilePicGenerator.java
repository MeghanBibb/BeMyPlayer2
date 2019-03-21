package graphics;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ProfilePicGenerator {
	public static List<JButton> getLoveList(Account a,ViewMatchesController b){
		List<JButton> loveList = new ArrayList<JButton>();
		for(String name: a.getLoveMatches()) {
			//	query db for name and get images 
			
			Image img1 = new ImageIcon("filepathfromDB").getImage();//	add try catch and dont add if invalid file path
			JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 50, Image.SCALE_DEFAULT)));
			setIcon.setLayout(new BorderLayout());
			setIcon.setName(name);
			setIcon.add(new JLabel(name),BorderLayout.PAGE_END);
			setIcon.addActionListener(b);
			loveList.add(setIcon);
			
		}
		return loveList;
	}
	public static List<JButton> getFriendList(Account a,ViewMatchesController b){
		List<JButton> friendList = new ArrayList<JButton>();
		for(String name: a.getFriendMatches()) {
			//	query db for name and get images 
			
			Image img1 = new ImageIcon("filepathfromDB").getImage();//	add try catch and dont add if invalid file path
			JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 50, Image.SCALE_DEFAULT)));
			setIcon.setLayout(new BorderLayout());
			setIcon.add(new JLabel(name),BorderLayout.PAGE_END);
			setIcon.addActionListener(b);
			setIcon.setName(name);
			friendList.add(setIcon);
			
		}
		return friendList;
	}
	
}
