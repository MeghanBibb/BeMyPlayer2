package model;

public enum MatchType {

	UNMATCHED,BLOCKED,FRIEND_MATCH,LOVE_MATCH;
	
	//Firebase schema values (do not modify!)
	public static final String _TYPE_UNMATCHED = "unmatched";
	public static final String _TYPE_BLOCKED = "blocked";
	public static final String _TYPE_FRIEND_MATCH = "friend";
	public static final String _TYPE_LOVE_MATCH = "love";
	
	public String getStatusString() {
		switch(this) {
			case UNMATCHED:
				return _TYPE_UNMATCHED;
			case BLOCKED:
				return _TYPE_BLOCKED;
			case FRIEND_MATCH:
				return _TYPE_FRIEND_MATCH;
			case LOVE_MATCH:
				return _TYPE_LOVE_MATCH;
		}
		return null;
	}
	
	public static MatchType getMatchType(String statusString) {
		switch(statusString) {
			case _TYPE_UNMATCHED:
				return UNMATCHED;
			case _TYPE_BLOCKED:
				return BLOCKED;
			case _TYPE_FRIEND_MATCH:
				return FRIEND_MATCH;
			case _TYPE_LOVE_MATCH:
				return LOVE_MATCH;
		}
		return null;
	}
}
