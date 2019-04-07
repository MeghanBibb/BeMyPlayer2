package graphics;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InvalidPopup {
	public InvalidPopup(JPanel p,List<String> warnings) {
		StringBuilder warning = new StringBuilder();
		for(String s : warnings) {
			warning.append(s);
		}
		JOptionPane.showConfirmDialog(p, warning, "Invalid input", JOptionPane.DEFAULT_OPTION);
	}
	
}
