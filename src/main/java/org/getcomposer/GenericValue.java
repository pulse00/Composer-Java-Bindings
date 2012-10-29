package org.getcomposer;

import java.lang.reflect.Type;

import org.getcomposer.collection.GenericArray;
import org.getcomposer.entities.GenericEntity;

public class GenericValue {

	private Object value;
	
	public GenericValue(Object value) {
		this.value = value;
	}
	
	/**
	 * Returns whether the value is instance of the given type.
	 * 
	 * @param type the type
	 * @return <ul>
	 * 	<li><code>true</code> property is instance of type</li>
	 * 	<li><code>false</code> property is not an instance of type</li>
	 * </ul>
	 */
	public boolean is(Type type) {
		return value.getClass().isAssignableFrom((Class<?>) type);
	}
	
	/**
	 * Returns whether the value is instance of an array.
	 * 
	 * @see #getAsArray
	 * @return <ul>
	 * 	<li><code>true</code> property is an array</li>
	 * 	<li><code>false</code> property is not an array</li>
	 * </ul>
	 */
	public boolean isArray() {
		return value instanceof GenericArray;
	}
	
	/**
	 * Returns whether the property is instance of an entity.
	 * 
	 * @see #getAsEntity
	 * @return <ul>
	 * 	<li><code>true</code> property is an entity</li>
	 * 	<li><code>false</code> property is not an entity</li>
	 * </ul>
	 */
	public boolean isEntity() {
		return value instanceof GenericEntity;
	}
	
	/**
	 * Returns the value.
	 * 
	 * @return the value
	 */
	public Object getAsObject() {
		return value;
	}
	
	/**
	 * Returns the value as array.
	 * 
	 * @return the value
	 */
	public GenericArray getAsArray() {
		return (GenericArray)value;
	}
	
	/**
	 * Returns the value as string.
	 * 
	 * @return the value as string
	 */
	public String getAsString() {
		return (String)value;
	}

	/**
	 * Returns the value as boolean.
	 * 
	 * @return the value as boolean
	 */
	public boolean getAsBoolean() {
		return Boolean.parseBoolean((String)value);
	}
	
	/**
	 * Returns the value as integer.
	 * 
	 * @return the value as integer
	 */
	public int getAsInteger() {
		return Integer.valueOf((String)value);
	}
	
	/**
	 * Returns the value as float.
	 * 
	 * @return the value as float
	 */
	public float getAsFloat() {
		return Float.valueOf((String)value);
	}
	
	/**
	 * Returns the value as entity.
	 * 
	 * @return the value as entity
	 */
	public GenericEntity getAsEntity() {
		return (GenericEntity)value;
	}
}
