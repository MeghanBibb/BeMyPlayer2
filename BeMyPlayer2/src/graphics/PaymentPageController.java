package graphics;

import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import firebase.DBFailureException;
import model.InformationExpert;
import model.PaymentInfo;

/**
 * The Class PaymentPageController.
 */
public class PaymentPageController extends PageController{
	
	/** The Constant BACK. */
	public static final String BACK = "back";
	
	public InvalidPopup pop;
	
	/** The Constant SUBMIT. */
	public static final String SUBMIT = "submit";
	
	/** The payment model. */
	private PaymentPageModel paymentModel = null;
	
	/** The payment panel. */
	private JPanel paymentPanel = null;
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(PaymentPageController.class.getName());
	
	/* (non-Javadoc)
	 * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
	 */
	public void launchPage(JFrame mainFrame, String back) {
		if(!back.equals(backPage)) {
			backPage = back;
		}
		
		PaymentPageView.launchPaymentPage(this,mainFrame);
	}
	
	/**
	 * Gets the payment model.
	 *
	 * @return the payment model
	 */
	public PaymentPageModel getPaymentModel() {
		return paymentModel;
	}

	/**
	 * Sets the payment model.
	 *
	 * @param paymentModel the new payment model
	 */
	public void setPaymentModel(PaymentPageModel paymentModel) {
		this.paymentModel = paymentModel;
	}



	/**
	 * Gets the payment panel.
	 *
	 * @return the payment panel
	 */
	public JPanel getPaymentPanel() {
		return paymentPanel;
	}



	/**
	 * Sets the payment panel.
	 *
	 * @param paymentPanel the new payment panel
	 */
	public void setPaymentPanel(JPanel paymentPanel) {
		this.paymentPanel = paymentPanel;
	}
	
	/**
	 * Verify payment.
	 *
	 * @return true, if successful
	 */
	public boolean verifyPayment() {
		int month;
		int year;
		int cvc;
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
			@SuppressWarnings("unused")
			BigInteger num = new BigInteger(paymentModel.getCardNumber().getText());
			if(paymentModel.getCardNumber().getText().length() != 16) {
				isValid = false;
				warnings.add("Invalid card number length\n");
			}
		} catch(NumberFormatException e) {
			isValid = false;
			warnings.add("Invalid card number digits\n");
		}
		if(isValid == false) {
			pop = new InvalidPopup(this.getPaymentPanel(),warnings);
		}
		return isValid;
	}



	/* (non-Javadoc)
	 * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
	 */
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
					GraphicsController.processPage(PageCreator.PROFILE_PAGE,backPage);
				}
				break;
		}
		
	}

}
