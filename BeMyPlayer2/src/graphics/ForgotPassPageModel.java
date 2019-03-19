package graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class ForgotPassPageModel {
	private JButton btnSubmit;
	private JButton back;
	//init text
	private JLabel lblSecurityQuestion;
	private JLabel lblAnswer;
	
	private String securityQuestions;
	
	private JFormattedTextField secQA;
	private JComboBox securityQ;
	
	//	init fields and listeners 
	private JFormattedTextField frmtdtextfldEnterUsername;
	private JFormattedTextField frmtdtextfldEnterEmail;
	private JFormattedTextField frmtdtextfldEnterNewPassword;
	private JFormattedTextField pwdEnterPass;
	

	public JFormattedTextField getFrmtdtextfldEnterEmail() {
		return frmtdtextfldEnterEmail;
	}

	public void setFrmtdtextfldEnterEmail(JFormattedTextField frmtdtextfldEnterEmail) {
		this.frmtdtextfldEnterEmail = frmtdtextfldEnterEmail;
	}

	public JFormattedTextField getFrmtdtextfldEnterNewPassword() {
		return frmtdtextfldEnterNewPassword;
	}

	public void setFrmtdtextfldEnterNewPassword(JFormattedTextField frmtdtextfldEnterNewPassword) {
		this.frmtdtextfldEnterNewPassword = frmtdtextfldEnterNewPassword;
	}

	public JFormattedTextField getFrmtdtextfldEnterUsername() {
		return frmtdtextfldEnterUsername;
	}

	public void setFrmtdtextfldEnterUsername(JFormattedTextField frmtdtextfldEnterUsername) {
		this.frmtdtextfldEnterUsername = frmtdtextfldEnterUsername;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnLogout(JButton btnLogout) {
		this.btnSubmit = btnLogout;
	}

	public JFormattedTextField getPwdEnterPass() {
		return pwdEnterPass;
	}

	public void setPwdEnterPass(JFormattedTextField pwdEnterPass) {
		this.pwdEnterPass = pwdEnterPass;
	}

	public JLabel getLblSecurityQuestion() {
		return lblSecurityQuestion;
	}

	public void setLblSecurityQuestion(JLabel lblSecurityQuestion) {
		this.lblSecurityQuestion = lblSecurityQuestion;
	}

	public JLabel getLblAnswer() {
		return lblAnswer;
	}

	public void setLblAnswer(JLabel lblAnswer) {
		this.lblAnswer = lblAnswer;
	}

	public String getSecurityQuestions() {
		return securityQuestions;
	}

	public void setSecurityQuestions(String securityQuestions) {
		this.securityQuestions = securityQuestions;
	}

	public JFormattedTextField getSecQA() {
		return secQA;
	}

	public void setSecQA(JFormattedTextField secQA) {
		this.secQA = secQA;
	}

	public JComboBox getSecurityQ() {
		return securityQ;
	}

	public void setSecurityQ(JComboBox securityQ) {
		this.securityQ = securityQ;
	}

	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}
	
}
