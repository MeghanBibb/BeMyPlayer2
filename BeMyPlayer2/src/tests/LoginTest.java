package tests;
/*
import graphics.LoginPageModel;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

import graphics.LoginPageController;

public class LoginTest {

	LoginPageModel model;
	LoginPageController controller;
	
	//this initializes the controller/model with valid, correct data
	@BeforeEach
	@DisplayName("Create Model")
	public void initModel() {
		model = new LoginPageModel();
		controller = new LoginPageController();
		
		JFormattedTextField username = new JFormattedTextField("a@gmail.com");
		model.setFrmtdtxtfldEnterUsername(username);
		
		JPasswordField pass = new JPasswordField("b");
		model.setPwdEnterPass(pass);
			
		controller.setLoginPageModel(model);
	}
	
	@Test
	public void validateLogin() {
		assert(controller.validateLogin("a@gmail.com", "b"));
	}
	
	@Test
	public void invalidLogin2() {
		assert(!controller.validateLogin("a@gmail.com", "badpassword"));
	}
	
	@Test
	public void invalidLogin() {
		assert(!controller.validateLogin("a", "b"));	
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
*/