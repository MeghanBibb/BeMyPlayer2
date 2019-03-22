package model;

import java.awt.Image;
import java.util.Date;
import java.util.List;

public class Profile {

	private String userId;
	
	private String username;
	private Date dateOB;
	private String gender;
	private String description;
	
	private List<Boolean> platforms;
	private List<Boolean> genres;
	
	private Image profilePicture = null;
	
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
	public Image getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(Image profilePicture) {
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
	
	
}
