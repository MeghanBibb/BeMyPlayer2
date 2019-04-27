package tests;

import login.LoginPageController;
import login.LoginPageModel;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

	LoginPageModel model;
	LoginPageController controller;

	@BeforeEach
	public void init(){
		model = new LoginPageModel();
		controller = new LoginPageController();

		JFormattedTextField username = new JFormattedTextField("a@gmail.com");
		model.setFrmtdtxtfldEnterUsername(username);

		JPasswordField pass = new JPasswordField("b");
		model.setPwdEnterPass(pass);

		controller.setLoginPageModel(model);
	}
	
	@Test
	public void invalidLogin3() {
		assert(!controller.validateLogin("a@gmail.com", ""));	
	}
	
	@Test
	public void invalidLogin4() {
		assert(!controller.validateLogin("", "b"));	
	}
}
