package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import firebase.FireBaseSchema;

public class Profile implements DBSerializable{
	
	// These cannot be modified, as they provide the basis of the 
	// field names for the current database schema.
	public static final String _USERNAME = "username",
							   _DATE_OF_BIRTH = "dateOfBirth",
							   _GENDER = "gender",
							   _DESCRIPTION = "description",
							   _PLATFORMS = "platforms",
							   _GENRES = "genres",
							   _IS_MUTE = "isMute";
	
	public static final SimpleDateFormat DOB_FORMAT = new SimpleDateFormat("dd/MM/yyyy"); 
	
	private String userId;
	private String username;
	private Date dateOB;
	private String gender;
	private String description;
	private Boolean isMute;
	
	private List<Boolean> platforms;
	private List<Boolean> genres;
	
	private BufferedImage profilePicture = null;
	
	public Profile() {
		// Initialize to default values
	}
	
	public Profile(String username, Date dateOB, String gender, String description) {
		this.userId = null;
		this.username = username;
		this.dateOB = dateOB;
		this.gender = gender;
		this.description = description;
		this.platforms = new ArrayList<Boolean>();
		this.genres = new ArrayList<Boolean>();
	}
	
	public Profile(String username, Date dateOB, String gender, String description,
			   List<Boolean> platforms, List<Boolean> genres) {
		this.userId = null;
		this.username = username;
		this.dateOB = dateOB;
		this.gender = gender;
		this.description = description;
		this.platforms = platforms;
		this.genres = genres;
	}
	
	public Profile(String userId, String username, Date dateOB, String gender, String description,
				   List<Boolean> platforms, List<Boolean> genres) {
		this.userId = userId;
		this.username = username;
		this.dateOB = dateOB;
		this.gender = gender;
		this.description = description;
		this.platforms = platforms;
		this.genres = genres;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDateOB() {
		return dateOB;
	}
	public void setDateOB(Date dateOB) {
		this.dateOB = dateOB;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BufferedImage getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(BufferedImage profilePicture) {
		this.profilePicture = profilePicture;
	}
	public List<Boolean> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(List<Boolean> platforms) {
		this.platforms = platforms;
	}
	public List<Boolean> getGenres() {
		return genres;
	}
	public void setGenres(List<Boolean> genres) {
		this.genres = genres;
	}
	public Boolean getMute() {
		return isMute;
	}
	public void setMute(Boolean mute) {
		isMute = mute;
	}

	@Override
	public DBDocumentPackage toDBPackage() {
		DBDocumentPackage p = new DBDocumentPackage();
		if(this.userId != null) {
			p.setPrimaryKey(userId);
		}
		
		//NOTE: Profile must be serialized separately!
		p.addValue(_USERNAME, this.username);
		p.addValue(_DATE_OF_BIRTH, this.dateOB);
		p.addValue(_GENDER, this.gender);
		p.addValue(_DESCRIPTION, this.description);
		p.addValue(_IS_MUTE, this.isMute);

		//flatten genres and platforms into a text array
		String pls = "", gs = "";
		for(Boolean b : this.platforms) { pls += b? "Y":"N"; };
		for(Boolean b : this.genres) { gs += b? "Y":"N"; };
		
		p.addValue(_PLATFORMS, pls);
		p.addValue(_GENRES, gs);
		
		return p;
	}

	@Override
	public void initializeFromPackage(DBDocumentPackage pkg) {
		
		this.userId = pkg.getPrimaryKey();
		
		for(String s : pkg.getValues().keySet()) {
			String arrStr;
			switch(s) {
				case _USERNAME:
					this.username = (String) pkg.getValues().get(s);
					break;
				case _DATE_OF_BIRTH:
					this.dateOB = FireBaseSchema.parseDate(pkg.getValues().get(s));
					break;
				case _GENDER:
					this.gender = (String) pkg.getValues().get(s);
					break;
				case _DESCRIPTION:
					this.description = (String) pkg.getValues().get(s);
					break;
				case _PLATFORMS:
					arrStr = ((String) pkg.getValues().get(s)).toUpperCase();
					this.platforms = new ArrayList<Boolean>();
					for(int i =0 ; i < arrStr.length(); ++i) {
						this.platforms.add(arrStr.charAt(i) == 'Y');
					}
					break;
				case _GENRES:
					arrStr = ((String) pkg.getValues().get(s)).toUpperCase();
					this.genres = new ArrayList<Boolean>();
					for(int i =0 ; i < arrStr.length(); ++i) {
						this.genres.add(arrStr.charAt(i) == 'Y');
					}
					break;
				case _IS_MUTE:
					this.isMute = (Boolean) pkg.getValues().get(s);
					break;
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	
}
