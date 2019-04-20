package model;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.Query;
import com.google.firebase.database.annotations.Nullable;

public interface DBQuerySynchronized {

	public void registerEventListener(Query q);
	
	public void removeEventListener();
}
