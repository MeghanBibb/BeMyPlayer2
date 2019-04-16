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
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;

import firebase.DBFailureException;
import model.Account;
import model.InformationExpert;
import model.Profile;
import model.ResourceManager;

public class ViewMatchesView {
	private static Logger logger = Logger.getLogger(ViewMatchesView.class.getName());
	static BufferedImage img1 = ResourceManager.loadImage("splash_heart.png");
	static JLabel heartImage = new JLabel();
	
	public static void startViewMatches(ViewMatchesController viewMatchController, JFrame mainFrame,Account a) {
		
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
		backbtn.setBackground(Colors.Yellow);
		backbtn.setForeground(Colors.Red);
		backbtn.setFont(Fonts.getFont((float) 12));
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(viewMatchController);
		viewMatchController.getViewMatchesPanel().add(backbtn);
		
		//	label
		
		heartImage.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		heartImage.setBounds(400, 25, 50, 50);

		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(Fonts.getFont((float) 30));
		lblBeMyPlayer.setForeground(Colors.Yellow);
		lblBeMyPlayer.setBounds(150,10,300,69);
		viewMatchController.getViewMatchesPanel().add(lblBeMyPlayer);
		viewMatchController.getViewMatchesPanel().add(heartImage);
		
		//	pull from match adapter giving the temp account
		GridLayout gridLayout = new GridLayout(0,2,5,5);
		JPanel profilePicPanel = new JPanel();
		profilePicPanel.setBackground(Colors.Yellow);
		profilePicPanel.setLayout(gridLayout);
		profilePicPanel.setPreferredSize(new Dimension(100,245));
		profilePicPanel.setSize(100, 245);
		
		JComboBox matchtype = new JComboBox();
		
		matchtype.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		//	generate new match request pull 
        		if(viewMatchController.getViewMatchesModel().getMatchtype().getSelectedItem().toString().equalsIgnoreCase("Love Matches")) {
        			
        			profilePicPanel.removeAll();
        			///	ACTUAL SOLUTION
        			if(InformationExpert.getClientModel().getLoveMatches() == null || InformationExpert.getClientModel().getLoveMatches().size() == 0) {
        				if(viewMatchController.getViewMatchesModel().getEmptylistLine2().isVisible() == false || viewMatchController.getViewMatchesModel().getEmptylistLine1().isVisible() == false) {
        					viewMatchController.getViewMatchesModel().getEmptylistLine1().setVisible(true);
            				viewMatchController.getViewMatchesModel().getEmptylistLine2().setVisible(true);
        				}
        				
        			}
        			else {
        				List<JButton> pics = ProfilePicGenerator.getLoveList(viewMatchController);
        				viewMatchController.getViewMatchesModel().getEmptylistLine1().setVisible(false);
        				viewMatchController.getViewMatchesModel().getEmptylistLine2().setVisible(false);
            			for(JButton icon: pics) {
            				profilePicPanel.add(icon);
            			}
            			
        			}
        			profilePicPanel.repaint();
        			profilePicPanel.validate();
        			profilePicPanel.revalidate();
        		}
        		else {
        			profilePicPanel.removeAll();
        			///	ACTUAL SOLUTION
        			if(InformationExpert.getClientModel().getFriendMatches() == null ||InformationExpert.getClientModel().getFriendMatches().size() == 0) {
        				if(viewMatchController.getViewMatchesModel().getEmptylistLine2().isVisible() == false || viewMatchController.getViewMatchesModel().getEmptylistLine1().isVisible() == false) {
        					viewMatchController.getViewMatchesModel().getEmptylistLine1().setVisible(true);
            				viewMatchController.getViewMatchesModel().getEmptylistLine2().setVisible(true);
        				}
        			}
        			else {
        				List<JButton> pics = ProfilePicGenerator.getFriendList(viewMatchController);
        				viewMatchController.getViewMatchesModel().getEmptylistLine1().setVisible(false);
        				viewMatchController.getViewMatchesModel().getEmptylistLine2().setVisible(false);
            			for(JButton icon: pics) {
            				profilePicPanel.add(icon);
            			}
        			}
        			profilePicPanel.repaint();
        			profilePicPanel.validate();
        			profilePicPanel.revalidate();
        		}
        		viewMatchController.getViewMatchesPanel().add(viewMatchController.getViewMatchesModel().getEmptylistLine1());
        		viewMatchController.getViewMatchesPanel().add(viewMatchController.getViewMatchesModel().getEmptylistLine2());
        	}
        });
		
		matchtype.setToolTipText("Match type");
		matchtype.setModel(new DefaultComboBoxModel(new String[] {"Love Matches", "Friend Matches"}));
		matchtype.setFont(Fonts.getFont(12f));
		matchtype.setForeground(Colors.Red);
		matchtype.setBackground(Colors.Yellow);
		matchtype.setBounds(45, 85, 150, 25);
		matchtype.setVisible(true);
		viewMatchController.getViewMatchesModel().setMatchtype(matchtype);
		viewMatchController.getViewMatchesPanel().add(matchtype);
		
		//	send type request to match adapter to pull list of matches for account
		
		////	real implementation
		/// 	REMOVE WHEN MATCHING IS DONE///////////////////////
		
		
		//////////////////////////////////////////////////////////
//		if empty
	    if(InformationExpert.getClientModel().getLoveMatches() == null || InformationExpert.getClientModel().getLoveMatches().size() == 0) {
	    	JLabel noMatchlbl1 = new JLabel("Your princess is in another castle");
		    noMatchlbl1.setFont(Fonts.getFont(9f));
		    noMatchlbl1.setForeground(Colors.Red);
		    noMatchlbl1.setBounds(40,160,260,69);
		 
		    viewMatchController.getViewMatchesPanel().add(noMatchlbl1);
			
			JLabel noMatchlbl2 = new JLabel("Get back out there and find a match");
			noMatchlbl2.setFont(Fonts.getFont(9f));
			noMatchlbl2.setForeground(Colors.Red);
			noMatchlbl2.setBounds(40,175,260,69);
			viewMatchController.getViewMatchesPanel().add(noMatchlbl2);
			
			viewMatchController.getViewMatchesModel().setEmptylistLine1(noMatchlbl1);
			viewMatchController.getViewMatchesModel().setEmptylistLine2(noMatchlbl2);
	    }
	    else {
	    	List<JButton> pics = ProfilePicGenerator.getLoveList(viewMatchController);
			for(JButton icon: pics) {
				profilePicPanel.add(icon);
			}
	    }

		//	SCROLLPANE
	    JScrollPane scrollPane = new JScrollPane(profilePicPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	    scrollPane.setBounds(new Rectangle(25, 120, 215, 245));
	    Color black = new Color(0,0,0);
	    
	    
		viewMatchController.getViewMatchesPanel().add(scrollPane);
	    
	    mainFrame.pack();
		mainFrame.setVisible(true);
	}
}

