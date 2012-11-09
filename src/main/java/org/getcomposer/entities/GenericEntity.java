package org.getcomposer.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.getcomposer.GenericValue;
import org.getcomposer.collection.GenericArray;
import org.getcomposer.collection.JsonMap;
import org.getcomposer.internal.serialization.GenericEntitySerializer;

/**
 * Represents a generic entity.
 * 
 * @author Thomas Gossmann <gos.si>
 *
 */
public class GenericEntity extends JsonMap<GenericEntity, GenericValue> implements Cloneable {

	private transient Map<String, PropertyChangeListener> listeners = new HashMap<String, PropertyChangeListener>();
	
	/**
	 * Creates an empty entity
	 */
	public GenericEntity() {
		super(Object.class);
	}
	
	/**
	 * Returns whether the property is instance of the given type.
	 * 
	 * @param property the property
	 * @param type the type
	 * @return <ul>
	 * 	<li><code>true</code> property is instance of type</li>
	 * 	<li><code>false</code> property is not an instance of type</li>
	 * </ul>
	 */
	public boolean is(String property, Type type) {
		return properties.get(property).is(type);
	}
	
	/**
	 * Returns whether the property is instance of an array.
	 * 
	 * @see #getAsArray
	 * @param property the property
	 * @return <ul>
	 * 	<li><code>true</code> property is an array</li>
	 * 	<li><code>false</code> property is not an array</li>
	 * </ul>
	 */
	public boolean isArray(String property) {
		return properties.get(property).isArray();
	}
	
	/**
	 * Returns whether the property is instance of an entity.
	 * 
	 * @see #getAsEntity
	 * @param property the property
	 * @return <ul>
	 * 	<li><code>true</code> property is an entity</li>
	 * 	<li><code>false</code> property is not an entity</li>
	 * </ul>
	 */
	public boolean isEntity(String property) {
		return properties.get(property).isEntity();
	}
	
	/**
	 * Returns the property value as string.
	 * 
	 * @param property the property
	 * @return the value
	 */
	public Object getAsObject(String property) {
		return properties.get(property);
	}
	
	/**
	 * Returns the property value as array.
	 * 
	 * @param property the property
	 * @return the value
	 */
	public GenericArray getAsArray(String property) {
		if (!properties.containsKey(property)) {
			properties.put(property, new GenericValue(new GenericArray()));
		}
		return properties.get(property).getAsArray();
	}
	
	/**
	 * Returns the property value as string.
	 * 
	 * @param property the property
	 * @return the value as string
	 */
	public String getAsString(String property) {
		return properties.get(property).getAsString();
	}

	/**
	 * Returns the property value as boolean.
	 * 
	 * @param property the property
	 * @return the value as boolean
	 */
	public boolean getAsBoolean(String property) {
		return properties.get(property).getAsBoolean();
	}
	
	/**
	 * Returns the property value as integer.
	 * 
	 * @param property the property
	 * @return the value as integer
	 */
	public int getAsInteger(String property) {
		return properties.get(property).getAsInteger();
	}
	
	/**
	 * Returns the property value as double.
	 * 
	 * @param property the property
	 * @return the value as double
	 */
	public float getAsFloat(String property) {
		return properties.get(property).getAsFloat();
	}
	
	/**
	 * Returns the property value as entity.
	 * 
	 * @param property the property
	 * @return the value as entity
	 */
	public GenericEntity getAsEntity(String property) {
		return properties.get(property).getAsEntity();
	}

	/**
	 * Sets a new value for the given property.
	 * 
	 * @param property the property
	 * @param value the new value
	 * @return this
	 */
	public GenericEntity set(final String property, GenericValue value) {
		super.set(property, value);
		
		// install listener to be aware of changes 
		if ((value.isEntity() || value.isArray())
				&& !listeners.containsKey(property)) {
			listeners.put(property, new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					firePropertyChange(property, evt.getOldValue(), evt.getNewValue());
				}
			});
		}
		
		// remove listener if new value is a primitive
		if (!(value.isEntity() || value.isArray())
				&& listeners.containsKey(property)) {
			listeners.remove(property);
		}
		
		return (GenericEntity) this;
	}
	
	/**
	 * Sets a new value for the given property.
	 * 
	 * @param property the property
	 * @param value the new value
	 * @return this
	 */
	public GenericEntity set(String property, Object value) {
		set(property, new GenericValue(value));
		return (GenericEntity) this;
	}
	
	/**
	 * Removes the given property.
	 * 
	 * @param property the property
	 * @return this
	 */
	public GenericEntity remove(String property) {
		remove(property);
		listeners.remove(property);
		return this;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public GenericEntity clone() {
		try {
			return (GenericEntity)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	public static Object getSerializer() {
		return new GenericEntitySerializer();
	}
}
