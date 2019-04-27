package graphics;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.Account;
import model.ClientManager;
import model.ResourceManager;

/**
 * The Class ViewMatchesView.
 */
public class ViewMatchesView {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(ViewMatchesView.class.getName());
	
	/** The img 1. */
	static BufferedImage img1 = ResourceManager.loadImage("splash_heart.png");
	
	/** The heart image. */
	static JLabel heartImage = new JLabel();
	
	/**
	 * Start view matches.
	 *
	 * @param viewMatchController the view match controller
	 * @param mainFrame the main frame
	 * @param a the a
	 */
	public static void startViewMatches(ViewMatchesController viewMatchController, JFrame mainFrame,Account a) {
		
		viewMatchController.setViewMatchesModel(new ViewMatchesModel());
		//	init panel
		viewMatchController.setViewMatchesPanel(new BackgroundPanel(null));
		viewMatchController.getViewMatchesPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		viewMatchController.getViewMatchesPanel().setPreferredSize(new Dimension(500,400));
		viewMatchController.getViewMatchesPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(viewMatchController.getViewMatchesPanel());
		
		
		//	init buttons and add to panel
		ImgButton backbtn = new ImgButton("Back");
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
		
		profilePicPanel.setPreferredSize(new Dimension(100,250));
		
		//profilePicPanel.setSize(100, 245);
		JComboBox<String> matchtype = new JComboBox<String>();
		
		matchtype.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		//	generate new match request pull 
        		if(e.getStateChange() == ItemEvent.SELECTED) {
  
        		if(viewMatchController.getViewMatchesModel().getMatchtype().getSelectedItem().toString().equalsIgnoreCase("Love Matches")) {
        			
        			profilePicPanel.removeAll();
        			///	ACTUAL SOLUTION
        			
        			if(ClientManager.getClientModel().getLoveMatches() == null || ClientManager.getClientModel().getLoveMatches().size() == 0) {
        				//profilePicPanel.add(viewMatchController.getViewMatchesModel().getEmptyPanel());
        				JLabel temp1 = viewMatchController.getViewMatchesModel().getNoMatchMessage();
        				temp1.setVisible(true);
        				mainFrame.add(temp1);
        				mainFrame.repaint();
        			}
        			else {
        				JLabel temp1 = viewMatchController.getViewMatchesModel().getNoMatchMessage();
        				temp1.setVisible(false);
        				viewMatchController.getViewMatchesModel().setNoMatchMessage(temp1);
        				mainFrame.add(viewMatchController.getViewMatchesModel().getNoMatchMessage());
        				List<JButton> pics = ProfilePicGenerator.getLoveList(viewMatchController);
        		    	double temp = pics.size();
        		    	boolean addTrash = false;
        		    	if(ClientManager.getClientModel().getLoveMatches().size()  > 4) {
        		    		addTrash = true;
        		    		temp = (Math.ceil(Math.abs(temp/4)));
        		    		profilePicPanel.setPreferredSize(new Dimension(100,(int)(250*temp)));
        		    	}
        		    	else {
        		    		profilePicPanel.setPreferredSize(new Dimension(100,250));
        		    	}
        				for(JButton icon: pics) {
        					profilePicPanel.add(icon);
        				}
        				if(addTrash == true) {
        				while((int)temp%4 != 0) {
        	    			temp++;
        	    			JButton trash =new JButton();
        	    			trash.setEnabled(false);
        	    			trash.setVisible(false);
        	    			profilePicPanel.add(trash);
        	    		}
        				}
        			}
        			profilePicPanel.repaint();
        			profilePicPanel.validate();
        			profilePicPanel.revalidate();
        		}
        		else {
        			profilePicPanel.removeAll();
        			///	ACTUAL SOLUTION
        			
        			if(ClientManager.getClientModel().getFriendMatches() == null ||ClientManager.getClientModel().getFriendMatches().size() == 0) {
        			//	profilePicPanel.add(viewMatchController.getViewMatchesModel().getEmptyPanel());
        				JLabel temp1 = viewMatchController.getViewMatchesModel().getNoMatchMessage();
        				temp1.setVisible(true);
        				mainFrame.add(temp1);
        				mainFrame.repaint();
        			}
        			else {
        				JLabel temp1 = viewMatchController.getViewMatchesModel().getNoMatchMessage();
        				temp1.setVisible(false);
        				viewMatchController.getViewMatchesModel().setNoMatchMessage(temp1);
        				mainFrame.add(viewMatchController.getViewMatchesModel().getNoMatchMessage());
        				mainFrame.repaint();
        				List<JButton> pics = ProfilePicGenerator.getFriendList(viewMatchController);
        		    	double temp = pics.size();
        		    	boolean addTrash = false;
        		    	if(ClientManager.getClientModel().getFriendMatches().size() > 4) {
        		    		addTrash = true;
        		    		temp = (Math.ceil(Math.abs(temp/4)));
        		    		profilePicPanel.setPreferredSize(new Dimension(100,(int)(250*temp)));
        		    	}
        		    	else {
        		    		profilePicPanel.setPreferredSize(new Dimension(100,250));
        		    	}
        				for(JButton icon: pics) {
        					profilePicPanel.add(icon);
        				}
        				if(addTrash == true) {
        				while((int)temp%4 != 0) {
        	    			temp++;
        	    			JButton trash  =new JButton();
        	    			trash.setEnabled(false);
        	    			trash.setVisible(false);
        	    			profilePicPanel.add(trash);
        	    		}}
        			}
        			profilePicPanel.repaint();
        			profilePicPanel.validate();
        			profilePicPanel.revalidate();
        		}
        		
        	}
        	}
        });
		
		matchtype.setToolTipText("Match type");
		matchtype.setModel(new DefaultComboBoxModel<String>(new String[] {"Love Matches", "Friend Matches"}));
		matchtype.setFont(Fonts.getFont(12f));
		matchtype.setForeground(Colors.Red);
		matchtype.setBackground(Colors.Yellow);
		matchtype.setBounds(45, 85, 150, 25);
		matchtype.setVisible(true);
		viewMatchController.getViewMatchesModel().setMatchtype(matchtype);
		viewMatchController.getViewMatchesPanel().add(matchtype);
		
		if(ClientManager.getClientModel().getLoveMatches() == null || ClientManager.getClientModel().getLoveMatches().size() == 0) {
			//profilePicPanel.add(viewMatchController.getViewMatchesModel().getEmptyPanel());
			JLabel temp1 = viewMatchController.getViewMatchesModel().getNoMatchMessage();
			temp1.setVisible(true);
			mainFrame.add(temp1);
			mainFrame.repaint();
	    }
	    else {
	    	List<JButton> pics = ProfilePicGenerator.getLoveList(viewMatchController);
	    	double temp = pics.size();
	    	
	    	if(ClientManager.getClientModel().getLoveMatches().size() > 4) {
	    		temp = (Math.ceil(Math.abs(temp/4)));
	    		profilePicPanel.setPreferredSize(new Dimension(100,(int) (250*temp)));
	    	}
	    	else {
	    		profilePicPanel.setPreferredSize(new Dimension(100,250));
	    	}
			for(JButton icon: pics) {
				profilePicPanel.add(icon);
			}
			while((int)temp%4 != 0) {
    			temp++;
    			JButton trash  =new JButton();
    			trash.setEnabled(false);
    			trash.setVisible(false);
    			profilePicPanel.add(trash);
    		}
	    }
		
		//	SCROLLPANE
	    JScrollPane scrollPane = new JScrollPane(profilePicPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    scrollPane.setBounds(new Rectangle(25, 120, 215, 245));
	    
		viewMatchController.getViewMatchesPanel().add(scrollPane);
	    
	    mainFrame.pack();
		mainFrame.setVisible(true);
	}
}

