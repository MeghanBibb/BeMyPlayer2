package firebase;

import java.util.Date;

import com.google.cloud.Timestamp;

public class FireBaseSchema {
	
	public static final String ACCOUNTS_TABLE = "Accounts",
							   PROFILES_TABLE = "Profiles";
	
	public static Date parseDate(Object obj) {
		//perform additional formatting on Date object as needed:
		if(obj instanceof Timestamp) {
			return ((Timestamp) obj).toDate();
		}else if(obj instanceof Date) {
			return (Date) obj;
		}
		
		return null;
	}
	//Handle any additional Schema based logic here...
}
