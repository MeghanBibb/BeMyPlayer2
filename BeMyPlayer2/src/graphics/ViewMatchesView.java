package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Account;

public class ViewMatchesView {
	public static void startViewMatches(ViewMatchesController viewMatchController, JFrame mainFrame,Account a) {
		
		Color red = new Color(134,48,111);
		Color yellow = new Color(254, 195, 123);
		viewMatchController.setViewMatchesModel(new ViewMatchesModel());
		//	init panel
		viewMatchController.setViewMatchesPanel(new BackgroundPanel(null));
		viewMatchController.getViewMatchesPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		viewMatchController.getViewMatchesPanel().setPreferredSize(new Dimension(500,400));
		viewMatchController.getViewMatchesPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(viewMatchController.getViewMatchesPanel());
		
		
		//	init buttons and add to panel
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(25, 25, 90, 40);
		backbtn.setBackground(yellow);
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(viewMatchController);
		viewMatchController.getViewMatchesPanel().add(backbtn);
		
		//	label
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblBeMyPlayer.setForeground(yellow);
		lblBeMyPlayer.setBounds(160,0,204,69);
		viewMatchController.getViewMatchesPanel().add(lblBeMyPlayer);
		
		//	pull from match adapter giving the temp account
		GridLayout gridLayout = new GridLayout(0,2,5,5);
		JPanel profilePicPanel = new JPanel();
		profilePicPanel.setBackground(yellow);
		profilePicPanel.setLayout(gridLayout);
		profilePicPanel.setPreferredSize(new Dimension(100,245));
		profilePicPanel.setSize(100, 245);
		
		Image img1 = new ImageIcon(viewMatchController.getClass().getResource("/booth1.jpg")).getImage();
		Image img2 = new ImageIcon(viewMatchController.getClass().getResource("/fry1.jpg")).getImage();
		Image img3 = new ImageIcon(viewMatchController.getClass().getResource("/cerny1.png")).getImage();
		Image img4 = new ImageIcon(viewMatchController.getClass().getResource("/fendt.jpg")).getImage();
		Image img5 = new ImageIcon(viewMatchController.getClass().getResource("/hammerly1.jpg")).getImage();
		Image img6 = new ImageIcon(viewMatchController.getClass().getResource("/maars1.jpg")).getImage();
		JComboBox matchtype = new JComboBox();
		
		matchtype.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		System.out.println("updated");
        		System.out.println(viewMatchController.getViewMatchesModel().getMatchtype().getSelectedItem().toString());
        		///viewMatchController.getViewMatchesModel().setMatchtype(matchtype);
        		//	generate new match request pull 
        		if(viewMatchController.getViewMatchesModel().getMatchtype().getSelectedItem().toString().equalsIgnoreCase("Love Matches")) {
        			profilePicPanel.removeAll();
        			//a.getLoveMatches();
        			/////	FOR DEMO PURPOSES
        			final JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        			setIcon.setLayout(new BorderLayout());
        			setIcon.setName("Dr.Booth");
        			setIcon.add(new JLabel("Dr.Booth"),BorderLayout.PAGE_END);
        			final JButton setIcon2 = new JButton(new ImageIcon(new ImageIcon(img2).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        			setIcon2.setLayout(new BorderLayout());
        			setIcon2.setName("Prof.Fry");
        			setIcon2.add(new JLabel("Prof.Fry"),BorderLayout.PAGE_END);
        			final JButton setIcon3 = new JButton(new ImageIcon(new ImageIcon(img3).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        			setIcon3.setLayout(new BorderLayout());
        			setIcon3.setName("Dr.Cerny");
        			setIcon3.add(new JLabel("Dr.Cerny"),BorderLayout.PAGE_END);
        			setIcon.setActionCommand("profileclick");
        			setIcon.addActionListener(viewMatchController);
        			setIcon2.setActionCommand("profileclick");
        			setIcon2.addActionListener(viewMatchController);
        			setIcon3.setActionCommand("profileclick");
        			setIcon3.addActionListener(viewMatchController);
        			profilePicPanel.add(setIcon);
        			profilePicPanel.add(setIcon2);
        			profilePicPanel.add(setIcon3);
        			profilePicPanel.validate();
        			///	ACTUAL SOLUTION
        			/*
        			List<JButton> pics = ProfilePicGenerator.getLoveList(a,viewMatchController);
        			for(JButton icon: pics) {
        				profilePicPanel.add(icon);
        			}
        			profilePicPanel.validate();*/
        		}
        		else {
        			profilePicPanel.removeAll();
        			///	FOR DEMO PURPOSES
        			final JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img4).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        			setIcon.setLayout(new BorderLayout());
        			setIcon.setName("Dr.Fendt");
        			setIcon.add(new JLabel("Dr.Fendt"),BorderLayout.PAGE_END);
        			final JButton setIcon2 = new JButton(new ImageIcon(new ImageIcon(img5).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        			setIcon2.setLayout(new BorderLayout());
        			setIcon2.setName("Dr.Hammerly");
        			setIcon2.add(new JLabel("Dr.Hammerly"),BorderLayout.PAGE_END);
        			final JButton setIcon3 = new JButton(new ImageIcon(new ImageIcon(img6).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
        			setIcon3.setLayout(new BorderLayout());
        			setIcon3.setName("Prof.Aars");
        			setIcon3.add(new JLabel("Prof.Aars"),BorderLayout.PAGE_END);
        			setIcon.setActionCommand("profileclick");
        			setIcon.addActionListener(viewMatchController);
        			setIcon2.setActionCommand("profileclick");
        			setIcon2.addActionListener(viewMatchController);
        			setIcon3.setActionCommand("profileclick");
        			setIcon3.addActionListener(viewMatchController);
        			profilePicPanel.add(setIcon);
        			profilePicPanel.add(setIcon2);
        			profilePicPanel.add(setIcon3);
        			///	ACTUAL SOLUTION
        			/*
        			List<JButton> pics = ProfilePicGenerator.getFriendList(a,viewMatchController);
        			for(JButton icon: pics) {
        				profilePicPanel.add(icon);
        			}*/
        			profilePicPanel.validate();
        		}
        	}
        });
		
		matchtype.setToolTipText("Match type");
		matchtype.setModel(new DefaultComboBoxModel(new String[] {"Love Matches", "Friend Matches"}));
		matchtype.setBounds(45, 85, 150, 25);
		matchtype.setVisible(true);
		viewMatchController.getViewMatchesModel().setMatchtype(matchtype);
		viewMatchController.getViewMatchesPanel().add(matchtype);
		
		//	send type request to match adapter to pull list of matches for account
		
		//	load temp profile on the right side - > megan's file
		//	245,75,
		
		////////DEMO PURPOSES ONLY
		final JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
		setIcon.setLayout(new BorderLayout());
		setIcon.setName("Dr.Booth");
		setIcon.add(new JLabel("Dr.Booth"),BorderLayout.PAGE_END);
		setIcon.setActionCommand("profileclick");
		setIcon.addActionListener(viewMatchController);
		final JButton setIcon2 = new JButton(new ImageIcon(new ImageIcon(img2).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
		setIcon2.setLayout(new BorderLayout());
		setIcon2.setName("Prof.Fry");
		setIcon2.add(new JLabel("Prof.Fry"),BorderLayout.PAGE_END);
		setIcon2.setActionCommand("profileclick");
		setIcon2.addActionListener(viewMatchController);
		final JButton setIcon3 = new JButton(new ImageIcon(new ImageIcon(img3).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT)));
		setIcon3.setLayout(new BorderLayout());
		setIcon3.setName("Dr.Cerny");
		setIcon3.add(new JLabel("Dr.Cerny"),BorderLayout.PAGE_END);
		setIcon3.setActionCommand("profileclick");
		setIcon3.addActionListener(viewMatchController);
		profilePicPanel.add(setIcon);
		profilePicPanel.add(setIcon2);
		profilePicPanel.add(setIcon3);
		
		////	real implementation
		/*
		List<JButton> pics = ProfilePicGenerator.getFriendList(a,viewMatchController);
		for(JButton icon: pics) {
			profilePicPanel.add(icon);
		}*/
		
		//	SCROLLPANE
	    JScrollPane scrollPane = new JScrollPane(profilePicPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setBounds(new Rectangle(25, 120, 215, 245));
	    Color black = new Color(0,0,0);
	    
	    //	if empty
	    /*
	    JLabel noMatchlbl1 = new JLabel("Your princess is in another castle");
	    noMatchlbl1.setFont(new Font("Monospaced", Font.BOLD, 9));
	    noMatchlbl1.setForeground(black);
	    noMatchlbl1.setBounds(50,160,260,69);
	    viewMatchController.getViewMatchesPanel().add(noMatchlbl1);
		
		JLabel noMatchlbl2 = new JLabel("Get back out there and find a match");
		noMatchlbl2.setFont(new Font("Monospaced", Font.BOLD, 9));
		noMatchlbl2.setForeground(black);
		noMatchlbl2.setBounds(50,175,260,69);
		viewMatchController.getViewMatchesPanel().add(noMatchlbl2);
		*/
		
		viewMatchController.getViewMatchesPanel().add(scrollPane);
	    
	    mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
