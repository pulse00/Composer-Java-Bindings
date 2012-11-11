package org.getcomposer.collection;

import org.getcomposer.serialization.GenericArraySerializer;


public class GenericArray extends JsonList<Object>  implements Iterable<Object> {

	public GenericArray() {
		super(Object.class);
	}
	
	@SuppressWarnings("unchecked")
	public GenericArray add(Object value) {
		return (GenericArray)super.add(value);
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
	
	public static Object getSerializer() {
		return new GenericArraySerializer();
	}
}
