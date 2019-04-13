package graphics;

import javax.swing.*;

import model.Account;
import model.Profile;
import model.InformationExpert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MessageController extends PageController {
    public static final String SEND = "send";
    public static final String BACK = "back";

    private MessageModel messageModel = null;
    private JPanel messagePanel = null;
    private Account account;
    private Profile otherProf; 
    private static Logger logger = Logger.getLogger(MessageController.class.getName());
    public void launchPage(JFrame mainFrame, String back) {
    	if(back != null) {
    		backPage = back;
    	}
    	setOtherProf(InformationExpert.getOtherProfile());
    	account = InformationExpert.getActiveAccount();
        MessageView.startMessagePage(this,mainFrame);
    }
    
    public Account getAccount() {
    	return account;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case SEND:
                if (validateMsg()){

                }

            	/*
            	 * FOR DEMO PRESENTATION

            	String t = messageModel.getThread().getText();
            	if(t.isEmpty()) {
            		if(!messageModel.getSendBox().getText().isEmpty()) {
                		t="Me: ";
            			t += messageModel.getSendBox().getText();
            		}
            	} else {
            		t+="\nMe: ";
            		t+=messageModel.getSendBox().getText();
            	}
            	messageModel.getThread().setText(t);
            	messageModel.getSendBox().setText("");

                /*
                Message Sending logic with database adapter
                 */
                break;
            case BACK:
                logger.info("Back");
                GraphicsController.processPage(PageCreator.PROFILE_PAGE,backPage);
        }
    }

    public boolean validateMsg() {
        boolean valid = true;

        //	CHECK FIELDS ARE NOT EMPTY OR SQL COMMANDS TO DELETE OUR TABLES
        //	VALIDATION FROM CREATE ACCOUNT PAGE + DATABASE VALIDATION
        List<String> warnings = new ArrayList<>();
        if(this.messageModel.getSendBox().getText().equals("")) {
            valid = false;
            warnings.add("Please enter a description\n");
        }

        if (this.messageModel.getThread().getText().contains(";")){
            valid = false;
        }

        if(valid == false) {
            InvalidPopup p = new InvalidPopup(this.messagePanel, warnings);
        }
        return valid;
    }

    public MessageModel getMessageModel() {
        return messageModel;
    }

    public void setMessageModel(MessageModel messageModel) {
        this.messageModel = messageModel;
    }

    public JPanel getMessagePanel() {
        return messagePanel;
    }

    public void setMessagePanel(JPanel messagePanel) {
        this.messagePanel = messagePanel;
    }

	public Profile getOtherProf() {
		return otherProf;
	}

	public void setOtherProf(Profile otherProf) {
		this.otherProf = otherProf;
	}
}
