package org.getcomposer.collection;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class IterableJsonMap<C, V> extends JsonMap<C, V> implements Iterable<Entry<String, V>> {
	
	protected IterableJsonMap(Type type) {
		super(type);
	}

	public Iterator<Entry<String, V>> iterator() {
		return collection.entrySet().iterator();
	}


}
