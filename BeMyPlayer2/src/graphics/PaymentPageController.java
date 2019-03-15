package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaymentPageController implements ActionListener{
	
	public static final String BACK = "back";
	public static final String SUBMIT = "submit";
	
	private PaymentPageModel paymentModel = null;
	private JPanel paymentPanel = null;
	
	public void launchPaymentPage(JFrame j) {
		PaymentPageView.launchPaymentPage(this,j);
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
				GraphicsController.launchEditPage();
				break;
			case SUBMIT:
				System.out.println("Submit");
				GraphicsController.launchEditPage();
				break;
		}
		
	}

}
