package match;

/**
 * The Enum MatchStatus.
 */
public enum MatchStatus {

	/** The no match. */
	NO_MATCH,/** The swipe right. */
SWIPE_RIGHT,/** The swipe left. */
SWIPE_LEFT;
	
	/** The Constant _STATUS_NO_MATCH. */
	//Firebase schema values (do not modify!)
	public static final String _STATUS_NO_MATCH = "no_match";
	
	/** The Constant _STATUS_SWIPE_RIGHT. */
	public static final String _STATUS_SWIPE_RIGHT = "swipe_right";
	
	/** The Constant _STATUS_SWIPE_LEFT. */
	public static final String _STATUS_SWIPE_LEFT = "swipe_left";
	
	/**
	 * Gets the status string.
	 *
	 * @return the status string
	 */
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
	
	/**
	 * Gets the match status.
	 *
	 * @param statusString the status string
	 * @return the match status
	 */
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
