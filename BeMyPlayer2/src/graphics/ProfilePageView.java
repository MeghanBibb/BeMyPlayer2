package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Account;

public class ProfilePageView {

	public static void startProfilePage(ProfilePageController profileController, JFrame mainFrame) {
		//init Model
		profileController.setProfileModel(new ProfilePageModel());
		
		//init colors
		Color red = new Color(134,48,111);
		Color yellow = new Color(254, 195, 123);
		
		//init panel
		profileController.setProfilePanel(new BackgroundPanel(null));
		profileController.getProfilePanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		profileController.getProfilePanel().setPreferredSize(new Dimension(500,400));
		profileController.getProfilePanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(profileController.getProfilePanel());
		mainFrame.getContentPane().setBackground(red);
		
		//init image (ideally load this from user)
		
		/**
		 * TO DO: load image from user/database
		 */
		JLabel imgLabel = new JLabel("");
		Image img = new ImageIcon(profileController.getClass().getResource("/defaultIcon.png")).getImage();
		imgLabel.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		imgLabel.setBounds(10, 60, 100, 100);
		profileController.getProfileModel().setProfileImage(imgLabel);
		
		
		//init buttons
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10,10,90,40);
		btnBack.setActionCommand(profileController.BACK);
		btnBack.setBackground(yellow);
		btnBack.addActionListener(profileController);
		profileController.getProfileModel().setBtnBack(btnBack);
		
		//	verify a == Information Expert's active account
		/*
		 * if(a.equals(InformationExpert.account)){
		 * 		//	add edit profile options
		 * }
		 * else{
		 * 		//	add message and block buttons 
		 * }
		 */
		
		//	pull information for fields from account passed 
		if(profileController.isActiveAccount()) {
			JButton btnEdit = new JButton("Edit Profile");
			btnEdit.setBounds(390,10,100,40);
			btnEdit.setActionCommand(profileController.EDIT_ACCOUNT);
			btnEdit.setBackground(yellow);
			btnEdit.addActionListener(profileController);
			profileController.getProfileModel().setBtnEdit(btnEdit);
			profileController.getProfilePanel().add(profileController.getProfileModel().getBtnEdit());
		} else {
			JButton btnBlock = new JButton("Block");
			btnBlock.setBounds(300, 350, 90, 40);
			btnBlock.setActionCommand(profileController.BLOCK);
			btnBlock.setBackground(yellow);
			btnBlock.addActionListener(profileController);
			profileController.getProfileModel().setBtnBlock(btnBlock);
			profileController.getProfilePanel().add(profileController.getProfileModel().getBtnBlock());
			
			JButton btnMessage = new JButton("Message");
			btnMessage.setBounds(100,350,90,40);
			btnMessage.setActionCommand(profileController.MESSAGE);
			btnMessage.setBackground(yellow);
			btnMessage.addActionListener(profileController);
			profileController.getProfileModel().setBtnMessage(btnMessage);
			profileController.getProfilePanel().add(profileController.getProfileModel().getBtnMessage());
		}
		
		//init Labels
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblBeMyPlayer.setForeground(yellow);
		lblBeMyPlayer.setBounds(160,0,204,69);
		profileController.getProfileModel().setLblBeMyPlayer(lblBeMyPlayer);
		
		JLabel lblUsername = new JLabel();
		lblUsername.setText(profileController.getAccount().getAccountProfile().getUsername());
		lblUsername.setFont(new Font("Impact", Font.PLAIN,15));
		lblUsername.setForeground(yellow);
		lblUsername.setBounds(120,35,90,90);
		profileController.getProfileModel().setLblUsername(lblUsername);
		

		/*Age stuff*/
		JLabel lblAge = new JLabel();
		LocalDate now = LocalDate.now();
		Date nowDate = java.sql.Date.valueOf(now);
		Calendar cnow = Calendar.getInstance();
		cnow.setTime(nowDate);
		Date bday = profileController.getAccount().getAccountProfile().getDateOB();
		Calendar cbday = Calendar.getInstance();
		cbday.setTime(bday);
		int diff = cnow.get(Calendar.YEAR) - cbday.get(Calendar.YEAR);
		if(cnow.get(Calendar.MONTH) == cbday.get(Calendar.MONTH) && cnow.get(Calendar.DATE) > cbday.get(Calendar.DATE) ) {
			diff--;
		}
		lblAge.setText(Integer.toString(diff) + " years old");
		lblAge.setForeground(yellow);
		lblAge.setBounds(120,65,150,90);
		profileController.getProfileModel().setLblAge(lblAge);
		
		JLabel lblGender = new JLabel();
		lblGender.setText(profileController.getAccount().getAccountProfile().getGender());
		lblGender.setForeground(yellow);
		lblGender.setBounds(120,95,90,90);
		profileController.getProfileModel().setLblGender(lblGender);
		
		JLabel lblConsoles = new JLabel("Consoles:");
		lblConsoles.setForeground(yellow);
		lblConsoles.setBounds(370,30,90,90);
		profileController.getProfileModel().setLblConsoles(lblConsoles);
		
