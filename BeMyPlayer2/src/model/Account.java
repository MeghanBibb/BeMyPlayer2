package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Account.
 */
public class Account implements DBSerializable{
	
	// These cannot be modified, as they provide the basis of the 
	/** The Constant _FRIEND_MATCHES. */
	// field names for the current database schema.
	public static final String _EMAIL = "email",
							   _PASSWORD_HASH = "password",
							   _SECURITY_Q1 = "securityQ1",
							   _SECURITY_Q2 = "securityQ2",
							   _SECURITY_Q1A = "securityQ1A",
							   _SECURITY_Q2A = "securityQ2A",
							   _LOVE_MATCHES = "loveMatches",
							   _FRIEND_MATCHES = "friendMatches";
	
	/** The user id. */
	private String userId = null;
	
	/** The email. */
	private String email; // (for now we will treat this like a username)
	
	/** The password hash. */
	private String passwordHash;
	
	/** The security Q 1. */
	private String securityQ1;
	
	/** The security Q 1 ans hash. */
	private String securityQ1AnsHash;
	
	/** The security Q 2. */
	private String securityQ2;
	
	/** The security Q 2 ans hash. */
	private String securityQ2AnsHash;
	
	private Boolean isMute = true;
	
	/** The account profile. */
	Profile accountProfile = null; // This composition is optional
	
	/**
	 * Instantiates a new account.
	 */
	public Account() {
		//Initialize to default values
	}
	
	/**
	 * Instantiates a new account.
	 *
	 * @param email the email
	 * @param pHash the hash
	 * @param sq1 the sq 1
	 * @param sqa1 the sqa 1
	 * @param sq2 the sq 2
	 * @param sqa2 the sqa 2
	 */
	public Account(String email, String pHash, String sq1, String sqa1, String sq2, String sqa2) {
		this.userId = null;
		this.email = email;
		this.passwordHash = pHash;
		this.securityQ1 = sq1;
		this.securityQ1AnsHash = sqa1;
		this.securityQ2 = sq2;
		this.securityQ2AnsHash = sqa2;
	}
	
	/**
	 * Instantiates a new account.
	 *
	 * @param userId the user id
	 * @param email the email
	 * @param pHash the hash
	 * @param sq1 the sq 1
	 * @param sqa1 the sqa 1
	 * @param sq2 the sq 2
	 * @param sqa2 the sqa 2
	 */
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
	
	/**
	 * Gets the account profile.
	 *
	 * @return the account profile
	 */
	public Profile getAccountProfile() {
		return accountProfile;
	}
	
	/**
	 * Sets the account profile.
	 *
	 * @param accountProfile the new account profile
	 */
	public void setAccountProfile(Profile accountProfile) {
		this.accountProfile = accountProfile;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the security Q 1.
	 *
	 * @return the security Q 1
	 */
	public String getSecurityQ1() {
		return securityQ1;
	}
	
	/**
	 * Sets the security Q 1.
	 *
	 * @param securityQ1 the new security Q 1
	 */
	public void setSecurityQ1(String securityQ1) {
		this.securityQ1 = securityQ1;
	}
	
	/**
	 * Gets the security Q 1 ans hash.
	 *
	 * @return the security Q 1 ans hash
	 */
	public String getSecurityQ1AnsHash() {
		return securityQ1AnsHash;
	}
	
	/**
	 * Sets the security Q 1 ans hash.
	 *
	 * @param securityQ1AnsHash the new security Q 1 ans hash
	 */
	public void setSecurityQ1AnsHash(String securityQ1AnsHash) {
		this.securityQ1AnsHash = securityQ1AnsHash;
	}
	
	/**
	 * Gets the security Q 2.
	 *
	 * @return the security Q 2
	 */
	public String getSecurityQ2() {
		return securityQ2;
	}
	
	/**
	 * Sets the security Q 2.
	 *
	 * @param securityQ2 the new security Q 2
	 */
	public void setSecurityQ2(String securityQ2) {
		this.securityQ2 = securityQ2;
	}
	
	/**
	 * Gets the security Q 2 ans hash.
	 *
	 * @return the security Q 2 ans hash
	 */
	public String getSecurityQ2AnsHash() {
		return securityQ2AnsHash;
	}
	
	/**
	 * Sets the security Q 2 ans hash.
	 *
	 * @param securityQ2AnsHash the new security Q 2 ans hash
	 */
	public void setSecurityQ2AnsHash(String securityQ2AnsHash) {
		this.securityQ2AnsHash = securityQ2AnsHash;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the password hash.
	 *
	 * @return the password hash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}
	
	/**
	 * Sets the password hash.
	 *
	 * @param p the new password hash
	 */
	public void setPasswordHash(String p) {
		this.passwordHash = p;
	}

	public Boolean getIsMute() {
		return isMute;
	}

	public void setIsMute(Boolean isMute) {
		this.isMute = isMute;
	}

	/* (non-Javadoc)
	 * @see model.DBSerializable#toDBPackage()
	 */
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

	/* (non-Javadoc)
	 * @see model.DBSerializable#initializeFromPackage(model.DBDocumentPackage)
	 */
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
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountProfile == null) ? 0 : accountProfile.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountProfile == null) {
			if (other.accountProfile != null)
				return false;
		} else if (!accountProfile.equals(other.accountProfile))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	
}
	