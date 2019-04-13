package graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import firebase.DBFailureException;
import model.InformationExpert;

import java.awt.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class MessageView {
	private static Logger logger = Logger.getLogger(MessageView.class.getName());
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
        Image img;
		try {
			img = InformationExpert.getProfileImage(messageController.getOtherProf().getUserId());
			//Image img = new ImageIcon(messageController.getClass().getResource("/defaultIcon.png")).getImage();
	        imgLabel.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
	        imgLabel.setBounds(35, 60, 100, 100);
	        messageController.getMessageModel().setProfileImage(imgLabel);
		} catch (DBFailureException e) {
			// TODO Auto-generated catch block
			logger.warning("database failed to get profile image from profile" + messageController.getOtherProf().getUserId());
		}
        

        JLabel lblUsername = new JLabel();
        lblUsername.setText(messageController.getAccount().getAccountProfile().getUsername());
        lblUsername.setFont(new Font("Impact", Font.PLAIN,15));
        lblUsername.setFont(Fonts.getFont((float) 12));
        //lblUsername.setForeground(Colors.Red);
        lblUsername.setForeground(Colors.Yellow);
        lblUsername.setBounds(150,35,90,90);
        messageController.getMessageModel().setLblUsername(lblUsername);

        
        /*age stuff*/
        JLabel lblAge = new JLabel();
        LocalDate now = LocalDate.now();
		Date nowDate = java.sql.Date.valueOf(now);
		Calendar cnow = Calendar.getInstance();
		cnow.setTime(nowDate);
		Date bday = messageController.getAccount().getAccountProfile().getDateOB();
		Calendar cbday = Calendar.getInstance();
		cbday.setTime(bday);
		int diff = cnow.get(Calendar.YEAR) - cbday.get(Calendar.YEAR);
		if(cnow.get(Calendar.MONTH) == cbday.get(Calendar.MONTH) && cnow.get(Calendar.DATE) > cbday.get(Calendar.DATE) ) {
			diff--;
		}
		lblAge.setText(Integer.toString(diff) + " years old");
        lblAge.setForeground(Colors.Yellow);
        lblAge.setFont(Fonts.getFont((float) 12));
        //lblAge.setForeground(Colors.Red);
        lblAge.setBounds(150,60,200,90);
        messageController.getMessageModel().setLblAge(lblAge);

        JLabel lblGender = new JLabel();
        lblGender.setText(messageController.getAccount().getAccountProfile().getGender());
        lblGender.setForeground(Colors.Yellow);
        lblGender.setFont(Fonts.getFont((float) 12));
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

        //	init fields and listeners
        JTextArea thread = new JTextArea();
        thread.setBounds(35, 165, 400, 200);
        thread.setVisible(true);
        thread.setFont(Fonts.getFont((float) 12));
        thread.setForeground(Colors.Red);
        messageController.getMessageModel().setThread(thread);

        for (int i = 0; i < messageController.getCurrentThread().getMessages().size(); i++){
            String print;
            if (messageController.getCurrentThread().getMessages().get(i).getSenderId().equals(InformationExpert.getActiveUserID())){
                thread.append("Me: ");
                thread.append(messageController.getCurrentThread().getMessages().get(i).getMessage());
                thread.append("\n");
            }
            else {
                thread.append(InformationExpert.getOtherProfile().getUserId() + ": ");
                thread.append(messageController.getCurrentThread().getMessages().get(i).getMessage());
                thread.append("\n");
            }
        }

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
        messageController.getMessagePanel().add(messageController.getMessageModel().getThread());
        messageController.getMessagePanel().add(messageController.getMessageModel().getSendBox());

        //pack and set visible
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}

