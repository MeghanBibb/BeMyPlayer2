package graphics;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ViewMatchesModel {
	private JComboBox matchtype;
	private List<JButton> loveList;
	private List<JButton> friendList;
	public JComboBox getMatchtype() {
		return matchtype;
	}

	public void setMatchtype(JComboBox matchtype) {
		this.matchtype = matchtype;
	}
}
