package firebase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hasher {
	
	/*
	 * This class provides a basic implementation of MD5 hashing
	 * for an extra layer of security; for information on this
	 * algorithm see the below URL:
	 * 
	 * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	 * 
	 * Note that the below algorithm is unsalted, but secure enough for the purposes of this application.
	 */
	
	public final static Logger LOGGER = Logger.getLogger(Hasher.class.getName());
	
	private static final String DIGEST = "MD5";
	
	public static String hashString(String plaintext) {
		
        String hashedString = null;
        try {

            MessageDigest md = MessageDigest.getInstance(DIGEST);
            md.update(plaintext.getBytes());
            
            byte[] bytes = md.digest();
            
            StringBuilder hash = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                hash.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedString = hash.toString();
            
        }
        catch (NoSuchAlgorithmException e)
        {
        	LOGGER.log(Level.WARNING,"Error- unable to hash string value; digest \"" 
        			   + DIGEST + "\" not found.");
        }
        
        return hashedString;
	}
}
