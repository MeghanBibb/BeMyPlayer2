package model;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import firebase.FireBaseAdapter;

public class ClientModel {
	
	int friendMatchBatch = 0;
	int loveMatchBatch = 0;
	
	private Queue<Profile> newFriendMatchQueue = new PriorityBlockingQueue<Profile>();
	private Queue<Profile> newLoveMatchQueue = new PriorityBlockingQueue<Profile>();
	
	private List<Profile> loveMatches;
	private List<Profile> friendMatches;
	
	private Map<String,BufferedImage> profileImageCache;
	
	public void enqueueFriendProfiles(List<Profile> newFriends) {
		newFriendMatchQueue.addAll(newFriends);
	}
	
	public void enqueueLoveProfiles(List<Profile> newLoves) {
		newLoveMatchQueue.addAll(newLoves);
	}
	
	public Profile getFriendProfileFront() {
		return newFriendMatchQueue.peek();
	}
	
	public Profile getLoveProfileFront() {
		return newFriendMatchQueue.peek();
	}
	
	public void dequeueFriendProfile() {
		newFriendMatchQueue.remove();
	}
	
	public void dequeLoveProfile() {
		newLoveMatchQueue.remove();
	}
	
	public List<Profile> getLoveMatches() {
		return loveMatches;
	}

	public void setLoveMatches(List<Profile> loveMatches) {
		loveMatches = loveMatches;
	}

	public List<Profile> getFriendMatches() {
		return friendMatches;
	}

	public void setFriendMatches(List<Profile> matches) {
		friendMatches = matches;
	}
	
	
}
