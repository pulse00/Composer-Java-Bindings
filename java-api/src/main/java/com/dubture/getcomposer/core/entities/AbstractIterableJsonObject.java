package com.dubture.getcomposer.core.entities;

import java.util.Iterator;
import java.util.Map.Entry;

public abstract class AbstractIterableJsonObject<V> extends AbstractJsonObject<V> implements Iterable<Entry<String, V>>{

	@Override
	public Iterator<Entry<String, V>> iterator() {
		return properties.entrySet().iterator();
	}
}
