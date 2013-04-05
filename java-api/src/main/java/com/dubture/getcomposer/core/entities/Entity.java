package com.dubture.getcomposer.core.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Entity {
	
	protected transient PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	
	/**
	 * Adds a listener to be notified when a property changes
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * Removes a listener that no longer receives notification about property changes
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * Adds a listener to be notified when the passed property changes
	 * 
	 * @param propertyName the property upon which the listener will be notified
	 * @param listener
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Removes a listener that no longer receives notification about changes from
	 * the passed property
	 * 
	 * @param propertyName the property upon which the listener has been notified
	 * @param listener
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Notify listeners that a property has changed its value
	 * 
	 * @param propertyName
	 * @param oldValue
	 * @param newValue
	 */
	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		
		
		if (oldValue instanceof JsonValue) {
			oldValue = getRawObject((JsonValue)oldValue);
		}
		
		if (newValue instanceof JsonValue) {
			newValue = getRawObject((JsonValue)newValue);
		}
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	private Object getRawObject(JsonValue value) {
		if (value.isArray()) {
			return value.getAsArray();
		} else if (value.isObject()) {
			return value.getAsObject();
		} else if (value.isNumber()) {
			return value.getAsNumber();
		} else if (value.isBoolean()) {
			return value.getAsBoolean();
		} else {
			return value.getAsString();
		}
	}
}
