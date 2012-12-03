package org.getcomposer.collection;

import java.lang.reflect.Type;
import java.util.LinkedList;

import org.getcomposer.Entity;
import org.getcomposer.GenericValue;
import org.getcomposer.entities.Autoload;

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
