package model;

import java.util.ArrayList;

import firebase.FireBaseSchema;

public class Match implements DBSerializable{ 
	
	//private Profile 
	private Profile clientProfile = null;
	private Profile otherProfile = null;
	private Boolean blockStatus = false;
	private MatchStatus clientMatchStatus = MatchStatus.NO_MATCH;
	private MatchStatus otherMatchStatus = MatchStatus.NO_MATCH;
	
	//Database schema values (do not modify!)
	public static final String _BLOCK_STATUS = "block_status";
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
		newPackage.addValue(_BLOCK_STATUS, this.blockStatus);
		newPackage.addValue(_CLIENT_MATCH_STATUS, this.clientMatchStatus.getStatusString());
		newPackage.addValue(_OTHER_MATCH_STATUS, this.otherMatchStatus.getStatusString());
		
		return newPackage;
	}
	
	public DBDocumentPackage converseMatchToDBPackage() {
		DBDocumentPackage newPackage = new DBDocumentPackage(otherProfile.getUserId());
		newPackage.addValue(_CLIENT_MATCH_STATUS, this.otherMatchStatus.getStatusString());
		newPackage.addValue(_OTHER_MATCH_STATUS, this.clientMatchStatus.getStatusString());
		
		return newPackage;
	}
	
	@Override
	public void initializeFromPackage(DBDocumentPackage pkg) {
		
		for(String s : pkg.getValues().keySet()) {
			switch(s) {
				case _BLOCK_STATUS:
					this.blockStatus = (Boolean) pkg.getValues().get(s);
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

	public Boolean getBlockStatus() {
		return blockStatus;
	}

	public void setBlockStatus(Boolean blockStatus) {
		this.blockStatus = blockStatus;
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
