package graphics;

import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * The Class EditAccountPageModel.
 */
public class EditAccountPageModel {
	
	/** The lbl be my player. */
	//init label
	private JLabel lblBeMyPlayer;
	
	/** The btn back. */
	//init primary buttons
	private JButton btnBack;
	
	/** The btn submit. */
	private JButton btnSubmit;
	
	/** The btn cancel. */
	private JButton btnCancel;
	
	/** The btn account. */
	private JButton btnAccount;
	
	/** The btn questionnaire. */
	private JButton btnQuestionnaire;
	
	/** The btn profile. */
	private JButton btnProfile;
	
	/** The btn upgrade. */
	private JButton btnUpgrade;
	
	/** The btn delete. */
	private JButton btnDelete;
	
	/** The btn mute. */
	private JButton btnMute;
	
	/** The pwd enter pass. */
	//init account fields
	private JPasswordField pwdEnterPass;
	
	/** The pwd validate pass. */
	private JPasswordField pwdValidatePass;
	
	/** The frmtdtxtfld enter username. */
	private JFormattedTextField frmtdtxtfldEnterUsername;
	
	/** The sec QA. */
	private JFormattedTextField secQA;
	
	/** The age. */
	private JFormattedTextField age;
	
	/** The gender. */
	private String gender;
	
	/** The security questions. */
	private String securityQuestions;
	
	/** The gender box. */
	private JComboBox<String> genderBox;
	
	/** The security Q. */
	private JComboBox<String> securityQ;
	
	/** The charcount. */
	private JLabel charcount;
	
	/** The check list. */
	//init questionnaire fields
	private List<JCheckBox> checkList;
	
	/** The check lister. */
	private List<Boolean> checkLister;
	
	/** The profile img. */
	//init profile fields
	private BufferedImage profileImg;
	
	/** The image path. */
	private String imagePath;
	
	/** The char description. */
	private JTextArea charDescription;
	
	/** The profile pic label. */
	private JLabel profilePicLabel;
	
	/** The dob. */
	private Date dob;
	
	
	
	/**
	 * Gets the btn mute.
	 *
	 * @return the btn mute
	 */
	public JButton getBtnMute() {
		return btnMute;
	}
	
	/**
	 * Sets the btn mute.
	 *
	 * @param btnMute the new btn mute
	 */
	public void setBtnMute(JButton btnMute) {
		this.btnMute = btnMute;
	}
	
	/**
	 * Gets the btn delete.
	 *
	 * @return the btn delete
	 */
	public JButton getBtnDelete() {
		return btnDelete;
	}
	
	/**
	 * Sets the btn delete.
	 *
	 * @param btnDelete the new btn delete
	 */
	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
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
	
