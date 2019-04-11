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
		backbtn.setBounds(45, 265, 90, 40);
		backbtn.setBackground(Colors.Yellow);
		backbtn.setForeground(Colors.Red);
		backbtn.setFont(Fonts.getFont((float) 12));
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(forgotPasswordController);
		forgotPasswordController.getForgotPasswordPageModel().setBtnBack(backbtn);
		
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getBtnBack());
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(340,265,90,40);
		btnSubmit.setActionCommand(forgotPasswordController.SUBMIT);
		btnSubmit.setBackground(Colors.Yellow);
		btnSubmit.setForeground(Colors.Red);
		btnSubmit.setFont(Fonts.getFont((float) 12));
		btnSubmit.addActionListener(forgotPasswordController);
		forgotPasswordController.getForgotPasswordPageModel().setBtnSubmit(btnSubmit);
		
		//	init fields and listeners 
		forgotPasswordController.getForgotPasswordPageModel().setFrmtdtextfldEnterEmail(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setBounds(50,130,180,32);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterEmail().setFont(Fonts.getFont((float) 12));
		
		forgotPasswordController.getForgotPasswordPageModel().setFrmtdtextfldEnterUsername(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setBounds(50,75,180,32);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterUsername().setFont(Fonts.getFont((float) 12));
			
		forgotPasswordController.getForgotPasswordPageModel().setFrmtdtextfldEnterNewPassword(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setBounds(250,75,180,32);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getFrmtdtextfldEnterNewPassword().setFont(Fonts.getFont((float) 12));
		
		forgotPasswordController.getForgotPasswordPageModel().setPwdEnterPass(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setBounds(250,130,180,32);
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getPwdEnterPass().setFont(Fonts.getFont((float) 12));


		JLabel enterEmail = new JLabel("Enter Email:");
		enterEmail.setBounds(51, 20, 128, 82);
		enterEmail.setForeground(Colors.Yellow);
		enterEmail.setFont(Fonts.getFont((float) 12));
		forgotPasswordController.getForgotPasswordPageModel().setLblEmail(enterEmail);
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getLblEmail());


		JLabel enterUsername = new JLabel("Enter Username:");
		enterUsername.setBounds(51, 80, 180, 82);
		enterUsername.setForeground(Colors.Yellow);
		enterUsername.setFont(Fonts.getFont((float) 12));
		forgotPasswordController.getForgotPasswordPageModel().setLblUsername(enterUsername);
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getLblUsername());


		JLabel enterPassword = new JLabel("Enter New Password:");
		enterPassword.setBounds(250, 20, 180, 82);
		enterPassword.setForeground(Colors.Yellow);
		enterPassword.setFont(Fonts.getFont((float) 12));
		forgotPasswordController.getForgotPasswordPageModel().setLblNewPassword(enterPassword);
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getLblNewPassword());


		JLabel reenterNewPass = new JLabel("Re-Enter New Password:");
		reenterNewPass.setBounds(250, 80, 128, 82);
		reenterNewPass.setForeground(Colors.Yellow);
		reenterNewPass.setFont(Fonts.getFont((float) 12));
		forgotPasswordController.getForgotPasswordPageModel().setLblReEnterNewPassword(reenterNewPass);
		forgotPasswordController.getForgotPasswordPanel().add(forgotPasswordController.getForgotPasswordPageModel().getLblReEnterNewPassword());


		JComboBox securityQuestions = new JComboBox();
		securityQuestions.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		forgotPasswordController.getForgotPasswordPageModel().setSecurityQuestions(e.getItem().toString());
        	}
        });
		securityQuestions.setToolTipText("Security Question");
		securityQuestions.setModel(new DefaultComboBoxModel(new String[] {"Please Select A Security Question to Answer", "Favorite Game?", "First Console Owned?", "Favorite Character?"}));
		securityQuestions.setBounds(50, 180, 380, 22);
		securityQuestions.setVisible(true);
		forgotPasswordController.getForgotPasswordPageModel().setSecurityQ(securityQuestions);
		forgotPasswordController.getForgotPasswordPageModel().getSecurityQ().setBackground(Colors.Yellow);
		forgotPasswordController.getForgotPasswordPageModel().getSecurityQ().setForeground(Colors.Red);
		forgotPasswordController.getForgotPasswordPageModel().getSecurityQ().setFont(Fonts.getFont((float) 12));
		
		//secQA
		forgotPasswordController.getForgotPasswordPageModel().setSecQA(new JFormattedTextField(""));
		forgotPasswordController.getForgotPasswordPageModel().getSecQA().setHorizontalAlignment(SwingConstants.CENTER);
		forgotPasswordController.getForgotPasswordPageModel().getSecQA().setBounds(50, 220, 380, 22);
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
