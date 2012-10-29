package org.getcomposer.internal.serialization;

import java.lang.reflect.Type;
import java.util.Map.Entry;

import org.getcomposer.collection.GenericArray;
import org.getcomposer.entities.GenericEntity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GenericEntitySerializer implements JsonSerializer<GenericEntity>,
		JsonDeserializer<GenericEntity> {

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

	public JsonElement serialize(GenericEntity src, Type typeOfSrc,
			JsonSerializationContext context) {
		
		return null;
	}

}
