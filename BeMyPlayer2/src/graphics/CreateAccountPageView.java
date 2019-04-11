package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CreateAccountPageView {
	public static void startCreateAccountPage(final CreateAccountPageController capController,JFrame mainFrame, boolean visited) {
		if(visited == false) {
			CreateAccountPageModel temp = new CreateAccountPageModel();
			capController.setCreateAccountPageModel(temp);
		}

		Color red = new Color(134, 48, 111);
		Color yellow = new Color(254, 195, 123);
		//	init panel
		capController.setCreateAccountPanel(new BackgroundPanel(null));
		capController.getCreateAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		capController.getCreateAccountPanel().setPreferredSize(new Dimension(500,400));
		capController.getCreateAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(capController.getCreateAccountPanel());
		mainFrame.getContentPane().setBackground(red);
		
		
		//	init buttons and add to panel
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(45, 345, 90, 40);
		backbtn.setBackground(Colors.Yellow);
		backbtn.setForeground(Colors.Red);
		backbtn.setFont(Fonts.getFont((float) 12));
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(capController);
		capController.getCreateAccountPageModel().setBack(backbtn);
		
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getBack());
		
		JButton nextbtn = new JButton("Next");
		nextbtn.setBounds(345, 345, 90, 40);
		nextbtn.setBackground(Colors.Yellow);
		nextbtn.setForeground(Colors.Red);
		nextbtn.setFont(Fonts.getFont((float) 12));
		nextbtn.setActionCommand(CreateAccountPageController.NEXT);
		nextbtn.addActionListener(capController);
		capController.getCreateAccountPageModel().setNext(nextbtn);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getNext());
		
		//	enter user name
		JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField();
		frmtdtxtfldEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldEnterUsername.setBackground(Colors.Yellow);
		frmtdtxtfldEnterUsername.setForeground(Colors.Red);
		frmtdtxtfldEnterUsername.setFont(Fonts.getFont((float) 12));
		if(visited == true) {
			frmtdtxtfldEnterUsername.setText(capController.getCreateAccountPageModel().getFrmtdtxtfldEnterUsername().getText());
		}
		frmtdtxtfldEnterUsername.setBounds(45, 95, 128, 32);
		capController.getCreateAccountPageModel().setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getFrmtdtxtfldEnterUsername());
			
		//	enter email
		JFormattedTextField enterEmail = new JFormattedTextField();
		enterEmail.setHorizontalAlignment(SwingConstants.CENTER);
		enterEmail.setBackground(Colors.Yellow);
		enterEmail.setForeground(Colors.Red);
		enterEmail.setFont(Fonts.getFont((float) 12));
		if(visited == true) {
			enterEmail.setText(capController.getCreateAccountPageModel().getEnterEmail().getText());
		}
		enterEmail.setBounds(45, 165, 128, 32);
		capController.getCreateAccountPageModel().setEnterEmail(enterEmail);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getEnterEmail());
		
		
		//	init fields and listeners 
		//	password
		JPasswordField pwdEnterPass = new JPasswordField();
		pwdEnterPass.setHorizontalAlignment(SwingConstants.CENTER);
		pwdEnterPass.setBackground(Colors.Yellow);
		pwdEnterPass.setForeground(Colors.Red);
		pwdEnterPass.setFont(Fonts.getFont((float) 12));
		pwdEnterPass.setBounds(45, 240, 128, 32);
		capController.getCreateAccountPageModel().setPwdEnterPass(pwdEnterPass);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getPwdEnterPass());
		
		//	reenter password
		JPasswordField pwdValidatePass= new JPasswordField();
		pwdValidatePass.setHorizontalAlignment(SwingConstants.CENTER);
		pwdValidatePass.setBackground(Colors.Yellow);
		pwdValidatePass.setForeground(Colors.Red);
		pwdValidatePass.setFont(Fonts.getFont((float) 12));
		pwdValidatePass.setBounds(45, 300, 128, 32);
		capController.getCreateAccountPageModel().setPwdValidatePass(pwdValidatePass);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getPwdValidatePass());
		
			
		JFormattedTextField secQA = new JFormattedTextField();
		secQA.setHorizontalAlignment(SwingConstants.CENTER);
		secQA.setBackground(Colors.Yellow);
		secQA.setForeground(Colors.Red);
		secQA.setFont(Fonts.getFont((float) 12));

		if(visited == true) {
			secQA.setText(capController.getCreateAccountPageModel().getSecQA().getText());
		}
		else {
			secQA.setText("");
		}
		secQA.setBounds(275, 205, 128, 32);
		capController.getCreateAccountPageModel().setSecQA(secQA);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getSecQA());
		
		JFormattedTextField age = new JFormattedTextField();
		age.setHorizontalAlignment(SwingConstants.CENTER);
		age.setBackground(Colors.Yellow);
		age.setForeground(Colors.Red);
		age.setFont(Fonts.getFont((float) 12));
		if(visited == true) {
			age.setText(capController.getCreateAccountPageModel().getAge().getText());
		}
		age.setText("dd/mm/yyyy");
		age.setBounds(275, 95, 128, 32);
		capController.getCreateAccountPageModel().setAge(age);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getAge());
		//	init drop downs
		
		JComboBox gender = new JComboBox();
		gender.setBackground(Colors.Yellow);
		gender.setForeground(Colors.Red);
		gender.setFont(Fonts.getFont((float) 12));
		gender.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		capController.getCreateAccountPageModel().setGender(e.getItem().toString());
        	}
        });
		gender.setToolTipText("Gender");
		gender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		gender.setBounds(275, 275, 94, 22);
		gender.setVisible(true);
		if(visited == true) {
			gender.setSelectedItem(capController.getCreateAccountPageModel().getGenderBox().getSelectedItem());
		}
		capController.getCreateAccountPageModel().setGenderBox(gender);
		capController.getCreateAccountPanel().add(gender);
		
		//	security question
		JComboBox securityQuestions = new JComboBox();
		securityQuestions.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		capController.getCreateAccountPageModel().setSecurityQuestions(e.getItem().toString());
        	}
        });
		securityQuestions.setBackground(Colors.Yellow);
		securityQuestions.setForeground(Colors.Red);
		securityQuestions.setFont(Fonts.getFont((float) 12));
		securityQuestions.setToolTipText("Security Question");
		securityQuestions.setModel(new DefaultComboBoxModel(new String[] {"Q1", "Q2"}));
		securityQuestions.setBounds(275, 170, 94, 22);
		securityQuestions.setVisible(true);
		if(visited == true) {
			securityQuestions.setSelectedItem(capController.getCreateAccountPageModel().getSecurityQ().getSelectedItem());
		}
		capController.getCreateAccountPageModel().setSecurityQ(securityQuestions);
		capController.getCreateAccountPanel().add(securityQuestions);
		
		//	set text
		JLabel lblAccInfo = new JLabel("Account Info");
		lblAccInfo.setForeground(Colors.Yellow);
		lblAccInfo.setFont(Fonts.getFont((float)20));
		lblAccInfo.setBounds(45, 0, 204, 69);
		capController.getCreateAccountPanel().add(lblAccInfo);
		
		JLabel lbldob = new JLabel("Age:");
		lbldob.setForeground(yellow);
		lbldob.setFont(Fonts.getFont((float)12));
		lbldob.setBounds(275, 65, 204, 32);
		capController.getCreateAccountPanel().add(lbldob);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(Colors.Yellow);
		lblGender.setFont(Fonts.getFont((float)12));
		lblGender.setBounds(275, 245, 204, 32);	
		capController.getCreateAccountPanel().add(lblGender);
		
		JLabel lblSecQ = new JLabel("Security Question");
		lblSecQ.setForeground(yellow);
		lblSecQ.setFont(Fonts.getFont((float)20));
		lblSecQ.setBounds(275, 110, 204, 69);
		capController.getCreateAccountPanel().add(lblSecQ);
	
		JLabel userFieldPrmptLoc = new JLabel("Enter Username:");
		userFieldPrmptLoc.setForeground(Colors.Yellow);
		userFieldPrmptLoc.setFont(Fonts.getFont((float)12));
		userFieldPrmptLoc.setBounds(45,65,128,32);
		capController.getCreateAccountPanel().add(userFieldPrmptLoc);
		
		JLabel emailPrmpt = new JLabel("Enter Email:");
		emailPrmpt.setForeground(Colors.Yellow);
		emailPrmpt.setFont(Fonts.getFont((float)12));
		emailPrmpt.setBounds(45,135,128,32);
		capController.getCreateAccountPanel().add(emailPrmpt);
		
		JLabel pwdField1Prmpt = new JLabel("Enter Password:");
		pwdField1Prmpt.setForeground(Colors.Yellow);
		pwdField1Prmpt.setFont(Fonts.getFont((float)12));
		pwdField1Prmpt.setBounds(45, 205, 128, 32);
		capController.getCreateAccountPanel().add(pwdField1Prmpt);
		
		JLabel pwdfield2PrmptLoc = new JLabel("Re-enter Password:");
		pwdfield2PrmptLoc.setForeground(Colors.Yellow);
		pwdfield2PrmptLoc.setFont(Fonts.getFont((float)12));
		pwdfield2PrmptLoc.setBounds(45,275,155,32);
		capController.getCreateAccountPanel().add(pwdfield2PrmptLoc);

		
		JLabel answerPrompt = new JLabel("Answer:");
		answerPrompt.setForeground(Colors.Yellow);
		answerPrompt.setFont(Fonts.getFont((float)12));
		answerPrompt.setBounds(220,205,128,32);
		capController.getCreateAccountPanel().add(answerPrompt);
		
		//	set attributes, pack and set visible 
		mainFrame.pack();
		mainFrame.setVisible(true);
		//set attributes in loginController:
		
	}
	public static void startQuestionaire(final CreateAccountPageController capController,JFrame mainFrame,boolean visited) {
		//	load questionare 
		Color red = new Color(134, 48, 111);
		Color yellow = new Color(254, 195, 123);
		
		//	 set up panel

		capController.setCreateAccountPanel(new BackgroundPanel(null));
		capController.getCreateAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		capController.getCreateAccountPanel().setPreferredSize(new Dimension(500,400));
		capController.getCreateAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(capController.getCreateAccountPanel());
		mainFrame.getContentPane().setBackground(Colors.Red);

		//	Checkboxes
		if(visited == false) {
			capController.getCreateAccountPageModel().setCheckList(new ArrayList<JCheckBox>());
		}
		final JCheckBox xboxBtn = new JCheckBox("Xbox");
		xboxBtn.setBackground(red);
		xboxBtn.setForeground(Colors.Yellow);
		xboxBtn.setFont(Fonts.getFont((float)14));
		xboxBtn.setBounds(45, 80, 75, 25);
		
		if(visited == true) {
			xboxBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(0).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(xboxBtn);
		}
		xboxBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(0).setSelected(xboxBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(xboxBtn);
		

		final JCheckBox psBtn = new JCheckBox("Playstation");
		psBtn.setBackground(red);
		psBtn.setForeground(Colors.Yellow);
		psBtn.setFont(Fonts.getFont((float)14));
		psBtn.setBounds(45, 105, 120, 25);
		if(visited == true) {
			psBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(1).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(psBtn);
		}
		psBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(1).setSelected(psBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(psBtn);
		
		
		final JCheckBox nintendoBtn = new JCheckBox("Nintendo");
		nintendoBtn.setBackground(red);
		nintendoBtn.setForeground(Colors.Yellow);
		nintendoBtn.setFont(Fonts.getFont((float)14));
		nintendoBtn.setBounds(45, 130, 120, 25);
		if(visited == true) {
			nintendoBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(2).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(nintendoBtn);
		}
		nintendoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(2).setSelected(nintendoBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(nintendoBtn);
		
		
		final JCheckBox pcBtn = new JCheckBox("PC");
		pcBtn.setBackground(red);
		pcBtn.setForeground(Colors.Yellow);
		pcBtn.setFont(Fonts.getFont((float)14));
		pcBtn.setBounds(45, 155, 75, 25);
		
		if(visited == true) {
			pcBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(3).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(pcBtn);
		}
		pcBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(3).setSelected(pcBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(pcBtn);
		
		
		final JCheckBox vrBtn = new JCheckBox("VR");
		vrBtn.setBackground(red);
		vrBtn.setForeground(Colors.Yellow);
		vrBtn.setFont(Fonts.getFont((float)14));
		vrBtn.setBounds(45, 180, 75, 25);
		if(visited == true) {
			vrBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(4).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(vrBtn);
		}
		vrBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(4).setSelected(vrBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(vrBtn);
		
		
		final JCheckBox RetroBtn = new JCheckBox("Retro");
		RetroBtn.setBackground(red);
		RetroBtn.setForeground(Colors.Yellow);
		RetroBtn.setFont(Fonts.getFont((float)14));
		RetroBtn.setBounds(45, 205, 75, 25);
		if(visited == true) {
			RetroBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(5).isSelected());
		}
		else{
			capController.getCreateAccountPageModel().getCheckList().add(RetroBtn);
		}
		RetroBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(5).setSelected(RetroBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(RetroBtn);
		
		
		//	action adventure fps mmo moba puzzle platformer rythem rpg rts strategy sandbox 
		//	genres
		final JCheckBox actionBtn = new JCheckBox("Action");
		actionBtn.setBackground(red);
		actionBtn.setForeground(Colors.Yellow);
		actionBtn.setFont(Fonts.getFont((float)14));
		actionBtn.setBounds(245, 80, 75, 25);
		if(visited == true) {
			actionBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(6).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(actionBtn);
		}
		actionBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(6).setSelected(actionBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(actionBtn);
		
		
		final JCheckBox advBtn = new JCheckBox("Adventure");
		advBtn.setBackground(red);
		advBtn.setForeground(Colors.Yellow);
		advBtn.setFont(Fonts.getFont((float)14));
		advBtn.setBounds(245, 105, 120, 25);
		if(visited == true) {
			advBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(7).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(advBtn);
		}
		advBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(7).setSelected(advBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(advBtn);
		
		
		final JCheckBox FPSBtn = new JCheckBox("FPS");
		FPSBtn.setBackground(red);
		FPSBtn.setForeground(Colors.Yellow);
		FPSBtn.setFont(Fonts.getFont((float)14));
		FPSBtn.setBounds(245, 130, 120, 25);
		if(visited == true) {
			FPSBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(8).isSelected());
		}
		else{
			capController.getCreateAccountPageModel().getCheckList().add(FPSBtn);
		}
		FPSBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(8).setSelected(FPSBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(FPSBtn);
		
		
		final JCheckBox MMOBtn = new JCheckBox("MMO");
		MMOBtn.setBackground(red);
		MMOBtn.setForeground(Colors.Yellow);
		MMOBtn.setFont(Fonts.getFont((float)14));
		MMOBtn.setBounds(245, 155, 75, 25);
		if(visited == true) {
			MMOBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(9).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(MMOBtn);
		}
		MMOBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(9).setSelected(MMOBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(MMOBtn);
		
		
		final JCheckBox MOBABtn = new JCheckBox("MOBA");
		MOBABtn.setBackground(red);
		MOBABtn.setForeground(Colors.Yellow);
		MOBABtn.setFont(Fonts.getFont((float)14));
		MOBABtn.setBounds(245, 180, 75, 25);
		if(visited == true) {
			MOBABtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(10).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(MOBABtn);
		}
		MOBABtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(10).setSelected(MOBABtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(MOBABtn);
		
		
		final JCheckBox pzlBtn = new JCheckBox("Puzzle");
		pzlBtn.setBackground(red);
		pzlBtn.setForeground(Colors.Yellow);
		pzlBtn.setFont(Fonts.getFont((float)14));
		pzlBtn.setBounds(245, 205, 125, 25);
		if(visited == true) {
			pzlBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(11).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(pzlBtn);
		}
		pzlBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(11).setSelected(pzlBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(pzlBtn);
		
		
		final JCheckBox rythBtn = new JCheckBox("Rythm");
		rythBtn.setBackground(red);
		rythBtn.setForeground(Colors.Yellow);
		rythBtn.setFont(Fonts.getFont((float)14));
		rythBtn.setBounds(365, 80, 75, 25);
		if(visited == true) {
			rythBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(12).isSelected());
		}
		else{
			capController.getCreateAccountPageModel().getCheckList().add(rythBtn);
		}
		rythBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(12).setSelected(rythBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(rythBtn);
		
		
		final JCheckBox platBtn = new JCheckBox("Platformer");
		platBtn.setBackground(red);
		platBtn.setForeground(Colors.Yellow);
		platBtn.setFont(Fonts.getFont((float)14));
		platBtn.setBounds(365, 105, 125, 25);
		if(visited == true) {
			platBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(13).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(platBtn);
		}
		platBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(13).setSelected(platBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(platBtn);
		
		
		final JCheckBox RTSBtn = new JCheckBox("RTS");
		RTSBtn.setBackground(red);
		RTSBtn.setForeground(Colors.Yellow);
		RTSBtn.setFont(Fonts.getFont((float)14));
		RTSBtn.setBounds(365, 130, 75, 25);
		if(visited == true) {
			RTSBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(14).isSelected());
		}
		else{
			capController.getCreateAccountPageModel().getCheckList().add(RTSBtn);
		}
		RTSBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(14).setSelected(RTSBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(RTSBtn);
		
		
		final JCheckBox RPGBtn = new JCheckBox("RPG");
		RPGBtn.setBackground(red);
		RPGBtn.setForeground(Colors.Yellow);
		RPGBtn.setFont(Fonts.getFont((float)14));
		RPGBtn.setBounds(365, 155, 75, 25);
		if(visited == true) {
			RPGBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(15).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(RPGBtn);
		}
		RPGBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(15).setSelected(RPGBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(RPGBtn);
		
		
		final JCheckBox stratBtn = new JCheckBox("Strategy");
		stratBtn.setBackground(red);
		stratBtn.setForeground(Colors.Yellow);
		stratBtn.setFont(Fonts.getFont((float)14));
		stratBtn.setBounds(365, 180, 125, 25);
		if(visited == true) {
			stratBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(16).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(stratBtn);
		}
		stratBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(16).setSelected(stratBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(stratBtn);
		
		final JCheckBox sandBtn = new JCheckBox("Sandbox");
		sandBtn.setBackground(red);
		sandBtn.setForeground(Colors.Yellow);
		sandBtn.setFont(Fonts.getFont((float)14));
		sandBtn.setBounds(365, 205, 125, 25);
		if(visited == true) {
			sandBtn.setSelected(capController.getCreateAccountPageModel().getCheckList().get(17).isSelected());
		}
		else {
			capController.getCreateAccountPageModel().getCheckList().add(sandBtn);
			
		}
		sandBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				capController.getCreateAccountPageModel().getCheckList().get(17).setSelected(sandBtn.isSelected());
			}
			
		});
		capController.getCreateAccountPanel().add(sandBtn);
		
		
		//		init buttons and add to panel
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(45, 345, 90, 40);
		backbtn.setBackground(Colors.Yellow);
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(capController);
		capController.getCreateAccountPageModel().setBack(backbtn);
		
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getBack());
		
		JButton nextbtn = new JButton("Next");
		nextbtn.setBounds(345, 345, 90, 40);
		nextbtn.setBackground(Colors.Yellow);
		nextbtn.setActionCommand(CreateAccountPageController.NEXT);
		nextbtn.addActionListener(capController);
		capController.getCreateAccountPageModel().setNext(nextbtn);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getNext());
	
		
		//	Labels 
		JLabel choosePlatPrmpt = new JLabel("Gaming platforms");
		choosePlatPrmpt.setForeground(Colors.Yellow);
		choosePlatPrmpt.setFont(Fonts.getFont((float)18));
		choosePlatPrmpt.setBounds(25, 0, 300, 69);
		capController.getCreateAccountPanel().add(choosePlatPrmpt);
		
		JLabel chooseGenrePrmpt = new JLabel("Favorite genres");
		chooseGenrePrmpt.setForeground(Colors.Yellow);
		chooseGenrePrmpt.setFont(Fonts.getFont((float)16));
		chooseGenrePrmpt.setBounds(245, 0, 250, 69);
		capController.getCreateAccountPanel().add(chooseGenrePrmpt);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	public static void startProfileForm(final CreateAccountPageController capController,JFrame mainFrame, boolean visited) {
		Color red = new Color(134,48,111);
		Color yellow = new Color(254, 195, 123);
		
		//	 set up panel

		capController.setCreateAccountPanel(new BackgroundPanel(null));
		capController.getCreateAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		capController.getCreateAccountPanel().setPreferredSize(new Dimension(500,400));
		capController.getCreateAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(capController.getCreateAccountPanel());
		mainFrame.getContentPane().setBackground(red);
		
		//	default icon
		BufferedImage img1 = null;
		if(visited == true) {
			img1 = capController.getCreateAccountPageModel().getProfileImg();
			if(img1 == null) {
				try {
					img1 = ImageIO.read(capController.getClass().getResource("/defaultIcon.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else {
			try {
				img1 = ImageIO.read(capController.getClass().getResource("/defaultIcon.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//lblNewLabel.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
		//lblNewLabel.setBounds(75, 25, 150, 150);
		capController.getCreateAccountPageModel().setImagePath(img1.toString());
		final JButton setIcon = new JButton();
		setIcon.setMargin(new Insets(0,0,0,0));
		setIcon.setContentAreaFilled(false);
		setIcon.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
		setIcon.setBackground(red);
		setIcon.setBounds(125,25,150,150);
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
						img1 = ImageIO.read(new File(f.getAbsolutePath()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					capController.getCreateAccountPageModel().setImagePath(f.getAbsolutePath());
				}
				else if(f == null){
					try {
						img1 = ImageIO.read(capController.getClass().getResource("/defaultIcon.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					capController.getCreateAccountPageModel().setImagePath(img1.toString());
				}
				setIcon.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
				//setIcon.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
				setIcon.setBounds(125, 25, 150, 150);
				capController.getCreateAccountPageModel().setProfileImg(img1);
				
			}});
		capController.getCreateAccountPanel().add(setIcon);
		
		//	text field
		JLabel lblAccInfo = new JLabel("<--- Set Avatar");
		lblAccInfo.setForeground(Colors.Yellow);
		lblAccInfo.setFont(Fonts.getFont((float)20));
		lblAccInfo.setBounds(300, 0, 215, 115);
		capController.getCreateAccountPanel().add(lblAccInfo);
		
		//	char count on description
		JLabel charCount = new JLabel();
		charCount.setFont(Fonts.getFont((float)12));
		charCount.setBounds(125, 350, 190, 50);
		charCount.setText("250 characters remaining");
		capController.getCreateAccountPageModel().setCharcount(charCount);
		capController.getCreateAccountPanel().add(charCount);
		//	description box
		JTextArea description = new JTextArea();
		if(visited == true) {
			description.setText(capController.getCreateAccountPageModel().getCharDescription().getText());
			capController.getCreateAccountPageModel().getCharcount().setText(
					250 - description.getText().length() + " characters remaining");
			capController.getCreateAccountPanel().revalidate();
		}
		
		description.setBounds(125, 230, 250, 140);
		description.getDocument().addDocumentListener(new DocumentListener() {
			public void update() {
				capController.getCreateAccountPageModel().getCharcount().setText( 
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
		capController.getCreateAccountPageModel().setCharDescription(description);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getCharDescription());
		
		
		//	load submit button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(385, 345, 90, 40);
		btnSubmit.setBackground(Colors.Yellow);
		btnSubmit.setActionCommand(CreateAccountPageController.SUBMIT);
		btnSubmit.addActionListener(capController);
		capController.getCreateAccountPanel().add(btnSubmit);
		
		//	load back button
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(25, 345, 90, 40);
		backbtn.setBackground(Colors.Yellow);
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(capController);
		capController.getCreateAccountPageModel().setBack(backbtn);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getBack());
		
		JLabel descritionPrmpt = new JLabel("Describe yourself:");
		descritionPrmpt.setForeground(Colors.Yellow);
		descritionPrmpt.setFont(Fonts.getFont((float)12));
		descritionPrmpt.setBounds(125,190,265,32);
		capController.getCreateAccountPanel().add(descritionPrmpt);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
