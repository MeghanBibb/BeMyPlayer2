package model;

import firebase.FireBaseSchema;

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
		//(Primary key is auto-generated)
		p.addValue(_MESSAGE, this.message);
		p.addValue(_TYPE, this.type);
		p.addValue(_USER_ID, this.userID);
		
		return p;
	}

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
