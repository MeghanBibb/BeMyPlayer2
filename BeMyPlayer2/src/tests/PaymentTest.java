package tests;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFormattedTextField;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import payment.PaymentPageController;
import payment.PaymentPageModel;
import payment.PaymentPageView;

public class PaymentTest {
	
	PaymentPageModel model;
	PaymentPageView view;
	PaymentPageController controller;
	
	//this initializes the controller/model with valid, correct data
	@BeforeEach
	//@DisplayName("Create Model")
	public void initModel() {
		model = new PaymentPageModel();
		controller = new PaymentPageController();
		view = new PaymentPageView();
		
		JFormattedTextField cardCVC = new JFormattedTextField("123");
		model.setCardCVC(cardCVC);
			
		JFormattedTextField cardMonth = new JFormattedTextField("11");
		model.setCardMonth(cardMonth);
			
		JFormattedTextField cardNumber = new JFormattedTextField("1234567891234567");
		model.setCardNumber(cardNumber);
			
		JFormattedTextField cardYear = new JFormattedTextField("25");
		model.setCardYear(cardYear);
			
		controller.setPaymentModel(model);
	}
		
		
		@Test
		public void testBadMonth() {
			JFormattedTextField cardMonth2 = new JFormattedTextField("14");
			model.setCardMonth(cardMonth2);
			controller.setPaymentModel(model);
			
			assertTrue(controller.verifyPayment());
		}
		
		@Test
		public void testBadYear() {

			
			JFormattedTextField cardYear2 = new JFormattedTextField("2025");
			model.setCardYear(cardYear2);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
		
		@Test
		public void testBadCard() {

			
			JFormattedTextField cardNumber2 = new JFormattedTextField("1234567");
			model.setCardNumber(cardNumber2);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
		
		@Test
		public void testEmptyCard() {

			JFormattedTextField cardNumber2 = new JFormattedTextField("");
			model.setCardNumber(cardNumber2);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
		
		@Test
		public void testBadCVC() {

			
			JFormattedTextField cardCVC2 = new JFormattedTextField("12");
			model.setCardCVC(cardCVC2);
			controller.setPaymentModel(model);
			
			assertTrue(controller.verifyPayment());
		}
		
		@Test
		public void testBadCVC2() {

			
			JFormattedTextField cardCVC2 = new JFormattedTextField("1234");
			model.setCardCVC(cardCVC2);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
		
		@Test
		public void testEmptyCVC() {

			
			JFormattedTextField cardCVC2 = new JFormattedTextField("");
			model.setCardCVC(cardCVC2);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
}
