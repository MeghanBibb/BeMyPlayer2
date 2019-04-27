package forgotPassword;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

/**
 * The Class ForgotPassPageModel.
 */
public class ForgotPassPageModel {
	
	/** The btn submit. */
	private JButton btnSubmit;
	
	/** The btn back. */
	private JButton btnBack;
	
	/** The lbl security question. */
	//init text
	private JLabel lblSecurityQuestion;
	
	/** The lbl answer. */
	private JLabel lblAnswer;
	
	/** The security questions. */
	private String securityQuestions;
	
	/** The sec QA. */
	private JFormattedTextField secQA;
	
	/** The security Q. */
	private JComboBox<String> securityQ;
	
	/** The frmtdtextfld enter username. */
	//	init fields and listeners 
	private JFormattedTextField frmtdtextfldEnterUsername;
	
	/** The frmtdtextfld enter email. */
	private JFormattedTextField frmtdtextfldEnterEmail;
	
	/** The frmtdtextfld enter new password. */
	private JPasswordField frmtdtextfldEnterNewPassword;
	
	/** The pwd enter pass. */
	private JPasswordField pwdEnterPass;

	/** The lbl email. */
	private JLabel lblEmail;
	
	/** The lbl username. */
	private JLabel lblUsername;
	
	/** The lbl new password. */
	private JLabel lblNewPassword;
	
	/** The lbl re enter new password. */
	private JLabel lblReEnterNewPassword;


	/**
	 * Gets the lbl email.
	 *
	 * @return the lbl email
	 */
	public JLabel getLblEmail() {
		return lblEmail;
	}

	/**
	 * Sets the lbl email.
	 *
	 * @param lblEmail the new lbl email
	 */
	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
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
	 * @param lblUsername the new lbl username
	 */
	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}

	/**
	 * Gets the lbl new password.
	 *
	 * @return the lbl new password
	 */
	public JLabel getLblNewPassword() {
		return lblNewPassword;
	}

	/**
	 * Sets the lbl new password.
	 *
	 * @param lblNewPassword the new lbl new password
	 */
	public void setLblNewPassword(JLabel lblNewPassword) {
		this.lblNewPassword = lblNewPassword;
	}

	/**
	 * Gets the lbl re enter new password.
	 *
	 * @return the lbl re enter new password
	 */
	public JLabel getLblReEnterNewPassword() {
		return lblReEnterNewPassword;
	}

	/**
	 * Sets the lbl re enter new password.
	 *
	 * @param lblReEnterNewPassword the new lbl re enter new password
	 */
	public void setLblReEnterNewPassword(JLabel lblReEnterNewPassword) {
		this.lblReEnterNewPassword = lblReEnterNewPassword;
	}

	/**
	 * Gets the frmtdtextfld enter email.
	 *
	 * @return the frmtdtextfld enter email
	 */
	public JFormattedTextField getFrmtdtextfldEnterEmail() {
		return frmtdtextfldEnterEmail;
	}

	/**
	 * Sets the frmtdtextfld enter email.
	 *
	 * @param frmtdtextfldEnterEmail the new frmtdtextfld enter email
	 */
	public void setFrmtdtextfldEnterEmail(JFormattedTextField frmtdtextfldEnterEmail) {
		this.frmtdtextfldEnterEmail = frmtdtextfldEnterEmail;
	}

	/**
	 * Gets the frmtdtextfld enter new password.
	 *
	 * @return the frmtdtextfld enter new password
	 */
	public JPasswordField getFrmtdtextfldEnterNewPassword() {
		return frmtdtextfldEnterNewPassword;
	}

	/**
	 * Sets the frmtdtextfld enter new password.
	 *
	 * @param frmtdtextfldEnterNewPassword the new frmtdtextfld enter new password
	 */
	public void setFrmtdtextfldEnterNewPassword(JPasswordField frmtdtextfldEnterNewPassword) {
		this.frmtdtextfldEnterNewPassword = frmtdtextfldEnterNewPassword;
	}

	/**
	 * Gets the frmtdtextfld enter username.
	 *
	 * @return the frmtdtextfld enter username
	 */
	public JFormattedTextField getFrmtdtextfldEnterUsername() {
		return frmtdtextfldEnterUsername;
	}

	/**
	 * Sets the frmtdtextfld enter username.
	 *
	 * @param frmtdtextfldEnterUsername the new frmtdtextfld enter username
	 */
	public void setFrmtdtextfldEnterUsername(JFormattedTextField frmtdtextfldEnterUsername) {
		this.frmtdtextfldEnterUsername = frmtdtextfldEnterUsername;
	}

	/**
	 * Gets the btn submit.
	 *
	 * @return the btn submit
	 */
	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	/**
	 * Sets the btn logout.
	 *
	 * @param btnLogout the new btn logout
	 */
	public void setBtnLogout(JButton btnLogout) {
		this.btnSubmit = btnLogout;
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
	 * Gets the lbl security question.
	 *
	 * @return the lbl security question
	 */
	public JLabel getLblSecurityQuestion() {
		return lblSecurityQuestion;
	}

	/**
	 * Sets the lbl security question.
	 *
	 * @param lblSecurityQuestion the new lbl security question
	 */
	public void setLblSecurityQuestion(JLabel lblSecurityQuestion) {
		this.lblSecurityQuestion = lblSecurityQuestion;
	}

	/**
	 * Gets the lbl answer.
	 *
	 * @return the lbl answer
	 */
	public JLabel getLblAnswer() {
		return lblAnswer;
	}

	/**
	 * Sets the lbl answer.
	 *
	 * @param lblAnswer the new lbl answer
	 */
	public void setLblAnswer(JLabel lblAnswer) {
		this.lblAnswer = lblAnswer;
	}

	/**
	 * Gets the security questions.
	 *
	 * @return the security questions
	 */
	public String getSecurityQuestions() {
		return securityQuestions;
	}

	/**
	 * Sets the security questions.
	 *
	 * @param securityQuestions the new security questions
	 */
	public void setSecurityQuestions(String securityQuestions) {
		this.securityQuestions = securityQuestions;
	}

	/**
	 * Gets the sec QA.
	 *
	 * @return the sec QA
	 */
	public JFormattedTextField getSecQA() {
		return secQA;
	}

	/**
	 * Sets the sec QA.
	 *
	 * @param secQA the new sec QA
	 */
	public void setSecQA(JFormattedTextField secQA) {
		this.secQA = secQA;
	}

	/**
	 * Gets the security Q.
	 *
	 * @return the security Q
	 */
	public JComboBox<String> getSecurityQ() {
		return securityQ;
	}

	/**
	 * Sets the security Q.
	 *
	 * @param securityQ the new security Q
	 */
	public void setSecurityQ(JComboBox<String> securityQ) {
		this.securityQ = securityQ;
	}

	/**
	 * Sets the btn submit.
	 *
	 * @param btnSubmit the new btn submit
	 */
	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	/**
	 * Gets the btn back.
	 *
	 * @return the btn back
	 */
	public JButton getBtnBack() {
		return btnBack;
	}

	/**
	 * Sets the btn back.
	 *
	 * @param btnBack the new btn back
	 */
	public void setBtnBack(JButton btnBack) {
		this.btnBack = btnBack;
	}
	
}
