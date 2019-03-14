package org.baylor.software1.bemyplayer2.app;

import javax.swing.JFrame;

import org.baylor.software1.bemyplayer2.controller.MainWindowController;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final String APPLICATION_NAME = "App Name";
	
    public static void main(String[] args) {
    	
    	//TODO: Move this to the AppLauncher class:
    	MainWindowController testController = new MainWindowController();
    	JFrame frame = new JFrame("Test Application");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	testController.initializeWindowInFrame(frame);
    	
    }
}
