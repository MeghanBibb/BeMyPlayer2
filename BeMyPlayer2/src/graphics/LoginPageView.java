package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginPageView {
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void startLoginPage(LoginPageController loginController,JFrame mainFrame) {
		//	init table model 
		loginController.setLoginPageModel(new LoginPageModel());
		
		//	init colors
		Color red = new Color(134,48,111);
		Color yellow = new Color(254, 195, 123);
		
		
		//	init panel 
		loginController.setLoginPanel(new JPanel(null));
		loginController.getLoginPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		loginController.getLoginPanel().setPreferredSize(new Dimension(500,400));
		loginController.getLoginPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(loginController.getLoginPanel());
		mainFrame.getContentPane().setBackground(red);
		
		//	init buttons
		loginController.getLoginPageModel().setBtnLogin(new JButton("Login")); 
		loginController.getLoginPageModel().getBtnLogin().setBounds(65, 145, 90, 40);
		loginController.getLoginPageModel().getBtnLogin().setBackground(yellow);
		loginController.getLoginPageModel().getBtnLogin().setActionCommand(LoginPageController.LOGIN);
		loginController.getLoginPageModel().getBtnLogin().addActionListener(loginController);
		
		loginController.getLoginPageModel().setBtnCreateNewAccount(new JButton("Create New Account"));
		loginController.getLoginPageModel().getBtnCreateNewAccount().setBackground(yellow);
		loginController.getLoginPageModel().getBtnCreateNewAccount().setBounds(24, 196, 175, 40);
		loginController.getLoginPageModel().getBtnCreateNewAccount().setActionCommand(LoginPageController.CREATE_ACCOUNT);
		loginController.getLoginPageModel().getBtnCreateNewAccount().addActionListener(loginController);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.setBackground(yellow);
		btnForgotPassword.setBounds(24, 245, 175, 40);
		btnForgotPassword.setActionCommand(LoginPageController.FORGOT_PASSWORD);
		btnForgotPassword.addActionListener(loginController);
		loginController.getLoginPageModel().setBtnForgotPassword(btnForgotPassword);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(yellow);
		btnExit.setBounds(275,350,175,40);
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
		loginController.getLoginPageModel().getPwdEnterPass().setBounds(300, 186, 128, 32);
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getPwdEnterPass());
		
		JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField();
		frmtdtxtfldEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldEnterUsername.setBounds(300, 145, 128, 30);
		loginController.getLoginPageModel().setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getFrmtdtxtfldEnterUsername());
		
		//	set images
	
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img1;// = new ImageIcon(loginController.getClass().getResource("/hearts.png")).getImage();
		img1 = new ImageIcon("C:\\Backup of student files\\Spring 2019\\BeMyPlayer2\\BeMyPlayer2\\BeMyPlayer2\\img\\hearts.png").getImage();
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		lblNewLabel.setBounds(275, 31, 128, 82);
		loginController.getLoginPageModel().setLblNewLabel(lblNewLabel);
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getLblNewLabel());
		
		//	set text
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setForeground(yellow);
		lblBeMyPlayer.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblBeMyPlayer.setBounds(24, 44, 204, 69);
		loginController.getLoginPageModel().setLblBeMyPlayer(lblBeMyPlayer);
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getLblBeMyPlayer());
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(230, 120, 128, 82);
		usernameLabel.setForeground(yellow);
		loginController.getLoginPageModel().setLblUsername(usernameLabel);
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getLblUsername());
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(230,160,128,82);
		passwordLabel.setForeground(yellow);
		loginController.getLoginPageModel().setLblPassword(passwordLabel);
		loginController.getLoginPanel().add(loginController.getLoginPageModel().getLblPassword());
		
		//	set attributes, pack and set visible 
		mainFrame.pack();
		mainFrame.setVisible(true);
		//set attributes in loginController:
		
	}
}
