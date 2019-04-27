package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import org.junit.Test;

import createAccount.CreateAccountPageController;
import createAccount.CreateAccountPageModel;
import createAccount.CreateAccountPageView;
import editAccount.EditAccountPageController;
import editAccount.EditAccountPageModel;
import editAccount.EditAccountPageView;


public class EditAccountTest {
		EditAccountPageModel model;
		EditAccountPageController controller;
		EditAccountPageView view;
		
		//this initializes the controller/model with valid, correct data
		//@BeforeEach
		//@DisplayName("Create Model")
		public void initModel() {
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
			JFormattedTextField age = new JFormattedTextField("20");
			model.setAge(age);
				
			//JFormattedTextField dob = new JFormattedTextField("11/19/1998");
			model.setDob(new Date(1998,11,19));

			JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField("SICEM");
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
			
			model.setGender("female");
			JPasswordField pass = new JPasswordField("pass");
			
			model.setPwdEnterPass(pass);
			model.setPwdValidatePass(pass);
			//helpppp
			//model.setProfileImg(new BufferedImage(new File("BeMyPlayer2\\img\\heards.png")));
			//model.setProfileImg(profileImg);
			
			JFormattedTextField secQA = new JFormattedTextField("answer");
			model.setSecQA(secQA);
			
			JTextArea area = new JTextArea("hello my name is Meghan");
			model.setCharDescription(area);
				
			controller.setEditAccountModel(model);
		}
		
