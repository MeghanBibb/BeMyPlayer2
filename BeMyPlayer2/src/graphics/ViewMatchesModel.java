package graphics;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Class ViewMatchesModel.
 */
public class ViewMatchesModel {
	/** The matchtype. */
	private JComboBox<String> matchtype;
	
	/** The emptylist line 1. */
	private JLabel emptylistLine1;
	
	/** The emptylist line 2. */
	private JLabel emptylistLine2;
	
	private JLabel noMatchMessage;
	
	/**
	 * Gets the matchtype.
	 *
	 * @return the matchtype
	 */
	public JComboBox<String> getMatchtype() {
		return matchtype;
	}

	/**
	 * Sets the matchtype.
	 *
	 * @param matchtype the new matchtype
	 */
	public void setMatchtype(JComboBox<String> matchtype) {
		this.matchtype = matchtype;
	}

	public JPanel getEmptyPanel() {
		JPanel profilePicPanel = new JPanel();
		profilePicPanel.setBackground(Colors.Yellow);	
		profilePicPanel.setPreferredSize(new Dimension(100,250));
		
		return profilePicPanel;
	}

	public JLabel getNoMatchMessage() {
		if(noMatchMessage == null) {
			noMatchMessage = new JLabel("Your princess is in another castle, Get back out there and find a match!");
			noMatchMessage.setFont(Fonts.getFont(11f));
			noMatchMessage.setForeground(Colors.White);
			noMatchMessage.setBounds(25,345,450,69);
		}
		return noMatchMessage;
	}

	public void setNoMatchMessage(JLabel noMatchMessage) {
		this.noMatchMessage = noMatchMessage;
	}
	
}
