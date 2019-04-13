package model;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ClientModel {
	
	
	
	int friendMatchBatch = 0;
	int loveMatchBatch = 0;
	
	Queue<Profile> newFriendMatchQueue;
	Queue<Profile> newLoveMatchQueue;
	
	List<Profile> loveMatches;
	List<Profile> friendMatches;
	
	Map<String,BufferedImage> profileImageCache;
	
	
	
}
