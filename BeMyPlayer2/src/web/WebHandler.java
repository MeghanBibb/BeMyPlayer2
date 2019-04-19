package web;

import java.util.logging.Logger;

import javax.swing.JPanel;

import graphics.InvalidPopup;

// TODO: Auto-generated Javadoc
/**
 * The Class WebHandler.
 */
public class WebHandler {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(WebHandler.class.getName());
	
	/**
	 * Open web page.
	 *
	 * @param url the url
	 * @param temp the temp
	 */
	public static void openWebPage(String url,JPanel temp) {
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		}catch(java.io.IOException e) {
			logger.warning("Failed to launch webpage due to connection issue");
			InvalidPopup p = new InvalidPopup(temp,"Error Loading Webpage");
		}
	}
}	
