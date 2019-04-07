package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Account;

public class PaymentPageController extends PageController{
	
	public static final String BACK = "back";
	public static final String SUBMIT = "submit";
	
	private PaymentPageModel paymentModel = null;
	private JPanel paymentPanel = null;
	
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



	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case BACK:
				System.out.println("back");
				GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE,backPage);
				break;
			case SUBMIT:
				System.out.println("Submit");
				GraphicsController.processPage(PageCreator.EDIT_ACCOUNT_PAGE,backPage);
				break;
		}
		
	}

}
