package graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import firebase.DBFailureException;
import model.InformationExpert;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageView.
 */
public class MessageView {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(MessageView.class.getName());
    
    /**
     * Start message page.
     *
     * @param messageController the message controller
     * @param mainFrame the main frame
     */
    public static void startMessagePage(MessageController messageController, JFrame mainFrame) {
        //init Model
        messageController.setMessageModel(new MessageModel());

        //init colors
        Color red = Colors.Red;
        Color yellow = Colors.Yellow;

        //init panel
        messageController.setMessagePanel(new BackgroundPanel(null));
        messageController.getMessagePanel().setBorder(new EmptyBorder(5, 5, 5, 5));
        messageController.getMessagePanel().setPreferredSize(new Dimension(500,400));
        messageController.getMessagePanel().setMaximumSize(new Dimension(500,400));
        mainFrame.setContentPane(messageController.getMessagePanel());

        JLabel imgLabel = new JLabel("");
		BufferedImage img = null;
		try {
			img = InformationExpert.getProfileImage(InformationExpert.getOtherProfile().getUserId());
		} catch (DBFailureException e) {
			logger.warning("failed to load profile picture for " + InformationExpert.getOtherProfile().getUserId());
		}
		
		if(img == null) {
			img = CreateAccountPageModel.DEFAULT_PROFILE_IMAGE;
		}
		imgLabel.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        imgLabel.setBounds(35, 60, 100, 100);
        messageController.getMessageModel().setProfileImage(imgLabel);

        JLabel lblUsername = new JLabel();
        lblUsername.setText(InformationExpert.getOtherProfile().getUsername());
        lblUsername.setFont(Fonts.getFont((float)15));
        lblUsername.setForeground(Colors.Yellow);
        lblUsername.setBounds(150,35,90,90);
        messageController.getMessageModel().setLblUsername(lblUsername);

        
        /*age stuff*/
        JLabel lblAge = new JLabel();
        LocalDate now = LocalDate.now();
		Date nowDate = java.sql.Date.valueOf(now);
		Calendar cnow = Calendar.getInstance();
		cnow.setTime(nowDate);
		Date bday = InformationExpert.getOtherProfile().getDateOB();
		Calendar cbday = Calendar.getInstance();
		cbday.setTime(bday);
		int diff = cnow.get(Calendar.YEAR) - cbday.get(Calendar.YEAR);
		if(cnow.get(Calendar.MONTH) == cbday.get(Calendar.MONTH) && cnow.get(Calendar.DATE) > cbday.get(Calendar.DATE) ) {
			diff--;
		}
		lblAge.setText(Integer.toString(diff) + " years old");
        lblAge.setForeground(Colors.Yellow);
        lblAge.setFont(Fonts.getFont((float) 15));
        //lblAge.setForeground(Colors.Red);
        lblAge.setBounds(150,60,200,90);
        messageController.getMessageModel().setLblAge(lblAge);

        JLabel lblGender = new JLabel();
        lblGender.setText(messageController.getOtherProf().getGender());
        lblGender.setForeground(Colors.Yellow);
        lblGender.setFont(Fonts.getFont((float) 15));
        //lblGender.setForeground(Colors.Red);
        lblGender.setBounds(150,85,90,90);
        messageController.getMessageModel().setLblGender(lblGender);

        //init buttons
        JButton backbtn = new JButton("Back");
        backbtn.setBounds(10,10,90,40);
        backbtn.setBackground(Colors.Yellow);
        backbtn.setFont(Fonts.getFont((float) 12));
        backbtn.setForeground(Colors.Red);
        backbtn.setActionCommand(MessageController.BACK);
        backbtn.addActionListener(messageController);
        messageController.getMessageModel().setBack(backbtn);

        messageController.getMessagePanel().add(messageController.getMessageModel().getBack());

        JButton btnSend = new JButton("Send");
        btnSend.setBounds(345,365,90,30);
        btnSend.setActionCommand(MessageController.SEND);
        btnSend.setBackground(Colors.White);
        btnSend.setFont(Fonts.getFont((float) 12));
        btnSend.setForeground(Colors.Red);
        btnSend.addActionListener(messageController);
        messageController.getMessageModel().setBtnSend(btnSend);

        //  init Thread scroll pane
        JTextArea thread = new JTextArea();
        //thread.setBounds(35, 165, 400, 200);
        thread.setVisible(true);
        thread.setEditable(false);
        thread.setFont(Fonts.getFont((float) 12));
        thread.setForeground(Colors.Red);
        messageController.getMessageModel().setThread(thread);
        
        if(messageController.getCurrentThread() != null) {
        	 for (int i = 0; i < messageController.getCurrentThread().getMessages().size(); i++){
                 if (messageController.getCurrentThread().getMessages().get(i).getSenderId().equals(InformationExpert.getActiveUserID())){
                     thread.append("Me: ");
                     thread.append(messageController.getCurrentThread().getMessages().get(i).getMessage());
                     thread.append("\n");
                 }
                 else {
                     thread.append(InformationExpert.getOtherProfile().getUsername() + ": ");
                     thread.append(messageController.getCurrentThread().getMessages().get(i).getMessage());
                     thread.append("\n");
                 }
             }
        }
       
        JScrollPane tPane = new JScrollPane(thread,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tPane.setVisible(true);
        tPane.setBounds(35, 165, 400, 200);

        JTextField sendBox = new JTextField();
        sendBox.setBounds(35, 365, 310, 30);
        sendBox.setVisible(true);
        messageController.getMessageModel().setSendBox(sendBox);

        //add to panel
        messageController.getMessagePanel().add(messageController.getMessageModel().getProfileImage());
        messageController.getMessagePanel().add(messageController.getMessageModel().getLblUsername());
        messageController.getMessagePanel().add(messageController.getMessageModel().getLblAge());
        messageController.getMessagePanel().add(messageController.getMessageModel().getLblGender());
        messageController.getMessagePanel().add(messageController.getMessageModel().getBtnSend());
        messageController.getMessagePanel().add(messageController.getMessageModel().getBack());
        messageController.getMessagePanel().add(tPane);
        messageController.getMessagePanel().add(messageController.getMessageModel().getSendBox());

        //pack and set visible
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}

