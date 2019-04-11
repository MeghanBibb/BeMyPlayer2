package graphics;

import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InvalidPopup {
	private static Logger logger = Logger.getLogger(InvalidPopup.class.getName());
	public InvalidPopup(JPanel p,List<String> warnings) {
		StringBuilder warning = new StringBuilder();
		for(String s : warnings) {
			warning.append(s);
		}
		logger.info("Invalid input" + warnings);
		JOptionPane.showConfirmDialog(p, warning, "Invalid input", JOptionPane.DEFAULT_OPTION);
	}
	public InvalidPopup(JPanel p, String dbError) {
		JOptionPane.showConfirmDialog(p, dbError,"Database error",JOptionPane.DEFAULT_OPTION);
	}
	
}
