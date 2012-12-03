package org.getcomposer.collection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.getcomposer.annotation.Name;
import org.getcomposer.entities.GenericEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public abstract class JsonMap<C, V> extends JsonCollection<V> {

	protected transient Map<String, V> properties = new HashMap<String, V>();
	
	public JsonMap(Type type) {
		super(type);
	}
	
	@SuppressWarnings("unchecked")
	protected void parse(Object obj) {
		clear();
		if (obj instanceof JSONObject) {
			for (Entry<String, Object> entry : ((Map<String, Object>)obj).entrySet()) {
				parseValue((JSONObject)obj, entry.getKey());
			}
		}
	}

	protected void parseValue(JSONObject json, String property) {
		Object value = null;
		if (json.containsKey(property)) {
			value = json.get(property);
			if (value instanceof JSONArray) {
				value = new GenericArray(value);
			} else if (value instanceof JSONObject) {
				value = new GenericEntity(value);
			}
		}
		set(property, value);
	}
	
	public Object prepareJson(LinkedList<String> fields) {
		
		// First: add properties that aren't in the hashmap yet
		for (Entry<String, V> entry : properties.entrySet()) {
			fields.add(entry.getKey());
		}
		
		// create an index to search for field names
		HashMap<String, Field> namedFields = new HashMap<String, Field>();
		for (Field field : getFields(this.getClass())) {
			field.setAccessible(true);
			namedFields.put(getFieldName(field), field);
		}
		
		LinkedHashMap<String, Object> out = new LinkedHashMap<String, Object>();
		// Second: find field contents (either field or property key)
		for (String entry : fields) {
			if (out.containsKey(entry)) {
				continue;
			}
			Object value = null;
			
			// check properties first
			if (properties.containsKey(entry)) {
				value = properties.get(entry);
			} 
			
			// search class fields
			else if (namedFields.containsKey(entry)) {
				try {
					value = namedFields.get(entry).get(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			value = prepareJsonValue(value);
			
			if (value == null || value.equals("")) {
				continue;
			}
			
			// run value.toJson() if available
			out.put(entry, value);
		}
		
		return out;
	}
	
	@SuppressWarnings("rawtypes")
	private ArrayList<Field> getFields(Class entity) {
		ArrayList<Field> fields = new ArrayList<Field>();
		Class superClass = entity;
		
		while (superClass != null) {
			for (Field field : superClass.getDeclaredFields()) {
				if (!((field.getModifiers() & Modifier.TRANSIENT) == Modifier.TRANSIENT)) {
					fields.add(field);
				}
			}
			superClass = superClass.getSuperclass();		
		}
		
		return fields;
	}
	
	private String getFieldName(Field field) {
		String name = field.getName();
		for (Annotation anno : field.getAnnotations()) {
			if (anno.annotationType() == Name.class) {
				name = ((Name) anno).value();
			}
		}
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see org.getcomposer.properties.JsonCollection#size()
	 */
	public int size() {
		return properties.size();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.getcomposer.collection.JsonCollection#clear()
	 */
	public void clear() {
		properties.clear();
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
