package model;

public class Match {
	
	private Account primaryAccount;
	private Account secondaryAccount;
	private boolean blockStatus = false;
	private MatchStatus primaryMatchStatus = MatchStatus.NO_MATCH;
	private MatchStatus secondaryMatchStatus = MatchStatus.NO_MATCH;

}
