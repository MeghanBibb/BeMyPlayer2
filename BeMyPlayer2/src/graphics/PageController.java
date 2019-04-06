package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public abstract class PageController implements ActionListener {
	
	protected static String backPage = null;

	public abstract void launchPage(JFrame mainFrame, String back);
	
	public abstract void actionPerformed(ActionEvent e);
	

}
