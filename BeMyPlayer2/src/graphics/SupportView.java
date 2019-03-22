package graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SupportView {

    public static void startSupportPage(SupportController supportController, JFrame mainFrame){
        //init Model
        supportController.setSupportModel(new SupportModel());

        //init colors
        Color red = new Color(134,48,111);
		Color white = new Color(254, 195, 123);//	aka peach

        //init panel
        supportController.setSupportPanel(new JPanel(null));
        supportController.getSupportPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
        supportController.getSupportPanel().setPreferredSize(new Dimension(500,400));
        supportController.getSupportPanel().setMaximumSize(new Dimension(500,400));
        mainFrame.setContentPane(supportController.getSupportPanel());
        mainFrame.getContentPane().setBackground(red);

        //init buttons
        JButton backbtn = new JButton("Back");
        backbtn.setBounds(35, 325, 90, 40);
        backbtn.setBackground(white);
        backbtn.setActionCommand(SupportController.BACK);
        backbtn.addActionListener(supportController);
        supportController.getSupportModel().setBack(backbtn);

        supportController.getSupportPanel().add(supportController.getSupportModel().getBack());

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(338,325,90,40);
        btnSubmit.setActionCommand(supportController.SUBMIT);
        btnSubmit.setBackground(white);
        btnSubmit.addActionListener(supportController);
        supportController.getSupportModel().setBtnSubmit(btnSubmit);
   
        //	probably replaced with header in iteration 3
        JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblBeMyPlayer.setForeground(white);
		lblBeMyPlayer.setBounds(160,0,204,69);
		supportController.getSupportPanel().add(lblBeMyPlayer);
        //	init fields and listeners


        JComboBox issueSelection = new JComboBox();
        issueSelection.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //supportController.getSupportModel().setissueSelection(e.getItem().toString());
            }
        });
        issueSelection.setToolTipText("What kind of issue are you having?");
        issueSelection.setModel(new DefaultComboBoxModel(new String[] {"Select issue type","type1", "type2", "type3"}));
        issueSelection.setBounds(35, 70, 400, 22);
        issueSelection.setVisible(true);
        supportController.getSupportModel().setProbArea(issueSelection);
		
        JLabel descript = new JLabel("Enter description:");
        descript.setForeground(white);
        descript.setFont(new Font("Monospaced", Font.BOLD, 16));
        descript.setBounds(35,100,204,50);
		supportController.getSupportPanel().add(descript);
		
        JFormattedTextField desc = new JFormattedTextField();
        desc.setBounds(35, 150, 400, 150);
        desc.setVisible(true);
        supportController.getSupportModel().setDescription(desc);

        //add to panel
        supportController.getSupportPanel().add(supportController.getSupportModel().getProbArea());
        supportController.getSupportPanel().add(supportController.getSupportModel().getBtnSubmit());
        supportController.getSupportPanel().add(supportController.getSupportModel().getBack());
        supportController.getSupportPanel().add(supportController.getSupportModel().getDescription());

        supportController.getSupportPanel().add(issueSelection);

        //pack and set visible
        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}