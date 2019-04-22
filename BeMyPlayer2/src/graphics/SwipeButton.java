package graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

/**
 * The Class SwipeButton.
 */
public class SwipeButton extends ImgButton{
	
	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;

	/** The controller. */
	SwipeButtonController controller;
	
	/** The size. */
	Dimension size = new Dimension(50, 50);
	
	/** The background. */
	Color background = new Color(253,168,120);
	
	/**
	 * Instantiates a new swipe button.
	 *
	 * @param controller the controller
	 */
	public SwipeButton(SwipeButtonController controller) {
		super();
		this.setFont(Fonts.getFont((float) 15));
		this.setForeground(Colors.Red);
		this.controller = controller;
		this.setText(controller.text);
		this.addActionListener(controller);
	}
	
}
