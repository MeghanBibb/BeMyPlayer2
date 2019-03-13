package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginPageModel {
	//	data members of login
	
	private String defaultImage = null;
	
	//	init buttons
	private JButton btnLogin;
	
	private JButton btnCreateNewAccount;
	
	private JButton btnForgotPassword;
	
	//	init fields and listeners 
	private JPasswordField pwdEnterPass;
	
	private JFormattedTextField frmtdtxtfldEnterUsername;
	
	//	set images
	private JLabel lblNewLabel;

	private JLabel lblBeMyPlayer;
	
	public String getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JButton getBtnCreateNewAccount() {
		return btnCreateNewAccount;
	}

	public void setBtnCreateNewAccount(JButton btnCreateNewAccount) {
		this.btnCreateNewAccount = btnCreateNewAccount;
	}

	public JPasswordField getPwdEnterPass() {
		return pwdEnterPass;
	}

	public void setPwdEnterPass(JPasswordField pwdEnterPass) {
		this.pwdEnterPass = pwdEnterPass;
	}

	public JFormattedTextField getFrmtdtxtfldEnterUsername() {
		return frmtdtxtfldEnterUsername;
	}

	public void setFrmtdtxtfldEnterUsername(JFormattedTextField frmtdtxtfldEnterUsername) {
		this.frmtdtxtfldEnterUsername = frmtdtxtfldEnterUsername;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JLabel getLblBeMyPlayer() {
		return lblBeMyPlayer;
	}

	public void setLblBeMyPlayer(JLabel lblBeMyPlayer) {
		this.lblBeMyPlayer = lblBeMyPlayer;
	}

	public JButton getBtnForgotPassword() {
		return btnForgotPassword;
	}

	public void setBtnForgotPassword(JButton btnForgotPassword) {
		this.btnForgotPassword = btnForgotPassword;
	}
	
	
	
	//	set text
	
}
