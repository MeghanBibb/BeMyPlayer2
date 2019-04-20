package tests;
/*
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import graphics.CreateAccountPageModel;
import graphics.EditAccountPageController;
import graphics.EditAccountPageModel;
import graphics.EditAccountPageView;


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
			
			//helpppp
			//model.setProfileImg(new BufferedImage(new File("BeMyPlayer2\\img\\heards.png")));
			//model.setProfileImg(profileImg);
			
			JFormattedTextField secQA = new JFormattedTextField("answer");
			model.setSecQA(secQA);
			
			JTextArea area = new JTextArea("hello my name is Meghan");
			model.setCharDescription(area);
				
			controller.setEditAccountModel(model);
		}
		
		//@Test
		public void testBadUsername() {
			JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField("B AD,z.m,.,23#xjhsdi B @@@@@@@@@@@@!!!!!!!!!!!!!!!!!!!@@@@@@@@@@AD S ICE M");
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		//@Test
		public void testEmptyUsername() {
			JFormattedTextField frmtdtxtfldEnterUsername = new JFormattedTextField();
			model.setFrmtdtxtfldEnterUsername(frmtdtxtfldEnterUsername);
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		//@Test
		public void testBadAge() {
			JFormattedTextField age = new JFormattedTextField("-15");
			model.setAge(age);
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		//@Test
		public void testBadAge2() {
			JFormattedTextField age = new JFormattedTextField("10215");
			model.setAge(age);
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		//@Test
		public void testEmptyAge() {
			JFormattedTextField age = new JFormattedTextField();
			model.setAge(age);
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		//@Test
		public void testBadDOB() {
			model.setDob(new Date(1000,11,19));
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		//@Test
		public void testBadDOB2() {
			model.setDob(new Date(1998,11,1922));
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		//@Test
		public void testBadDOB3() {
			model.setDob(new Date(1998,13,22));
			controller.setEditAccountModel(model);
			
			assert(!controller.validateCreatePage1());
		}
		
		//@Test
		public void validPage2Test() {
			JFrame j = new JFrame();
			view.launchEditQuestionnairePage(controller, j);
			model = controller.getEditAccountModel();
			List<JCheckBox> check = model.getCheckList();
			List<Boolean> check2 = model.getCheckLister();
			check.iterator().next().setEnabled(true);
			check.iterator().next().setEnabled(true);
			model.setCheckList(check);
			controller.setEditAccountModel(model);
			assert(controller.validateCreatePage2());
		}
				
}
*/


