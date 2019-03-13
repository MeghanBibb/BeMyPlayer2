package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CreateAccountPageView {
	public static void startCreateAccountPage(CreateAccountPageController capController,JFrame mainFrame, boolean visited) {
		if(visited == false) {
			CreateAccountPageModel temp = new CreateAccountPageModel();
			capController.setCreateAccountPageModel(temp);
		}

		Color red = new Color(128,0,0);
		Color yellow = new Color(255,215,0);
		//	init panal
		capController.setCreateAccountPanel(new JPanel(null));
		capController.getCreateAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		capController.getCreateAccountPanel().setPreferredSize(new Dimension(500,400));
		capController.getCreateAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(capController.getCreateAccountPanel());
		mainFrame.getContentPane().setBackground(red);
		
		
		//	init buttons and add to panel
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(45, 345, 90, 40);
		backbtn.setBackground(yellow);
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(capController);
		capController.getCreateAccountPageModel().setBack(backbtn);
		
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getBack());
		
		JButton nextbtn = new JButton("next");
		nextbtn.setBounds(345, 345, 90, 40);
		nextbtn.setBackground(yellow);
		nextbtn.setActionCommand(CreateAccountPageController.NEXT);
		nextbtn.addActionListener(capController);
		capController.getCreateAccountPageModel().setNext(nextbtn);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getNext());
		
		
		//	init fields and listeners 
		JPasswordField pwdEnterPass = new JPasswordField();
		pwdEnterPass.setHorizontalAlignment(SwingConstants.CENTER);
		if(visited == true) {
			pwdEnterPass.setText(capController.getCreateAccountPageModel().getPwdEnterPass().getText());
		}
		//pwdEnterPass.setText("enter password");
		pwdEnterPass.setBounds(45, 165, 128, 32);
		capController.getCreateAccountPageModel().setPwdEnterPass(pwdEnterPass);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getPwdEnterPass());
		
		
		JPasswordField pwdValidatePass= new JPasswordField();
		pwdValidatePass.setHorizontalAlignment(SwingConstants.CENTER);
		if(visited == true) {
			pwdValidatePass.setText(capController.getCreateAccountPageModel().getPwdValidatePass().getText());
		}
		//pwdValidatePass.setText("re-enter password");
		pwdValidatePass.setBounds(45, 240, 128, 32);
		capController.getCreateAccountPageModel().setPwdValidatePass(pwdValidatePass);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getPwdValidatePass());
		
		JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField();
		frmtdtxtfldEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		if(visited == true) {
			frmtdtxtfldEnterUsername.setText(capController.getCreateAccountPageModel().getFrmtdtxtfldEnterUsername().getText());
		}
		//frmtdtxtfldEnterUsername.setText("enter gamertag");
		frmtdtxtfldEnterUsername.setBounds(45, 95, 128, 32);
		capController.getCreateAccountPageModel().setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getFrmtdtxtfldEnterUsername());
		
		JFormattedTextField secQA = new JFormattedTextField();
		secQA.setHorizontalAlignment(SwingConstants.CENTER);
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
		if(visited == true) {
			age.setText(capController.getCreateAccountPageModel().getAge().getText());
		}
		//age.setText("enter age");
		age.setBounds(275, 95, 128, 32);
		capController.getCreateAccountPageModel().setAge(age);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getAge());
		//	init drop downs
		
		JComboBox gender = new JComboBox();
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
		lblAccInfo.setForeground(yellow);
		lblAccInfo.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblAccInfo.setBounds(45, 0, 204, 69);
		capController.getCreateAccountPanel().add(lblAccInfo);
		
		JLabel lbldob = new JLabel("Age:");
		lbldob.setForeground(yellow);
		lbldob.setFont(new Font("Monospaced", Font.BOLD, 12));
		lbldob.setBounds(275, 65, 204, 32);
		capController.getCreateAccountPanel().add(lbldob);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(yellow);
		lblGender.setFont(new Font("Monospaced", Font.BOLD, 12));
		lblGender.setBounds(275, 245, 204, 32);	
		capController.getCreateAccountPanel().add(lblGender);
		
		JLabel lblSecQ = new JLabel("Security Question");
		lblSecQ.setForeground(yellow);
		lblSecQ.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblSecQ.setBounds(275, 110, 204, 69);
		capController.getCreateAccountPanel().add(lblSecQ);
	
		JLabel pwdField1Prmpt = new JLabel("Enter Password:");
		pwdField1Prmpt.setForeground(yellow);
		pwdField1Prmpt.setFont(new Font("Monospaced",Font.BOLD,12));
		pwdField1Prmpt.setBounds(45, 135, 128, 32);
		capController.getCreateAccountPanel().add(pwdField1Prmpt);
		
		JLabel pwdfield2PrmptLoc = new JLabel("Re-enter Password:");
		pwdfield2PrmptLoc.setForeground(yellow);
		pwdfield2PrmptLoc.setFont(new Font("Monospaced",Font.BOLD,12));
		pwdfield2PrmptLoc.setBounds(45,205,155,32);
		capController.getCreateAccountPanel().add(pwdfield2PrmptLoc);
		
		JLabel userFieldPrmptLoc = new JLabel("Enter Username:");
		userFieldPrmptLoc.setForeground(yellow);
		userFieldPrmptLoc.setFont(new Font("Monospaced",Font.BOLD,12));
		userFieldPrmptLoc.setBounds(45,65,128,32);
		capController.getCreateAccountPanel().add(userFieldPrmptLoc);
		
		JLabel answerPrompt = new JLabel("Answer:");
		answerPrompt.setForeground(yellow);
		answerPrompt.setFont(new Font("Monospaced",Font.BOLD,12));
		answerPrompt.setBounds(220,205,128,32);
		capController.getCreateAccountPanel().add(answerPrompt);
		
		//	set attributes, pack and set visible 
		mainFrame.pack();
		mainFrame.setVisible(true);
		//set attributes in loginController:
		
	}
	public static void startQuestionaire(CreateAccountPageController capController,JFrame mainFrame,boolean visited) {
		//	load questionare 
		Color red = new Color(128,0,0);
		Color yellow = new Color(255,215,0);
		
		//	 set up panel

		capController.setCreateAccountPanel(new JPanel(null));
		capController.getCreateAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		capController.getCreateAccountPanel().setPreferredSize(new Dimension(500,400));
		capController.getCreateAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(capController.getCreateAccountPanel());
		mainFrame.getContentPane().setBackground(red);

		//		init buttons and add to panel
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(45, 345, 90, 40);
		backbtn.setBackground(yellow);
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(capController);
		capController.getCreateAccountPageModel().setBack(backbtn);
		
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getBack());
		
		JButton nextbtn = new JButton("next");
		nextbtn.setBounds(345, 345, 90, 40);
		nextbtn.setBackground(yellow);
		nextbtn.setActionCommand(CreateAccountPageController.NEXT);
		nextbtn.addActionListener(capController);
		capController.getCreateAccountPageModel().setNext(nextbtn);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getNext());
	
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	public static void startProfileForm(CreateAccountPageController capController,JFrame mainFrame, boolean visited) {
		Color red = new Color(128,0,0);
		Color yellow = new Color(255,215,0);
		
		//	 set up panel

		capController.setCreateAccountPanel(new JPanel(null));
		capController.getCreateAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		capController.getCreateAccountPanel().setPreferredSize(new Dimension(500,400));
		capController.getCreateAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(capController.getCreateAccountPanel());
		mainFrame.getContentPane().setBackground(red);
		
		//	default icon
		Image img1;
		if(visited == true) {
			img1 = capController.getCreateAccountPageModel().getProfileImg();
			if(img1 == null) {
				img1 = new ImageIcon(capController.getClass().getResource("/defaultIcon.png")).getImage();
			}
		}
		else {
			img1 = new ImageIcon(capController.getClass().getResource("/defaultIcon.png")).getImage();
		}
		
		//lblNewLabel.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
		//lblNewLabel.setBounds(75, 25, 150, 150);
		capController.getCreateAccountPageModel().setImagePath(img1.toString());
		final JButton setIcon = new JButton(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
		setIcon.setBounds(125,25,150,150);
		setIcon.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                addActionPerformed();
            }

			private void addActionPerformed() {
//				load images
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("."));
				//fc.setAcceptAllFileFilterUsed(false);
				//fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				File f = null;
				int returnValue = fc.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					f = fc.getSelectedFile();
				}
