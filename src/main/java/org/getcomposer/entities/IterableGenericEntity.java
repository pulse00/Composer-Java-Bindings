package org.getcomposer.entities;

import java.util.Iterator;
import java.util.Map.Entry;

import org.getcomposer.GenericValue;

public class IterableGenericEntity extends GenericEntity implements Iterable<Entry<String, GenericValue>> {

	public Iterator<Entry<String, GenericValue>> iterator() {
		return properties.entrySet().iterator();
	}
}
