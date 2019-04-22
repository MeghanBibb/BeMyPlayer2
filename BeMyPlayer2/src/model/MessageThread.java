package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentChange.Type;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;

import graphics.ExternalListener;

/**
 * The Class MessageThread.
 */
public class MessageThread implements DBQuerySynchronized{
	
	/** The Constant CHRONOLOGICAL_MSG_SORT. */
	private static final Comparator<Message> CHRONOLOGICAL_MSG_SORT = new Comparator<Message>() {
		public int compare(Message lm, Message rm) {
			return lm.getTimestamp().compareTo(rm.getTimestamp());
		}
	};
	
	/** The messages. */
	//other variables go here...
	private List<Message> messages = new ArrayList<Message>();
	private ListenerRegistration registration = null;
	private ExternalListener updateListener = null;
	
	/**
	 * Sets the messages.
	 *
	 * @param messages the new messages
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public List<Message> getMessages() {
		this.messages.sort(CHRONOLOGICAL_MSG_SORT);
		return messages;
	}
	
	/**
	 * Adds the message.
	 *
	 * @param m the m
	 */
	public void addMessage(Message m) {
		if(this.messages == null) {
			this.messages = new ArrayList<Message>();
		}
		this.messages.add(m);
	}
	
	/**
	 * Sort messages.
	 */
	public void sortMessages() {
		this.messages.sort(CHRONOLOGICAL_MSG_SORT);
	}
	
	/**
	 * Adds the messages.
	 *
	 * @param messages the messages
	 */
	public void addMessages(List<Message> messages) {
		this.messages.addAll(messages);
	}
	
	@Override
	public void registerEventListener(Query q) {
		this.registration = q.addSnapshotListener(this.getEventListener());
	}

	@Override
	public void removeEventListener() {
		this.registration.remove();
		this.registration = null;
	}
	

	@Override
	public void finalize() {
		if(this.registration != null) {
			this.registration.remove();
		}
	}
	
	public void setUpdateListener(ExternalListener e) {
		this.updateListener = e;
	}
	
	public void updateListener() {
		if(this.updateListener != null) {
			this.updateListener.externalUpdate();
		}
		
	}
	
	private EventListener<QuerySnapshot> getEventListener(){
		
		
		return new EventListener<QuerySnapshot>(){
			
			@Override
			public void onEvent(QuerySnapshot value, FirestoreException error) {
				if (error != null) {
					System.err.println("Listen failed: " + error);
					return;
			    }
				
				for(DocumentChange d : value.getDocumentChanges()) {
					if(d.getType() == Type.ADDED) {
						
						
						Map<String, Object> data = d.getDocument().getData();
						
						messages.add(new Message(
								(String) data.get(Message._MESSAGE),
								(Timestamp) data.get(Message._TIMESTAMP),
							    (String) data.get(Message._SENDER_ID))
						);
						
						updateListener();
					}
				}
				
			}
		};
	}
}