//				set images
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				Image img1 = new ImageIcon(f.getAbsolutePath()).getImage();
				capController.getCreateAccountPageModel().setImagePath(f.getAbsolutePath());
				setIcon.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
				//setIcon.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
				setIcon.setBounds(125, 25, 150, 150);
				capController.getCreateAccountPageModel().setProfileImg(img1);
				
			}});
		capController.getCreateAccountPanel().add(setIcon);
		
		//	text field
		JLabel lblAccInfo = new JLabel("<--- Set Avatar");
		lblAccInfo.setForeground(yellow);
		lblAccInfo.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblAccInfo.setBounds(300, 0, 215, 115);
		capController.getCreateAccountPanel().add(lblAccInfo);
		
		//	description box
		JFormattedTextField description = new JFormattedTextField();
		description.setHorizontalAlignment(SwingConstants.CENTER);
		if(visited == true) {
			description.setText(capController.getCreateAccountPageModel().getCharDescription().getText());
		}
		description.setBounds(125, 230, 250, 150);
		capController.getCreateAccountPageModel().setCharDescription(description);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getCharDescription());
		
		//	load submit button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(385, 345, 90, 40);
		btnSubmit.setBackground(yellow);
		btnSubmit.setActionCommand(CreateAccountPageController.SUBMIT);
		btnSubmit.addActionListener(capController);
		capController.getCreateAccountPanel().add(btnSubmit);
		
		//	load back button
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(25, 345, 90, 40);
		backbtn.setBackground(yellow);
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(capController);
		capController.getCreateAccountPageModel().setBack(backbtn);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getBack());
		
		JLabel descritionPrmpt = new JLabel("Describe yourself:");
		descritionPrmpt.setForeground(yellow);
		descritionPrmpt.setFont(new Font("Monospaced",Font.BOLD,20));
		descritionPrmpt.setBounds(125,190,265,32);
		capController.getCreateAccountPanel().add(descritionPrmpt);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
