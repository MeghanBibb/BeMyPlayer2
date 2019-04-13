package model;

import java.util.Comparator;
import java.util.List;

public class Matchmaker{
	
	public static Comparator<Profile> getFriendComparator(Profile c) {
		
		return new Comparator<Profile>() {
	    	Profile current = c;
			@Override
			public int compare(Profile o1, Profile o2) {
				//This is a temporary stub:
				return o1.getUsername().compareTo(o2.getUsername());
			}
			
	    };
	}
	
	public static Comparator<Profile> getLoveComparator(Profile c) {
		
	    return new Comparator<Profile>() {
	    	Profile current = c;
			@Override
			public int compare(Profile o1, Profile o2) {
				//This is a temporary stub:
				return o1.getUsername().compareTo(o2.getUsername());
			}
			
	    };
	}
	
	
}
