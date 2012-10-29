package org.getcomposer.collection;

import org.getcomposer.internal.serialization.GenericArraySerializer;


public class GenericArray extends JsonList<Object> {

	public GenericArray() {
		super(Object.class);
	}
	
	@SuppressWarnings("unchecked")
	public GenericArray add(Object value) {
		return (GenericArray)super.add(value);
	}

	public static Object getSerializer() {
		return new GenericArraySerializer();
	}
}
