package tests;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
/*
import org.junit.jupiter.api.*;

import graphics.PaymentPageController;
import graphics.PaymentPageModel;
import graphics.PaymentPageView;
import graphics.ForgotPassPageController;
import graphics.ForgotPassPageModel;
import graphics.ForgotPassPageView;
import graphics.InvalidPopup;
import model.Account;
import model.Profile;

import static org.junit.Assert.*;

public class PaymentTest {
	
	PaymentPageModel model;
	PaymentPageView view;
	PaymentPageController controller;
	
	//this initializes the controller/model with valid, correct data
	@BeforeEach
	@DisplayName("Create Model")
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
		
	//this has valid data, should pass
	@Test
	public void testPasswordVerification() {
		assert(controller.verifyPayment());
	}
		
		@Test
		public void testBadMonth() {
			JFormattedTextField cardMonth = new JFormattedTextField("14");
			model.setCardMonth(cardMonth);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
		
		//@Test
		public void testBadYear() {
			JFormattedTextField cardYear = new JFormattedTextField("2025");
			model.setCardYear(cardYear);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
		
		//@Test
		public void testBadCard() {
			JFormattedTextField cardNumber = new JFormattedTextField("1234567");
			model.setCardNumber(cardNumber);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
		
		//@Test
		public void testEmptyCard() {
			JFormattedTextField cardNumber = new JFormattedTextField("");
			model.setCardNumber(cardNumber);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
		
		//@Test
		public void testBadCVC() {
			JFormattedTextField cardCVC = new JFormattedTextField("12");
			model.setCardCVC(cardCVC);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
		
		//@Test
		public void testBadCVC2() {
			JFormattedTextField cardCVC = new JFormattedTextField("1234");
			model.setCardCVC(cardCVC);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
		
		//@Test
		public void testEmptyCVC() {
			JFormattedTextField cardCVC = new JFormattedTextField("");
			model.setCardCVC(cardCVC);
			controller.setPaymentModel(model);
			
			assertFalse(controller.verifyPayment());
		}
}
*/