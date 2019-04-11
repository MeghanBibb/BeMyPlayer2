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
		
		//init panel
		forgotPasswordController.setForgotPasswordPanel(new BackgroundPanel(null));
		forgotPasswordController.getForgotPasswordPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		forgotPasswordController.getForgotPasswordPanel().setPreferredSize(new Dimension(500,400));
		forgotPasswordController.getForgotPasswordPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(forgotPasswordController.getForgotPasswordPanel());
		
		//init buttons
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(45, 225, 90, 40);
		backbtn.setBackground(Colors.Yellow);
		backbtn.setForeground(Colors.Red);
		backbtn.setFont(Fonts.getFont((float) 12));
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(forgotPasswordController);
		forgotPasswordController.getForgotPasswordPageModel().setBtnBack(backbtn);
		
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getBtnBack());
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(340,225,90,40);
		btnSubmit.setActionCommand(forgotPasswordController.SUBMIT);
		btnSubmit.setBackground(Colors.Yellow);
		btnSubmit.setForeground(Colors.Red);
		btnSubmit.setFont(Fonts.getFont((float) 12));
		btnSubmit.addActionListener(forgotPasswordController);
		forgotPasswordController.getForgotPasswordPageModel().setBtnSubmit(btnSubmit);
		
		//	init fields and listeners 
		forgotPasswordController.getForgotPasswordPageModel().setFrmtdtextfldEnterEmail(new JFormattedTextField("Enter Email"));
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setBounds(50,40,180,32);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setFont(Fonts.getFont((float) 12));
		
		forgotPasswordController.getForgotPasswordPageModel().setFrmtdtextfldEnterUsername(new JFormattedTextField("Enter Username"));
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setBounds(50,90,180,32);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setFont(Fonts.getFont((float) 12));
			
		forgotPasswordController.getForgotPasswordPageModel().setFrmtdtextfldEnterNewPassword(new JFormattedTextField("Enter New Password"));
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setBounds(250,40,180,32);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setFont(Fonts.getFont((float) 12));
		
		forgotPasswordController.getForgotPasswordPageModel().setPwdEnterPass(new JFormattedTextField("Re-Enter New Password"));
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setBounds(250,90,180,32);
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setFont(Fonts.getFont((float) 12));
		
		JComboBox securityQuestions = new JComboBox();
		securityQuestions.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		forgotPasswordController.getForgotPasswordPageModel().setSecurityQuestions(e.getItem().toString());
        	}
        });
		securityQuestions.setToolTipText("Security Question");
		securityQuestions.setModel(new DefaultComboBoxModel(new String[] {"Please Select A Security Question to Answer","Q1", "Q2", "What is your mother's maden name?"}));
		securityQuestions.setBounds(50, 140, 380, 22);
		securityQuestions.setVisible(true);
		forgotPasswordController.getForgotPasswordPageModel().setSecurityQ(securityQuestions);
		forgotPasswordController.getForgotPasswordPageModel().getSecurityQ().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getSecurityQ().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getSecurityQ().setFont(Fonts.getFont((float) 12));
		
		//secQA
		forgotPasswordController.getForgotPasswordPageModel().setSecQA(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getSecQA().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getSecQA().setBounds(50, 180, 380, 22);
		forgotPasswordController.getForgotPasswordPageModel().getSecQA().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getSecQA().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getSecQA().setFont(Fonts.getFont((float) 10));

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
