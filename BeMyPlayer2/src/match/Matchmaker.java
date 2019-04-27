package match;

import java.util.Comparator;
import java.util.logging.Logger;

import firebase.DBFailureException;
import model.ClientManager;
import model.Profile;
import viewMatches.ViewMatchesView;

/**
 * The Class Matchmaker.
 */
public class Matchmaker{
	private static Logger logger = Logger.getLogger(ViewMatchesView.class.getName());
	/**
	 * Gets the friend comparator.
	 *
	 * @param c the c
	 * @return the friend comparator
	 */
	public static Comparator<Profile> getFriendComparator(Profile c) {
		
		return new Comparator<Profile>() {
	    	//Profile current = c;
			@Override
			public int compare(Profile o1, Profile o2) {
				//This is a temporary stub:
				//	user profile and compare < 0 if left more similar than right or pos if right more like
				//lower score is better return left - right
				if(o1 == o2 ) {
					return 0;
				}
				
				if(o1 == null || o2 == null) {
					return o1 == null ? -1 : 1;
				}
				return generateZScore(o1) - generateZScore(o2);
			}
			
			public int generateZScore(Profile o) {
				int score = 0;
				if(o.getUserId().equals(c.getUserId())) {
					return 1000;
				}
				if(o.getUsername().equals(c.getUsername())) {
					return 1000;
				}
				if(o.isMute() == true) {
					return 1000;
				}
				try {
					if(ClientManager.getPaymentInfo(o.getUserId()) != null) {
						return -5000;
					}
				} catch (DBFailureException e) {
					logger.warning("Profile not found exception");
				}
				score += o.getGender().compareTo(o.getGender());
				for(int i = 0; i < o.getPlatforms().size(); i++) {
					if(o.getPlatforms().get(i).equals(c.getPlatforms().get(i))) {
						score--;
					}
				}
				return score;
			}
			
	    };
	}
	
	/**
	 * Gets the love comparator.
	 *
	 * @param c the c
	 * @return the love comparator
	 */
	public static Comparator<Profile> getLoveComparator(Profile c) {
		
	    return new Comparator<Profile>() {
	    	//Profile current = c;
			@Override
			public int compare(Profile o1, Profile o2) {
				//This is a temporary stub:
				//System.out.println("comparing " + o1.getUsername() + " and " + o2.getUsername());
				//System.out.println(generateZScore(o1) + " : " + generateZScore(o2));
				if(o1 == o2 ) {
					return 0;
				}
				
				if(o1 == null || o2 == null) {
					return o1 == null ? -1 : 1;
				}
				return generateZScore(o1) - generateZScore(o2);
			}
			public int generateZScore(Profile o) {
				int score = 0;
				
				if(o.getUserId().equals(c.getUserId())) {
					return 1000;
				}
				if(o.getUsername().equals(c.getUsername())) {
					return 1000;
				}
				if(o.getGender().equals(c.getGender())) {
					return 1000;
				}
				if(o.isMute() == true) {
					return 1000;
				}
				try {
					if(ClientManager.getPaymentInfo(o.getUserId()) != null) {
						return -5000;
					}
				} catch (DBFailureException e) {
					logger.warning("Profile not found exception");
				}
				for(int i = 0; i < o.getPlatforms().size(); i++) {
					if(o.getPlatforms().get(i).equals(c.getPlatforms().get(i))) {
						score--;
					}
				}
				for(int i = 0; i < o.getGenres().size(); i++) {
					if(o.getGenres().get(i).equals(c.getGenres().get(i))) {
						score--;
					}
				}
				return score;
			}
			
	    };
	}
	
	
}
