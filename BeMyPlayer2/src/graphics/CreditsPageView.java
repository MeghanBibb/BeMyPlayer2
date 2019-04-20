package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.ResourceManager;

// TODO: Auto-generated Javadoc
/**
 * The Class CreditsPageView.
 */
public class CreditsPageView {

	/**
	 * Launch credits page.
	 *
	 * @param creditscontrol the creditscontrol
	 * @param mainFrame the main frame
	 */
	public static void launchPage(CreditsPageController creditscontrol, JFrame mainFrame) {
		// TODO Auto-generated method stub
		
		//init colors
		JLabel heartImage = new JLabel();
		
		//init panel
		creditscontrol.setCreditsPanel(new BackgroundPanel(null));
		creditscontrol.getCreditsPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		creditscontrol.getCreditsPanel().setPreferredSize(new Dimension(500,400));
		creditscontrol.getCreditsPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(creditscontrol.getCreditsPanel());
				
		//init Label
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(Fonts.getFont((float) 20));
		lblBeMyPlayer.setForeground(Colors.Yellow);
		lblBeMyPlayer.setBounds(145,0,204,69);
		creditscontrol.getCreditsPanel().add(lblBeMyPlayer);
		
		JLabel blurb = new JLabel("Brought to with love you by...");
		blurb.setFont(Fonts.getFont(16f));
		blurb.setForeground(Colors.Yellow);
		blurb.setBounds(25,50,250,69);
		creditscontrol.getCreditsPanel().add(blurb);
		
		BufferedImage img1 = ResourceManager.loadImage("splash_heart.png");
		//img1 = new ImageIcon("C:\\Backup of student files\\Spring 2019\\BeMyPlayer2\\BeMyPlayer2\\BeMyPlayer2\\img\\hearts.png").getImage();
		heartImage.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		heartImage.setBounds(315, 22, 30, 30);
		creditscontrol.getCreditsPanel().add(heartImage);
		
		
		//init buttons
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 10, 90, 40);
		btnBack.setActionCommand(CreditsPageController.BACK);
		btnBack.setBackground(Colors.Yellow);
		btnBack.setFont(Fonts.getFont((float) 12));
		btnBack.setForeground(Colors.Red);
		btnBack.addActionListener(creditscontrol);
		creditscontrol.getCreditsPanel().add(btnBack);
		
		GridLayout gridLayout = new GridLayout(0,2,5,5);
		JPanel profilePicPanel = new JPanel();
		profilePicPanel.setBackground(Colors.Yellow);
		profilePicPanel.setLayout(gridLayout);
		profilePicPanel.setPreferredSize(new Dimension(100,245));
		profilePicPanel.setSize(100, 245);
		
		List<JButton> pics = ProfilePicGenerator.getCreatorsList(creditscontrol);
		for(JButton icon: pics) {
			profilePicPanel.add(icon);
		}
		
//		SCROLLPANE
	    JScrollPane scrollPane = new JScrollPane(profilePicPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setBounds(new Rectangle(25, 120, 215, 245));
	    creditscontrol.getCreditsPanel().add(scrollPane);
		// creators
		
		// picture
		// name 
		// responsibilities:
		
		//pack and make visible
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}
