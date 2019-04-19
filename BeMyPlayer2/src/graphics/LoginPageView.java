package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.ResourceManager;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginPageView.
 */
public class LoginPageView {
	
	/**
	 * Start login page.
	 *
	 * @param loginController the login controller
	 * @param mainFrame the main frame
	 * @wbp.parser.entryPoint 
	 */
	public static void startLoginPage(LoginPageController loginController,JFrame mainFrame) {
		//	init table model 
		loginController.setLoginPageModel(new LoginPageModel());
		
		//	init panel 
		loginController.setLoginPanel(new BackgroundPanel(null));
		loginController.getLoginPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		loginController.getLoginPanel().setPreferredSize(new Dimension(500,400));
		loginController.getLoginPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(loginController.getLoginPanel());
		//mainFrame.getContentPane().setBackground(Colors.Red);
		
		//	init buttons
		loginController.getLoginPageModel().setBtnLogin(new JButton("Begin My Quest")); 
		loginController.getLoginPageModel().getBtnLogin().setBounds(39, 165,  175, 40);
		loginController.getLoginPageModel().getBtnLogin().setBackground(Colors.Yellow);
		loginController.getLoginPageModel().getBtnLogin().setFont(Fonts.getFont((float) 12));
		loginController.getLoginPageModel().getBtnLogin().setForeground(Colors.Red);
		loginController.getLoginPageModel().getBtnLogin().setActionCommand(LoginPageController.LOGIN);
		loginController.getLoginPageModel().getBtnLogin().addActionListener(loginController);
		
		loginController.getLoginPageModel().setBtnCreateNewAccount(new JButton("Create New Account"));
		loginController.getLoginPageModel().getBtnCreateNewAccount().setBackground(Colors.Yellow);
		loginController.getLoginPageModel().getBtnCreateNewAccount().setFont(Fonts.getFont((float) 12));
		loginController.getLoginPageModel().getBtnCreateNewAccount().setForeground(Colors.Red);
		loginController.getLoginPageModel().getBtnCreateNewAccount().setBounds(39, 216, 175, 40);
		loginController.getLoginPageModel().getBtnCreateNewAccount().setActionCommand(LoginPageController.CREATE_ACCOUNT);
		loginController.getLoginPageModel().getBtnCreateNewAccount().addActionListener(loginController);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.setFont(Fonts.getFont((float) 12));
		btnForgotPassword.setForeground(Colors.Red);
		btnForgotPassword.setBackground(Colors.Yellow);
		btnForgotPassword.setBounds(39, 265, 175, 40);
		btnForgotPassword.setActionCommand(LoginPageController.FORGOT_PASSWORD);
		btnForgotPassword.addActionListener(loginController);
		loginController.getLoginPageModel().setBtnForgotPassword(btnForgotPassword);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(Fonts.getFont((float) 12));
		btnExit.setForeground(Colors.Red);
		btnExit.setBackground(Colors.Yellow);
		btnExit.setBounds(410,350,70,40);
		btnExit.setActionCommand(LoginPageController.EXIT);
		btnExit.addActionListener(loginController);
		loginController.getLoginPageModel().setBtnExit(btnExit);
		
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getBtnForgotPassword());
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getBtnLogin());
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getBtnCreateNewAccount());
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getBtnExit());
		
		//	init fields and listeners 
		loginController.getLoginPageModel().setPwdEnterPass(new JPasswordField());
		loginController.getLoginPageModel().getPwdEnterPass().setHorizontalAlignment(SwingConstants.CENTER);
		loginController.getLoginPageModel().getPwdEnterPass().setBackground(Colors.Yellow);
		loginController.getLoginPageModel().getPwdEnterPass().setForeground(Colors.Red);
		loginController.getLoginPageModel().getPwdEnterPass().setBounds(315, 206, 128, 32);

		loginController.getLoginPanel().add(loginController.getLoginPageModel().getPwdEnterPass());
		
		JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField();
		frmtdtxtfldEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldEnterUsername.setBounds(315, 165, 128, 30);

		loginController.getLoginPageModel().setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
		loginController.getLoginPageModel().getFrmtdtxtfldEnterUsername().setBackground(Colors.Yellow);
		loginController.getLoginPageModel().getFrmtdtxtfldEnterUsername().setForeground(Colors.Red);
		loginController.getLoginPageModel().getFrmtdtxtfldEnterUsername().setFont(Fonts.getFont((float) 12));
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getFrmtdtxtfldEnterUsername());
		
		//	set images
	
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		BufferedImage img1 = ResourceManager.loadImage("splash_heart.png");
		//img1 = new ImageIcon("C:\\Backup of student files\\Spring 2019\\BeMyPlayer2\\BeMyPlayer2\\BeMyPlayer2\\img\\hearts.png").getImage();
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
		lblNewLabel.setBounds(320, 64, 100, 56);
		loginController.getLoginPageModel().setLblNewLabel(lblNewLabel);
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getLblNewLabel());
		
		//	set text
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(Fonts.getFont((float) 30));
		lblBeMyPlayer.setForeground(Colors.Yellow);
		lblBeMyPlayer.setBounds(82,56,300,69);
		//lblBeMyPlayer.setBounds(24, 44, 204, 69);
		loginController.getLoginPageModel().setLblBeMyPlayer(lblBeMyPlayer);
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getLblBeMyPlayer());
		
		JLabel usernameLabel = new JLabel("Email:");
		usernameLabel.setBounds(276, 140, 128, 82);
		usernameLabel.setForeground(Colors.Yellow);
		usernameLabel.setFont(Fonts.getFont((float) 12));
		loginController.getLoginPageModel().setLblUsername(usernameLabel);
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getLblUsername());
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(245,180,128,82);
		passwordLabel.setForeground(Colors.Yellow);
		passwordLabel.setFont(Fonts.getFont((float) 12));
		loginController.getLoginPageModel().setLblPassword(passwordLabel);
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getLblPassword());
		
		
		
		//	set attributes, pack and set visible 
		mainFrame.pack();
		mainFrame.setVisible(true);
		//set attributes in loginController:
		
	}
}
