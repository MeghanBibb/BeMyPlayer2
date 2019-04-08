package graphics;

import javax.swing.*;

import model.Account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class MessageController extends PageController {
    public static final String SEND = "send";
    public static final String BACK = "back";

    private MessageModel messageModel = null;
    private JPanel messagePanel = null;
    private Account account;
    private static Logger logger = Logger.getLogger(MessageController.class.getName());
    public void launchPage(JFrame mainFrame, String back) {
    	if(back != null) {
    		backPage = back;
    	}
    	account = GraphicsController.getActiveAccount();
        MessageView.startMessagePage(this,mainFrame);
    }
    
    public Account getAccount() {
    	return account;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case SEND:
            	/* 
            	 * FOR DEMO PRESENTATION
            	 */
            	String t = messageModel.getThread().getText();
            	if(t.isBlank()) {
            		if(!messageModel.getSendBox().getText().isBlank()) {
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
}
