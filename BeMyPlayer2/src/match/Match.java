package match;

import firebase.DBDocumentPackage;
import firebase.DBSerializable;
import model.Profile;

/**
 * The Class Match.
 */
public class Match implements DBSerializable{ 
	
	/** The client profile. */
	//private Profile 
	private Profile clientProfile = null;
	
	/** The other profile. */
	private Profile otherProfile = null;
	
	/** The type. */
	private MatchType type = MatchType.UNMATCHED;
	
	/** The client match status. */
	private MatchStatus clientMatchStatus = MatchStatus.NO_MATCH;
	
	/** The other match status. */
	private MatchStatus otherMatchStatus = MatchStatus.NO_MATCH;
	
	/** The Constant _TYPE. */
	//Database schema values (do not modify!)
	public static final String _TYPE = "match_type";
	
	/** The Constant _CLIENT_MATCH_STATUS. */
	public static final String _CLIENT_MATCH_STATUS = "client_match_status";
	
	/** The Constant _OTHER_MATCH_STATUS. */
	public static final String _OTHER_MATCH_STATUS = "other_match_status";
	
	/**
	 * Instantiates a new match.
	 */
	public Match() {
		this.clientProfile = null;
	}
	
	/**
	 * Instantiates a new match.
	 *
	 * @param client the client
	 * @param other the other
	 */
	public Match(Profile client, Profile other) {
		this.clientProfile = client;
		this.otherProfile = other;
	}
	
	/* (non-Javadoc)
	 * @see model.DBSerializable#toDBPackage()
	 */
	@Override
	public DBDocumentPackage toDBPackage() {
		DBDocumentPackage newPackage = new DBDocumentPackage(clientProfile.getUserId());
		newPackage.addValue(_TYPE, this.type.getStatusString());
		newPackage.addValue(_CLIENT_MATCH_STATUS, this.clientMatchStatus.getStatusString());
		newPackage.addValue(_OTHER_MATCH_STATUS, this.otherMatchStatus.getStatusString());
		
		return newPackage;
	}
	
	/**
	 * Converse match to DB package.
	 *
	 * @return the DB document package
	 */
	public DBDocumentPackage converseMatchToDBPackage() {
		DBDocumentPackage newPackage = new DBDocumentPackage(otherProfile.getUserId());
		newPackage.addValue(_TYPE, this.type.getStatusString());
		newPackage.addValue(_CLIENT_MATCH_STATUS, this.otherMatchStatus.getStatusString());
		newPackage.addValue(_OTHER_MATCH_STATUS, this.clientMatchStatus.getStatusString());
		
		return newPackage;
	}
	
	/* (non-Javadoc)
	 * @see model.DBSerializable#initializeFromPackage(model.DBDocumentPackage)
	 */
	@Override
	public void initializeFromPackage(DBDocumentPackage pkg) {
		
		for(String s : pkg.getValues().keySet()) {
			switch(s) {
				case _TYPE:
					this.type = MatchType.getMatchType((String) pkg.getValues().get(s));
					break;
				case _CLIENT_MATCH_STATUS:
					this.clientMatchStatus = MatchStatus.getMatchStatus((String) pkg.getValues().get(s));
					break;
				case _OTHER_MATCH_STATUS:
					this.otherMatchStatus = MatchStatus.getMatchStatus((String) pkg.getValues().get(s));
					break;
			}
		}
		
	}

	/**
	 * Gets the client profile.
	 *
	 * @return the client profile
	 */
	public Profile getClientProfile() {
		return clientProfile;
	}

	/**
	 * Sets the client profile.
	 *
	 * @param clientProfile the new client profile
	 */
	public void setClientProfile(Profile clientProfile) {
		this.clientProfile = clientProfile;
	}

	/**
	 * Gets the other profile.
	 *
	 * @return the other profile
	 */
	public Profile getOtherProfile() {
		return otherProfile;
	}

	/**
	 * Sets the other profile.
	 *
	 * @param otherProfile the new other profile
	 */
	public void setOtherProfile(Profile otherProfile) {
		this.otherProfile = otherProfile;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public MatchType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param newType the new type
	 */
	public void setType(MatchType newType) {
		this.type = newType;
	}
	
	/**
	 * Gets the client match status.
	 *
	 * @return the client match status
	 */
	public MatchStatus getClientMatchStatus() {
		return clientMatchStatus;
	}

	/**
	 * Sets the client match status.
	 *
	 * @param clientMatchStatus the new client match status
	 */
	public void setClientMatchStatus(MatchStatus clientMatchStatus) {
		this.clientMatchStatus = clientMatchStatus;
	}
	
	/**
	 * Gets the other match status.
	 *
	 * @return the other match status
	 */
	public MatchStatus getOtherMatchStatus() {
		return otherMatchStatus;
	}

	/**
	 * Sets the other match status.
	 *
	 * @param otherMatchStatus the new other match status
	 */
	public void setOtherMatchStatus(MatchStatus otherMatchStatus) {
		this.otherMatchStatus = otherMatchStatus;
	}

}
