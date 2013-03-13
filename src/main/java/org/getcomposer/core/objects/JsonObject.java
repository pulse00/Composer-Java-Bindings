package org.getcomposer.core.objects;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map.Entry;

import org.getcomposer.core.collection.JsonArray;
import org.getcomposer.core.entities.AbstractJsonObject;
import org.getcomposer.core.entities.JsonValue;

public class JsonObject extends AbstractJsonObject<JsonValue> {

	public JsonObject() {
		listen();
	}

	public JsonObject(Object json) {
		fromJson(json);
		listen();
	}

	public JsonObject(String json) {
		fromJson(json);
		listen();
	}

	public JsonObject(File file) throws IOException {
		fromJson(file);
		listen();
	}

	public JsonObject(Reader reader) throws IOException {
		fromJson(reader);
		listen();
	}

	@Override
	protected void initialize() {
		super.initialize();
	}

	/**
	 * Returns whether the property is instance of the given type.
	 * 
	 * @param property
	 *            the property
	 * @param type
	 *            the type
	 * @return <ul>
	 *         <li><code>true</code> property is instance of type</li>
	 *         <li><code>false</code> property is not an instance of type</li>
	 *         </ul>
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
	 * @param property
	 *            the property
	 * @return <ul>
	 *         <li><code>true</code> property is an array</li>
	 *         <li><code>false</code> property is not an array</li>
	 *         </ul>
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
	 * @param property
	 *            the property
	 * @return <ul>
	 *         <li><code>true</code> property is an entity</li>
	 *         <li><code>false</code> property is not an entity</li>
	 *         </ul>
	 */
	public boolean isObject(String property) {
		if (has(property)) {
			return get(property).isObject();
		}
		return false;
	}

	/**
	 * Returns the property value as string.
	 * 
	 * @param property
	 *            the property
	 * @return the value
	 */
	public Object getAsRaw(String property) {
		return get(property).getAsRaw();
	}

	/**
	 * Returns the property value as array.
	 * 
	 * @param property
	 *            the property
	 * @return the value
	 */
	public JsonArray getAsArray(String property) {
		if (!has(property)) {
			set(property, new JsonArray());
		}
		return get(property).getAsArray();
	}

	/**
	 * Returns the property value as string.
	 * 
	 * @param property
	 *            the property
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
	 * @param property
	 *            the property
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
	 * @param property
	 *            the property
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
	 * @param property
	 *            the property
	 * @return the value as double
	 */
	public Float getAsFloat(String property) {
		if (has(property)) {
			return get(property).getAsFloat();
		}
		return null;
	}

	/**
	 * Returns the property value as object.
	 * 
	 * @param property
	 *            the property
	 * @return the value as entity
	 */
	public JsonObject getAsObject(String property) {
		if (!has(property)) {
			set(property, new JsonObject());
		}
		return get(property).getAsObject();
	}

	/**
	 * Sets a new value for the given property.
	 * 
	 * @param property
	 *            the property
	 * @param value
	 *            the new value
	 */
	public void set(String property, Object value) {
		super.set(property, new JsonValue(value));
	}

//	/**
//	 * Sets a new value for the given property.
//	 * 
//	 * @param property
//	 *            the property
//	 * @param value
//	 *            the new value
//	 */
//	public void set(String property, JsonValue value) {
//		if (silentProps.contains(property)) {
//			properties.put(property, value);
//		} else {
//			super.set(property, value);
//		}
//	}

	protected void cloneProperties(JsonObject clone) {
		for (Entry<String, JsonValue> entry : properties.entrySet()) {
			clone.set(entry.getKey(), entry.getValue());
		}
	}
}
