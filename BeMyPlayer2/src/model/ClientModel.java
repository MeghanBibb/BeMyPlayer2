package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import firebase.FireBaseAdapter;

public class ClientModel {
	
	private int friendMatchBatch = 0;
	private int loveMatchBatch = 0;
	
	private Queue<Profile> newFriendMatchQueue = null;//new PriorityBlockingQueue<Profile>();
	private Queue<Profile> newLoveMatchQueue = null; //new PriorityBlockingQueue<Profile>();
	
	private List<Profile> loveMatches = new ArrayList<Profile>();
	private List<Profile> friendMatches = new ArrayList<Profile>();
	
	private Map<String,BufferedImage> profileImageCache = new HashMap<String, BufferedImage>();
	
	public ClientModel(Profile currentAccountProfile) {
		this.newFriendMatchQueue = new PriorityQueue<Profile>(
										Matchmaker.getFriendComparator(currentAccountProfile));
		this.newLoveMatchQueue = new PriorityQueue<Profile>(
										Matchmaker.getLoveComparator(currentAccountProfile));
	}
	
	public void enqueueFriendProfiles(List<Profile> newFriends) {
		newFriendMatchQueue.addAll(newFriends);
	}
	
	public void enqueueLoveProfiles(List<Profile> newLoves) {
		newLoveMatchQueue.addAll(newLoves);
	}
	
	public void importPartialFriendBatch(List<Profile> newFriends) {
		newFriendMatchQueue.addAll(newFriends);
	}
	
	public void importPartialLoveBatch(List<Profile> newLoves) {
		newLoveMatchQueue.addAll(newLoves);
	}
	
	public void importUnmatchedLoveBatch(List<Profile> newLoves) {
		newLoveMatchQueue.addAll(newLoves);
		loveMatchBatch++;
	}
	
	public void importUnmatchedFriendBatch(List<Profile> newFriends) {
		newFriendMatchQueue.addAll(newFriends);
		friendMatchBatch++;
	}
	
	public Profile getFriendProfileFront() {
		if(newFriendMatchQueue.size() == 0) {
			return null;
		}
		return newFriendMatchQueue.peek();
	}
	
	public Profile getLoveProfileFront() {
		if(newLoveMatchQueue.size() == 0) {
			return null;
		}
		return newLoveMatchQueue.peek();
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

	public void resetFriendMatchBatch() {
		this.friendMatchBatch = 0;
	}

	public void resetLoveMatchBatch() {
		this.loveMatchBatch = 0;
	}
	
	public int getLoveMatchBatch() {
		return this.loveMatchBatch;
	}
	
	public int getFriendMatchBatch() {
		return this.friendMatchBatch;
	}
	
}
