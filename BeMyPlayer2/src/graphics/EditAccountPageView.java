package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import firebase.DBFailureException;
import firebase.ImageConverter;
import model.InformationExpert;
import model.PaymentInfo;
import model.ResourceManager;

// TODO: Auto-generated Javadoc
/**
 * The Class EditAccountPageView.
 */
public class EditAccountPageView {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(EditAccountPageView.class.getName());
	/**
	 * Launch edit page.
	 *
	 * @param editController the edit controller
	 * @param mainFrame the main frame
	 */
	public static void launchEditPage(EditAccountPageController editController, JFrame mainFrame) {
		//set model
		editController.setEditAccountModel(new EditAccountPageModel());
		//init colors
		//	init panel
		editController.setEditAccountPanel(new BackgroundPanel(null));
		editController.getEditAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		editController.getEditAccountPanel().setPreferredSize(new Dimension(500,400));
		editController.getEditAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(editController.getEditAccountPanel());
		
		//init bemyplayer2 label
		JLabel heartImage = new JLabel();
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(Fonts.getFont((float) 20));
		lblBeMyPlayer.setForeground(Colors.Yellow);
		lblBeMyPlayer.setBounds(145,0,204,69);

		
		BufferedImage img1 = ResourceManager.loadImage("splash_heart.png");
		heartImage .setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		heartImage.setBounds(315, 22, 30, 30);
		editController.getEditAccountPanel().add(heartImage);
		editController.getEditAccountModel().setLblBeMyPlayer(lblBeMyPlayer);
		
		//init Buttons
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10,10,90,40);
		btnBack.setBackground(Colors.Yellow);
		btnBack.setFont(Fonts.getFont((float)12));
		btnBack.setForeground(Colors.Red);
		btnBack.setActionCommand(editController.BACK);
		btnBack.addActionListener(editController);
		editController.getEditAccountModel().setBtnBack(btnBack);
		
		JButton btnProfile = new JButton("Edit Profile Details");
		btnProfile.setBounds(115,70,250,40);
		btnProfile.setBackground(Colors.Yellow);
		btnProfile.setFont(Fonts.getFont((float)12));
		btnProfile.setForeground(Colors.Red);
		btnProfile.setActionCommand(editController.PROFILE);
		btnProfile.addActionListener(editController);
		editController.getEditAccountModel().setBtnProfile(btnProfile);
		
		JButton btnQuestionnaire = new JButton("Edit Gaming Details");
		btnQuestionnaire.addActionListener(editController);
		btnQuestionnaire.setBounds(115,120,250,40);
		btnQuestionnaire.setBackground(Colors.Yellow);
		btnQuestionnaire.setFont(Fonts.getFont((float)12));
		btnQuestionnaire.setForeground(Colors.Red);
		btnQuestionnaire.setActionCommand(editController.QUESTIONNAIRE);
		editController.getEditAccountModel().setBtnQuestionnaire(btnQuestionnaire);
		
		JButton btnAccount = new JButton("Edit Account Details");
		btnAccount.setBounds(115,170,250,40);
		btnAccount.setBackground(Colors.Yellow);
		btnAccount.setFont(Fonts.getFont((float)12));
		btnAccount.setForeground(Colors.Red);
		btnAccount.setActionCommand(editController.ACCOUNT);
		btnAccount.addActionListener(editController);
		editController.getEditAccountModel().setBtnAccount(btnAccount);
		
		JButton btnUpgrade = new JButton("Upgrade Account!");
		btnUpgrade.setActionCommand(editController.UPGRADE);
		btnUpgrade.setBounds(115,220,250,40);
		btnUpgrade.setBackground(Colors.Yellow);
		btnUpgrade.setFont(Fonts.getFont((float)12));
		btnUpgrade.setForeground(Colors.Red);
		btnUpgrade.addActionListener(editController);
		editController.getEditAccountModel().setBtnUpgrade(btnUpgrade);
		
