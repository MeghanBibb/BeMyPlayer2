package model;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class DBDocumentPackage.
 */
public class DBDocumentPackage {
	
	/** The primary key. */
	private String primaryKey = null;
	
	/** The values. */
	private Map<String, Object> values;
	
	/**
	 * Instantiates a new DB document package.
	 */
	public DBDocumentPackage(){
		this.primaryKey = null;
		this.values = new HashMap<String, Object>();
	}
	
	/**
	 * Instantiates a new DB document package.
	 *
	 * @param key the key
	 */
	public DBDocumentPackage(String key) {
		this.primaryKey = key;
		this.values = new HashMap<String, Object>();
	}
	
	/**
	 * Instantiates a new DB document package.
	 *
	 * @param key the key
	 * @param values the values
	 */
	public DBDocumentPackage(String key, Map<String, Object> values) {
		this.primaryKey = key;
		this.values = values;
	}
	
	/**
	 * Adds the value.
	 *
	 * @param type the type
	 * @param value the value
	 */
	public void addValue(String type, Object value) {
		this.values.put(type, value);
	}
	
	/**
	 * Gets the primary key.
	 *
	 * @return the primary key
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}
	
	/**
	 * Sets the primary key.
	 *
	 * @param primaryKey the new primary key
	 */
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public Map<String, Object> getValues() {
		return values;
	}
	
	/**
	 * Sets the values.
	 *
	 * @param values the values
	 */
	public void setValues(Map<String, Object> values) {
		this.values = values;
	}
	
	
}
