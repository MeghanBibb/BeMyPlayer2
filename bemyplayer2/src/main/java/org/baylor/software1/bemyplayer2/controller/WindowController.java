package org.baylor.software1.bemyplayer2.controller;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

public abstract class WindowController implements ActionListener{
	
	protected JFrame appFrame = null;
	
	public abstract void initializeWindowInFrame(JFrame frame);


	public JFrame getAppFrame() {
		return appFrame;
	}
	
	
}
