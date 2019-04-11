package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
		Rectangle briefSize = new Rectangle(150,100,222,200);
		this.frame = t_frame;

		////
		Image backgroundImage;
		try {
			backgroundImage = ImageIO.read(new File("main/resources/img/background.png"));
			ImageIcon background=new ImageIcon(backgroundImage.getScaledInstance(500, 400, Image.SCALE_SMOOTH));
		    JLabel back=new JLabel(background);
		    this.frame.setContentPane(back);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		////
		
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		frame.getContentPane().setLayout(layout);
		backButton.setBackground(Colors.Yellow);
		backButton.setFont(Fonts.getFont((float) 12));
		backButton.setForeground(Colors.Red);
		
		
		this.left = new SwipeButton(new SwipeLeftController());
		this.right = new SwipeButton(new SwipeRightController());
		
		layout.setHgap(70);
		layout.setVgap(40);
		JLabel lblBeMyPlayer = new JLabel("Be My Player 2");
		lblBeMyPlayer.setFont(Fonts.getFont((float) 30));
		lblBeMyPlayer.setForeground(Colors.Yellow);
		lblBeMyPlayer.setBounds(160,10,204,69);
		
		frame.getContentPane().add(lblBeMyPlayer, BorderLayout.NORTH);
		frame.getContentPane().add(this.left, BorderLayout.LINE_START);
		frame.getContentPane().add(this.right, BorderLayout.LINE_END);
		frame.getContentPane().add(this.backButton, BorderLayout.PAGE_END);
		frame.getContentPane().add(new ProfileBriefModel("Dr.Booth",briefSize,PageCreator.SWIPE_PAGE), BorderLayout.CENTER);
	}
	
}