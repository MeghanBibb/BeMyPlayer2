package swiping;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Class SwipeButtonController.
 */
public abstract class SwipeButtonController implements ActionListener{
	
	/** The text. */
	String text;
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	/**
	 * Instantiates a new swipe button controller.
	 *
	 * @param text the text
	 */
	public SwipeButtonController(String text) {
		super();
		this.text = text;
	}
	
}