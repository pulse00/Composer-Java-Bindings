package org.getcomposer.collection;

import java.lang.reflect.Type;

import org.getcomposer.Entity;

/**
 * Represents a dependency section of a composer package, either require or
 * require-dev
 * 
 * @see http://getcomposer.org/doc/04-schema.md#require
 * @see http://getcomposer.org/doc/04-schema.md#require-dev
 * @author Thomas Gossmann <gos.si>
 */
public abstract class JsonCollection<V> extends Entity {
	
	private transient Type valueType;
	
	public JsonCollection(Type type) {
		super();
		this.valueType = type;
	}
	
	public Type getValueType() {
		return valueType;
	}
	
	/**
	 * Returns the amount of items in the collection
	 * 
	 * @return the amount
	 */
	public abstract int size();
}
