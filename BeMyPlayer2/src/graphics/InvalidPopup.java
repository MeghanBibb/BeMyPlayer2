package graphics;

import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidPopup.
 */
public class InvalidPopup {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(InvalidPopup.class.getName());
	
	/**
	 * Instantiates a new invalid popup.
	 *
	 * @param p the p
	 * @param warnings the warnings
	 */
	public InvalidPopup(JPanel p,List<String> warnings) {
		StringBuilder warning = new StringBuilder();
		for(String s : warnings) {
			warning.append(s);
		}
		//logger.info("Invalid input" + warnings);
		JOptionPane.showConfirmDialog(p, warning, "Invalid input", JOptionPane.DEFAULT_OPTION);
	}
	
	/**
	 * Instantiates a new invalid popup.
	 *
	 * @param p the p
	 * @param error the error
	 */
	public InvalidPopup(JPanel p, String error) {
		JOptionPane.showConfirmDialog(p, error,"Error",JOptionPane.DEFAULT_OPTION);
	}
	
	
}
