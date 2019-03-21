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
        Color red = new Color(127,4,42);
        Color white = new Color(255,255,255);

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

        //	init fields and listeners


        JComboBox issueSelection = new JComboBox();
        issueSelection.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //supportController.getSupportModel().setissueSelection(e.getItem().toString());
            }
        });
        issueSelection.setToolTipText("What kind of issue are you having?");
        issueSelection.setModel(new DefaultComboBoxModel(new String[] {"Select what kind of issue it is","type1", "type2", "type3"}));
        issueSelection.setBounds(35, 70, 400, 22);
        issueSelection.setVisible(true);
        supportController.getSupportModel().setProbArea(issueSelection);

        JFormattedTextField desc = new JFormattedTextField("Tell us about the issue");
        desc.setBounds(35, 100, 400, 200);
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
