package graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageController implements ActionListener {
    public static final String SEND = "send";
    public static final String BACK = "back";

    private MessageModel messageModel = null;
    private JPanel messagePanel = null;

    public void launchMessagePage(JFrame j) {
        MessageView.startMessagePage(this,j);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case SEND:
                /*
                Message Sending logic with database adapter
                 */
                break;
            case BACK:
                System.out.println("Back");
                GraphicsController.launchProfilePage();
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