		//init description
		JLabel description = new JLabel();
		//description.setText("<HTML>This is a sample description.<br> Very nice.</HTML>");
		description.setText(profileController.getAccount().getAccountProfile().getDescription());
		description.setBounds(10, 170, 250, 150);
		description.setOpaque(false);
		description.setForeground(yellow);
		description.setHorizontalAlignment(JTextField.LEFT);
		description.setVerticalAlignment(JTextField.NORTH);
		description.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		profileController.getProfileModel().setTxtField(description);
		
		//checkbox
		profileController.getProfileModel().setCheckList(new ArrayList<JCheckBox>());
		JCheckBox xboxBtn = new JCheckBox("Xbox");
		if(profileController.getAccount().getAccountProfile().getPlatforms().get(0)) {
			xboxBtn.setSelected(true);
		}
		xboxBtn.setEnabled(false);
		xboxBtn.setBackground(red);
		xboxBtn.setForeground(yellow);
		xboxBtn.setFont(new Font("Monospace",Font.BOLD,14));
		xboxBtn.setBounds(370, 100, 75, 25);
		profileController.getProfileModel().getCheckList().add(xboxBtn);
		profileController.getProfilePanel().add(xboxBtn);
		

		JCheckBox psBtn = new JCheckBox("Playstation");
		if(profileController.getAccount().getAccountProfile().getPlatforms().get(1)) {
			psBtn.setSelected(true);
		}
		psBtn.setEnabled(false);
		psBtn.setBackground(red);
		psBtn.setForeground(yellow);
		psBtn.setFont(new Font("Monospace",Font.BOLD,14));
		psBtn.setBounds(370, 125, 120, 25);
		profileController.getProfileModel().getCheckList().add(psBtn);
		profileController.getProfilePanel().add(psBtn);
		
		
		JCheckBox nintendoBtn = new JCheckBox("Nintendo");if(profileController.getAccount().getAccountProfile().getPlatforms().get(2)) {
			nintendoBtn.setSelected(true);
		}
		nintendoBtn.setEnabled(false);
		nintendoBtn.setBackground(red);
		nintendoBtn.setForeground(yellow);
		nintendoBtn.setFont(new Font("Monospace",Font.BOLD,14));
		nintendoBtn.setBounds(370, 150, 120, 25);
		profileController.getProfileModel().getCheckList().add(nintendoBtn);
		profileController.getProfilePanel().add(nintendoBtn);
		
		
		JCheckBox pcBtn = new JCheckBox("PC");
		if(profileController.getAccount().getAccountProfile().getPlatforms().get(3)) {
			pcBtn.setSelected(true);
		}
		pcBtn.setEnabled(false);
		pcBtn.setBackground(red);
		pcBtn.setForeground(yellow);
		pcBtn.setFont(new Font("Monospace",Font.BOLD,14));
		pcBtn.setBounds(370, 175, 75, 25);
		
		profileController.getProfileModel().getCheckList().add(pcBtn);
		profileController.getProfilePanel().add(pcBtn);
		
		
		JCheckBox vrBtn = new JCheckBox("VR");
		if(profileController.getAccount().getAccountProfile().getPlatforms().get(4)) {
			vrBtn.setSelected(true);
		}
		vrBtn.setEnabled(false);
		vrBtn.setBackground(red);
		vrBtn.setForeground(yellow);
		vrBtn.setFont(new Font("Monospace",Font.BOLD,14));
		vrBtn.setBounds(370, 200, 75, 25);
		profileController.getProfileModel().getCheckList().add(vrBtn);
		profileController.getProfilePanel().add(vrBtn);
		
		
		JCheckBox RetroBtn = new JCheckBox("Retro");if(profileController.getAccount().getAccountProfile().getPlatforms().get(5)) {
			RetroBtn.setSelected(true);
		}
		RetroBtn.setEnabled(false);
		RetroBtn.setBackground(red);
		RetroBtn.setForeground(yellow);
		RetroBtn.setFont(new Font("Monospace",Font.BOLD,14));
		RetroBtn.setBounds(370, 225, 75, 25);
		profileController.getProfileModel().getCheckList().add(RetroBtn);
		profileController.getProfilePanel().add(RetroBtn);

		
		//add to panel
		profileController.getProfilePanel().add(profileController.getProfileModel().getBtnBack());
		profileController.getProfilePanel().add(profileController.getProfileModel().getLblBeMyPlayer());
		profileController.getProfilePanel().add(profileController.getProfileModel().getLblUsername());
		profileController.getProfilePanel().add(profileController.getProfileModel().getLblAge());
		profileController.getProfilePanel().add(profileController.getProfileModel().getLblGender());
		profileController.getProfilePanel().add(profileController.getProfileModel().getLblConsoles());
		profileController.getProfilePanel().add(profileController.getProfileModel().getProfileImage());
		profileController.getProfilePanel().add(profileController.getProfileModel().getTxtField());
		
		
		//pack and set visible
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
