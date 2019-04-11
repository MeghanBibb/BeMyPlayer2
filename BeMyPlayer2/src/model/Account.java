package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Account implements DBSerializable{
	
	// These cannot be modified, as they provide the basis of the 
	// field names for the current database schema.
	public static final String _EMAIL = "email",
							   _PASSWORD_HASH = "password",
							   _SECURITY_Q1 = "securityQ1",
							   _SECURITY_Q2 = "securityQ2",
							   _SECURITY_Q1A = "securityQ1A",
							   _SECURITY_Q2A = "securityQ2A",
							   _LOVE_MATCHES = "loveMatches",
							   _FRIEND_MATCHES = "friendMatches";
	
	private String userId = null;
	
	private String email; // (for now we will treat this like a username)
	private String passwordHash;
	
	private String securityQ1;
	private String securityQ1AnsHash;
	private String securityQ2;
	private String securityQ2AnsHash;
	
	private List<String> loveMatches = null;
	private List<String> friendMatches = null;
	
	Profile accountProfile = null; // This composition is optional
	
	public Account() {
		//Initialize to default values
	}
	
	public Account(String email, String pHash, String sq1, String sqa1, String sq2, String sqa2) {
		this.userId = null;
		this.email = email;
		this.passwordHash = pHash;
		this.securityQ1 = sq1;
		this.securityQ1AnsHash = sqa1;
		this.securityQ2 = sq2;
		this.securityQ2AnsHash = sqa2;
	}
	
	public Account(String userId, String email, String pHash, String sq1, String sqa1, String sq2, String sqa2) {
		this.userId = userId;
		this.email = email;
		this.passwordHash = pHash;
		this.securityQ1 = sq1;
		this.securityQ1AnsHash = sqa1;
		this.securityQ2 = sq2;
		this.securityQ2AnsHash = sqa2;
	}
	
	//account profile must be set manually
	
	public Profile getAccountProfile() {
		return accountProfile;
	}
	public void setAccountProfile(Profile accountProfile) {
		this.accountProfile = accountProfile;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSecurityQ1() {
		return securityQ1;
	}
	public void setSecurityQ1(String securityQ1) {
		this.securityQ1 = securityQ1;
	}
	public String getSecurityQ1AnsHash() {
		return securityQ1AnsHash;
	}
	public void setSecurityQ1AnsHash(String securityQ1AnsHash) {
		this.securityQ1AnsHash = securityQ1AnsHash;
	}
	public String getSecurityQ2() {
		return securityQ2;
	}
	public void setSecurityQ2(String securityQ2) {
		this.securityQ2 = securityQ2;
	}
	public String getSecurityQ2AnsHash() {
		return securityQ2AnsHash;
	}
	public void setSecurityQ2AnsHash(String securityQ2AnsHash) {
		this.securityQ2AnsHash = securityQ2AnsHash;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String p) {
		this.passwordHash = p;
	}
	public List<String> getLoveMatches() {
		return loveMatches;
	}
	
	//TODO: Add/Remove matches functionality
	
	public void setLoveMatches(List<String> loveMatches) {
		this.loveMatches = loveMatches;
	}
	public List<String> getFriendMatches() {
		return friendMatches;
	}
	public void setFriendMatches(List<String> friendMatches) {
		this.friendMatches = friendMatches;
	}

	
	@Override
	public DBDocumentPackage toDBPackage(){
		
		DBDocumentPackage p = new DBDocumentPackage();
		if(this.userId != null) {
			p.setPrimaryKey(userId);
		}
		
		//NOTE: Profile must be serialized separately!
		p.addValue(_EMAIL, this.email);
		p.addValue(_PASSWORD_HASH, this.passwordHash);
		p.addValue(_SECURITY_Q1, this.securityQ1);
		p.addValue(_SECURITY_Q2, this.securityQ2);
		p.addValue(_SECURITY_Q1A, this.securityQ1AnsHash);
		p.addValue(_SECURITY_Q2A, this.securityQ2AnsHash);
		
		return p;
	}

	@Override
	public void initializeFromPackage(DBDocumentPackage pkg) {
		
		this.userId = pkg.getPrimaryKey();
		for(String s : pkg.getValues().keySet()) {
			switch(s) {
				case _EMAIL:
					this.email = (String) pkg.getValues().get(s);
					break;
				case _PASSWORD_HASH:
					this.passwordHash = (String) pkg.getValues().get(s);
					break;
				case _SECURITY_Q1:
					this.securityQ1 = (String) pkg.getValues().get(s);
					break;
				case _SECURITY_Q2:
					this.securityQ2 = (String) pkg.getValues().get(s);
					break;
				case _SECURITY_Q1A:
					this.securityQ1AnsHash = (String) pkg.getValues().get(s);
					break;
				case _SECURITY_Q2A:
					this.securityQ2AnsHash = (String) pkg.getValues().get(s);
					break;
				case _LOVE_MATCHES:
					if(pkg.getValues().get(s) instanceof List<?>) {
						this.loveMatches = (List<String>) pkg.getValues().get(s);
					}
				case _FRIEND_MATCHES:
					if(pkg.getValues().get(s) instanceof List<?>) {
						this.friendMatches = (List<String>) pkg.getValues().get(s);
					}
			}
		}
	}
	
	
	
	
}
	