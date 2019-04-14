package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PaymentPageView {
	
	public static void launchPaymentPage(PaymentPageController paymentController, JFrame mainFrame) {
		//init model
		paymentController.setPaymentModel(new PaymentPageModel());
		
		//init panel
		paymentController.setPaymentPanel(new BackgroundPanel(null));
		paymentController.getPaymentPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		paymentController.getPaymentPanel().setPreferredSize(new Dimension(500,400));
		paymentController.getPaymentPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(paymentController.getPaymentPanel());
		
		//init buttons
		JButton btnBack = new JButton("Cancel");
		btnBack.setBounds(10,10,90,40);
		btnBack.setActionCommand(paymentController.BACK);
		btnBack.setBackground(Colors.Yellow);
		btnBack.setForeground(Colors.Red);
		btnBack.setFont(Fonts.getFont(12f));
		btnBack.addActionListener(paymentController);
		paymentController.getPaymentModel().setBtnBack(btnBack);
		
		JButton btnSubmit = new JButton("Submit Info");
		btnSubmit.setBounds(70,350,120,40);
		btnSubmit.setActionCommand(paymentController.SUBMIT);
		btnSubmit.setBackground(Colors.Yellow);
		btnSubmit.setForeground(Colors.Red);
		btnSubmit.setFont(Fonts.getFont(12f));
		btnSubmit.addActionListener(paymentController);
		paymentController.getPaymentModel().setBtnSubmit(btnSubmit);
		
		//int labels
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(Fonts.getFont(20f));
		lblBeMyPlayer.setForeground(Colors.Yellow);
		lblBeMyPlayer.setBounds(160,0,204,69);
		paymentController.getPaymentModel().setLblBeMyPlayer(lblBeMyPlayer);
		
		JLabel lblCardNumber = new JLabel("Enter Card Number (numbers only):");
		lblCardNumber.setBounds(10,70,260,40);
		lblCardNumber.setForeground(Colors.Yellow);
		lblCardNumber.setFont(Fonts.getFont(12f));
		paymentController.getPaymentModel().setLblcardNumber(lblCardNumber);
		
		JLabel lblCardDate = new JLabel("Enter Card Expiration Month and Year:");
		lblCardDate.setBounds(10,140,260,40);
		lblCardDate.setForeground(Colors.Yellow);
		lblCardDate.setFont(Fonts.getFont(12f));
		paymentController.getPaymentModel().setLblcardDate(lblCardDate);
		
		JLabel lblCardCVC = new JLabel("Enter 3 Digit CVC Code:");
		lblCardCVC.setBounds(10,210,200,40);
		lblCardCVC.setForeground(Colors.Yellow);
		lblCardCVC.setFont(Fonts.getFont(12f));
		paymentController.getPaymentModel().setLblcardCVC(lblCardCVC);
		
		JLabel lblSlash = new JLabel("/");
		lblSlash.setBounds(60,170,40,40);
		lblSlash.setForeground(Colors.Yellow);
		lblSlash.setFont(Fonts.getFont(20f));
		paymentController.getPaymentModel().setLblSlash(lblSlash);
		
		JLabel lblDescription = new JLabel("<HTML>Description of payment pricing and services here.</HTML>");
		lblDescription.setForeground(Colors.Yellow);
		lblDescription.setBounds(280,80,200,200);
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescription.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		paymentController.getPaymentModel().setLblDescription(lblDescription);
		
		//init text fields
		JFormattedTextField cardNumber = new JFormattedTextField("");
		cardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		cardNumber.setBounds(10,100,200,40);
		cardNumber.setBackground(Colors.Yellow);
		cardNumber.setForeground(Colors.Red);
		cardNumber.setFont(Fonts.getFont(20f));
		paymentController.getPaymentModel().setCardNumber(cardNumber);
		
		JFormattedTextField cardMonth = new JFormattedTextField("");
		cardMonth.setHorizontalAlignment(SwingConstants.CENTER);
		cardMonth.setBounds(10,170,40,40);
		cardMonth.setBackground(Colors.Yellow);
		cardMonth.setForeground(Colors.Red);
		cardMonth.setFont(Fonts.getFont(20f));
		paymentController.getPaymentModel().setCardMonth(cardMonth);
		
		JFormattedTextField cardYear = new JFormattedTextField("");
		cardYear.setHorizontalAlignment(SwingConstants.CENTER);
		cardYear.setBounds(80,170,40,40);
		cardYear.setBackground(Colors.Yellow);
		cardYear.setForeground(Colors.Red);
		cardYear.setFont(Fonts.getFont(20f));
		paymentController.getPaymentModel().setCardYear(cardYear);
		
		JFormattedTextField cardCVC = new JFormattedTextField("");
		cardCVC.setHorizontalAlignment(SwingConstants.CENTER);
		cardCVC.setBounds(10,240,60,40);
		cardCVC.setBackground(Colors.Yellow);
		cardCVC.setForeground(Colors.Red);
		cardCVC.setFont(Fonts.getFont(20f));
		paymentController.getPaymentModel().setCardCVC(cardCVC);
		
		
		//add to Panel
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getBtnBack());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getBtnSubmit());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getCardCVC());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getCardMonth());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getCardNumber());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getCardYear());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getLblBeMyPlayer());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getLblcardCVC());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getLblcardDate());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getLblcardNumber());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getLblDescription());
		paymentController.getPaymentPanel().add(paymentController.getPaymentModel().getLblSlash());
		
		//pack and visible
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
}