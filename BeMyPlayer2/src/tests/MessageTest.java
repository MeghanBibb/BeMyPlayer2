package tests;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;

import graphics.InvalidPopup;
import graphics.MessageController;
import graphics.MessageModel;

public class MessageTest {
	MessageModel model;
	MessageController controller;
	
	//this initializes the controller/model with valid, correct data
	//@BeforeEach
	public void initModel() {
		model = new MessageModel();
		controller = new MessageController();
		
		JTextArea messageThread = new JTextArea("hello there hot stuff");
		model.setThread(messageThread);
		model.setSendBox(new JTextField());
		controller.setMessageModel(model);
	}
	@Test
	public void invalidMessage() {
		model = new MessageModel();
		controller = new MessageController();
		JTextArea messageThread = new JTextArea("");
		model.setThread(messageThread);
		model.setSendBox(new JTextField());
		controller.setMessageModel(model);
		assertFalse(controller.validateMsg());
	}
	@Test
	public void nullMessage() {
		model = new MessageModel();
		controller = new MessageController();
		
		JTextArea messageThread = new JTextArea();
		model.setSendBox(new JTextField());
		model.setThread(messageThread);
			
		controller.setMessageModel(model);
		assertFalse(controller.validateMsg());
	}
}
