package org.getcomposer.core.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.getcomposer.core.collection.JsonArray;
import org.getcomposer.core.objects.JsonObject;
import org.getcomposer.httpclient.HttpAsyncClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class AbstractJsonObject<V> extends JsonEntity implements
		JsonCollection {

	
	private transient Map<String, PropertyChangeListener> listeners = new HashMap<String, PropertyChangeListener>();
	protected transient Map<String, V> properties = new HashMap<String, V>();
	private Log log = LogFactory.getLog(HttpAsyncClient.class);	
	

	@SuppressWarnings("unchecked")
	protected void parse(Object obj) {
		clear();
		if (obj instanceof JSONObject) {
			for (Entry<String, Object> entry : ((Map<String, Object>) obj)
					.entrySet()) {
				parseValue((JSONObject) obj, entry.getKey());
			}
		}
	}

	protected void parseValue(JSONObject json, String property) {
		Object value = null;
		if (json.containsKey(property)) {
			value = json.get(property);
			if (value instanceof JSONArray) {
				value = new JsonArray(value);
			} else if (value instanceof JSONObject) {
				value = new JsonObject(value);
			}
		}
		set(property, value);
	}

	protected void parseField(JSONObject json, String property) {
		if (json.containsKey(property)) {
			Field field = getFieldByName(this.getClass(), property);

			if (field != null
					&& JsonEntity.class.isAssignableFrom(field.getType())) {
				try {
					field.setAccessible(true);
					JsonEntity entity = (JsonEntity) field.get(this);
					entity.fromJson(json.get(property));
					json.remove(property);
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
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

			// search class fields first
			if (namedFields.containsKey(entry)) {
				try {
					value = namedFields.get(entry).get(this);
				} catch (Exception e) {
					log.error(e);
				}
			}

			// check properties
			else if (properties.containsKey(entry)) {
				value = properties.get(entry);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.getcomposer.core.entities.JsonCollection#size()
	 */
	public int size() {
		return properties.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.getcomposer.core.entities.JsonCollection#clear()
	 */
	public void clear() {
		properties.clear();
	}

	/**
	 * Returns whether the given property is present.
	 * 
	 * @param property
	 *            the property to look for
	 * @return <ul>
	 *         <li><code>true</code> property present</li>
	 *         <li><code>false</code> property not present</li>
	 *         </ul>
	 */
	public boolean has(String property) {
		return properties.containsKey(property);
	}

	/**
	 * Returns the property value.
	 * 
	 * @param property
	 *            the property
	 * @return the value
	 */
	public V get(String property) {
		return properties.get(property);
	}

	/**
	 * Sets a new value for the given property.
	 * 
	 * @param property
	 *            the property
	 * @param value
	 *            the new value
	 */
	@SuppressWarnings("unchecked")
	public void set(String property, Object value) {
		
		// remove listener on the current value if there is one yet
		uninstallListener(property);

		JsonEntity entity = null;
		// install listener to be aware of changes
		if (value instanceof JsonValue) {
			entity = getEntity((JsonValue) value);
		} else if (value instanceof JsonEntity) {
			entity = (JsonEntity) value;
		}
		
		if (entity != null && !listeners.containsKey(property)) {
			installListener(property, entity);
		}

		V oldValue = properties.get(property);
		properties.put(property, (V) value);
		firePropertyChange(property, oldValue, (V) value);
	}
	

	/**
	 * Removes the given property.
	 * 
	 * @param property
	 *            the property
	 */
	public void remove(String property) {
		uninstallListener(property);
		Object oldValue = get(property);
		properties.remove(property);
		firePropertyChange(property, oldValue, null);
	}
	
	private void installListener(final String property, JsonEntity entity) {
		if (entity != null) {
			listeners.put(property, new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					firePropertyChange(property + "." + evt.getPropertyName(), 
							evt.getOldValue(), evt.getNewValue());
				}
			});

			entity.addPropertyChangeListener(listeners.get(property));
		}
	}

	private void uninstallListener(String property) {
		if (listeners.containsKey(property)) {
			if (has(property)) {
				JsonEntity entity = getEntity(get(property));
				if (entity != null) {
					entity.removePropertyChangeListener(listeners.get(property));
				}
			}
			listeners.remove(property);
		}
	}

	private JsonEntity getEntity(Object value) {
		JsonEntity entity = null;
		
		if (value instanceof JsonValue) {
			JsonValue val = (JsonValue) value; 
			
			if (val.isArray()) {
				entity = val.getAsArray();
			}

			if (val.isObject()) {
				entity = val.getAsObject();
			}
		}

		return entity;
	}
}
