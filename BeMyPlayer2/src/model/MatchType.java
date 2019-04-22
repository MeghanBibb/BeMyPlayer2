package model;

/**
 * The Enum MatchType.
 */
public enum MatchType {

	/** The unmatched. */
	UNMATCHED,/** The blocked. */
BLOCKED,/** The friend match. */
FRIEND_MATCH,/** The love match. */
LOVE_MATCH;
	
	/** The Constant _TYPE_UNMATCHED. */
	//Firebase schema values (do not modify!)
	public static final String _TYPE_UNMATCHED = "unmatched";
	
	/** The Constant _TYPE_BLOCKED. */
	public static final String _TYPE_BLOCKED = "blocked";
	
	/** The Constant _TYPE_FRIEND_MATCH. */
	public static final String _TYPE_FRIEND_MATCH = "friend";
	
	/** The Constant _TYPE_LOVE_MATCH. */
	public static final String _TYPE_LOVE_MATCH = "love";
	
	/**
	 * Gets the status string.
	 *
	 * @return the status string
	 */
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
	
	/**
	 * Gets the match type.
	 *
	 * @param statusString the status string
	 * @return the match type
	 */
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