		PaymentInfo p;
		try {
			p = InformationExpert.getPaymentInfo(InformationExpert.getActiveUserID());

			if(p != null) {	
				btnUpgrade.setText("Cancel Payment Plan");
				btnUpgrade.setActionCommand(editController.END_PAYMENT);
			}
		} catch (DBFailureException e) {
			//database error, go with default
		}
		
		
		JButton btnMute = new JButton ("Mute Account");
		btnMute.setBounds(115,270,250,40);
		btnMute.setBackground(Colors.Yellow);
		btnMute.setFont(Fonts.getFont((float) 12));
		btnMute.setForeground(Colors.Red);
		btnMute.setActionCommand(editController.MUTE);
		btnMute.addActionListener(editController);
		editController.getEditAccountModel().setBtnMute(btnMute);
		
		JButton btnDelete = new JButton("Delete Account");
		btnDelete.setBounds(115,320,250,40);
		btnDelete.setBackground(Colors.Yellow);
		btnDelete.setForeground(Colors.Red);
		btnDelete.setActionCommand(editController.DELETE);
		btnDelete.addActionListener(editController);
		btnDelete.setFont(Fonts.getFont((float) 12));
		editController.getEditAccountModel().setBtnDelete(btnDelete);
		
		//add to panel
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getBtnBack());
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getBtnProfile());
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getBtnQuestionnaire());
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getBtnAccount());
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getBtnUpgrade());
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getLblBeMyPlayer());
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getBtnDelete());
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getBtnMute());
		
		//pack and make visible
		mainFrame.pack();
		mainFrame.setVisible(true);
		
	}
	
	/**
	 * Launch edit account page.
	 *
	 * @param editController the edit controller
	 * @param mainFrame the main frame
	 */
	public static void launchEditAccountPage(final EditAccountPageController editController, JFrame mainFrame) {
	
		//get mdoel
		editController.setEditAccountModel(new EditAccountPageModel());

		//init colors
		//	init panel
		editController.setEditAccountPanel(new BackgroundPanel(null));
		editController.getEditAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		editController.getEditAccountPanel().setPreferredSize(new Dimension(500,400));
		editController.getEditAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(editController.getEditAccountPanel());
		
		
		//	init buttons and add to panel
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(45,345,90,40);
		btnCancel.setBackground(Colors.Yellow);
		btnCancel.setFont(Fonts.getFont((float)12));
		btnCancel.setForeground(Colors.Red);
		btnCancel.setActionCommand(editController.CANCEL);
		btnCancel.addActionListener(editController);
		editController.getEditAccountModel().setBtnCancel(btnCancel);
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getBtnCancel());
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(345,345,90,40);
		btnSubmit.setBackground(Colors.Yellow);
		btnSubmit.setFont(Fonts.getFont((float)12));
		btnSubmit.setForeground(Colors.Red);
		btnSubmit.setBackground(Colors.Yellow);
		btnSubmit.setActionCommand(editController.SUBMITEDITACCOUNT);
		btnSubmit.addActionListener(editController);
		editController.getEditAccountModel().setBtnSubmit(btnSubmit);
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getBtnSubmit());
		
		
		//	init fields and listeners 
		JPasswordField pwdEnterPass = new JPasswordField();
		pwdEnterPass.setHorizontalAlignment(SwingConstants.CENTER);
		pwdEnterPass.setBounds(45, 165, 128, 32);
		pwdEnterPass.setFont(Fonts.getFont((float)12));
		pwdEnterPass.setForeground(Colors.Red);
		pwdEnterPass.setBackground(Colors.Yellow);
		editController.getEditAccountModel().setPwdEnterPass(pwdEnterPass);
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getPwdEnterPass());
		
		
		JPasswordField pwdValidatePass= new JPasswordField();
		pwdValidatePass.setFont(Fonts.getFont((float)12));
		pwdValidatePass.setForeground(Colors.Red);
		pwdValidatePass.setBackground(Colors.Yellow);
		pwdValidatePass.setHorizontalAlignment(SwingConstants.CENTER);
		
		pwdValidatePass.setBounds(45, 240, 128, 32);
		editController.getEditAccountModel().setPwdValidatePass(pwdValidatePass);
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getPwdValidatePass());
		
		JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField();
		frmtdtxtfldEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldEnterUsername.setText(InformationExpert.getActiveAccount().getAccountProfile().getUsername());
		frmtdtxtfldEnterUsername.setBounds(45, 95, 128, 32);
		frmtdtxtfldEnterUsername.setFont(Fonts.getFont((float)12));
		frmtdtxtfldEnterUsername.setForeground(Colors.Red);
		frmtdtxtfldEnterUsername.setBackground(Colors.Yellow);
		editController.getEditAccountModel().setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getFrmtdtxtfldEnterUsername());
		
		JFormattedTextField secQA = new JFormattedTextField();
		secQA.setHorizontalAlignment(SwingConstants.CENTER);
		secQA.setBounds(275, 240, 128, 32);
		secQA.setFont(Fonts.getFont((float)12));
		secQA.setForeground(Colors.Red);
		secQA.setBackground(Colors.Yellow);
		editController.getEditAccountModel().setSecQA(secQA);
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getSecQA());
		
		JFormattedTextField age = new JFormattedTextField();
		age.setHorizontalAlignment(SwingConstants.CENTER);
		age.setFont(Fonts.getFont((float)12));
		age.setForeground(Colors.Red);
		age.setBackground(Colors.Yellow);
		Date date = InformationExpert.getActiveAccount().getAccountProfile().getDateOB();
		SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy");
		
		age.setText(tf.format(date));
		age.setBounds(275, 95, 128, 32);
		editController.getEditAccountModel().setAge(age);
		editController.getEditAccountPanel().add(age);
		//	init drop downs
		
		JComboBox gender = new JComboBox();
		gender.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		editController.getEditAccountModel().setGender(e.getItem().toString());
        		//capController.getCreateAccountPageModel().setGender(e.getItem().toString());
        	}
        });
		gender.setToolTipText("Gender");
		gender.setFont(Fonts.getFont((float)12));
		gender.setForeground(Colors.Red);
		gender.setBackground(Colors.Yellow);
		gender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		if(InformationExpert.getActiveAccount().getAccountProfile().getGender().equals("Male")) {
			gender.setSelectedIndex(0);
		} else {
			gender.setSelectedIndex(1);
		}
		gender.setBounds(275, 305, 94, 22);
		gender.setVisible(true);
		
		editController.getEditAccountModel().setGenderBox(gender);
		editController.getEditAccountPanel().add(gender);
		
		//	security question
		JComboBox securityQuestions = new JComboBox();
		securityQuestions.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		editController.getEditAccountModel().setSecurityQuestions(e.getItem().toString());
        	}
        });
		securityQuestions.setToolTipText("Security Question");
		securityQuestions.setModel(new DefaultComboBoxModel(new String[] { "Favorite Game?", "First Console Owned?", "Favorite Character?"}));
		securityQuestions.setBounds(275, 165, 180, 30);
		securityQuestions.setFont(Fonts.getFont((float)12));
		securityQuestions.setForeground(Colors.Red);
		securityQuestions.setBackground(Colors.Yellow);
		securityQuestions.setVisible(true);
		
		String defaultQuestion = InformationExpert.getActiveAccount().getSecurityQ1();
		if(defaultQuestion.equals("Favorite Character?")) {
			securityQuestions.setSelectedIndex(2);
		} else if(defaultQuestion.equals("First Console Owned?")) {
			securityQuestions.setSelectedIndex(1);
		}
		
		editController.getEditAccountModel().setSecurityQ(securityQuestions);
		editController.getEditAccountPanel().add(securityQuestions);
		
		//	set text
		JLabel lblAccInfo = new JLabel("Account Info");
		lblAccInfo.setFont(Fonts.getFont((float)20));
		lblAccInfo.setForeground(Colors.Yellow);
		lblAccInfo.setBounds(45, 0, 204, 69);
		editController.getEditAccountPanel().add(lblAccInfo);
		//capController.getCreateAccountPanel().add(lblAccInfo);
		
		JLabel lbldob = new JLabel("Age:");
		lbldob.setFont(Fonts.getFont((float)12));
		lbldob.setForeground(Colors.Yellow);
		lbldob.setBounds(275, 65, 204, 32);
		editController.getEditAccountPanel().add(lbldob);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(Fonts.getFont((float)12));
		lblGender.setForeground(Colors.Yellow);
		lblGender.setBounds(275, 275, 204, 32);	
		editController.getEditAccountPanel().add(lblGender);
		
		JLabel lblSecQ = new JLabel("Security Question");
		lblSecQ.setFont(Fonts.getFont((float)12));
		lblSecQ.setForeground(Colors.Yellow);
		lblSecQ.setBounds(275, 110, 204, 69);
		editController.getEditAccountPanel().add(lblSecQ);
	
		JLabel pwdField1Prmpt = new JLabel("Enter Password:");
		pwdField1Prmpt.setFont(Fonts.getFont((float)12));
		pwdField1Prmpt.setForeground(Colors.Yellow);
		pwdField1Prmpt.setBounds(45, 135, 128, 32);
		editController.getEditAccountPanel().add(pwdField1Prmpt);
		
		JLabel pwdfield2PrmptLoc = new JLabel("Re-enter Password:");
		pwdfield2PrmptLoc.setFont(Fonts.getFont((float)12));
		pwdfield2PrmptLoc.setForeground(Colors.Yellow);
		pwdfield2PrmptLoc.setBounds(45,205,155,32);
		editController.getEditAccountPanel().add(pwdfield2PrmptLoc);
		
		JLabel userFieldPrmptLoc = new JLabel("Enter Username:");
		userFieldPrmptLoc.setFont(Fonts.getFont((float)12));
		userFieldPrmptLoc.setForeground(Colors.Yellow);
		userFieldPrmptLoc.setBounds(45,65,128,32);
		editController.getEditAccountPanel().add(userFieldPrmptLoc);
		
		JLabel answerPrompt = new JLabel("Answer:");
		answerPrompt.setFont(Fonts.getFont((float)12));
		answerPrompt.setForeground(Colors.Yellow);
		answerPrompt.setBounds(275,205,128,32);
		editController.getEditAccountPanel().add(answerPrompt);
		
		//add to panel
		
		//	set attributes, pack and set visible 
		mainFrame.pack();
		mainFrame.setVisible(true);
		//set attributes in loginController:
	}
	
	/**
	 * Launch edit questionnaire page.
	 *
	 * @param editController the edit controller
	 * @param mainFrame the main frame
	 */
	public static void launchEditQuestionnairePage(EditAccountPageController editController, JFrame mainFrame) {
		
		//init colors
		
		//	 set up panel

		editController.setEditAccountPanel(new BackgroundPanel(null));
		editController.getEditAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		editController.getEditAccountPanel().setPreferredSize(new Dimension(500,400));
		editController.getEditAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(editController.getEditAccountPanel());

		//Checkboxes
		editController.getEditAccountModel().setCheckList(new ArrayList<JCheckBox>());
		
		JCheckBox xboxBtn = new JCheckBox("Xbox");
		
		xboxBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getPlatforms().get(0));
		xboxBtn.setFont(Fonts.getFont((float)14));
		xboxBtn.setForeground(Colors.Yellow);
		xboxBtn.setBackground(Colors.Red);
		xboxBtn.setBounds(45, 80, 75, 25);
		editController.getEditAccountModel().getCheckList().add(xboxBtn);
		editController.getEditAccountPanel().add(xboxBtn);
		

		JCheckBox psBtn = new JCheckBox("Playstation");
		psBtn.setBackground(Colors.Red);
		psBtn.setForeground(Colors.Yellow);
		psBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getPlatforms().get(1));
		psBtn.setFont(Fonts.getFont((float)12));
		psBtn.setBounds(45, 105, 120, 25);
		editController.getEditAccountModel().getCheckList().add(psBtn);
		editController.getEditAccountPanel().add(psBtn);
		
		
		JCheckBox nintendoBtn = new JCheckBox("Nintendo");
		nintendoBtn.setBackground(Colors.Red);
		nintendoBtn.setForeground(Colors.Yellow);
		nintendoBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getPlatforms().get(2));
		nintendoBtn.setFont(Fonts.getFont((float)12));
		nintendoBtn.setBounds(45, 130, 120, 25);
		editController.getEditAccountModel().getCheckList().add(nintendoBtn);
		editController.getEditAccountPanel().add(nintendoBtn);
		
		
		JCheckBox pcBtn = new JCheckBox("PC");
		pcBtn.setBackground(Colors.Red);
		pcBtn.setForeground(Colors.Yellow);
		pcBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getPlatforms().get(3));
		pcBtn.setFont(Fonts.getFont((float)12));
		pcBtn.setBounds(45, 155, 75, 25);
		editController.getEditAccountModel().getCheckList().add(pcBtn);
		editController.getEditAccountPanel().add(pcBtn);
		
		
		JCheckBox vrBtn = new JCheckBox("VR");
		vrBtn.setBackground(Colors.Red);
		vrBtn.setForeground(Colors.Yellow);
		vrBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getPlatforms().get(4));
		vrBtn.setFont(Fonts.getFont((float)12));
		vrBtn.setBounds(45, 180, 75, 25);
		editController.getEditAccountModel().getCheckList().add(vrBtn);
		editController.getEditAccountPanel().add(vrBtn);
		
		
		JCheckBox RetroBtn = new JCheckBox("Retro");
		RetroBtn.setBackground(Colors.Red);
		RetroBtn.setForeground(Colors.Yellow);
		RetroBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getPlatforms().get(5));
		RetroBtn.setFont(Fonts.getFont((float)12));
		RetroBtn.setBounds(45, 205, 75, 25);
		editController.getEditAccountModel().getCheckList().add(RetroBtn);
		editController.getEditAccountPanel().add(RetroBtn);
		
		
		//	action adventure fps mmo moba puzzle platformer rythem rpg rts strategy sandbox 
		//	genres
		JCheckBox actionBtn = new JCheckBox("Action");
		actionBtn.setBackground(Colors.Red);
		actionBtn.setForeground(Colors.Yellow);
		actionBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(0));
		actionBtn.setFont(Fonts.getFont((float)12));
		actionBtn.setBounds(245, 80, 75, 25);
		editController.getEditAccountModel().getCheckList().add(actionBtn);
		editController.getEditAccountPanel().add(actionBtn);
		
		
		JCheckBox advBtn = new JCheckBox("Adventure");
		advBtn.setBackground(Colors.Red);
		advBtn.setForeground(Colors.Yellow);
		advBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(1));
		advBtn.setFont(Fonts.getFont((float)12));
		advBtn.setBounds(245, 105, 120, 25);
		editController.getEditAccountModel().getCheckList().add(advBtn);
		editController.getEditAccountPanel().add(advBtn);
		
		
		JCheckBox FPSBtn = new JCheckBox("FPS");
		FPSBtn.setBackground(Colors.Red);
		FPSBtn.setForeground(Colors.Yellow);
		FPSBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(2));
		FPSBtn.setFont(Fonts.getFont((float)12));
		FPSBtn.setBounds(245, 130, 120, 25);
		editController.getEditAccountModel().getCheckList().add(FPSBtn);
		editController.getEditAccountPanel().add(FPSBtn);
		
		
		JCheckBox MMOBtn = new JCheckBox("MMO");
		MMOBtn.setBackground(Colors.Red);
		MMOBtn.setForeground(Colors.Yellow);
		MMOBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(3));
		MMOBtn.setFont(Fonts.getFont((float)12));
		MMOBtn.setBounds(245, 155, 75, 25);
		editController.getEditAccountModel().getCheckList().add(MMOBtn);
		editController.getEditAccountPanel().add(MMOBtn);
		
		
		JCheckBox MOBABtn = new JCheckBox("MOBA");
		MOBABtn.setBackground(Colors.Red);
		MOBABtn.setForeground(Colors.Yellow);
		MOBABtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(4));
		MOBABtn.setFont(Fonts.getFont((float)12));
		MOBABtn.setBounds(245, 180, 75, 25);
		editController.getEditAccountModel().getCheckList().add(MOBABtn);
		editController.getEditAccountPanel().add(MOBABtn);
		
		
		JCheckBox pzlBtn = new JCheckBox("Puzzle");
		pzlBtn.setBackground(Colors.Red);
		pzlBtn.setForeground(Colors.Yellow);
		pzlBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(5));
		pzlBtn.setFont(Fonts.getFont((float)12));
		pzlBtn.setBounds(245, 205, 125, 25);
		editController.getEditAccountModel().getCheckList().add(pzlBtn);
		editController.getEditAccountPanel().add(pzlBtn);
		
		
		JCheckBox rythBtn = new JCheckBox("Rythm");
		rythBtn.setBackground(Colors.Red);
		rythBtn.setForeground(Colors.Yellow);
		rythBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(6));
		rythBtn.setFont(Fonts.getFont((float)12));
		rythBtn.setBounds(365, 80, 75, 25);
		editController.getEditAccountModel().getCheckList().add(RetroBtn);
		editController.getEditAccountPanel().add(rythBtn);
		
		
		JCheckBox platBtn = new JCheckBox("Platformer");
		platBtn.setBackground(Colors.Red);
		platBtn.setForeground(Colors.Yellow);
		platBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(7));
		platBtn.setFont(Fonts.getFont((float)12));
		platBtn.setBounds(365, 105, 125, 25);
		editController.getEditAccountModel().getCheckList().add(platBtn);
		editController.getEditAccountPanel().add(platBtn);
		
		
		JCheckBox RTSBtn = new JCheckBox("RTS");
		RTSBtn.setBackground(Colors.Red);
		RTSBtn.setForeground(Colors.Yellow);
		RTSBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(8));
		RTSBtn.setFont(Fonts.getFont((float)12));
		RTSBtn.setBounds(365, 130, 75, 25);
		editController.getEditAccountModel().getCheckList().add(RTSBtn);
		editController.getEditAccountPanel().add(RTSBtn);
		
		
		JCheckBox RPGBtn = new JCheckBox("RPG");
		RPGBtn.setBackground(Colors.Red);
		RPGBtn.setForeground(Colors.Yellow);
		RPGBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(9));
		RPGBtn.setFont(Fonts.getFont((float)12));
		RPGBtn.setBounds(365, 155, 75, 25);
		editController.getEditAccountModel().getCheckList().add(RPGBtn);
		editController.getEditAccountPanel().add(RPGBtn);
		
		
		JCheckBox stratBtn = new JCheckBox("Strategy");
		stratBtn.setBackground(Colors.Red);
		stratBtn.setForeground(Colors.Yellow);
		stratBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(10));
		stratBtn.setFont(new Font("Monospace",Font.BOLD,14));
		stratBtn.setBounds(365, 180, 125, 25);
		editController.getEditAccountModel().getCheckList().add(stratBtn);
		editController.getEditAccountPanel().add(stratBtn);
		
		JCheckBox sandBtn = new JCheckBox("Sandbox");
		sandBtn.setBackground(Colors.Red);
		sandBtn.setForeground(Colors.Yellow);
		sandBtn.setSelected(InformationExpert.getActiveAccount().getAccountProfile().getGenres().get(11));
		sandBtn.setFont(Fonts.getFont((float)12));
		sandBtn.setBounds(365, 205, 125, 25);
		editController.getEditAccountModel().getCheckList().add(sandBtn);
		editController.getEditAccountPanel().add(sandBtn);
		
		
		//		init buttons and add to panel
		JButton backbtn = new JButton("Cancel");
		backbtn.setBounds(45, 345, 90, 40);
		backbtn.setBackground(Colors.Yellow);
		backbtn.setForeground(Colors.Red);
		backbtn.setFont(Fonts.getFont((float)12));
		backbtn.setActionCommand(EditAccountPageController.CANCEL);
		backbtn.addActionListener(editController);
		editController.getEditAccountModel().setBtnCancel(backbtn);
		editController.getEditAccountPanel().add(backbtn);
		
		JButton nextbtn = new JButton("Submit");
		nextbtn.setBounds(345, 345, 90, 40);
		nextbtn.setBackground(Colors.Yellow);
		nextbtn.setForeground(Colors.Red);
		nextbtn.setFont(Fonts.getFont((float)12));
		nextbtn.setActionCommand(EditAccountPageController.SUBMITEDITQUESTIONAIRE);
		nextbtn.addActionListener(editController);
		editController.getEditAccountModel().setBtnSubmit(nextbtn);
		editController.getEditAccountPanel().add(nextbtn);
	
		
		//	Labels 
		JLabel choosePlatPrmpt = new JLabel("Gaming platforms");
		choosePlatPrmpt.setForeground(Colors.Yellow);
		choosePlatPrmpt.setFont(Fonts.getFont((float)18));
		choosePlatPrmpt.setBounds(25, 0, 300, 69);
		editController.getEditAccountPanel().add(choosePlatPrmpt);
		
		JLabel chooseGenrePrmpt = new JLabel("Favorite genres");
		chooseGenrePrmpt.setForeground(Colors.Yellow);
		chooseGenrePrmpt.setFont(Fonts.getFont((float)16));
		chooseGenrePrmpt.setBounds(245, 0, 250, 69);
		editController.getEditAccountPanel().add(chooseGenrePrmpt);
		
		
		//pack and make visible
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Launch edit profile page.
	 *
	 * @param editController the edit controller
	 * @param mainFrame the main frame
	 */
	public static void launchEditProfilePage(final EditAccountPageController editController, JFrame mainFrame) {
		
		//init colors
		
		//	 set up panel
		editController.setEditAccountPanel(new BackgroundPanel(null));
		editController.getEditAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		editController.getEditAccountPanel().setPreferredSize(new Dimension(500,400));
		editController.getEditAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(editController.getEditAccountPanel());
		
		//	default icon
		BufferedImage img1 = null;
		try {
			img1 = InformationExpert.getProfileImage(InformationExpert.getActiveUserID());
		} catch (DBFailureException e1) {
			e1.printStackTrace();
		}
		if(img1 == null) {
			img1 = CreateAccountPageModel.DEFAULT_PROFILE_IMAGE;
		}
		
		//lblNewLabel.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
		//lblNewLabel.setBounds(75, 25, 150, 150);
		editController.getEditAccountModel().setImagePath(img1.toString());
		editController.getEditAccountModel().setProfileImg(img1);
		final JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		setIcon.setBounds(125,25,150,150);
		setIcon.setContentAreaFilled(false);
		setIcon.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                addActionPerformed();
            }

			private void addActionPerformed() {
				//				load images
				JFileChooser fc = new JFileChooser();
				FileFilter imageFilter = new FileNameExtensionFilter(
					    "Image files", ImageIO.getReaderFileSuffixes());
				fc.addChoosableFileFilter(imageFilter);
				fc.setAcceptAllFileFilterUsed(false);
				fc.setCurrentDirectory(new java.io.File("."));
				BufferedImage img1 = null;
				//	force file chooser
				File f = null;
				int returnValue = fc.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					f = fc.getSelectedFile();
				}
				
