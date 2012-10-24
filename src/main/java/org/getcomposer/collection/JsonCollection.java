package org.getcomposer.collection;

import java.lang.reflect.Type;
import java.util.Collection;

import org.getcomposer.ObservableModel;

/**
 * Represents a dependency section of a composer package, either require or
 * require-dev
 * 
 * @see http://getcomposer.org/doc/04-schema.md#require
 * @see http://getcomposer.org/doc/04-schema.md#require-dev
 * @author Thomas Gossmann <gos.si>
 */
public abstract class JsonCollection<V> extends ObservableModel {

	protected Collection<V> collection;
	
	
	private Type type;
	
	protected JsonCollection(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	/**
	 * Returns the amount of items in the collection
	 * 
	 * @return the amount
	 */
	public abstract int size();
}
