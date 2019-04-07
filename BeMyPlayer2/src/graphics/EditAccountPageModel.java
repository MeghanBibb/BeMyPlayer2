package graphics;

import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;

public class EditAccountPageModel {
	
	//init label
	private JLabel lblBeMyPlayer;
	
	//init primary buttons
	private JButton btnBack;
	private JButton btnSubmit;
	private JButton btnCancel;
	private JButton btnAccount;
	private JButton btnQuestionnaire;
	private JButton btnProfile;
	private JButton btnUpgrade;
	
	//init account fields
	private JPasswordField pwdEnterPass;
	private JPasswordField pwdValidatePass;
	private JFormattedTextField frmtdtxtfldEnterUsername;
	private JFormattedTextField secQA;
	private JFormattedTextField age;
	private String gender;
	private String securityQuestions;
	private JComboBox genderBox;
	private JComboBox securityQ;
	
	//init questionnaire fields
	private List<JCheckBox> checkList;
	private List<Boolean> checkLister;
	
	//init profile fields
	private Image profileImg;
	private String imagePath;
	private JTextArea charDescription;
	private JLabel profilePicLabel;
	private Date dob;
	
	public JLabel getLblBeMyPlayer() {
		return lblBeMyPlayer;
	}
	public void setLblBeMyPlayer(JLabel lblBeMyPlayer) {
		this.lblBeMyPlayer = lblBeMyPlayer;
	}
	public JButton getBtnBack() {
		return btnBack;
	}
	public void setBtnBack(JButton btnBack) {
		this.btnBack = btnBack;
	}
	public JButton getBtnSubmit() {
		return btnSubmit;
	}
	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
	public JButton getBtnAccount() {
		return btnAccount;
	}
	public void setBtnAccount(JButton btnAccount) {
		this.btnAccount = btnAccount;
	}
	public JButton getBtnQuestionnaire() {
		return btnQuestionnaire;
	}
	public void setBtnQuestionnaire(JButton btnQuestionnaire) {
		this.btnQuestionnaire = btnQuestionnaire;
	}
	public JButton getBtnProfile() {
		return btnProfile;
	}
	public void setBtnProfile(JButton btnProfile) {
		this.btnProfile = btnProfile;
	}
	public JButton getBtnUpgrade() {
		return btnUpgrade;
	}
	public void setBtnUpgrade(JButton btnUpgrade) {
		this.btnUpgrade = btnUpgrade;
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
	public JFormattedTextField getAge() {
		return age;
	}
	public void setAge(JFormattedTextField age) {
		this.age = age;
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
	public JComboBox getGenderBox() {
		return genderBox;
	}
	public void setGenderBox(JComboBox genderBox) {
		this.genderBox = genderBox;
	}
	public JComboBox getSecurityQ() {
		return securityQ;
	}
	public void setSecurityQ(JComboBox securityQ) {
		this.securityQ = securityQ;
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
	public void setCheckLister(List<Boolean> checkLister) {
		this.checkLister = checkLister;
	}
	public Image getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(Image profileImg) {
		this.profileImg = profileImg;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public JTextArea getCharDescription() {
		return charDescription;
	}
	public void setCharDescription(JTextArea charDescription) {
		this.charDescription = charDescription;
	}
	public JLabel getProfilePicLabel() {
		return profilePicLabel;
	}
	public void setProfilePicLabel(JLabel profilePicLabel) {
		this.profilePicLabel = profilePicLabel;
	}
	
	public Date getDob() throws ParseException {
		SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy");
		this.dob = tf.parse(this.age.getText().strip());
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

}
