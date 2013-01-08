package org.getcomposer.collection;

import java.io.File;
import java.io.IOException;
import java.io.Reader;


public class GenericArray extends JsonList<Object>  implements Iterable<Object> {

	public GenericArray() {
		super(Object.class);
	}
	
	public GenericArray(Object json) {
		super(Object.class);
		fromJson(json);
	}
	
	public GenericArray(String json) {
		super(Object.class);
		fromJson(json);
	}
	
	public GenericArray(File file) throws IOException {
		super(Object.class);
		fromJson(file);
	}
	
	public GenericArray(Reader reader) throws IOException {
		super(Object.class);
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
