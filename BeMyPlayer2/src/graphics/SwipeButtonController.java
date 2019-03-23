package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class SwipeButtonController implements ActionListener{
	
	String text;
	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	public SwipeButtonController(String text) {
		super();
		this.text = text;
	}
	
}