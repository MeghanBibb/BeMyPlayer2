package graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// TODO: Auto-generated Javadoc
/**
 * The Class SupportView.
 */
public class SupportView {

    /**
     * Start support page.
     *
     * @param supportController the support controller
     * @param mainFrame the main frame
     */
    public static void startSupportPage(SupportController supportController, JFrame mainFrame){
        //init Model
        supportController.setSupportModel(new SupportModel());
        Color red = Colors.Red;
        Color yellow = Colors.Yellow;


        //init panel
        supportController.setSupportPanel(new BackgroundPanel(null));
        supportController.getSupportPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
        supportController.getSupportPanel().setPreferredSize(new Dimension(500,400));
        supportController.getSupportPanel().setMaximumSize(new Dimension(500,400));
        mainFrame.setContentPane(supportController.getSupportPanel());

        //init buttons
        JButton backbtn = new JButton("Back");
        backbtn.setBounds(35, 325, 90, 40);
        backbtn.setBackground(Colors.Yellow);
        backbtn.setFont(Fonts.getFont((float) 12));
        backbtn.setForeground(Colors.Red);
        backbtn.setActionCommand(SupportController.BACK);
        backbtn.addActionListener(supportController);
        supportController.getSupportModel().setBack(backbtn);

        supportController.getSupportPanel().add(supportController.getSupportModel().getBack());

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(338,325,90,40);
        btnSubmit.setActionCommand(supportController.SUBMIT);
        btnSubmit.setFont(Fonts.getFont((float) 12));
        btnSubmit.setBackground(Colors.Yellow);
        btnSubmit.setForeground(Colors.Red);
        btnSubmit.addActionListener(supportController);
        supportController.getSupportModel().setBtnSubmit(btnSubmit);
   
        //	probably replaced with header in iteration 3
        JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblBeMyPlayer.setForeground(Colors.Yellow);
		//lblBeMyPlayer.setForeground(Colors.Red);
		lblBeMyPlayer.setBounds(160,0,204,69);
		lblBeMyPlayer.setFont(Fonts.getFont((float) 12));
		supportController.getSupportPanel().add(lblBeMyPlayer);
        //	init fields and listeners


        JComboBox issueSelection = new JComboBox<String>();
        issueSelection.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //supportController.getSupportModel().setissueSelection(e.getItem().toString());
            }
        });
        issueSelection.setToolTipText("What kind of issue are you having?");
        issueSelection.setModel(new DefaultComboBoxModel(new String[] {"Select closest issue type",
                "Other Users", "Messaging", "Payment", "My Profile"}));
        issueSelection.setBounds(35, 70, 400, 22);
        issueSelection.setFont(Fonts.getFont((float) 12));
        issueSelection.setForeground(Colors.Red);
        issueSelection.setBackground(yellow);
        issueSelection.setVisible(true);
        supportController.getSupportModel().setProbArea(issueSelection);
        JTextArea desc = new JTextArea();
        desc.setBounds(35, 100, 400, 200);
        desc.setVisible(true);
        desc.setFont(Fonts.getFont((float) 12));
        desc.setForeground(Colors.Red);
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