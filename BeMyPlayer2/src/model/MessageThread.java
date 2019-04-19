package model;

import java.util.Comparator;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageThread.
 */
public class MessageThread{
	
	/** The Constant CHRONOLOGICAL_MSG_SORT. */
	private static final Comparator<Message> CHRONOLOGICAL_MSG_SORT = new Comparator<Message>() {
		public int compare(Message lm, Message rm) {
			return lm.getTimestamp().compareTo(rm.getTimestamp());
		}
	};
	
	/** The messages. */
	//other variables go here...
	private List<Message> messages;
	
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
		return messages;
	}
	
	/**
	 * Adds the message.
	 *
	 * @param m the m
	 */
	public void addMessage(Message m) {
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
}
