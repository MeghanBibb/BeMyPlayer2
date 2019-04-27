package support;

import firebase.DBDocumentPackage;
import firebase.DBSerializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Issue.
 */
public class Issue implements DBSerializable{
	
	/** The Constant _USER_ID. */
	public static final String _MESSAGE = "Message",
							   _TYPE = "Type",
							   _USER_ID = "UserID";
	
	
	/** The user ID. */
	private String userID = null;
	
	/** The message. */
	private String message = null;
	
	/** The type. */
	private String type = null;
	
	/**
	 * Instantiates a new issue.
	 *
	 * @param message the message
	 * @param type the type
	 * @param userID the user ID
	 */
	public Issue(String message, String type, String userID) {
		this.userID = userID;
		this.message = message;
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see model.DBSerializable#toDBPackage()
	 */
	@Override
	public DBDocumentPackage toDBPackage() {
		
		DBDocumentPackage p = new DBDocumentPackage();
		//(Primary key is auto-generated)
		p.addValue(_MESSAGE, this.message);
		p.addValue(_TYPE, this.type);
		p.addValue(_USER_ID, this.userID);
		
		return p;
	}

	/* (non-Javadoc)
	 * @see model.DBSerializable#initializeFromPackage(model.DBDocumentPackage)
	 */
	@Override
	public void initializeFromPackage(DBDocumentPackage pkg) {
		for(String s : pkg.getValues().keySet()) {
			switch(s) {
				case _MESSAGE:
					this.message = (String) pkg.getValues().get(s);
					break;
				case _TYPE:
					this.type = (String) pkg.getValues().get(s);
					break;
				case _USER_ID:
					this.userID = (String) pkg.getValues().get(s);
					break;
			}
		}
	}

}
