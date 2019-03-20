package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SwipePageModel {
	
	SwipeButton left;
	SwipeButton right;
	BorderLayout layout = new BorderLayout();
	JFrame frame = null;
	JButton backButton = new JButton("Back");
	
	public SwipePageModel(JFrame t_frame){
		
		this.frame = t_frame;

		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		frame.getContentPane().setLayout(layout);
		Color yellow = new Color(254, 195, 123);
		backButton.setBackground(yellow);
		backButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    			System.out.println("back");
	    			GraphicsController.launchHomePage();
	    	}
	    });
		
		this.left = new SwipeButton(new SwipeLeftController());
		this.right = new SwipeButton(new SwipeRightController());
		
		layout.setHgap(20);
		layout.setVgap(15);
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblBeMyPlayer.setForeground(yellow);
		lblBeMyPlayer.setBounds(160,0,204,69);
		
		frame.getContentPane().add(lblBeMyPlayer, BorderLayout.NORTH);
		frame.getContentPane().add(this.left, BorderLayout.LINE_START);
		frame.getContentPane().add(this.right, BorderLayout.LINE_END);
		frame.getContentPane().add(this.backButton, BorderLayout.PAGE_END);

	}
	
}