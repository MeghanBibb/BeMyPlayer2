package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SwipeButton extends JButton{
	SwipeButtonController controller;
	Dimension size = new Dimension(50, 50);
	Color background = new Color(253,168,120);
	public SwipeButton(SwipeButtonController controller) {
		super();
		this.setFont(Fonts.getFont((float) 15));
		this.setForeground(Colors.Red);
		this.controller = controller;
		this.setText(controller.text);
		//this.setMinimumSize(size);
		this.setBackground(Colors.Yellow);
		this.addActionListener(controller);
	}
	
}
