package model;

import java.util.Comparator;
import java.util.List;

public class MessageThread {
	
	private static final Comparator<Message> CHRONOLOGICAL_MSG_SORT = new Comparator<Message>() {
		public int compare(Message lm, Message rm) {
			return lm.getTimestamp().compareTo(rm.getTimestamp());
		}
	};
	
	//other variables go here...
	private List<Message> messages;
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void addMessage(Message m) {
		this.messages.add(m);
	}
	
	public void sortMessages() {
		this.messages.sort(CHRONOLOGICAL_MSG_SORT);
	}
	
	public void addMessages(List<Message> messages) {
		this.messages.addAll(messages);
	}
}
