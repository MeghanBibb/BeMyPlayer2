package graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class MessageView {
    public static void startMessagePage(MessageController messageController, JFrame mainFrame){
        //init Model
        messageController.setMessageModel(new MessageModel());

        //init colors
        Color red = new Color(134,48,111);
		Color yellow = new Color(254, 195, 123);
        Color white = new Color(255,255,255);

        //init panel
        messageController.setMessagePanel(new BackgroundPanel(null));
        messageController.getMessagePanel().setBorder(new EmptyBorder(5, 5, 5, 5));
        messageController.getMessagePanel().setPreferredSize(new Dimension(500,400));
        messageController.getMessagePanel().setMaximumSize(new Dimension(500,400));
        mainFrame.setContentPane(messageController.getMessagePanel());
        mainFrame.getContentPane().setBackground(red);

        JLabel imgLabel = new JLabel("");
        Image img = new ImageIcon(messageController.getClass().getResource("/defaultIcon.png")).getImage();
        imgLabel.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        imgLabel.setBounds(35, 60, 100, 100);
        messageController.getMessageModel().setProfileImage(imgLabel);

        JLabel lblUsername = new JLabel();
        lblUsername.setText(messageController.getAccount().getAccountProfile().getUsername());
        lblUsername.setFont(new Font("Impact", Font.PLAIN,15));
        lblUsername.setForeground(yellow);
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
        lblAge.setForeground(yellow);
        lblAge.setBounds(150,60,200,90);
        messageController.getMessageModel().setLblAge(lblAge);

        JLabel lblGender = new JLabel();
        lblGender.setText(messageController.getAccount().getAccountProfile().getGender());
        lblGender.setForeground(yellow);
        lblGender.setBounds(150,85,90,90);
        messageController.getMessageModel().setLblGender(lblGender);

        //init buttons
        JButton backbtn = new JButton("Back");
        backbtn.setBounds(10,10,90,40);
        backbtn.setBackground(yellow);
        backbtn.setActionCommand(MessageController.BACK);
        backbtn.addActionListener(messageController);
        messageController.getMessageModel().setBack(backbtn);

        messageController.getMessagePanel().add(messageController.getMessageModel().getBack());

        JButton btnSend = new JButton("Send");
        btnSend.setBounds(345,365,90,20);
        btnSend.setActionCommand(MessageController.SEND);
        btnSend.setBackground(white);
        btnSend.addActionListener(messageController);
        messageController.getMessageModel().setBtnSend(btnSend);

        //	init fields and listeners
        JTextArea thread = new JTextArea();
        thread.setBounds(35, 165, 400, 200);
        thread.setVisible(true);
        messageController.getMessageModel().setThread(thread);

        JTextField sendBox = new JTextField();
        sendBox.setBounds(35, 365, 310, 20);
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

