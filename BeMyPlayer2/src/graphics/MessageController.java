package graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageController extends PageController {
    public static final String SEND = "send";
    public static final String BACK = "back";

    private MessageModel messageModel = null;
    private JPanel messagePanel = null;

    public void launchPage(JFrame mainFrame, String back) {
    	if(back != null) {
    		backPage = back;
    	}
        MessageView.startMessagePage(this,mainFrame);
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
                System.out.println("Back");
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