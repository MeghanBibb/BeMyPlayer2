package org.baylor.software1.bemyplayer2.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.baylor.software1.bemyplayer2.controller.WindowController;

public class MainWindowView {

	public static void launchGui(WindowController controller) {
		
		JFrame frame = controller.getAppFrame();
		frame.dispose();
		
		frame.setLayout(new BorderLayout());
		
		JPanel menuBarPanel = new JPanel();
		
				
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
