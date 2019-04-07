package model;

public enum MatchStatus {
	
	NO_MATCH,SWIPE_RIGHT,SWIPE_LEFT;
	
	//Firebase schema values (do not modify!)
	public static final String _STATUS_NO_MATCH = "no_match";
	public static final String _STATUS_SWIPE_RIGHT = "swipe_right";
	public static final String _STATUS_SWIPE_LEFT = "swipe_left";
	
	public String getStatusString() {
		switch(this) {
			case NO_MATCH:
				return _STATUS_NO_MATCH;
			case SWIPE_RIGHT:
				return _STATUS_SWIPE_RIGHT;
			case SWIPE_LEFT:
				return _STATUS_SWIPE_LEFT;
		}
		return null;
	}
	
	public static MatchStatus getMatchStatus(String statusString) {
		switch(statusString) {
			case _STATUS_NO_MATCH:
				return NO_MATCH;
			case _STATUS_SWIPE_RIGHT:
				return SWIPE_RIGHT;
			case _STATUS_SWIPE_LEFT:
				return SWIPE_LEFT;
		}
		return null;
	}
}
