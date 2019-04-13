package model;

import java.util.ArrayList;

import firebase.FireBaseSchema;

public class Match implements DBSerializable{ 
	
	//private Profile 
	private Profile clientProfile = null;
	private Profile otherProfile = null;
	private MatchType type = MatchType.UNMATCHED;
	
	private MatchStatus clientMatchStatus = MatchStatus.NO_MATCH;
	private MatchStatus otherMatchStatus = MatchStatus.NO_MATCH;
	
	//Database schema values (do not modify!)
	public static final String _TYPE = "match_type";
	public static final String _CLIENT_MATCH_STATUS = "client_match_status";
	public static final String _OTHER_MATCH_STATUS = "other_match_status";
	
	public Match() {
		this.clientProfile = null;
	}
	
	public Match(Profile client, Profile other) {
		this.clientProfile = client;
		this.otherProfile = other;
	}
	
	@Override
	public DBDocumentPackage toDBPackage() {
		DBDocumentPackage newPackage = new DBDocumentPackage(clientProfile.getUserId());
		newPackage.addValue(_TYPE, this.type);
		newPackage.addValue(_CLIENT_MATCH_STATUS, this.clientMatchStatus.getStatusString());
		newPackage.addValue(_OTHER_MATCH_STATUS, this.otherMatchStatus.getStatusString());
		
		return newPackage;
	}
	
	public DBDocumentPackage converseMatchToDBPackage() {
		DBDocumentPackage newPackage = new DBDocumentPackage(otherProfile.getUserId());
		newPackage.addValue(_TYPE, this.type);
		newPackage.addValue(_CLIENT_MATCH_STATUS, this.otherMatchStatus.getStatusString());
		newPackage.addValue(_OTHER_MATCH_STATUS, this.clientMatchStatus.getStatusString());
		
		return newPackage;
	}
	
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

	public Profile getClientProfile() {
		return clientProfile;
	}

	public void setClientProfile(Profile clientProfile) {
		this.clientProfile = clientProfile;
	}

	public Profile getOtherProfile() {
		return otherProfile;
	}

	public void setOtherProfile(Profile otherProfile) {
		this.otherProfile = otherProfile;
	}

	public MatchType getType() {
		return type;
	}

	public void setType(MatchType newType) {
		this.type = newType;
	}
	
	public MatchStatus getClientMatchStatus() {
		return clientMatchStatus;
	}

	public void setClientMatchStatus(MatchStatus clientMatchStatus) {
		this.clientMatchStatus = clientMatchStatus;
	}
	
	public MatchStatus getOtherMatchStatus() {
		return otherMatchStatus;
	}

	public void setOtherMatchStatus(MatchStatus otherMatchStatus) {
		this.otherMatchStatus = otherMatchStatus;
	}

}
