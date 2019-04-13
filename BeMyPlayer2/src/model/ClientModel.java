package model;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class ClientModel {
	
	static int friendMatchBatch = 0;
	static int loveMatchBatch = 0;
	
	private static Queue<Profile> newFriendMatchQueue = new PriorityBlockingQueue<Profile>();
	private static Queue<Profile> newLoveMatchQueue = new PriorityBlockingQueue<Profile>();
	
	static List<Profile> loveMatches;
	static List<Profile> friendMatches;
	
	static Map<String,BufferedImage> profileImageCache;
	
	public static void enqueueFriendProfiles(List<Profile> newFriends) {
		newFriendMatchQueue.addAll(newFriends);
	}
	
	public static void enqueueLoveProfiles(List<Profile> newLoves) {
		newLoveMatchQueue.addAll(newLoves);
	}
	
	public static Profile getFriendProfileFront() {
		return newFriendMatchQueue.peek();
	}
	
	public static Profile getLoveProfileFront() {
		return newFriendMatchQueue.peek();
	}
	
	public static void dequeueFriendProfile() {
		newFriendMatchQueue.remove();
	}
	
	public static void dequeLoveProfile() {
		newLoveMatchQueue.remove();
	}

	public static List<Profile> getLoveMatches() {
		return loveMatches;
	}

	public static void setLoveMatches(List<Profile> loveMatches) {
		ClientModel.loveMatches = loveMatches;
	}

	public static List<Profile> getFriendMatches() {
		return friendMatches;
	}

	public static void setFriendMatches(List<Profile> friendMatches) {
		ClientModel.friendMatches = friendMatches;
	}
	
	public static void pollBatchOfFriends(List<Profile> friendMatches)
	
	
	
	
}
