package graphics;
import java.awt.Image;
import java.util.List;

public class Account {
	
	private String username;
	private String email;
	private String password;
	private String securityQ;
	private String securityQA;
	private String dateOB;
	private String gender;
	private String descrition;
	private Image profile;
	private List<Boolean> platforms;
	private List<Boolean> genres;
	
	private List<String> loveMatches;
	private List<String> friendMatches;
	public List<String> getLoveMatches() {
		return loveMatches;
	}
	public void setLoveMatches(List<String> loveMatches) {
		this.loveMatches = loveMatches;
	}
	public List<String> getFriendMatches() {
		return friendMatches;
	}
	public void setFriendMatches(List<String> friendMatches) {
		this.friendMatches = friendMatches;
	}
	
}
	