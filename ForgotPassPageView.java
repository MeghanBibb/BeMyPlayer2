package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ForgotPassPageView {
	
	public static void startForgotPasswordPage(final ForgotPassPageController forgotPasswordController, JFrame mainFrame) {
		//init Model
		forgotPasswordController.setForgotPasswordPageModel(new ForgotPassPageModel());
		
		//init colors
		Color red = new Color(134,48,111);
		Color white = new Color(254,195,123);
		
		//init panel
		forgotPasswordController.setForgotPasswordPanel(new JPanel(null));
		forgotPasswordController.getForgotPasswordPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		forgotPasswordController.getForgotPasswordPanel().setPreferredSize(new Dimension(500,400));
		forgotPasswordController.getForgotPasswordPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(forgotPasswordController.getForgotPasswordPanel());
		mainFrame.getContentPane().setBackground(red);
		
		//init buttons
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(45, 245, 90, 40);
		backbtn.setBackground(white);
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(forgotPasswordController);
		forgotPasswordController.getForgotPasswordPageModel().setBtnBack(backbtn);
		
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getBtnBack());
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(340,245,90,40);
		btnSubmit.setActionCommand(forgotPasswordController.SUBMIT);
		btnSubmit.setBackground(white);
		btnSubmit.addActionListener(forgotPasswordController);
		forgotPasswordController.getForgotPasswordPageModel().setBtnSubmit(btnSubmit);
		
		//	set text
		JLabel enterEmailLabel = new JLabel("Enter Email Label:");
		enterEmailLabel.setForeground(white);
		enterEmailLabel.setBounds(50, 15, 204, 69);
		forgotPasswordController.getForgotPasswordPanel().add(enterEmailLabel);
		
		JLabel enterNewPassword = new JLabel("Enter New Password:");
		enterNewPassword.setForeground(white);
		enterNewPassword.setBounds(250, 15, 204, 69);
		forgotPasswordController.getForgotPasswordPanel().add(enterNewPassword);
		
		JLabel enterUserName = new JLabel("Enter Username:");
		enterUserName.setForeground(white);
		enterUserName.setBounds(50, 75, 204, 69);
		forgotPasswordController.getForgotPasswordPanel().add(enterUserName);
		
		JLabel reEnterPassoword = new JLabel("Re-Enter New Password:");
		reEnterPassoword.setForeground(white);
		reEnterPassoword.setBounds(250, 75, 204, 69);
		forgotPasswordController.getForgotPasswordPanel().add(reEnterPassoword);
		
		//	init fields and listeners 
		forgotPasswordController.getForgotPasswordPageModel().setFrmtdtextfldEnterEmail(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setBounds(50,60,180,32);
		
		forgotPasswordController.getForgotPasswordPageModel().setFrmtdtextfldEnterUsername(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setBounds(50,120,180,32);
			
		forgotPasswordController.getForgotPasswordPageModel().setFrmtdtextfldEnterNewPassword(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setBounds(250,60,180,32);
		
		forgotPasswordController.getForgotPasswordPageModel().setPwdEnterPass(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setBounds(250,120,180,32);
		
		JComboBox securityQuestions = new JComboBox();
		/*securityQuestions.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		forgotPasswordController.getForgotPasswordPageModel().setSecurityQuestions(e.getItem().toString());
        	}
        });*/
		securityQuestions.setToolTipText("Security Question");
		securityQuestions.setModel(new DefaultComboBoxModel(forgotPasswordController.getForgotPasswordPageModel().getSecurityQuestions()));
		securityQuestions.setBounds(50, 170, 380, 22);
		securityQuestions.setVisible(true);
		forgotPasswordController.getForgotPasswordPageModel().setSecurityQ(securityQuestions);
		
		//secQA
		forgotPasswordController.getForgotPasswordPageModel().setSecQA(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getSecQA().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getSecQA().setBounds(50, 200, 380, 22);

		//add to panel
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass());
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail());
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername());
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword());
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getBtnSubmit());
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getSecQA());
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getBtnBack());

		forgotPasswordController.getForgotPasswordPanel().add(securityQuestions);
		
		//pack and set visible
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
