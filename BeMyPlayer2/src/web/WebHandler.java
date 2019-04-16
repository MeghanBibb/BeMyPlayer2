package web;

import java.util.logging.Logger;

import javax.swing.JPanel;

import graphics.InvalidPopup;

public class WebHandler {
	private static Logger logger = Logger.getLogger(WebHandler.class.getName());
	public static void openWebPage(String url,JPanel temp) {
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		}catch(java.io.IOException e) {
			logger.warning("Failed to launch webpage due to connection issue");
			InvalidPopup p = new InvalidPopup(temp,"Error Loading Webpage");
		}
	}
}	
