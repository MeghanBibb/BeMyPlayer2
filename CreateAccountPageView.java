package blacklist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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
	public static void startCreateAccountPage(CreateAccountPageController capController,JFrame mainFrame) {
		
		capController.setCreateAccountPageModel(new CreateAccountPageModel());
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
		pwdEnterPass.setText("enter password");
		pwdEnterPass.setBounds(45, 105, 128, 32);
		capController.getCreateAccountPageModel().setPwdEnterPass(pwdEnterPass);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getPwdEnterPass());
		
		
		JPasswordField pwdValidatePass= new JPasswordField();
		pwdValidatePass.setHorizontalAlignment(SwingConstants.CENTER);
		pwdValidatePass.setText("re-enter password");
		pwdValidatePass.setBounds(45, 145, 128, 32);
		capController.getCreateAccountPageModel().setPwdValidatePass(pwdValidatePass);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getPwdValidatePass());
		
		JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField();
		frmtdtxtfldEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldEnterUsername.setText("enter gamertag");
		frmtdtxtfldEnterUsername.setBounds(45, 65, 128, 32);
		capController.getCreateAccountPageModel().setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getFrmtdtxtfldEnterUsername());
		
		JFormattedTextField secQA = new JFormattedTextField();
		secQA.setHorizontalAlignment(SwingConstants.CENTER);
		secQA.setText("enter answer");
		secQA.setBounds(45, 275, 128, 32);
		capController.getCreateAccountPageModel().setSecQA(secQA);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getSecQA());
		
		//	init drop downs
		
		JComboBox gender = new JComboBox();
		gender.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		
        	}
        });
		gender.setToolTipText("Gender");
		gender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		gender.setBounds(300, 265, 94, 22);
		gender.setVisible(true);
		capController.getCreateAccountPanel().add(gender);
		
		//	security question
		JComboBox securityQuestions = new JComboBox();
		securityQuestions.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		
        	}
        });
		securityQuestions.setToolTipText("Security Question");
		securityQuestions.setModel(new DefaultComboBoxModel(new String[] {"Q1", "Q2"}));
		securityQuestions.setBounds(45, 245, 94, 22);
		securityQuestions.setVisible(true);
		capController.getCreateAccountPanel().add(securityQuestions);

		//	birth date
		/*
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		*/
		
		//	set text
		JLabel lblAccInfo = new JLabel("Account Info");
		lblAccInfo.setForeground(yellow);
		lblAccInfo.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblAccInfo.setBounds(45, 0, 204, 69);
		capController.getCreateAccountPanel().add(lblAccInfo);
		
		JLabel lbldob = new JLabel("Date of Birth");
		lbldob.setForeground(yellow);
		lbldob.setFont(new Font("Monospaced", Font.BOLD, 20));
		lbldob.setBounds(275, 0, 204, 69);
		capController.getCreateAccountPanel().add(lbldob);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(yellow);
		lblGender.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblGender.setBounds(300, 190, 204, 69);	
		capController.getCreateAccountPanel().add(lblGender);
		
		JLabel lblSecQ = new JLabel("Security Question");
		lblSecQ.setForeground(yellow);
		lblSecQ.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblSecQ.setBounds(45, 190, 204, 69);
		capController.getCreateAccountPanel().add(lblSecQ);
		
		//	set attributes, pack and set visible 
		mainFrame.pack();
		mainFrame.setVisible(true);
		//set attributes in loginController:
		
	}
	public static void startQuestionaire(CreateAccountPageController capController,JFrame mainFrame) {
		//	load questionare 
		
	}
	public static void startProfileForm(CreateAccountPageController capController,JFrame mainFrame) {
		Color red = new Color(128,0,0);
		Color yellow = new Color(255,215,0);
		
		//	 set up panel
		capController.setCreateAccountPageModel(new CreateAccountPageModel());
		
		capController.setCreateAccountPanel(new JPanel(null));
		capController.getCreateAccountPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		capController.getCreateAccountPanel().setPreferredSize(new Dimension(500,400));
		capController.getCreateAccountPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(capController.getCreateAccountPanel());
		mainFrame.getContentPane().setBackground(red);
		//	load images
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		//fc.setAcceptAllFileFilterUsed(false);
		//fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		File f = null;
		int returnValue = fc.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			f = fc.getSelectedFile();
		}
		//		set images
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image img1 = new ImageIcon(f.getAbsolutePath()).getImage();
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon(img1).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		lblNewLabel.setBounds(275, 31, 128, 82);
		capController.getCreateAccountPanel().add(lblNewLabel);

		//	description page
		JFormattedTextField description = new JFormattedTextField();
		description.setHorizontalAlignment(SwingConstants.CENTER);
		description.setText("enter answer");
		description.setBounds(275, 186, 128, 32);
		capController.getCreateAccountPageModel().setCharDescription(description);
		capController.getCreateAccountPanel().add(capController.getCreateAccountPageModel().getCharDescription());
		
		//	load submit button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(65, 145, 90, 40);
		btnSubmit.setBackground(yellow);
		btnSubmit.setActionCommand(CreateAccountPageController.SUBMIT);
		btnSubmit.addActionListener(capController);
		capController.getCreateAccountPanel().add(btnSubmit);
		
	}
}