		@Test
		public void testBadUsername() {
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
			JFormattedTextField age = new JFormattedTextField("20");
			model.setAge(age);
				
			//JFormattedTextField dob = new JFormattedTextField("11/19/1998");
			model.setDob(new Date(1998,11,19));

			JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField("SICEM");
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
			
			model.setGender("female");
			
			//helpppp
			//model.setProfileImg(new BufferedImage(new File("BeMyPlayer2\\img\\heards.png")));
			//model.setProfileImg(profileImg);
			JPasswordField pass = new JPasswordField("pass");
			
			model.setPwdEnterPass(pass);
			model.setPwdValidatePass(pass);
			JFormattedTextField secQA = new JFormattedTextField("answer");
			model.setSecQA(secQA);
			
			JTextArea area = new JTextArea("hello my name is Meghan");
			model.setCharDescription(area);
				
			controller.setEditAccountModel(model);
			
			JFormattedTextField frmtdtxtfldEnterUsername2 = new JFormattedTextField("B AD,z.m,.,23#xjhsdi B @@@@@@@@@@@@!!!!!!!!!!!!!!!!!!!@@@@@@@@@@AD S ICE M");
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		@Test
		public void testEmptyUsername() {
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
			JFormattedTextField age = new JFormattedTextField("20");
			model.setAge(age);
				
			//JFormattedTextField dob = new JFormattedTextField("11/19/1998");
			model.setDob(new Date(1998,11,19));

			JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField("SICEM");
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
			
			model.setGender("female");
			JPasswordField pass = new JPasswordField("pass");
			
			model.setPwdEnterPass(pass);
			model.setPwdValidatePass(pass);
			//helpppp
			//model.setProfileImg(new BufferedImage(new File("BeMyPlayer2\\img\\heards.png")));
			//model.setProfileImg(profileImg);
			
			JFormattedTextField secQA = new JFormattedTextField("answer");
			model.setSecQA(secQA);
			
			JTextArea area = new JTextArea("hello my name is Meghan");
			model.setCharDescription(area);
				
			controller.setEditAccountModel(model);
			
			JFormattedTextField frmtdtxtfldEnterUsername2 = new JFormattedTextField();
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername2);
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		@Test
		public void testBadAge() {
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
			JFormattedTextField age = new JFormattedTextField("20");
			model.setAge(age);
				
			//JFormattedTextField dob = new JFormattedTextField("11/19/1998");
			model.setDob(new Date(1998,11,19));

			JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField("SICEM");
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
			
			model.setGender("female");
			JPasswordField pass = new JPasswordField("pass");
			
			model.setPwdEnterPass(pass);
			model.setPwdValidatePass(pass);
			//helpppp
			//model.setProfileImg(new BufferedImage(new File("BeMyPlayer2\\img\\heards.png")));
			//model.setProfileImg(profileImg);
			
			JFormattedTextField secQA = new JFormattedTextField("answer");
			model.setSecQA(secQA);
			
			JTextArea area = new JTextArea("hello my name is Meghan");
			model.setCharDescription(area);
				
			controller.setEditAccountModel(model);
			
			JFormattedTextField age2 = new JFormattedTextField("-15");
			model.setAge(age2);
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		@Test
		public void testBadAge2() {
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
			JFormattedTextField age = new JFormattedTextField("20");
			model.setAge(age);
				
			//JFormattedTextField dob = new JFormattedTextField("11/19/1998");
			model.setDob(new Date(1998,11,19));

			JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField("SICEM");
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
			
			model.setGender("female");
			
			//helpppp
			//model.setProfileImg(new BufferedImage(new File("BeMyPlayer2\\img\\heards.png")));
			//model.setProfileImg(profileImg);
			JPasswordField pass = new JPasswordField("pass");
			
			model.setPwdEnterPass(pass);
			model.setPwdValidatePass(pass);
			JFormattedTextField secQA = new JFormattedTextField("answer");
			model.setSecQA(secQA);
			
			JTextArea area = new JTextArea("hello my name is Meghan");
			model.setCharDescription(area);
				
			controller.setEditAccountModel(model);
			
			JFormattedTextField age2 = new JFormattedTextField("10215");
			model.setAge(age2);
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		@Test
		public void testEmptyAge() {
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
			JFormattedTextField age = new JFormattedTextField("20");
			model.setAge(age);
			JPasswordField pass = new JPasswordField("pass");
			
			model.setPwdEnterPass(pass);
			model.setPwdValidatePass(pass);
			//JFormattedTextField dob = new JFormattedTextField("11/19/1998");
			model.setDob(new Date(1998,11,19));

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
			
			controller.setEditAccountModel(model);
			
			JFormattedTextField age2 = new JFormattedTextField();
			model.setAge(age2);
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		@Test
		public void testBadDOB() {
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
			JFormattedTextField age = new JFormattedTextField("20");
			model.setAge(age);
				
			//JFormattedTextField dob = new JFormattedTextField("11/19/1998");
			model.setDob(new Date(1998,11,19));

			JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField("SICEM");
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
			
			model.setGender("female");
			JPasswordField pass = new JPasswordField("pass");
			
			model.setPwdEnterPass(pass);
			model.setPwdValidatePass(pass);
			//helpppp
			//model.setProfileImg(new BufferedImage(new File("BeMyPlayer2\\img\\heards.png")));
			//model.setProfileImg(profileImg);
			
			JFormattedTextField secQA = new JFormattedTextField("answer");
			model.setSecQA(secQA);
			
			JTextArea area = new JTextArea("hello my name is Meghan");
			model.setCharDescription(area);
				
			controller.setEditAccountModel(model);
			
			model.setDob(new Date(1000,11,19));
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		@Test
		public void testBadDOB2() {
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
			JFormattedTextField age = new JFormattedTextField("20");
			model.setAge(age);
				
			//JFormattedTextField dob = new JFormattedTextField("11/19/1998");
			model.setDob(new Date(1998,11,19));

			JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField("SICEM");
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
			
			model.setGender("female");
			JPasswordField pass = new JPasswordField("pass");
			
			model.setPwdEnterPass(pass);
			model.setPwdValidatePass(pass);
			//helpppp
			//model.setProfileImg(new BufferedImage(new File("BeMyPlayer2\\img\\heards.png")));
			//model.setProfileImg(profileImg);
			
			JFormattedTextField secQA = new JFormattedTextField("answer");
			model.setSecQA(secQA);
			
			JTextArea area = new JTextArea("hello my name is Meghan");
			model.setCharDescription(area);
				
			controller.setEditAccountModel(model);
			
			model.setDob(new Date(1998,11,1922));
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		@Test
		public void testBadDOB3() {
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
			JFormattedTextField age = new JFormattedTextField("20");
			model.setAge(age);
			JPasswordField pass = new JPasswordField("pass");
			
			model.setPwdEnterPass(pass);
			model.setPwdValidatePass(pass);
			//JFormattedTextField dob = new JFormattedTextField("11/19/1998");
			model.setDob(new Date(1998,11,19));

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
				
			controller.setEditAccountModel(model);
			
			model.setDob(new Date(1998,13,22));
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		@Test
		public void testEmptyPlatforms() {
			
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
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
			
			model = new EditAccountPageModel();
			controller = new EditAccountPageController();
			view = new EditAccountPageView();
			
			JTextArea charDescription = new JTextArea();
			charDescription.setText("");
			model.setCharDescription(charDescription);
			controller.setEditAccountModel(model);
			assertFalse(controller.validateCreatePage3());
		}
				
}