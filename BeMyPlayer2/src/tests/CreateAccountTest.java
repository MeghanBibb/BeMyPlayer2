package tests;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

import graphics.InvalidPopup;
import graphics.CreateAccountPageController;
import graphics.CreateAccountPageModel;
import graphics.CreateAccountPageView;

public class CreateAccountTest {

	CreateAccountPageModel model;
	CreateAccountPageView view;
	CreateAccountPageController controller;
	
	//this initializes the controller/model with valid, correct data
	//@BeforeEach
	//@DisplayName("Create Model")
	public void initModel() {
		model = new CreateAccountPageModel();
		controller = new CreateAccountPageController();
		view = new CreateAccountPageView();
		
		JFormattedTextField age = new JFormattedTextField("20");
		model.setAge(age);
			
		//JFormattedTextField dob = new JFormattedTextField("11/19/1998");
		model.setDob(new Date(1998,11,19));
			
		JFormattedTextField email = new JFormattedTextField("mpbibb@gmail.com");
		model.setEnterEmail(email);
		
		JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField("SICEM");
		model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
		
		model.setGender("female");
		
		//helpppp
		//model.setProfileImg(new BufferedImage(new File("BeMyPlayer2\\img\\heards.png")));
		//model.setProfileImg(profileImg);
		
		JFormattedTextField secQA = new JFormattedTextField("answer");
		model.setSecQA(secQA);
		
		JTextArea area = new JTextArea("hello my name is Meghan");
		model.setCharDescription(area);
			
		controller.setCreateAccountPageModel(model);
	}
	
	//@Test
	public void testBadUsername() {
		JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField("B AD,zxjhsdi B @@@@@@@@@@@@!!!!!!!!!!!!!!!!!!!@@@@@@@@@@AD S ICE M");
		model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	//@Test
	public void testEmptyUsername() {
		JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField();
		model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	//@Test
	public void testBadAge() {
		JFormattedTextField age = new JFormattedTextField("-15");
		model.setAge(age);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	//@Test
	public void testBadAge2() {
		JFormattedTextField age = new JFormattedTextField("10215");
		model.setAge(age);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	//@Test
	public void testEmptyAge() {
		JFormattedTextField age = new JFormattedTextField();
		model.setAge(age);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	
	
	//@Test
	public void testBadDOB() {
		model.setDob(new Date(1000,11,19));
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	//@Test
	public void testBadDOB2() {
		model.setDob(new Date(1998,11,1922));
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	//@Test
	public void testBadDOB3() {
		model.setDob(new Date(1998,13,22));
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	//@Test
	public void testBadEmail() {
		JFormattedTextField email = new JFormattedTextField("BADBAD");
		model.setEnterEmail(email);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	//@Test
	public void testEmptyEmail() {
		JFormattedTextField email = new JFormattedTextField();
		model.setEnterEmail(email);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	
}