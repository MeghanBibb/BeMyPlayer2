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
		if(emptylistLine1 == null) {
			emptylistLine1 = new JLabel("Your princess is in another castle");
			emptylistLine1.setFont(Fonts.getFont(9f));
			emptylistLine1.setForeground(Colors.Red);
			emptylistLine1.setBounds(40,160,260,69);
		}
		return emptylistLine1;
	}

	public void setEmptylistLine1(JLabel emptylistLine1) {
		this.emptylistLine1 = emptylistLine1;
	}
	
	public JLabel getEmptylistLine2() {
		if(emptylistLine2 == null) {
			emptylistLine2 = new JLabel("Get back out there and find a match");
			emptylistLine2.setFont(Fonts.getFont(9f));
			emptylistLine2.setForeground(Colors.Red);
			emptylistLine2.setBounds(40,175,260,69);
		}
		return emptylistLine2;
	}

	public void setEmptylistLine2(JLabel emptylistLine2) {
		this.emptylistLine2 = emptylistLine2;
	}
}
