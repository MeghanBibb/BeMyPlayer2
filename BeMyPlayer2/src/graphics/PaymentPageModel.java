package graphics;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class PaymentPageModel {
	
	//init fields
	private JFormattedTextField cardNumber;
	private JFormattedTextField cardMonth;
	private JFormattedTextField cardYear;
	private JFormattedTextField cardCVC;
	
	//init labals
	private JLabel lblBeMyPlayer;
	private JLabel lblcardNumber;
	private JLabel lblcardDate;
	private JLabel lblSlash;
	private JLabel lblcardCVC;
	private JLabel lblDescription;
	
	//init buttons
	private JButton btnBack;
	private JButton btnSubmit;
	public JFormattedTextField getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(JFormattedTextField cardNumber) {
		this.cardNumber = cardNumber;
	}
	public JFormattedTextField getCardMonth() {
		return cardMonth;
	}
	public void setCardMonth(JFormattedTextField cardMonth) {
		this.cardMonth = cardMonth;
	}
	public JFormattedTextField getCardYear() {
		return cardYear;
	}
	public void setCardYear(JFormattedTextField cardYear) {
		this.cardYear = cardYear;
	}
	public JFormattedTextField getCardCVC() {
		return cardCVC;
	}
	public void setCardCVC(JFormattedTextField cardCVC) {
		this.cardCVC = cardCVC;
	}
	public JLabel getLblBeMyPlayer() {
		return lblBeMyPlayer;
	}
	public void setLblBeMyPlayer(JLabel lblBeMyPlayer) {
		this.lblBeMyPlayer = lblBeMyPlayer;
	}
	public JLabel getLblcardNumber() {
		return lblcardNumber;
	}
	public void setLblcardNumber(JLabel lblcardNumber) {
		this.lblcardNumber = lblcardNumber;
	}
	public JLabel getLblcardDate() {
		return lblcardDate;
	}
	public void setLblcardDate(JLabel lblcardDate) {
		this.lblcardDate = lblcardDate;
	}
	public JLabel getLblSlash() {
		return lblSlash;
	}
	public void setLblSlash(JLabel lblSlash) {
		this.lblSlash = lblSlash;
	}
	public JLabel getLblcardCVC() {
		return lblcardCVC;
	}
	public void setLblcardCVC(JLabel lblcardCVC) {
		this.lblcardCVC = lblcardCVC;
	}
	public JLabel getLblDescription() {
		return lblDescription;
	}
	public void setLblDescription(JLabel lblDescription) {
		this.lblDescription = lblDescription;
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
	
	

}
