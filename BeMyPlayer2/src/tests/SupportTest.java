package tests;

import graphics.SupportController;
import graphics.SupportModel;
import graphics.SupportView;
import model.Account;
import model.Profile;
/*
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import graphics.Colors;
import graphics.Fonts;
import graphics.InvalidPopup;

public class SupportTest {
	
	Account account;
	Profile profile;
	SupportModel model;
	SupportView view;
	SupportController controller;
	
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
	//@DisplayName("Create Model")
	public void initModel() {
		model = new SupportModel();
		controller = new SupportController();
		view = new SupportView();
		
		JTextArea text = new JTextArea("hello there this is my issue");
		model.setDescription(text);
		
        JComboBox issueSelection = new JComboBox<String>();
        issueSelection.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //supportController.getSupportModel().setissueSelection(e.getItem().toString());
            }
        });
        issueSelection.setToolTipText("What kind of issue are you having?");
        issueSelection.setModel(new DefaultComboBoxModel(new String[] {"Select closest issue type",
                "Other Users", "Messaging", "Payment", "My Profile"}));
        issueSelection.setSelectedIndex(1);
        model.setProbArea(issueSelection);
		
		controller.setSupportModel(model);
	}
	
	
	public void badMessageSemiColon() {
		JTextArea text = new JTextArea("hello there this is; my issue");
		model.setDescription(text);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	
	public void nullMessage() {
		JTextArea text = new JTextArea();
		model.setDescription(text);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	
	public void emptyMessage() {
		JTextArea text = new JTextArea();
		model.setDescription(text);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	
	public void emptyMessage2() {
		JTextArea text = new JTextArea("");
		model.setDescription(text);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	
	public void badIndexSelection() {
		JComboBox issueSelection = model.getProbArea();
		issueSelection.setSelectedIndex(0);
		model.setProbArea(issueSelection);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	
	public void badIndexSelection() {
		JComboBox issueSelection = model.getProbArea();
		issueSelection.setSelectedIndex(0);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	

}
*/