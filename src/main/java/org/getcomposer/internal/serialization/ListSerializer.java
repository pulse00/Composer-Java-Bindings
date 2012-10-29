package org.getcomposer.internal.serialization;

import java.lang.reflect.Type;

import org.getcomposer.collection.JsonList;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@SuppressWarnings("rawtypes")
public class ListSerializer<C extends JsonList, V> implements JsonDeserializer<C>, JsonSerializer<C> {

	public JsonElement serialize(C src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonArray json = new JsonArray();
		
		for (Object item : src) {
			json.add(context.serialize(item));
		}
		return json;
	}

	@SuppressWarnings({ "unchecked"})
	public C deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		try {
			
			Class<C> cls = (Class<C>) typeOfT;
			C list = cls.newInstance();
            
            for (JsonElement item : json.getAsJsonArray()) {
				list.add((V) context.deserialize(item, list.getValueType()));
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


}