//				set images
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				if(f != null) {
					try {
						img1 = ImageConverter.convertToJPG(ImageIO.read(new File(f.getAbsolutePath())));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.warning("Failed to load image");
						img1 = CreateAccountPageModel.DEFAULT_PROFILE_IMAGE;
						editController.getEditAccountModel().setImagePath(img1.toString());
						editController.getEditAccountModel().setProfileImg(img1);
					}
					editController.getEditAccountModel().setImagePath(f.getAbsolutePath());
					editController.getEditAccountModel().setProfileImg(img1);
				}
				
				else if(f == null){
					img1 = CreateAccountPageModel.DEFAULT_PROFILE_IMAGE;
					editController.getEditAccountModel().setImagePath(img1.toString());
					editController.getEditAccountModel().setProfileImg(img1);
				}
				setIcon.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				setIcon.setBounds(125, 25, 150, 150);
				editController.getEditAccountModel().setProfileImg(img1);
				
			}});
		editController.getEditAccountPanel().add(setIcon);
		
		//	text field
		JLabel lblAccInfo = new JLabel("<--- Set Avatar");
		lblAccInfo.setForeground(Colors.Yellow);
		lblAccInfo.setFont(Fonts.getFont(20f));
		lblAccInfo.setBounds(300, 0, 215, 115);
		editController.getEditAccountPanel().add(lblAccInfo);
		
		//	description box
		
