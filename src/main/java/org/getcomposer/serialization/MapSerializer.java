package org.getcomposer.serialization;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.getcomposer.collection.IterableJsonMap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@SuppressWarnings("rawtypes")
public class MapSerializer<C extends IterableJsonMap, V> implements JsonDeserializer<C>, JsonSerializer<C> {

	public JsonElement serialize(C src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		
		@SuppressWarnings("unchecked")
		Iterator<Entry<String, V>> it = src.iterator();
		while (it.hasNext()) {
			Entry<String, V> item = it.next();
			json.add(item.getKey(), context.serialize(item.getValue()));
		}
		
		// duh, eclipse shows an error here:
//		for (Entry<String, V> item : src) {
//			json.add(item.getKey(), context.serialize(item.getValue()));
//		}
		return json;
	}

	@SuppressWarnings({ "unchecked"})
	public C deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		try {
			Class<C> cls = (Class<C>) typeOfT;
			C map = cls.newInstance();
			
			Set<Entry<String, JsonElement>> items = json.getAsJsonObject().entrySet();
			
			for (Entry<String, JsonElement> entry : items) {
				V value = (V) context.deserialize(entry.getValue(), map.getValueType());
				map.set(entry.getKey(), value);
			}
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


}
