package model;
import java.awt.Image;
import java.util.List;
import java.util.Map;

public class Account implements TextSerializable{
	
	// These cannot be modified, as they provide the basis of the 
	// current database schema.
	public static final String EMAIL = "email",
							   PASSWORD_HASH = "password",
							   SECURITY_Q1 = "securityQ1",
							   SECURITY_Q2 = "securityQ2",
							   SECURITY_Q1A = "securityQ1A",
							   Security_Q2A = "securityQ2A";
	
	private String userId;
	
	private String email; // (for now we will treat this like a username)
	private String passwordHash;
	
	private String securityQ1;
	private String securityQ1AnsHash;
	private String securityQ2;
	private String securityQ2AnsHash;
	
	Profile accountProfile = null;
	
	
	
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
	public String getPasswordHash() {
		return passwordHash;
	}

	@Override
	public Map<String, String> attributeKeySet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
	