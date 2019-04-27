package firebase;

import java.util.Date;

import com.google.cloud.Timestamp;

/**
 * The Class FireBaseSchema.
 */
public class FireBaseSchema {
	
	/** The Constant PAYMENT_TABLE. */
	public static final String ACCOUNTS_TABLE = "Accounts",
							   PROFILES_TABLE = "Profiles",
							   MATCHES_TABLE = "Matches",
							   MATCHES_TABLE_COLLECTION = "userMatches",
							   MESSAGE_THREADS_TABLE = "MessageThreads",
							   MESSAGE_THREADS_TABLE_COLLECTION = "Messages",
							   ISSUES_TABLE = "Issues",
							   PAYMENT_TABLE = "Payments";
	
	/** The Constant PROFILE_IMAGE_PREFIX. */
	public static final String PROFILE_IMAGE_PREFIX = "p_img-";
	
	/** The Constant MESSAGE_THREAD_UID_DELIM. */
	public static final String MESSAGE_THREAD_UID_DELIM = "_+_";
	
	/**
	 * Parses the date.
	 *
	 * @param obj the obj
	 * @return the date
	 */
	public static Date parseDate(Object obj) {
		//perform additional formatting on Date object as needed:
		if(obj instanceof Timestamp) {
			return ((Timestamp) obj).toDate();
		}else if(obj instanceof Date) {
			return (Date) obj;
		}
		
		return null;
	}
	
	/**
	 * To profile image index.
	 *
	 * @param userId the user id
	 * @return the string
	 */
	public static String toProfileImageIndex(String userId) {
		return PROFILE_IMAGE_PREFIX + userId;
	}
	
	/**
	 * To message thread index.
	 *
	 * @param uid1 the uid 1
	 * @param uid2 the uid 2
	 * @return the string
	 */
	public static String toMessageThreadIndex(String uid1, String uid2) {
		if(uid1.compareTo(uid2) < 0) {
			return uid1 + MESSAGE_THREAD_UID_DELIM + uid2;
		}else {
			return uid2 + MESSAGE_THREAD_UID_DELIM + uid1;
		}
	}
	//Handle any additional Schema based logic here...
}
