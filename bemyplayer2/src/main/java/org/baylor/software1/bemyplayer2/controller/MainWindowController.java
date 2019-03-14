package org.baylor.software1.bemyplayer2.controller;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import org.baylor.software1.bemyplayer2.view.MainWindowView;

public class MainWindowController extends WindowController{

	public void actionPerformed(ActionEvent e) {
		//TODO: Add action logic
	}

	@Override
	public void initializeWindowInFrame(JFrame frame) {
		this.appFrame = frame;
		MainWindowView.launchGui(this);
	}

}
