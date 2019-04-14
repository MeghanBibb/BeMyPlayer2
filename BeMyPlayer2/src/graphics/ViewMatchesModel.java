package graphics;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class ViewMatchesModel {
	private JComboBox matchtype;
	private List<JButton> loveList;
	private List<JButton> friendList;
	private JLabel emptylistLine1;
	private JLabel emptylistLine2;
	
	public JComboBox getMatchtype() {
		return matchtype;
	}

	public void setMatchtype(JComboBox matchtype) {
		this.matchtype = matchtype;
	}

	public JLabel getEmptylistLine1() {
		return emptylistLine1;
	}

	public void setEmptylistLine1(JLabel emptylistLine1) {
		this.emptylistLine1 = emptylistLine1;
	}

	public JLabel getEmptylistLine2() {
		return emptylistLine2;
	}

	public void setEmptylistLine2(JLabel emptylistLine2) {
		this.emptylistLine2 = emptylistLine2;
	}
}
