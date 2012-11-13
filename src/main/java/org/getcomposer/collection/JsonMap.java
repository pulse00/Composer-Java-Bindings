package org.getcomposer.collection;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public abstract class JsonMap<C, V> extends JsonCollection<V> {

	protected transient Map<String, V> properties = new HashMap<String, V>();
	
	public JsonMap(Type type) {
		super(type);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.getcomposer.properties.JsonCollection#size()
	 */
	public int size() {
		return properties.size();
	}
	
	/**
	 * Returns whether the given property is present.
	 * 
	 * @param property the property to look for
	 * @return <ul>
	 * 	<li><code>true</code> property present</li>
	 * 	<li><code>false</code> property not present</li>
	 * </ul>
	 */
	public boolean has(String property) {
		return properties.containsKey(property);
	}
	
	/**
	 * Returns the property value.
	 * 
	 * @param property the property
	 * @return the value
	 */
	public V get(String property) {
		return properties.get(property);
	}
	
	/**
	 * Sets a new value for the given property.
	 * 
	 * @param property the property
	 * @param value the new value
	 */
	@SuppressWarnings("unchecked")
	public void set(String property, Object value) {
		Object oldValue = properties.get(property);
		properties.put(property, (V)value);
		firePropertyChange(property, oldValue, value);
	}
	
	/**
	 * Removes the given property.
	 * 
	 * @param property the property
	 */
	@SuppressWarnings("unchecked")
	public void remove(String property) {
		Map<String, V> oldProperties = (Map<String, V>) ((HashMap<String, V>)properties).clone();
		properties.remove(property);
		firePropertyChange("properties", oldProperties, properties);
	}

}
