package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import model.Account;
import model.InformationExpert;
import model.PaymentInfo;

public class PaymentPageController extends PageController{
	
	public static final String BACK = "back";
	public static final String SUBMIT = "submit";
	
	private PaymentPageModel paymentModel = null;
	private JPanel paymentPanel = null;
	private static Logger logger = Logger.getLogger(PaymentPageController.class.getName());
	
	public void launchPage(JFrame mainFrame, String back) {
		if(back != backPage) {
			backPage = back;
		}
		
		PaymentPageView.launchPaymentPage(this,mainFrame);
	}
	
	public PaymentPageModel getPaymentModel() {
		return paymentModel;
	}

	public void setPaymentModel(PaymentPageModel paymentModel) {
		this.paymentModel = paymentModel;
	}



	public JPanel getPaymentPanel() {
		return paymentPanel;
	}



	public void setPaymentPanel(JPanel paymentPanel) {
		this.paymentPanel = paymentPanel;
	}
	
	private boolean verifyPayment() {
		int month;
		int year;
		int cvc;
		BigInteger num;
		boolean isValid = true;
		List<String> warnings = new ArrayList<>();
		try {
			month = Integer.parseInt(paymentModel.getCardMonth().getText());
			if(month <= 0 && month >= 13) {
				isValid = false;
				warnings.add("Invalid month\n");
			}
			year = Integer.parseInt(paymentModel.getCardYear().getText());
			year += 2000;
			LocalDate now = LocalDate.now();
			int currentYear = now.getYear();
			if(currentYear > year || (year-2000) > 50) { // > 50 to ensure a two digit year
				isValid = false;
				warnings.add("Invalid year\n");
			} else if(currentYear == year && now.getMonthValue() > month) {
				isValid = false;
				warnings.add("Invalid card. Cannot be expired\n");
			}
		} catch(NumberFormatException e) {
			isValid = false;
			warnings.add("Invalid expiration date digits\n");
		}
		
		try {
			cvc = Integer.parseInt(paymentModel.getCardCVC().getText());
			if (cvc % 1000 != cvc) {
				isValid = false;
				warnings.add("Invalid cvc length\n");
			}
		} catch(NumberFormatException e) {
			isValid = false;
			warnings.add("Invalid cvc digits\n");
		}
		
		try {
			num = new BigInteger(paymentModel.getCardNumber().getText());
			if(paymentModel.getCardNumber().getText().length() != 16) {
				isValid = false;
				warnings.add("Invalid card number length\n");
			}
		} catch(NumberFormatException e) {
			isValid = false;
			warnings.add("Invalid card number digits\n");
		}
		if(isValid == false) {
			InvalidPopup p = new InvalidPopup(this.getPaymentPanel(),warnings);
		}
		return isValid;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case BACK:
				logger.info("back");
				GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE,backPage);
				break;
			case SUBMIT:
				if(verifyPayment()) {
					logger.info("attempting to submit payment info for " + InformationExpert.getActiveAccount().getEmail());
					PaymentInfo p = new PaymentInfo(this.getPaymentModel().getCardNumber().getText(), InformationExpert.getActiveUserID(),
							        Integer.parseInt(this.getPaymentModel().getCardMonth().getText()), Integer.parseInt(this.getPaymentModel().getCardYear().getText()),
							        Integer.parseInt(this.getPaymentModel().getCardCVC().getText()));
					try {
						InformationExpert.addPaymentInfo(p);
					} catch (DBFailureException e1) {
						logger.warning("Database Failure: Could not upload new Payment Info");
					}
					GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE,backPage);
				}
				break;
		}
		
	}

}
