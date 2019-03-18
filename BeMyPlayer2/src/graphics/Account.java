package graphics;
import java.awt.Image;
import java.util.List;

public class Account {
	
	//for initializing constants with SQL/Firebase connection:
	public static final int MAX_USERNAME_CHARS = 40,
							MAX_EMAIL_CHARS = 80,
							MAX_PASSWORD_CHARS = 80,
							MAX_SECURITY_Q_CHARS = 240,
							MAX_DOB_CHARS = 10,
							MAX_GENDER_CHARS = 10,
							MAX_DESCRIPTION_CHARS = 400;
	
	private String username;
	private String email;
	private String password;
	private String securityQ;
	private String securityQA;
	private String dateOB;
	private String gender;
	private String descrition;
	private Image profile;
	private List<Boolean> platforms;
	private List<Boolean> genres;
	
}
	