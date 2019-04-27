package tests;

import model.Account;
import model.Profile;
import support.SupportController;
import support.SupportModel;
import support.SupportView;

import static org.junit.Assert.assertFalse;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import org.junit.Test;

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
	
	@Test
	public void badMessageSemiColon() {
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
		
		JTextArea text2 = new JTextArea("hello there this is; my issue");
		model.setDescription(text2);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	@Test
	public void nullMessage() {
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
		
		JTextArea text2 = new JTextArea();
		model.setDescription(text2);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	@Test
	public void emptyMessage() {
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
		
		JTextArea text2 = new JTextArea();
		model.setDescription(text2);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	@Test
	public void emptyMessage2() {
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
		
		JTextArea text2 = new JTextArea("");
		model.setDescription(text2);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	@Test
	public void badIndexSelection() {
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
		
		JComboBox issueSelection2 = model.getProbArea();
		issueSelection.setSelectedIndex(0);
		model.setProbArea(issueSelection);
		controller.setSupportModel(model);
		assertFalse(controller.validateSupInfo());
	}
	

}
