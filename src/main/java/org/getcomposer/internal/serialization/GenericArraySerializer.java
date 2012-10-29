package org.getcomposer.internal.serialization;

import java.lang.reflect.Type;

import org.getcomposer.collection.GenericArray;
import org.getcomposer.entities.GenericEntity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GenericArraySerializer implements JsonDeserializer<GenericArray>,
		JsonSerializer<GenericArray> {

	public JsonElement serialize(GenericArray src, Type typeOfSrc,
			JsonSerializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	public GenericArray deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		GenericArray array = new GenericArray();
		
		for (JsonElement item : json.getAsJsonArray()) {
			if (item.isJsonObject()) {
				array.add(context.deserialize(item, GenericEntity.class));
			} else {
				array.add(item.getAsString());
			}
		}

		return array;
	}

}
