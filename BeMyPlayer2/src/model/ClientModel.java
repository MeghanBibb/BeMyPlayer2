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

// TODO: Auto-generated Javadoc
/**
 * The Class ClientModel.
 */
public class ClientModel {
	
	/** The friend match batch. */
	private int friendMatchBatch = 0;
	
	/** The love match batch. */
	private int loveMatchBatch = 0;
	
	/** The new friend match queue. */
	private Queue<Profile> newFriendMatchQueue = null;//new PriorityBlockingQueue<Profile>();
	
	/** The new love match queue. */
	private Queue<Profile> newLoveMatchQueue = null; //new PriorityBlockingQueue<Profile>();
	
	/** The love matches. */
	private List<Profile> loveMatches = new ArrayList<Profile>();
	
	/** The friend matches. */
	private List<Profile> friendMatches = new ArrayList<Profile>();
	
	/** The profile image cache. */
	private Map<String,BufferedImage> profileImageCache = new HashMap<String, BufferedImage>();
	
	/**
	 * Instantiates a new client model.
	 *
	 * @param currentAccountProfile the current account profile
	 */
	public ClientModel(Profile currentAccountProfile) {
		this.newFriendMatchQueue = new PriorityQueue<Profile>(
										Matchmaker.getFriendComparator(currentAccountProfile));
		this.newLoveMatchQueue = new PriorityQueue<Profile>(
										Matchmaker.getLoveComparator(currentAccountProfile));
	}
	
	/**
	 * Enqueue friend profiles.
	 *
	 * @param newFriends the new friends
	 */
	public void enqueueFriendProfiles(List<Profile> newFriends) {
		newFriendMatchQueue.addAll(newFriends);
	}
	
	/**
	 * Enqueue love profiles.
	 *
	 * @param newLoves the new loves
	 */
	public void enqueueLoveProfiles(List<Profile> newLoves) {
		newLoveMatchQueue.addAll(newLoves);
	}
	
	/**
	 * Import partial friend batch.
	 *
	 * @param newFriends the new friends
	 */
	public void importPartialFriendBatch(List<Profile> newFriends) {
		newFriendMatchQueue.addAll(newFriends);
	}
	
	/**
	 * Import partial love batch.
	 *
	 * @param newLoves the new loves
	 */
	public void importPartialLoveBatch(List<Profile> newLoves) {
		newLoveMatchQueue.addAll(newLoves);
	}
	
	/**
	 * Import unmatched love batch.
	 *
	 * @param newLoves the new loves
	 */
	public void importUnmatchedLoveBatch(List<Profile> newLoves) {
		newLoveMatchQueue.addAll(newLoves);
		loveMatchBatch++;
	}
	
	/**
	 * Import unmatched friend batch.
	 *
	 * @param newFriends the new friends
	 */
	public void importUnmatchedFriendBatch(List<Profile> newFriends) {
		newFriendMatchQueue.addAll(newFriends);
		friendMatchBatch++;
	}
	
	/**
	 * Gets the friend profile front.
	 *
	 * @return the friend profile front
	 */
	public Profile getFriendProfileFront() {
		if(newFriendMatchQueue.size() == 0) {
			return null;
		}
		return newFriendMatchQueue.peek();
	}
	
	/**
	 * Gets the love profile front.
	 *
	 * @return the love profile front
	 */
	public Profile getLoveProfileFront() {
		if(newLoveMatchQueue.size() == 0) {
			return null;
		}
		return newLoveMatchQueue.peek();
	}
	
	/**
	 * Dequeue friend profile.
	 */
	public void dequeueFriendProfile() {
		newFriendMatchQueue.remove();
	}
	
	/**
	 * Deque love profile.
	 */
	public void dequeLoveProfile() {
		newLoveMatchQueue.remove();
	}
	
	/**
	 * Gets the love matches.
	 *
	 * @return the love matches
	 */
	public List<Profile> getLoveMatches() {
		return loveMatches;
	}
	
	/**
	 * Adds the love match.
	 *
	 * @param b the b
	 */
	public void addLoveMatch(Profile b) {
		this.loveMatches.add(b);
	}
	
	/**
	 * Adds the friend match.
	 *
	 * @param b the b
	 */
	public void addFriendMatch(Profile b) {
		this.friendMatches.add(b);
	}
	
	/**
	 * Sets the love matches.
	 *
	 * @param loveMatches the new love matches
	 */
	public void setLoveMatches(List<Profile> loveMatches) {
		this.loveMatches = loveMatches;
	}

	/**
	 * Gets the friend matches.
	 *
	 * @return the friend matches
	 */
	public List<Profile> getFriendMatches() {
		return friendMatches;
	}

	/**
	 * Sets the friend matches.
	 *
	 * @param matches the new friend matches
	 */
	public void setFriendMatches(List<Profile> matches) {
		this.friendMatches = matches;
	}

	/**
	 * Reset friend match batch.
	 */
	public void resetFriendMatchBatch() {
		this.friendMatchBatch = 0;
	}

	/**
	 * Reset love match batch.
	 */
	public void resetLoveMatchBatch() {
		this.loveMatchBatch = 0;
	}
	
	/**
	 * Gets the love match batch.
	 *
	 * @return the love match batch
	 */
	public int getLoveMatchBatch() {
		return this.loveMatchBatch;
	}
	
	/**
	 * Gets the friend match batch.
	 *
	 * @return the friend match batch
	 */
	public int getFriendMatchBatch() {
		return this.friendMatchBatch;
	}
	
}
