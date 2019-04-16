package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ResourceManager;

public class HomePageView {
	
	

	public static void launchHomePage(HomePageController homeController, JFrame mainFrame) {
		//init model
		homeController.setHomePageModel(new HomePageModel());
		
		//init colors
		Color red = new Color(134,48,111);
		Color yellow = Colors.Yellow;
		JLabel heartImage = new JLabel();
		
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
		btnLogout.setFont(Fonts.getFont((float) 12));
		btnLogout.setForeground(Colors.Red);
		btnLogout.addActionListener(homeController);
		homeController.getHomePageModel().setBtnLogout(btnLogout);
		
		JButton btnAccount = new JButton("My Profile");
		btnAccount.setBounds(390,10,100,40);
		btnAccount.setActionCommand(homeController.ACCOUNT);
		btnAccount.setBackground(yellow);
		btnAccount.setFont(Fonts.getFont((float) 12));
		btnAccount.setForeground(Colors.Red);
		btnAccount.addActionListener(homeController);
		homeController.getHomePageModel().setBtnProfile(btnAccount);
		
		JButton btnLove = new JButton("Find Love");
		btnLove.setBounds(30,100,200,140);
		btnLove.setActionCommand(homeController.FIND_LOVE);
		btnLove.setBackground(yellow);
		btnLove.setFont(Fonts.getFont((float) 12));
		btnLove.setForeground(Colors.Red);
		btnLove.addActionListener(homeController);
		homeController.getHomePageModel().setBtnFindLove(btnLove);
		
		JButton btnFriends = new JButton("Find Friends");
		btnFriends.setBounds(270,100,200,140);
		btnFriends.setActionCommand(homeController.FIND_FRIENDS);
		btnFriends.setBackground(yellow);
		btnFriends.setFont(Fonts.getFont((float) 12));
		btnFriends.setForeground(Colors.Red);
		btnFriends.addActionListener(homeController);
		homeController.getHomePageModel().setBtnFindFriends(btnFriends);
		
		JButton btnSupport = new JButton("<HTML><U>Support</U></HTML>");
		btnSupport.setBounds(0,360,90,40);
		btnSupport.setActionCommand(homeController.SUPPORT);
		btnSupport.setOpaque(true);
		btnSupport.setContentAreaFilled(false);
		btnSupport.setBorderPainted(false);
		btnSupport.setForeground(Colors.White);
		btnSupport.setFont(Fonts.getFont((float) 12));
		
		btnSupport.addActionListener(homeController);
		homeController.getHomePageModel().setBtnSupport(btnSupport);
		
		JButton btnMatches = new JButton("View Matches");
		btnMatches.setBounds(60,270,360,90);
		btnMatches.setActionCommand(homeController.VIEW_MATCHES);
		btnMatches.setBackground(yellow);
		btnMatches.setFont(Fonts.getFont((float) 12));
		btnMatches.setForeground(Colors.Red);
		btnMatches.addActionListener(homeController);
		homeController.getHomePageModel().setBtnViewMatches(btnMatches);
		
		//init Label
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(Fonts.getFont((float) 20));
		lblBeMyPlayer.setForeground(Colors.Yellow);
		lblBeMyPlayer.setBounds(145,0,204,69);
		homeController.getHomePageModel().setLblBeMyPlayer(lblBeMyPlayer);
		//mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("filepath"));
		
		BufferedImage img1 = ResourceManager.loadImage("splash_heart.png");
		//img1 = new ImageIcon("C:\\Backup of student files\\Spring 2019\\BeMyPlayer2\\BeMyPlayer2\\BeMyPlayer2\\img\\hearts.png").getImage();
		heartImage.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		heartImage.setBounds(315, 22, 30, 30);
		
		//	website link
		JButton site = new JButton("<HTML><U>BeMyPlayer2 Webpage</U></HTML>");
		site.setActionCommand("launchsite");
		site.addActionListener(homeController);
		site.setBounds(300,360,200,30);
		site.setOpaque(true);
		site.setContentAreaFilled(false);
		site.setBorderPainted(false);
		site.setFont(Fonts.getFont(12f));
		site.setForeground(Colors.White);
		
		//add to frame
		homeController.getHomePanel().add(site);
		homeController.getHomePanel().add(heartImage);
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
