package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HomePageView {
	
	public static void launchHomePage(HomePageController homeController, JFrame mainFrame) {
		//init model
		homeController.setHomePageModel(new HomePageModel());
		
		//init colors
		Color red = new Color(134,48,111);
		Color yellow = new Color(254, 195, 123);
		
		//init panel
		homeController.setHomePanel(new BackgroundPanel(null));
		homeController.getHomePanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		homeController.getHomePanel().setPreferredSize(new Dimension(500,400));
		homeController.getHomePanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(homeController.getHomePanel());

		//init buttons
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(10, 10, 90, 40);
		btnLogout.setActionCommand(homeController.LOGOUT);
		btnLogout.setBackground(yellow);
		btnLogout.addActionListener(homeController);
		homeController.getHomePageModel().setBtnLogout(btnLogout);
		
		JButton btnAccount = new JButton("My Profile");
		btnAccount.setBounds(390,10,100,40);
		btnAccount.setActionCommand(homeController.ACCOUNT);
		btnAccount.setBackground(yellow);
		btnAccount.addActionListener(homeController);
		homeController.getHomePageModel().setBtnProfile(btnAccount);
		
		JButton btnLove = new JButton("Find Love");
		btnLove.setBounds(30,100,200,140);
		btnLove.setActionCommand(homeController.FIND_LOVE);
		btnLove.setBackground(yellow);
		btnLove.addActionListener(homeController);
		homeController.getHomePageModel().setBtnFindLove(btnLove);
		
		JButton btnFriends = new JButton("Find Friends");
		btnFriends.setBounds(270,100,200,140);
		btnFriends.setActionCommand(homeController.FIND_FRIENDS);
		btnFriends.setBackground(yellow);
		btnFriends.addActionListener(homeController);
		homeController.getHomePageModel().setBtnFindFriends(btnFriends);
		
		JButton btnSupport = new JButton("<HTML><U>Support</U></HTML>");
		btnSupport.setBounds(0,360,90,40);
		btnSupport.setActionCommand(homeController.SUPPORT);
		btnSupport.setOpaque(true);
		btnSupport.setContentAreaFilled(false);
		btnSupport.setBorderPainted(false);
		btnSupport.setForeground(yellow);
		
		btnSupport.addActionListener(homeController);
		homeController.getHomePageModel().setBtnSupport(btnSupport);
		
		JButton btnMatches = new JButton("View Matches");
		btnMatches.setBounds(60,270,360,90);
		btnMatches.setActionCommand(homeController.VIEW_MATCHES);
		btnMatches.setBackground(yellow);
		btnMatches.addActionListener(homeController);
		homeController.getHomePageModel().setBtnViewMatches(btnMatches);
		
		//init Label
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblBeMyPlayer.setForeground(yellow);
		lblBeMyPlayer.setBounds(160,0,204,69);
		homeController.getHomePageModel().setLblBeMyPlayer(lblBeMyPlayer);
		
		
		//add to frame
		homeController.getHomePanel().add(homeController.getHomePageModel().getBtnFindFriends());
		homeController.getHomePanel().add(homeController.getHomePageModel().getBtnFindLove());
		homeController.getHomePanel().add(homeController.getHomePageModel().getBtnLogout());
		homeController.getHomePanel().add(homeController.getHomePageModel().getBtnProfile());
		homeController.getHomePanel().add(homeController.getHomePageModel().getBtnSupport());
		homeController.getHomePanel().add(homeController.getHomePageModel().getBtnViewMatches());
		homeController.getHomePanel().add(homeController.getHomePageModel().getLblBeMyPlayer());
		
		
		//pack and set visible
		mainFrame.pack();
		mainFrame.setVisible(true);
		
	}

}
