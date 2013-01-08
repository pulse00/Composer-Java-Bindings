package org.getcomposer.collection;

import java.lang.reflect.Type;
import java.util.LinkedList;

import org.getcomposer.Entity;
import org.getcomposer.GenericValue;
import org.getcomposer.entities.Autoload;

/**
 * Represents a collection
 * 
 * @author Thomas Gossmann <gos.si>
 */
public abstract class JsonCollection<V> extends Entity {
	
	private transient Type valueType;
	
	public JsonCollection(Type type) {
		super();
		this.valueType = type;
	}
	
	/**
	 * Returns the type for the values of this collection
	 * 
	 * @return the type
	 */
	public Type getValueType() {
		return valueType;
	}
	
	@SuppressWarnings("rawtypes")
	protected Object prepareJsonValue(Object value) {
		if (value instanceof GenericValue) {
			return ((GenericValue)value).toJsonValue();
		} else if (value instanceof JsonCollection) {
			JsonCollection coll = (JsonCollection) value;
			if (coll.size() > 0 || value instanceof Autoload) {
				return coll.prepareJson(new LinkedList<String>());
			}
		} else {
			return value;
		}
		
		return null;
	}
	
	/**
	 * Returns the amount of items in the collection
	 * 
	 * @return the amount
	 */
	public abstract int size();
	
	public abstract void clear();
}
