package model;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface DBSerializable.
 */
public interface DBSerializable {
	
	/**
	 * To DB package.
	 *
	 * @return the DB document package
	 */
	// Must be serializable in plaintext format
	public DBDocumentPackage toDBPackage();
	
	/**
	 * Initialize from package.
	 *
	 * @param pkg the pkg
	 */
	public void initializeFromPackage(DBDocumentPackage pkg);
	
}
