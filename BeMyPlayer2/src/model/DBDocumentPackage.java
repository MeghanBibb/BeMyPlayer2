package model;

import java.util.HashMap;
import java.util.Map;

public class DBDocumentPackage {
	
	private String primaryKey = null;
	private Map<String, Object> values;
	
	
	public DBDocumentPackage(){
		this.primaryKey = null;
		this.values = new HashMap<String, Object>();
	}
	
	public DBDocumentPackage(String key) {
		this.primaryKey = key;
		this.values = new HashMap<String, Object>();
	}
	
	public void addValue(String type, Object value) {
		this.values.put(type, value);
	}
	
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public Map<String, Object> getValues() {
		return values;
	}
	public void setValues(Map<String, Object> values) {
		this.values = values;
	}
	
	
}
