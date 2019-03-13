package graphics;

import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class CreateAccountPageModel {
	
	//	init buttons
	private JButton back;
	
	private JButton next;
	
	//	init fields and listeners 
	private JPasswordField pwdEnterPass;
	private JPasswordField pwdValidatePass;
	private JFormattedTextField frmtdtxtfldEnterUsername;
	private JFormattedTextField secQA;
	private String gender;
	private String securityQuestions;
	
	private JFormattedTextField charDescription;
	private JLabel profilePicLabel;
	
	public JButton getBack() {
		return back;
	}
	public void setBack(JButton back) {
		this.back = back;
	}
	public JButton getNext() {
		return next;
	}
	public void setNext(JButton next) {
		this.next = next;
	}
	public JPasswordField getPwdEnterPass() {
		return pwdEnterPass;
	}
	public void setPwdEnterPass(JPasswordField pwdEnterPass) {
		this.pwdEnterPass = pwdEnterPass;
	}
	public JPasswordField getPwdValidatePass() {
		return pwdValidatePass;
	}
	public void setPwdValidatePass(JPasswordField pwdValidatePass) {
		this.pwdValidatePass = pwdValidatePass;
	}
	public JFormattedTextField getFrmtdtxtfldEnterUsername() {
		return frmtdtxtfldEnterUsername;
	}
	public void setFrmtdtxtfldEnterUsername(JFormattedTextField frmtdtxtfldEnterUsername) {
		this.frmtdtxtfldEnterUsername = frmtdtxtfldEnterUsername;
	}
	public JFormattedTextField getSecQA() {
		return secQA;
	}
	public void setSecQA(JFormattedTextField secQA) {
		this.secQA = secQA;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSecurityQuestions() {
		return securityQuestions;
	}
	public void setSecurityQuestions(String securityQuestions) {
		this.securityQuestions = securityQuestions;
	}
	public JFormattedTextField getCharDescription() {
		return charDescription;
	}
	public void setCharDescription(JFormattedTextField charDescription) {
		this.charDescription = charDescription;
	}
	public JLabel getProfilePicLabel() {
		return profilePicLabel;
	}
	public void setProfilePicLabel(JLabel profilePicLabel) {
		this.profilePicLabel = profilePicLabel;
	}
	
	
}
