package model;

import com.google.cloud.firestore.Query;

public interface DBQuerySynchronized {

	public void registerEventListener(Query q);
	
	public void removeEventListener();
}
