package org.getcomposer.serialization;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URI;

import org.getcomposer.Support;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SupportSerializer implements JsonSerializer<Support> {

	public JsonElement serialize(Support src, Type typeOfSrc,
			JsonSerializationContext context) {
		
		boolean empty = true;
		JsonObject json = new JsonObject();

		for (Field prop : src.getClass().getFields()) {
			empty = empty && addProperty(json, src, prop.getName());
		}
		
		if (empty) {
			return null;
		} else {
			return json;
		}
	}

	private boolean addProperty(JsonObject json, Support src, String prop) {
		
		try {
			URI value = (URI)src.getClass().getField(prop).get(src);
			if (!value.toString().isEmpty()) {
				json.addProperty(prop, value.toString());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
