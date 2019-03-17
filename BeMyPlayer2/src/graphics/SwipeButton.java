package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SwipeButton extends JButton{
	SwipeButtonController controller;
	Dimension size = new Dimension(50, 50);
	Color background = new Color(255,215,0);
	public SwipeButton(SwipeButtonController controller) {
		super();
		this.controller = controller;
		this.setText(controller.text);
		this.setMinimumSize(size);
		this.setBackground(background);
		this.addActionListener(controller);
	}
	
}
