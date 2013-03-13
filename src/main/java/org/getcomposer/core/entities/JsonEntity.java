package org.getcomposer.core.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.getcomposer.core.annotation.Name;
import org.getcomposer.core.objects.Autoload;
import org.getcomposer.core.utils.JsonFormatter;
import org.json.simple.JSONValue;

public abstract class JsonEntity extends Entity {

	private transient Set<String> listening = new HashSet<String>();
	@SuppressWarnings("rawtypes")
	private transient Map<Class, Map<String, Field>> fieldNameCache = new HashMap<Class, Map<String, Field>>();

	public JsonEntity() {
		listen();
		initialize();
	}
	
	// can be filled by subclasses
	protected void initialize() {
	}
	
	protected void listen() {
		try {
			for (Field field : getFields(this.getClass())) {
				if (JsonCollection.class.isAssignableFrom(field.getType())) {
					final String prop = getFieldName(field);	
					
					if (listening.contains(prop)) {
						continue;
					}
					
					field.setAccessible(true);
					JsonEntity obj = (JsonEntity)field.get(this);

					if (obj != null) {
						obj.addPropertyChangeListener(new PropertyChangeListener() {
							public void propertyChange(PropertyChangeEvent e) {
								firePropertyChange(prop + "." + e.getPropertyName(), 
										e.getOldValue(), e.getNewValue());
							}
						});
						
						listening.add(prop);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	protected ArrayList<Field> getFields(Class entity) {
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
	
	protected String getFieldName(Field field) {
		String name = field.getName();
		for (Annotation anno : field.getAnnotations()) {
			if (anno.annotationType() == Name.class) {
				name = ((Name) anno).value();
			}
		}
		return name;
	}
	
	@SuppressWarnings("rawtypes")
	protected Field getFieldByName(Class entity, String fieldName) {
		
		// create cache
		if (!fieldNameCache.containsKey(entity)) {
			Map<String, Field> mapping = new HashMap<String, Field>();
			
			for (Field field : getFields(entity)) {
				mapping.put(getFieldName(field), field);
			}
			fieldNameCache.put(entity, mapping);
		}
		
		Map<String, Field> mapping = fieldNameCache.get(entity);
		
		if (mapping.containsKey(fieldName)) {
			return mapping.get(fieldName);
		}
		
		return null;
	}
	
	
	// subclasses should implement that
	protected abstract void parse(Object obj);
	
	public abstract Object prepareJson(LinkedList<String> fields);
	
	protected Object prepareJsonValue(Object value) {
		if (value instanceof JsonValue) {
			return ((JsonValue)value).toJsonValue();
		} else if (value instanceof JsonCollection) {
			JsonCollection coll = (JsonCollection) value;
			if (coll.size() > 0 || value instanceof Autoload) {
				return ((JsonEntity)coll).prepareJson(new LinkedList<String>());
			}
		} else {
			return value;
		}

		return null;
	}
	
	public String toJson() {
		return JsonFormatter.format(prepareJson(new LinkedList<String>()));
	}
	
	public void fromJson(Object json) {
		parse(json);
	}
	
	public void fromJson(String json) {
		parse(JSONValue.parse(json));
	}
	
	public void fromJson(File file) throws IOException {
		fromJson(new FileReader(file));
	}
	
	public void fromJson(Reader reader) throws IOException {
		parse(JSONValue.parse(reader));
	}
}
