package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class ViewMatchesView {
	public static void startViewMatches(ViewMatchesController viewMatchController, JFrame mainFrame,Account a) {
		
		Color red = new Color(134,48,111);
		Color yellow = new Color(254, 195, 123);
		//	init panel
		viewMatchController.setViewMatchesPanel(new JPanel(null));
		viewMatchController.getViewMatchesPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		viewMatchController.getViewMatchesPanel().setPreferredSize(new Dimension(500,400));
		viewMatchController.getViewMatchesPanel().setMaximumSize(new Dimension(500,400));
		mainFrame.setContentPane(viewMatchController.getViewMatchesPanel());
		mainFrame.getContentPane().setBackground(red);
		
		
		//	init buttons and add to panel
		JButton backbtn = new JButton("Back");
		backbtn.setBounds(25, 25, 90, 40);
		backbtn.setBackground(yellow);
		backbtn.setActionCommand(CreateAccountPageController.BACK);
		backbtn.addActionListener(viewMatchController);
		viewMatchController.getViewMatchesPanel().add(backbtn);
		
		//	label
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblBeMyPlayer.setForeground(yellow);
		lblBeMyPlayer.setBounds(160,0,204,69);
		viewMatchController.getViewMatchesPanel().add(lblBeMyPlayer);
		
		//	load temp profile on the right side - > megan's file
		//	245,75,
		
		//	dyamically load 
		//	pull from match adapter giving the temp account

		//	SCROLLPANE
	    JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setBounds(new Rectangle(45, 75, 215, 300));
	    Color black = new Color(0,0,0);
	    //	if empty
	    JLabel noMatchlbl1 = new JLabel("Your princess is in another castle");
	    noMatchlbl1.setFont(new Font("Monospaced", Font.BOLD, 9));
	    noMatchlbl1.setForeground(black);
	    noMatchlbl1.setBounds(50,80,260,69);
	    viewMatchController.getViewMatchesPanel().add(noMatchlbl1);
		
		JLabel noMatchlbl2 = new JLabel("Get back out there and find a match");
		noMatchlbl2.setFont(new Font("Monospaced", Font.BOLD, 9));
		noMatchlbl2.setForeground(black);
		noMatchlbl2.setBounds(50,95,260,69);
		viewMatchController.getViewMatchesPanel().add(noMatchlbl2);
		
		viewMatchController.getViewMatchesPanel().add(scrollPane);
	    
	    mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