//		char count on description
			JLabel charCount = new JLabel();
			charCount.setFont(Fonts.getFont(12f));
			charCount.setBounds(125, 350, 190, 50);
			editController.getEditAccountModel().setCharcount(charCount);
			editController.getEditAccountPanel().add(charCount);
			//	description box
			JTextArea description = new JTextArea();
			String desc;
			desc = InformationExpert.getActiveAccount().getAccountProfile().getDescription();
			desc = desc.replace("<HTML>","");
			desc = desc.replace("</HTML>", "");
			desc = desc.replace("<br>", "\n");
			description.setText(desc);

			charCount.setText((250-description.getText().length()) + " characters remaining");
			description.setBounds(125, 230, 250, 140);
			description.getDocument().addDocumentListener(new DocumentListener() {
				public void update() {
					editController.getEditAccountModel().getCharcount().setText( 
							250 - description.getText().length() + " characters remaining");
				}
				@Override
				public void insertUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					update();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					update();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					update();
				}

			});

		description.setBounds(125, 230, 250, 140);
		editController.getEditAccountModel().setCharDescription(description);
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getCharDescription());
		
		//	load submit button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(385, 345, 90, 40);
		btnSubmit.setBackground(Colors.Yellow);
		btnSubmit.setForeground(Colors.Red);
		btnSubmit.setFont(Fonts.getFont(12f));
		btnSubmit.setActionCommand(EditAccountPageController.SUBMITEDITPROFILE);
		btnSubmit.addActionListener(editController);
		editController.getEditAccountModel().setBtnSubmit(btnSubmit);
		editController.getEditAccountPanel().add(btnSubmit);
		
		//	load back button
		JButton backbtn = new JButton("Cancel");
		backbtn.setBounds(25, 345, 90, 40);
		backbtn.setBackground(Colors.Yellow);
		backbtn.setForeground(Colors.Red);
		backbtn.setFont(Fonts.getFont(12f));
		backbtn.setActionCommand(EditAccountPageController.CANCEL);
		backbtn.addActionListener(editController);
		editController.getEditAccountModel().setBtnCancel(backbtn);
		editController.getEditAccountPanel().add(editController.getEditAccountModel().getBtnCancel());
		
		JLabel descritionPrmpt = new JLabel("Describe yourself:");
		descritionPrmpt.setForeground(Colors.Yellow);
		descritionPrmpt.setFont(Fonts.getFont(20f));
		descritionPrmpt.setBounds(125,190,265,32);
		editController.getEditAccountPanel().add(descritionPrmpt);
		
		//pack and set visible
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

}
