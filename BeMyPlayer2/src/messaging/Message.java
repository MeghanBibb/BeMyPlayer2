package messaging;

import java.util.Date;

import com.google.cloud.Timestamp;

import firebase.DBDocumentPackage;
import firebase.DBSerializable;
import firebase.FireBaseSchema;

/**
 * The Class Message.
 */
public final class Message implements DBSerializable{
	
	/** The message. */
	private String message;
	
	/** The timestamp. */
	private Timestamp timestamp;
	
	/** The sender id. */
	private String senderId;
	
	/** The Constant _MESSAGE. */
	//Do not touch- these are the DB Package field names!
	public static final String _MESSAGE = "message";
	
	/** The Constant _TIMESTAMP. */
	public static final String _TIMESTAMP = "timestamp";
	
	/** The Constant _SENDER_ID. */
	public static final String _SENDER_ID = "senderId";
	
	/**
	 * Instantiates a new message.
	 */
	public Message() {
		
	}
	
	public Message(String msg, Timestamp tstmp, String sender) {
		this.message = msg;
		this.timestamp = tstmp;
		this.senderId = sender;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public Date getTimestampDate() {
		return FireBaseSchema.parseDate(timestamp);
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Sets the timestamp now.
	 */
	public void setTimestampNow() {
		this.timestamp = Timestamp.now();
	}
	
	/**
	 * Gets the sender id.
	 *
	 * @return the sender id
	 */
	public String getSenderId() {
		return senderId;
	}
	
	/**
	 * Sets the sender id.
	 *
	 * @param senderId the new sender id
	 */
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	/* (non-Javadoc)
	 * @see model.DBSerializable#toDBPackage()
	 */
	@Override
	public DBDocumentPackage toDBPackage() {
		
		DBDocumentPackage newPackage = new DBDocumentPackage();
		newPackage.addValue(_MESSAGE, this.message);
		newPackage.addValue(_TIMESTAMP, this.timestamp);
		newPackage.addValue(_SENDER_ID, this.senderId);
		return newPackage;
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
				case _TIMESTAMP:
					this.timestamp = (Timestamp) pkg.getValues().get(s);
					break;
				case _SENDER_ID:
					this.senderId = (String) pkg.getValues().get(s);
					break;
			}
		}
		
	}
	
	
}
