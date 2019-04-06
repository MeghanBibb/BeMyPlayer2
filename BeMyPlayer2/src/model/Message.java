package model;

import java.util.Date;
import java.util.List;


import firebase.FireBaseSchema;

public final class Message implements DBSerializable{
	
	private String matchId;
	private String message;
	private Date timestamp;
	private String senderId;

	//Do not touch- these are the DB Package field names!
	public static final String _MESSAGE = "message";
	public static final String _TIMESTAMP = "timestamp";
	public static final String _SENDER_ID = "senderId";
	
	public Message(String matchId) {
		this.matchId = matchId;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	@Override
	public DBDocumentPackage toDBPackage() {
		
		DBDocumentPackage newPackage = new DBDocumentPackage(this.matchId);
		newPackage.addValue(_MESSAGE, this.message);
		newPackage.addValue(_TIMESTAMP, this.timestamp);
		newPackage.addValue(_SENDER_ID, this.senderId);
		return newPackage;
	}
	
	@Override
	public void initializeFromPackage(DBDocumentPackage pkg) {
		this.matchId = pkg.getPrimaryKey();
		for(String s : pkg.getValues().keySet()) {
			switch(s) {
				case _MESSAGE:
					this.message = (String) pkg.getValues().get(s);
					break;
				case _TIMESTAMP:
					this.timestamp = FireBaseSchema.parseDate(pkg.getValues().get(s));
					break;
				case _SENDER_ID:
					this.senderId = (String) pkg.getValues().get(s);
					break;
			}
		}
		
	}
	
	
}
