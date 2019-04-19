package graphics;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewMatchesModel.
 */
public class ViewMatchesModel {
	
	/** The matchtype. */
	private JComboBox matchtype;
	
	/** The love list. */
	private List<JButton> loveList;
	
	/** The friend list. */
	private List<JButton> friendList;
	
	/** The emptylist line 1. */
	private JLabel emptylistLine1;
	
	/** The emptylist line 2. */
	private JLabel emptylistLine2;
	
	/**
	 * Gets the matchtype.
	 *
	 * @return the matchtype
	 */
	public JComboBox getMatchtype() {
		return matchtype;
	}

	/**
	 * Sets the matchtype.
	 *
	 * @param matchtype the new matchtype
	 */
	public void setMatchtype(JComboBox matchtype) {
		this.matchtype = matchtype;
	}

	/**
	 * Gets the emptylist line 1.
	 *
	 * @return the emptylist line 1
	 */
	public JLabel getEmptylistLine1() {
		if(emptylistLine1 == null) {
			emptylistLine1 = new JLabel("Your princess is in another castle");
			emptylistLine1.setFont(Fonts.getFont(9f));
			emptylistLine1.setForeground(Colors.Red);
			emptylistLine1.setBounds(40,160,260,69);
		}
		return emptylistLine1;
	}

	/**
	 * Sets the emptylist line 1.
	 *
	 * @param emptylistLine1 the new emptylist line 1
	 */
	public void setEmptylistLine1(JLabel emptylistLine1) {
		this.emptylistLine1 = emptylistLine1;
	}
	
	/**
	 * Gets the emptylist line 2.
	 *
	 * @return the emptylist line 2
	 */
	public JLabel getEmptylistLine2() {
		if(emptylistLine2 == null) {
			emptylistLine2 = new JLabel("Get back out there and find a match");
			emptylistLine2.setFont(Fonts.getFont(9f));
			emptylistLine2.setForeground(Colors.Red);
			emptylistLine2.setBounds(40,175,260,69);
		}
		return emptylistLine2;
	}

	/**
	 * Sets the emptylist line 2.
	 *
	 * @param emptylistLine2 the new emptylist line 2
	 */
	public void setEmptylistLine2(JLabel emptylistLine2) {
		this.emptylistLine2 = emptylistLine2;
	}
}
