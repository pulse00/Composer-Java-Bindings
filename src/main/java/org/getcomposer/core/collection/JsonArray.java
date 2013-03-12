package org.getcomposer.core.collection;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.getcomposer.core.entities.AbstractJsonArray;

public class JsonArray extends AbstractJsonArray<Object> {

	public JsonArray() {
	}
	
	public JsonArray(Object json) {
		fromJson(json);
	}
	
	public JsonArray(String json) {
		fromJson(json);
	}
	
	public JsonArray(File file) throws IOException {
		fromJson(file);
	}
	
	public JsonArray(Reader reader) throws IOException {
		fromJson(reader);
	}

	/**
	 * Returns the values as an array of strings
	 * 
	 * @return
	 */
	public String[] toStringArray() {
		String[] arr = new String[values.size()];
		int i = 0;
		for (Object val : values) {
			arr[i++] = (String) val;
		}
		return arr;
	}
}
