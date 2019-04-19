package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import firebase.FireBaseSchema;

// TODO: Auto-generated Javadoc
/**
 * The Class Profile.
 */
public class Profile implements DBSerializable{
	
	// These cannot be modified, as they provide the basis of the 
	/** The Constant _GENRES. */
	// field names for the current database schema.
	public static final String _USERNAME = "username",
							   _DATE_OF_BIRTH = "dateOfBirth",
							   _GENDER = "gender",
							   _DESCRIPTION = "description",
							   _PLATFORMS = "platforms",
							   _GENRES = "genres";
	
	/** The Constant DOB_FORMAT. */
	public static final SimpleDateFormat DOB_FORMAT = new SimpleDateFormat("dd/MM/yyyy"); 
	
	/** The user id. */
	private String userId;
	
	/** The username. */
	private String username;
	
	/** The date OB. */
	private Date dateOB;
	
	/** The gender. */
	private String gender;
	
	/** The description. */
	private String description;
	
	/** The is mute. */
	private Boolean isMute = false;
	
	/** The platforms. */
	private List<Boolean> platforms;
	
	/** The genres. */
	private List<Boolean> genres;
	
	/** The profile picture. */
	private BufferedImage profilePicture = null;
	
	/**
	 * Instantiates a new profile.
	 */
	public Profile() {
		// Initialize to default values
	}
	
	/**
	 * Instantiates a new profile.
	 *
	 * @param username the username
	 * @param dateOB the date OB
	 * @param gender the gender
	 * @param description the description
	 */
	public Profile(String username, Date dateOB, String gender, String description) {
		this.userId = null;
		this.username = username;
		this.dateOB = dateOB;
		this.gender = gender;
		this.description = description;
		this.platforms = new ArrayList<Boolean>();
		this.genres = new ArrayList<Boolean>();
		this.isMute = false;
	}
	
	/**
	 * Instantiates a new profile.
	 *
	 * @param username the username
	 * @param dateOB the date OB
	 * @param gender the gender
	 * @param description the description
	 * @param platforms the platforms
	 * @param genres the genres
	 */
	public Profile(String username, Date dateOB, String gender, String description,
			   List<Boolean> platforms, List<Boolean> genres) {
		this.userId = null;
		this.username = username;
		this.dateOB = dateOB;
		this.gender = gender;
		this.description = description;
		this.platforms = platforms;
		this.genres = genres;
		this.isMute = false;
	}
	
	/**
	 * Instantiates a new profile.
	 *
	 * @param userId the user id
	 * @param username the username
	 * @param dateOB the date OB
	 * @param gender the gender
	 * @param description the description
	 * @param platforms the platforms
	 * @param genres the genres
	 */
	public Profile(String userId, String username, Date dateOB, String gender, String description,
				   List<Boolean> platforms, List<Boolean> genres) {
		this.userId = userId;
		this.username = username;
		this.dateOB = dateOB;
		this.gender = gender;
		this.description = description;
		this.platforms = platforms;
		this.genres = genres;
		this.isMute = false;
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
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the date OB.
	 *
	 * @return the date OB
	 */
	public Date getDateOB() {
		return dateOB;
	}
	
	/**
	 * Sets the date OB.
	 *
	 * @param dateOB the new date OB
	 */
	public void setDateOB(Date dateOB) {
		this.dateOB = dateOB;
	}
	
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the profile picture.
	 *
	 * @return the profile picture
	 */
	public BufferedImage getProfilePicture() {
		return profilePicture;
	}
	
	/**
	 * Sets the profile picture.
	 *
	 * @param profilePicture the new profile picture
	 */
	public void setProfilePicture(BufferedImage profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	/**
	 * Gets the platforms.
	 *
	 * @return the platforms
	 */
	public List<Boolean> getPlatforms() {
		return platforms;
	}
	
	/**
	 * Sets the platforms.
	 *
	 * @param platforms the new platforms
	 */
	public void setPlatforms(List<Boolean> platforms) {
		this.platforms = platforms;
	}
	
	/**
	 * Gets the genres.
	 *
	 * @return the genres
	 */
	public List<Boolean> getGenres() {
		return genres;
	}
	
	/**
	 * Sets the genres.
	 *
	 * @param genres the new genres
	 */
	public void setGenres(List<Boolean> genres) {
		this.genres = genres;
	}
	
	/**
	 * Gets the mute.
	 *
	 * @return the mute
	 */
	public Boolean getMute() {
		return isMute;
	}
	
	/**
	 * Sets the mute.
	 *
	 * @param mute the new mute
	 */
	public void setMute(Boolean mute) {
		isMute = mute;
	}

	/* (non-Javadoc)
	 * @see model.DBSerializable#toDBPackage()
	 */
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
		
		//flatten genres and platforms into a text array
		String pls = "", gs = "";
		for(Boolean b : this.platforms) { pls += b? "Y":"N"; };
		for(Boolean b : this.genres) { gs += b? "Y":"N"; };
		
		p.addValue(_PLATFORMS, pls);
		p.addValue(_GENRES, gs);
		
		return p;
	}

	/* (non-Javadoc)
	 * @see model.DBSerializable#initializeFromPackage(model.DBDocumentPackage)
	 */
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
		Profile other = (Profile) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	
}
