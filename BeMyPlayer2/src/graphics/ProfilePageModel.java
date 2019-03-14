package graphics;

import java.awt.Image;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProfilePageModel {
	
	//init buttons
	private JButton btnBack;
	private JButton btnEdit;
	private JButton btnBlock;
	private JButton btnMessage;
	
	//init text
	private JLabel lblBeMyPlayer;
	private JLabel lblUsername;
	private JLabel lblAge;
	private JLabel lblGender;
	private JLabel lblConsoles;
	private JLabel txtField;
	private List<JCheckBox> checkList;
	
	public List<JCheckBox> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<JCheckBox> checkList) {
		this.checkList = checkList;
	}

	//init image
	private String imagePath;
	private JLabel profileImage;

	public JButton getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(JButton btnBack) {
		this.btnBack = btnBack;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public JButton getBtnBlock() {
		return btnBlock;
	}

	public void setBtnBlock(JButton btnBlock) {
		this.btnBlock = btnBlock;
	}

	public JButton getBtnMessage() {
		return btnMessage;
	}

	public void setBtnMessage(JButton btnMessage) {
		this.btnMessage = btnMessage;
	}

	public JLabel getLblBeMyPlayer() {
		return lblBeMyPlayer;
	}

	public void setLblBeMyPlayer(JLabel lblBeMyPlayer) {
		this.lblBeMyPlayer = lblBeMyPlayer;
	}

	public JLabel getLblUsername() {
		return lblUsername;
	}

	public void setLblUsername(JLabel lblUsername) {
		this.lblUsername = lblUsername;
	}

	public JLabel getLblAge() {
		return lblAge;
	}

	public void setLblAge(JLabel lblAge) {
		this.lblAge = lblAge;
	}

	public JLabel getLblGender() {
		return lblGender;
	}

	public void setLblGender(JLabel lblGender) {
		this.lblGender = lblGender;
	}

	public JLabel getLblConsoles() {
		return lblConsoles;
	}

	public void setLblConsoles(JLabel lblConsoles) {
		this.lblConsoles = lblConsoles;
	}

	public JLabel getTxtField() {
		return txtField;
	}

	public void setTxtField(JLabel txtField) {
		this.txtField = txtField;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public JLabel getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(JLabel profileImage) {
		this.profileImage = profileImage;
	}
	
	

}
