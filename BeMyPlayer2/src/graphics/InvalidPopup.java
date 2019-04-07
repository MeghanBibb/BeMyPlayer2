package graphics;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InvalidPopup {
	public InvalidPopup(JPanel p,List<String> warnings) {
		JOptionPane.showConfirmDialog(p, warnings, title, optionType, messageType)
	}
	
}
