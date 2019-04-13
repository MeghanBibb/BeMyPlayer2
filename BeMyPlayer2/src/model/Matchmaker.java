package model;

import java.util.Comparator;
import java.util.List;

public class MatchMaker{
	
	
	
	public Comparator<Profile> friendComparator(Profile c) {
		
	    return new Comparator<Profile>() {
	    	Profile current = c;
			@Override
			public int compare(Profile o1, Profile o2) {
				o1 = current;
				Integer score = 1;
				Integer index = 0;

				for(Boolean e : o2.getPlatforms()) {
					if(!o1.getPlatforms().get(index).equals(e)) {
						score++;
					}
					index++;
				}
				index = 0;
				for(Boolean e : o2.getGenres()) {
					if(!o1.getGenres().get(index).equals(e)) {
						score++;
					}

				}
				return score;
			}
	    };
	}
	
	public Comparator<Profile> loveComparator(Profile c) {
		
	    return new Comparator<Profile>() {
	    	Profile current = c;
			@Override
			public int compare(Profile o1, Profile o2) {
				o1 = current;
				Integer score = 1;
				Integer index = 0;

				for(Boolean e : o2.getPlatforms()) {
					if(!o1.getPlatforms().get(index).equals(e)) {
						score++;
					}
					index++;
				}
				index = 0;
				for(Boolean e : o2.getGenres()) {
					if(!o1.getGenres().get(index).equals(e)) {
						score++;
					}

				}
				index = 0;
				
				if(o1.getGender().equals(o2.getGender())) {
					score = Integer.MAX_VALUE;
				}
				
				return score;
			}
	    };
	}
	
	
}
