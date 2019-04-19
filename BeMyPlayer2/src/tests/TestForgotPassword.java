package tests;

import model.Profile; 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

import graphics.InvalidPopup;
import graphics.ForgotPassPageModel;
import graphics.ForgotPassPageController;
import graphics.ForgotPassPageView;

import model.Account;

public class TestForgotPassword {
	Account account;
	Profile profile;
	ForgotPassPageModel model;
	ForgotPassPageView view;
	ForgotPassPageController controller;
	
	//@BeforeEach
	//@DisplayName("Create Profile")
	public void InitProfile() {
		profile = new Profile();
		account = new Account();
		profile.setDescription("hi this is my message");
		profile.setMute(false);
		profile.setDateOB(new Date(1998, 11, 19));
		profile.setGender("female");
		
		account.setEmail("mpbibb@gmail.com");
		account.setPasswordHash("myPassword1");
		account.setSecurityQ1("myAnswer");
		account.setUserId("myUsername!");
		account.setAccountProfile(profile);
	}
	
	//@BeforeEach
	//@DisplayName("Create Profile")
	public void initModel() {
		model = new ForgotPassPageModel();
		controller = new ForgotPassPageController();
		view = new ForgotPassPageView();
		
		JFormattedTextField tempEmail = new JFormattedTextField("mpbibb@gmail.com");
		model.setFrmtdtextfldEnterEmail(tempEmail);
		
		JPasswordField tempPassword1 = new JPasswordField("myPassword1");
		model.setFrmtdtextfldEnterNewPassword(tempPassword1);
		
		JPasswordField tempPassword2 = new JPasswordField("myPassword1");
		model.setPwdEnterPass(tempPassword2);
		
		JFormattedTextField tempUsername = new JFormattedTextField("myUsername!");
		model.setFrmtdtextfldEnterUsername(tempUsername);
		
		JFormattedTextField answer = new JFormattedTextField("myAnswer");
		model.setSecQA(answer);
		
		controller.setForgotPasswordPageModel(model);
	}
	
	//@Test
	public void testPasswordVerification() {
		model = new ForgotPassPageModel();
		assert(controller.validateInfo());
	}
	
}
