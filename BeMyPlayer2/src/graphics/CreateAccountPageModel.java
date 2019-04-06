package graphics;

import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.List;

import javax.swing.*;

public class CreateAccountPageModel {
	
	//	init buttons
	private JButton back;
	
	private JButton next;
	
	//	init fields and listeners 
	private JPasswordField pwdEnterPass;
	private JPasswordField pwdValidatePass;
	private JFormattedTextField frmtdtxtfldEnterUsername;
	private JFormattedTextField secQA;
	private JFormattedTextField age;
	private String gender;
	private String securityQuestions;
	private JComboBox genderBox;
	private JComboBox securityQ;
	private Image profileImg;
	private JFormattedTextField enterEmail;
	private List<JCheckBox> checkList;
	private List<Boolean> checkLister;
	
	private String imagePath;
	
	private JTextArea charDescription;
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
		return this.pwdEnterPass;
	}
	public void setPwdEnterPass(JPasswordField pwdEnterPass) {
		this.pwdEnterPass = pwdEnterPass;
	}
	public JPasswordField getPwdValidatePass() {
		return this.pwdValidatePass;
	}
	public void setPwdValidatePass(JPasswordField pwdValidatePass) {
		this.pwdValidatePass = pwdValidatePass;
	}
	public JFormattedTextField getFrmtdtxtfldEnterUsername() {
		return this.frmtdtxtfldEnterUsername;
	}
	public void setFrmtdtxtfldEnterUsername(JFormattedTextField frmtdtxtfldEnterUsername) {
		this.frmtdtxtfldEnterUsername = frmtdtxtfldEnterUsername;
	}
	public JFormattedTextField getSecQA() {
		return this.secQA;
	}
	public void setSecQA(JFormattedTextField secQA) {
		this.secQA = secQA;
	}
	public String getGender() {
		return this.getGenderBox().getSelectedItem().toString();
		//return gender;
	}
	public void setGender(String gender) {
	
		this.gender = gender;
	}
	public String getSecurityQuestions() {
		return this.getSecurityQ().getSelectedItem().toString();
		//return securityQuestions;
	}
	public void setSecurityQuestions(String securityQuestions) {
		this.securityQuestions = securityQuestions;
	}
	public JTextArea getCharDescription() {
		return this.charDescription;
	}
	public void setCharDescription(JTextArea charDescription) {
		this.charDescription = charDescription;
	}
	public JLabel getProfilePicLabel() {
		return this.profilePicLabel;
	}
	public void setProfilePicLabel(JLabel profilePicLabel) {
		this.profilePicLabel = profilePicLabel;
	}
	public JFormattedTextField getAge() {
		return this.age;
	}
	public void setAge(JFormattedTextField age) {
		this.age = age;
	}
	public JComboBox getSecurityQ() {
		return this.securityQ;
	}
	public void setSecurityQ(JComboBox securityQ) {
		this.securityQ = securityQ;
	}
	public JComboBox getGenderBox() {
		return genderBox;
	}
	public void setGenderBox(JComboBox genderBox) {
		this.genderBox = genderBox;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String string) {
		this.imagePath = string;
	}
	public Image getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(Image profileImg) {
		this.profileImg = profileImg;
	}
	public List<JCheckBox> getCheckList() {
		return checkList;
	}
	public void setCheckList(List<JCheckBox> checkList) {
		this.checkList = checkList;
	}
	public List<Boolean> getCheckLister() {
		return checkLister;
	}
	public List<Boolean> getPlatforms(){
		return checkLister.subList(0, 6);
	}
	public List<Boolean> getGenres(){
		return checkLister.subList(6, checkList.size());
	}
	public void setCheckLister(List<Boolean> checkLister) {
		this.checkLister = checkLister;
	}
	public JFormattedTextField getEnterEmail() {
		return enterEmail;
	}
	public void setEnterEmail(JFormattedTextField enterEmail) {
		this.enterEmail = enterEmail;
	}
	
	
}
