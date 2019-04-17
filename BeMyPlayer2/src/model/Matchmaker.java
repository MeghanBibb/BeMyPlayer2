package model;

import java.util.Comparator;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Matchmaker.
 */
public class Matchmaker{
	
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
				return o1.getUsername().compareTo(o2.getUsername());
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
				return o1.getUsername().compareTo(o2.getUsername());
			}
			
	    };
	}
	
	
}
