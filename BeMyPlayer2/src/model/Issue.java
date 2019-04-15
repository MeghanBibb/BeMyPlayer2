package model;

public class Issue implements DBSerializable{
	
	public static final String _MESSAGE = "Message",
							   _TYPE = "Type",
							   _USER_ID = "UserID";
	
	
	private String userID = null;
	private String message = null;
	private String type = null;
	
	public Issue(String message, String type, String userID) {
		this.userID = userID;
		this.message = message;
		this.type = type;
	}

	@Override
	public DBDocumentPackage toDBPackage() {
		
		DBDocumentPackage p = new DBDocumentPackage();
		
		p.addValue(_MESSAGE, this.message);
		p.addValue(_TYPE, this.type);
		p.addValue(_USER_ID, this.userID);
		
		return p;
	}

	@Override
	public void initializeFromPackage(DBDocumentPackage pkg) {
		//do nothing for now
		
	}

}