	/**
	 * Gets the btn submit.
	 *
	 * @return the btn submit
	 */
	public JButton getBtnSubmit() {
		return btnSubmit;
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
	 * Gets the btn cancel.
	 *
	 * @return the btn cancel
	 */
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	/**
	 * Sets the btn cancel.
	 *
	 * @param btnCancel the new btn cancel
	 */
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
	
	/**
	 * Gets the btn account.
	 *
	 * @return the btn account
	 */
	public JButton getBtnAccount() {
		return btnAccount;
	}
	
	/**
	 * Sets the btn account.
	 *
	 * @param btnAccount the new btn account
	 */
	public void setBtnAccount(JButton btnAccount) {
		this.btnAccount = btnAccount;
	}
	
	/**
	 * Gets the btn questionnaire.
	 *
	 * @return the btn questionnaire
	 */
	public JButton getBtnQuestionnaire() {
		return btnQuestionnaire;
	}
	
	/**
	 * Sets the btn questionnaire.
	 *
	 * @param btnQuestionnaire the new btn questionnaire
	 */
	public void setBtnQuestionnaire(JButton btnQuestionnaire) {
		this.btnQuestionnaire = btnQuestionnaire;
	}
	
	/**
	 * Gets the btn profile.
	 *
	 * @return the btn profile
	 */
	public JButton getBtnProfile() {
		return btnProfile;
	}
	
	/**
	 * Sets the btn profile.
	 *
	 * @param btnProfile the new btn profile
	 */
	public void setBtnProfile(JButton btnProfile) {
		this.btnProfile = btnProfile;
	}
	
	/**
	 * Gets the btn upgrade.
	 *
	 * @return the btn upgrade
	 */
	public JButton getBtnUpgrade() {
		return btnUpgrade;
	}
	
	/**
	 * Sets the btn upgrade.
	 *
	 * @param btnUpgrade the new btn upgrade
	 */
	public void setBtnUpgrade(JButton btnUpgrade) {
		this.btnUpgrade = btnUpgrade;
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
	 * Gets the pwd validate pass.
	 *
	 * @return the pwd validate pass
	 */
	public JPasswordField getPwdValidatePass() {
		return pwdValidatePass;
	}
	
	/**
	 * Sets the pwd validate pass.
	 *
	 * @param pwdValidatePass the new pwd validate pass
	 */
	public void setPwdValidatePass(JPasswordField pwdValidatePass) {
		this.pwdValidatePass = pwdValidatePass;
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
	 * Gets the age.
	 *
	 * @return the age
	 */
	public JFormattedTextField getAge() {
		return age;
	}
	
	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(JFormattedTextField age) {
		this.age = age;
	}
	
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * Gets the gender box.
	 *
	 * @return the gender box
	 */
	public JComboBox<String> getGenderBox() {
		return genderBox;
	}
	
	/**
	 * Sets the gender box.
	 *
	 * @param genderBox the new gender box
	 */
	public void setGenderBox(JComboBox<String> genderBox) {
		this.genderBox = genderBox;
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
	 * Gets the check list.
	 *
	 * @return the check list
	 */
	public List<JCheckBox> getCheckList() {
		return checkList;
	}
	
	/**
	 * Sets the check list.
	 *
	 * @param checkList the new check list
	 */
	public void setCheckList(List<JCheckBox> checkList) {
		this.checkList = checkList;
	}
	
	/**
	 * Gets the check lister.
	 *
	 * @return the check lister
	 */
	public List<Boolean> getCheckLister() {
		return checkLister;
	}
	
	/**
	 * Sets the check lister.
	 *
	 * @param checkLister the new check lister
	 */
	public void setCheckLister(List<Boolean> checkLister) {
		this.checkLister = checkLister;
	}
	
	/**
	 * Gets the platforms.
	 *
	 * @return the platforms
	 */
	public List<Boolean> getPlatforms(){
		return checkLister.subList(0, 6);
	}
	
	/**
	 * Gets the genres.
	 *
	 * @return the genres
	 */
	public List<Boolean> getGenres(){
		return checkLister.subList(6, checkList.size());
	}
	
	/**
	 * Gets the profile img.
	 *
	 * @return the profile img
	 */
	public BufferedImage getProfileImg() {
		return profileImg;
	}
	
	/**
	 * Sets the profile img.
	 *
	 * @param profileImg the new profile img
	 */
	public void setProfileImg(BufferedImage profileImg) {
		this.profileImg = profileImg;
	}
	
	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return imagePath;
	}
	
	/**
	 * Sets the image path.
	 *
	 * @param imagePath the new image path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	/**
	 * Gets the char description.
	 *
	 * @return the char description
	 */
	public JTextArea getCharDescription() {
		return charDescription;
	}
	
	/**
	 * Sets the char description.
	 *
	 * @param charDescription the new char description
	 */
	public void setCharDescription(JTextArea charDescription) {
		this.charDescription = charDescription;
	}
	
	/**
	 * Gets the profile pic label.
	 *
	 * @return the profile pic label
	 */
	public JLabel getProfilePicLabel() {
		return profilePicLabel;
	}
	
	/**
	 * Sets the profile pic label.
	 *
	 * @param profilePicLabel the new profile pic label
	 */
	public void setProfilePicLabel(JLabel profilePicLabel) {
		this.profilePicLabel = profilePicLabel;
	}
	
	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 * @throws ParseException the parse exception
	 */
	public Date getDob() throws ParseException {
		SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy");
		this.dob = tf.parse(this.age.getText().strip());
		return dob;
	}
	
	/**
	 * Sets the dob.
	 *
	 * @param dob the new dob
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	/**
	 * Gets the charcount.
	 *
	 * @return the charcount
	 */
	public JLabel getCharcount() {
		return charcount;
	}
	
	/**
	 * Sets the charcount.
	 *
	 * @param charcount the new charcount
	 */
	public void setCharcount(JLabel charcount) {
		this.charcount = charcount;
	}
}
