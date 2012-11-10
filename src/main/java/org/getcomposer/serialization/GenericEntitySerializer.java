package org.getcomposer.serialization;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

import org.getcomposer.GenericValue;
import org.getcomposer.collection.GenericArray;
import org.getcomposer.entities.GenericEntity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GenericEntitySerializer implements JsonSerializer<GenericEntity>,
		JsonDeserializer<GenericEntity> {

	public JsonElement serialize(GenericEntity src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		
		writeProperties(json, src, context);
		
		return json;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void writeProperties(JsonObject json, GenericEntity src, JsonSerializationContext context) {
		try {
			Class cls = src.getClass();
			Field props = null;
			while (props == null && cls != null) {
				try {
					props = cls.getDeclaredField("properties");
				} catch (Exception e) {
					
				}
				cls = cls.getSuperclass();
			}
			props.setAccessible(true);
			
			Map<String, GenericValue> vals = (Map<String, GenericValue>)props.get(src);
			
			for (Entry<String, GenericValue> entry : vals.entrySet()) {
				GenericValue val = entry.getValue();
				if (val.isArray()) {
					json.add(entry.getKey(), context.serialize(val.getAsArray()));
				} else if (val.isEntity()) {
					json.add(entry.getKey(), context.serialize(val.getAsEntity()));
				} 
				
				// primitives
				else if (val.isBoolean()) {
					json.add(entry.getKey(), new JsonPrimitive(val.getAsBoolean()));
				} else if (val.isNumber()) {
					json.add(entry.getKey(), new JsonPrimitive(val.getAsNumber()));
				} else {
					json.add(entry.getKey(), new JsonPrimitive(val.getAsString()));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public GenericEntity deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		GenericEntity entity = new GenericEntity();
		
		setProperties(entity, json, context);
		
		return entity;
	}
	
	void setProperties(GenericEntity obj, JsonElement json, JsonDeserializationContext context) {
		for (Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
			if (entry.getValue().isJsonArray()) {
				obj.set(entry.getKey(), context.deserialize(entry.getValue(), GenericArray.class));
			} else if (entry.getValue().isJsonObject()) {
				obj.set(entry.getKey(), context.deserialize(entry.getValue(), GenericEntity.class));
			} else {
				obj.set(entry.getKey(), entry.getValue().getAsString());
			}
		}
	}

}
