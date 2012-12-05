package org.getcomposer.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.getcomposer.Entity;
import org.getcomposer.GenericValue;
import org.getcomposer.collection.GenericArray;
import org.getcomposer.collection.JsonMap;

/**
 * Represents a generic entity.
 * 
 * @author Thomas Gossmann <gos.si>
 *
 */
public class GenericEntity extends JsonMap<GenericEntity, GenericValue> implements Cloneable {

	private transient Map<String, PropertyChangeListener> listeners = new HashMap<String, PropertyChangeListener>();
	private transient List<String> silentProps;
	
	/**
	 * Creates an empty entity
	 */
	public GenericEntity() {
		super(Object.class);
		listen();
	}
	
	public GenericEntity(Object json) {
		super(Object.class);
		fromJson(json);
		listen();
	}
	
	public GenericEntity(String json) {
		super(Object.class);
		fromJson(json);
		listen();
	}
	
	public GenericEntity(File file) throws IOException {
		super(Object.class);
		fromJson(file);
		listen();
	}
	
	public GenericEntity(Reader reader) throws IOException {
		super(Object.class);
		fromJson(reader);
		listen();
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		this.silentProps = getSilentProperties();
	}
	
	protected List<String> getSilentProperties() {
		return new ArrayList<String>();
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
		if (has(property)) {
			return get(property).is(type);
		}
		return false;
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
		if (has(property)) {
			return get(property).isArray();
		}
		return false;
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
		if (has(property)) {
			return get(property).isEntity();
		}
		return false;
	}
	
	/**
	 * Returns the property value as string.
	 * 
	 * @param property the property
	 * @return the value
	 */
	public Object getAsObject(String property) {
		return get(property).getAsObject();
	}
	
	/**
	 * Returns the property value as array.
	 * 
	 * @param property the property
	 * @return the value
	 */
	public GenericArray getAsArray(String property) {
		if (!has(property)) {
			set(property, new GenericArray());
		}
		return get(property).getAsArray();
	}
	
	/**
	 * Returns the property value as string.
	 * 
	 * @param property the property
	 * @return the value as string
	 */
	public String getAsString(String property) {
		if (has(property)) {
			return get(property).getAsString();
		}
		return null;
	}

	/**
	 * Returns the property value as boolean.
	 * 
	 * @param property the property
	 * @return the value as boolean
	 */
	public Boolean getAsBoolean(String property) {
		if (has(property)) {
			return get(property).getAsBoolean();
		}
		return null;
	}
	
	/**
	 * Returns the property value as integer.
	 * 
	 * @param property the property
	 * @return the value as integer
	 */
	public Integer getAsInteger(String property) {
		if (has(property)) {
			return get(property).getAsInteger();
		}
		return null;
	}
	
	/**
	 * Returns the property value as double.
	 * 
	 * @param property the property
	 * @return the value as double
	 */
	public Float getAsFloat(String property) {
		if (has(property)) {
			return get(property).getAsFloat();
		}
		return null;
	}
	
	/**
	 * Returns the property value as entity.
	 * 
	 * @param property the property
	 * @return the value as entity
	 */
	public GenericEntity getAsEntity(String property) {
		if (!has(property)) {
			set(property, new GenericEntity());
		}
		return get(property).getAsEntity();
	}

	/**
	 * Sets a new value for the given property.
	 * 
	 * @param property the property
	 * @param value the new value
	 */
	@Override
	public void set(String property, Object value) {
		set(property, new GenericValue(value));
	}
	
	/**
	 * Sets a new value for the given property.
	 * 
	 * @param property the property
	 * @param value the new value
	 */
	public void set(final String property, GenericValue value) {

		// install listener to be aware of changes 		
		if ((value.isEntity() || value.isArray()) && !listeners.containsKey(property)) {
			installListener(property, value);
		}

		// remove listener if new value is a primitive
		if (!(value.isEntity() || value.isArray()) && listeners.containsKey(property)) {
			uninstallListener(property);
		}

		if (silentProps.contains(property)) {
			properties.put(property, value);
		} else {
			super.set(property, value);
		}
	}
	
	private void installListener(final String property, GenericValue value) {
		Entity entity = getEntity(value);
		
		if (value != null) {
			listeners.put(property, new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					firePropertyChange(property, evt.getOldValue(), evt.getNewValue());
				}
			});

			entity.addPropertyChangeListener(listeners.get(property));
		}
	}
	
	private void uninstallListener(String property) {
		if (listeners.containsKey(property)) {
			Entity entity = getEntity(get(property));
			entity.removePropertyChangeListener(listeners.get(property));
			listeners.remove(property);
		}
	}
	
	private Entity getEntity(GenericValue value) {
		Entity entity = null;
		
		if (value.isArray()) {
			entity = value.getAsArray();
		}
		
		if (value.isEntity()) {
			entity = value.getAsEntity();
		}
		
		return entity;
	}
	
	/**
	 * Removes the given property.
	 * 
	 * @param property the property
	 */
	public void remove(String property) {
		uninstallListener(property);
		remove(property);
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
}
