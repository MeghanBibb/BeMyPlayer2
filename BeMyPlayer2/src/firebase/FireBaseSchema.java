package firebase;

import java.util.Date;

import com.google.cloud.Timestamp;

public class FireBaseSchema {
	
	public static final String ACCOUNTS_TABLE = "Accounts",
							   PROFILES_TABLE = "Profiles",
							   MATCHES_TABLE = "Matches",
							   MATCHES_TABLE_COLLECTION = "userMatches",
							   MESSAGE_THREADS_TABLE = "MessageThreads",
							   MESSAGE_THREADS_TABLE_COLLECTION = "Messages",
							   ISSUES_TABLE = "Issues";
	
	public static final String PROFILE_IMAGE_PREFIX = "p_img-";
	public static final String MESSAGE_THREAD_UID_DELIM = "_+_";
	
	public static Date parseDate(Object obj) {
		//perform additional formatting on Date object as needed:
		if(obj instanceof Timestamp) {
			return ((Timestamp) obj).toDate();
		}else if(obj instanceof Date) {
			return (Date) obj;
		}
		
		return null;
	}
	
	public static String toProfileImageIndex(String userId) {
		return PROFILE_IMAGE_PREFIX + userId;
	}
	
	public static String toMessageThreadIndex(String uid1, String uid2) {
		if(uid1.compareTo(uid2) < 0) {
			return uid1 + MESSAGE_THREAD_UID_DELIM + uid2;
		}else {
			return uid2 + MESSAGE_THREAD_UID_DELIM + uid1;
		}
	}
	//Handle any additional Schema based logic here...
}
