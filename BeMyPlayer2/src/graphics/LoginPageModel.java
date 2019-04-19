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

// TODO: Auto-generated Javadoc
/**
 * The Class LoginPageModel.
 */
public class LoginPageModel {
	//	data members of login
	
	/** The default image. */
	private String defaultImage = null;
	
	/** The lbl username. */
	//  init login fields
	private JLabel lblUsername;
	
	/** The lbl password. */
	private JLabel lblPassword;
	
	/** The btn login. */
	//	init buttons
	private JButton btnLogin;
	
	/** The btn create new account. */
	private JButton btnCreateNewAccount;
	
	/** The btn forgot password. */
	private JButton btnForgotPassword;
	
	/** The btn exit. */
	private JButton btnExit;
	
	/** The pwd enter pass. */
	//	init fields and listeners 
	private JPasswordField pwdEnterPass;
	
	/** The frmtdtxtfld enter username. */
	private JFormattedTextField frmtdtxtfldEnterUsername;
	
	/** The lbl new label. */
	//	set images
	private JLabel lblNewLabel;

	/** The lbl be my player. */
	private JLabel lblBeMyPlayer;
	
	/**
	 * Gets the default image.
	 *
	 * @return the default image
	 */
	public String getDefaultImage() {
		return defaultImage;
	}

	/**
	 * Sets the default image.
	 *
	 * @param defaultImage the new default image
	 */
	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	/**
	 * Gets the btn login.
	 *
	 * @return the btn login
	 */
	public JButton getBtnLogin() {
		return btnLogin;
	}

	/**
	 * Sets the btn login.
	 *
	 * @param btnLogin the new btn login
	 */
	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	/**
	 * Gets the btn create new account.
	 *
	 * @return the btn create new account
	 */
	public JButton getBtnCreateNewAccount() {
		return btnCreateNewAccount;
	}

	/**
	 * Sets the btn create new account.
	 *
	 * @param btnCreateNewAccount the new btn create new account
	 */
	public void setBtnCreateNewAccount(JButton btnCreateNewAccount) {
		this.btnCreateNewAccount = btnCreateNewAccount;
	}
	
	/**
	 * Gets the btn exit.
	 *
	 * @return the btn exit
	 */
	public JButton getBtnExit() {
		return this.btnExit;
	}
	
	/**
	 * Sets the btn exit.
	 *
	 * @param exitButton the new btn exit
	 */
	public void setBtnExit(JButton exitButton) {
		this.btnExit = exitButton;
	}

	/**
	 * Gets the pwd enter pass.
	 *
	 * @return the pwd enter pass
	 */
	public JPasswordField getPwdEnterPass() {
		return pwdEnterPass;
	}

	/**
	 * Sets the pwd enter pass.
	 *
	 * @param pwdEnterPass the new pwd enter pass
	 */
	public void setPwdEnterPass(JPasswordField pwdEnterPass) {
		this.pwdEnterPass = pwdEnterPass;
	}

	/**
	 * Gets the frmtdtxtfld enter username.
	 *
	 * @return the frmtdtxtfld enter username
	 */
	public JFormattedTextField getFrmtdtxtfldEnterUsername() {
		return frmtdtxtfldEnterUsername;
	}

	/**
	 * Sets the frmtdtxtfld enter username.
	 *
	 * @param frmtdtxtfldEnterUsername the new frmtdtxtfld enter username
	 */
	public void setFrmtdtxtfldEnterUsername(JFormattedTextField frmtdtxtfldEnterUsername) {
		this.frmtdtxtfldEnterUsername = frmtdtxtfldEnterUsername;
	}
	
	/**
	 * Gets the lbl username.
	 *
	 * @return the lbl username
	 */
	public JLabel getLblUsername() {
		return lblUsername;
	}
	
	/**
	 * Sets the lbl username.
	 *
	 * @param label the new lbl username
	 */
	public void setLblUsername(JLabel label) {
		this.lblUsername = label;
	}
	
	/**
	 * Gets the lbl password.
	 *
	 * @return the lbl password
	 */
	public JLabel getLblPassword() {
		return lblPassword;
	}
	
	/**
	 * Sets the lbl password.
	 *
	 * @param label the new lbl password
	 */
	public void setLblPassword(JLabel label) {
		this.lblPassword = label;
	}

	/**
	 * Gets the lbl new label.
	 *
	 * @return the lbl new label
	 */
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	/**
	 * Sets the lbl new label.
	 *
	 * @param lblNewLabel the new lbl new label
	 */
	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	/**
	 * Gets the lbl be my player.
	 *
	 * @return the lbl be my player
	 */
	public JLabel getLblBeMyPlayer() {
		return lblBeMyPlayer;
	}

	/**
	 * Sets the lbl be my player.
	 *
	 * @param lblBeMyPlayer the new lbl be my player
	 */
	public void setLblBeMyPlayer(JLabel lblBeMyPlayer) {
		this.lblBeMyPlayer = lblBeMyPlayer;
	}

	/**
	 * Gets the btn forgot password.
	 *
	 * @return the btn forgot password
	 */
	public JButton getBtnForgotPassword() {
		return btnForgotPassword;
	}

	/**
	 * Sets the btn forgot password.
	 *
	 * @param btnForgotPassword the new btn forgot password
	 */
	public void setBtnForgotPassword(JButton btnForgotPassword) {
		this.btnForgotPassword = btnForgotPassword;
	}
}
