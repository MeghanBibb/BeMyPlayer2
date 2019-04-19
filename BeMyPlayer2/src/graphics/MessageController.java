package graphics;

import javax.swing.*;

import firebase.DBFailureException;
import model.Account;
import model.MessageThread;
import model.Profile;
import model.InformationExpert;
import model.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageController.
 */
public class MessageController extends PageController {
    
    /** The Constant SEND. */
    public static final String SEND = "send";
    
    /** The Constant BACK. */
    public static final String BACK = "back";
    
    /** The Constant REFRESH. */
    public static final String REFRESH = "refresh";

    /** The message model. */
    private MessageModel messageModel = null;
    
    /** The current thread. */
    private MessageThread currentThread = null;
    
    /** The message panel. */
    private JPanel messagePanel = null;
    
    /** The account. */
    private Account account;
    
    /** The other prof. */
    private Profile otherProf; 
    
    /** The logger. */
    private static Logger logger = Logger.getLogger(MessageController.class.getName());
    
    /* (non-Javadoc)
     * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
     */
    public void launchPage(JFrame mainFrame, String back) {
    	if(back != null) {
    		backPage = back;
    	}
    	setOtherProf(InformationExpert.getOtherProfile());
    	account = InformationExpert.getActiveAccount();
        try {
            currentThread = InformationExpert.getMessageThread(InformationExpert.getActiveUserID(), InformationExpert.getOtherProfile().getUserId());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Could not find thread");
        }
        MessageView.startMessagePage(this,mainFrame);
    }
    
    /**
     * Gets the account.
     *
     * @return the account
     */
    public Account getAccount() {
    	return account;
    }

    /* (non-Javadoc)
     * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case SEND:
                if (validateMsg()){
                    Message newMessage = new Message();
                    newMessage.setMessage(this.getMessageModel().getSendBox().getText());
                    newMessage.setSenderId(InformationExpert.getActiveUserID());
                    newMessage.setTimestampNow();
                    
                    try {
						if(InformationExpert.addMessage(InformationExpert.getActiveUserID(), InformationExpert.getOtherProfile().getUserId(), newMessage)) {
							//message upload successful
							System.out.println("YES!!");
						}
					} catch (DBFailureException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                break;
            case BACK:
                logger.info("Back");
                GraphicsController.processPage(PageCreator.PROFILE_PAGE,backPage);
                break;
            case REFRESH:
                logger.info("Refreshed");
                /*try {
                    currentThread = InformationExpert.getMessageThread(InformationExpert.getActiveUserID(), InformationExpert.getOtherProfile().getUserId());
                } catch (DBFailureException e1) {
                    logger.log(Level.SEVERE, "Could not find Thread");
                }*/
        }
    }

    /**
     * Validate msg.
     *
     * @return true, if successful
     */
    public boolean validateMsg() {
        boolean valid = true;

        //	CHECK FIELDS ARE NOT EMPTY OR SQL COMMANDS TO DELETE OUR TABLES
        //	VALIDATION FROM CREATE ACCOUNT PAGE + DATABASE VALIDATION
        List<String> warnings = new ArrayList<>();
        if(this.messageModel.getSendBox().getText().equals("")) {
            valid = false;
            warnings.add("Don't be shy! Enter a message.\n");
        }
/*
        if (this.messageModel.getThread().getText().contains(";")){
            valid = false;
            warnings.add("Please be nice to me :(\n");
        }
*/
        if(valid == false) {
            InvalidPopup p = new InvalidPopup(this.messagePanel, warnings);
        }
        return valid;
    }

    /**
     * Gets the message model.
     *
     * @return the message model
     */
    public MessageModel getMessageModel() {
        return messageModel;
    }

    /**
     * Sets the message model.
     *
     * @param messageModel the new message model
     */
    public void setMessageModel(MessageModel messageModel) {
        this.messageModel = messageModel;
    }

    /**
     * Gets the message panel.
     *
     * @return the message panel
     */
    public JPanel getMessagePanel() {
        return messagePanel;
    }

    /**
     * Sets the message panel.
     *
     * @param messagePanel the new message panel
     */
    public void setMessagePanel(JPanel messagePanel) {
        this.messagePanel = messagePanel;
    }

	/**
	 * Gets the other prof.
	 *
	 * @return the other prof
	 */
	public Profile getOtherProf() {
		return otherProf;
	}

	/**
	 * Sets the other prof.
	 *
	 * @param otherProf the new other prof
	 */
	public void setOtherProf(Profile otherProf) {
		this.otherProf = otherProf;
	}

    /**
     * Gets the current thread.
     *
     * @return the current thread
     */
    public MessageThread getCurrentThread() {
        return currentThread;
    }

    /**
     * Sets the current thread.
     *
     * @param currentThread the new current thread
     */
    public void setCurrentThread(MessageThread currentThread) {
        this.currentThread = currentThread;
    }
}
