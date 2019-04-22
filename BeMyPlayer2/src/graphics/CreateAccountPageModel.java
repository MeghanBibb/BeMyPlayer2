package graphics;

import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import firebase.ImageConverter;
import model.ResourceManager;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateAccountPageModel.
 */
public class CreateAccountPageModel {
	
	/** The Constant DEFAULT_PROFILE_IMAGE. */
	public static final BufferedImage DEFAULT_PROFILE_IMAGE = 
			ImageConverter.convertToJPG(ResourceManager.loadImage("defaultIcon.png"));
	
	/** The back. */
	//	init buttons
	private JButton back;
	
	/** The next. */
	private JButton next;
	
	/** The pwd enter pass. */
	//	init fields and listeners 
	private JPasswordField pwdEnterPass;
	
	/** The pwd validate pass. */
	private JPasswordField pwdValidatePass;
	
	/** The frmtdtxtfld enter username. */
	private JFormattedTextField frmtdtxtfldEnterUsername;
	
	/** The sec QA. */
	private JFormattedTextField secQA;
	
	/** The age. */
	private JFormattedTextField age;
	
	/** The gender box. */
	private JComboBox<String> genderBox;
	
	/** The security Q. */
	private JComboBox<String> securityQ;
	
	/** The profile img. */
	private BufferedImage profileImg;
	
	/** The enter email. */
	private JFormattedTextField enterEmail;
	
	/** The check list. */
	private List<JCheckBox> checkList;
	
	/** The check lister. */
	private List<Boolean> checkLister;
	
	/** The dob. */
	private Date dob;
	
	/** The image path. */
	private String imagePath;
	
	/** The char description. */
	private JTextArea charDescription;
	
	/** The profile pic label. */
	private JLabel profilePicLabel;
	
	/** The charcount. */
	private JLabel charcount;
	
	/**
	 * Gets the back.
	 *
	 * @return the back
	 */
	public JButton getBack() {
		return back;
	}
	
	/**
	 * Sets the back.
	 *
	 * @param back the new back
	 */
	public void setBack(JButton back) {
		this.back = back;
	}
	
	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public JButton getNext() {
		return next;
	}
	
	/**
	 * Sets the next.
	 *
	 * @param next the new next
	 */
	public void setNext(JButton next) {
		this.next = next;
	}
	
	/**
	 * Gets the pwd enter pass.
	 *
	 * @return the pwd enter pass
	 */
	public JPasswordField getPwdEnterPass() {
		return this.pwdEnterPass;
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
		return this.pwdValidatePass;
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
		return this.frmtdtxtfldEnterUsername;
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
		return this.secQA;
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
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return this.getGenderBox().getSelectedItem().toString();
		//return gender;
	}
	
	/**
	 * Gets the security questions.
	 *
	 * @return the security questions
	 */
	public String getSecurityQuestions() {
		return this.getSecurityQ().getSelectedItem().toString();
		//return securityQuestions;
	}
	
	/**
	 * Gets the char description.
	 *
	 * @return the char description
	 */
	public JTextArea getCharDescription() {
		return this.charDescription;
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
		return this.profilePicLabel;
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
	 * Gets the age.
	 *
	 * @return the age
	 */
	public JFormattedTextField getAge() {
		return this.age;
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
	 * Gets the security Q.
	 *
	 * @return the security Q
	 */
	public JComboBox<String> getSecurityQ() {
		return this.securityQ;
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
	 * @param string the new image path
	 */
	public void setImagePath(String string) {
		this.imagePath = string;
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
	 * Sets the check lister.
	 *
	 * @param checkLister the new check lister
	 */
	public void setCheckLister(List<Boolean> checkLister) {
		this.checkLister = checkLister;
	}
	
	/**
	 * Gets the enter email.
	 *
	 * @return the enter email
	 */
	public JFormattedTextField getEnterEmail() {
		return enterEmail;
	}
	
	/**
	 * Sets the enter email.
	 *
	 * @param enterEmail the new enter email
	 */
	public void setEnterEmail(JFormattedTextField enterEmail) {
		this.enterEmail = enterEmail;
	}
	
	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 * @throws ParseException the parse exception
	 */
	public Date getDob() throws ParseException {
		SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy");
		this.dob = (Date)tf.parse(this.age.getText());
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
