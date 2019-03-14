package org.baylor.software1.bemyplayer2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.baylor.software1.bemyplayer2.controller.WindowController;

public class MainWindowView {

	public static void launchGui(WindowController controller) {
		
		JFrame frame = controller.getAppFrame();
		frame.dispose();
		
		frame.setLayout(new BorderLayout());
		
		JPanel menuBarPanel = new JPanel();
		menuBarPanel.setPreferredSize(new Dimension(600, 60));
		menuBarPanel.setBackground(new Color(100, 100, 100));
		
		frame.add(menuBarPanel, BorderLayout.PAGE_START);
		
		JPanel mainPanel = new JPanel(new GridLayout(3, 2));
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
