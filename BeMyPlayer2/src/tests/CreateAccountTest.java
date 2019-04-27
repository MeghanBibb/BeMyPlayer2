package tests;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;

import graphics.CreateAccountPageController;
import graphics.CreateAccountPageModel;
import graphics.CreateAccountPageView;


public class CreateAccountTest {

	public CreateAccountPageModel model;
	public CreateAccountPageView view;
	public CreateAccountPageController controller;
	
	//this initializes the controller/model with valid, correct data
	@BeforeEach
	public void init(){
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
		JPasswordField pass = new JPasswordField("pass");

		model.setPwdEnterPass(pass);
		model.setPwdValidatePass(pass);
		JComboBox<String> gender = new JComboBox<String>();
		gender.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female"}));
		model.setGenderBox(gender);

		JFormattedTextField secQA = new JFormattedTextField("answer");
		model.setSecQA(secQA);

		JTextArea area = new JTextArea("hello my name is Meghan");
		model.setCharDescription(area);

		controller.setCreateAccountPageModel(model);
	}
	
	@Test
	public void testBadUsername() {
		
		JFormattedTextField frmtdtxtfldEnterUsername2 = new JFormattedTextField("B AD,zxjhsdi B @@@@@@@@@@@@!!!!!!!!!!!!!!!!!!!@@@@@@@@@@AD S ICE M");
		model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername2);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	@Test
	public void testEmptyUsername() {
		
		JFormattedTextField frmtdtxtfldEnterUsername2 = new JFormattedTextField();
		model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername2);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	@Test
	public void testBadAge() {
		
		JFormattedTextField age2 = new JFormattedTextField("-15");
		model.setAge(age2);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	@Test
	public void testBadAge2() {
		
		JFormattedTextField age2 = new JFormattedTextField("10215");
		model.setAge(age2);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	@Test
	public void testEmptyAge() {
		
		JFormattedTextField age2 = new JFormattedTextField();
		model.setAge(age2);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	
	
	@Test
	public void testBadDOB() {
		
		model.setDob(new Date(1000,11,19));
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	@Test
	public void testBadDOB2() {
		
		model.setDob(new Date(1998,11,1922));
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	@Test
	public void testBadDOB3() {
		
		model.setDob(new Date(1998,13,22));
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	@Test
	public void testBadEmail() {
		
		JFormattedTextField email2 = new JFormattedTextField("BADBAD");
		model.setEnterEmail(email2);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	
	@Test
	public void testEmptyEmail() {

		JFormattedTextField email2 = new JFormattedTextField();
		email2.setText("");
		model.setEnterEmail(email2);
		controller.setCreateAccountPageModel(model);
		
		assertFalse(controller.validateCreatePage1());
	}
	@Test
	public void testEmptyPlatforms() {
		
		model = new CreateAccountPageModel();
		controller = new CreateAccountPageController();
		view = new CreateAccountPageView();
		
		List<JCheckBox> temp = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			temp.add(new JCheckBox());
		}
		model.setCheckList(temp);
		
		int countPlat = 0;
		for(int i = 0; i < model.getCheckList().size(); i++) {
			if(model.getCheckList().get(i).isSelected()) {
				countPlat++;
			}
		}
		assertEquals(0,countPlat);
	}
	
	@Test
	public void testEmptyDescription() {
		
		model = new CreateAccountPageModel();
		controller = new CreateAccountPageController();
		view = new CreateAccountPageView();
		
		JTextArea charDescription = new JTextArea();
		charDescription.setText("");
		model.setCharDescription(charDescription);
		controller.setCreateAccountPageModel(model);
		assertFalse(controller.validateCreatePage3());
	}
	public void validPage2Test() {
		assert(controller.validateCreatePage2());
	}
	
	
}
