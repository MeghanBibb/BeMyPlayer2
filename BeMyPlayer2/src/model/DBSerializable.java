package model;

import java.util.Map;

public interface DBSerializable {
	
	// Must be serializable in plaintext format
	public DBDocumentPackage toDBPackage();
	
	public void initializeFromPackage(DBDocumentPackage pkg);
	
}
