package blacklist;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Canvas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;



import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JPasswordField pwdEnterPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(new java.awt.Color(176, 224, 230));
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(45, 145, 90, 40);
		contentPane.add(btnNewButton);
		
		JButton btnCreateNewAccount = new JButton("Create New Account");
		btnCreateNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateNewAccount.setBounds(24, 196, 142, 40);
		contentPane.add(btnCreateNewAccount);
		
		pwdEnterPassword = new JPasswordField();
		pwdEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		pwdEnterPassword.setText("password");
		pwdEnterPassword.setBounds(225, 186, 128, 32);
		contentPane.add(pwdEnterPassword);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img1 = new ImageIcon(this.getClass().getResource("/hearts.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		
		lblNewLabel.setBounds(225, 31, 128, 82);
		contentPane.add(lblNewLabel);
		
		JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField();
		frmtdtxtfldEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldEnterUsername.setText("enter username");
		frmtdtxtfldEnterUsername.setBounds(225, 145, 128, 30);
		contentPane.add(frmtdtxtfldEnterUsername);
		
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblBeMyPlayer.setBounds(24, 44, 204, 69);
		contentPane.add(lblBeMyPlayer);
	}
}
