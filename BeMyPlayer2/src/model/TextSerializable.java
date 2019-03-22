package model;

import java.util.Map;

public interface TextSerializable {
	
	// Must be serializable in plaintext format
	public Map<String, String> attributeKeySet();
	
}
