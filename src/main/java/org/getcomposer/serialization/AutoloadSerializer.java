package org.getcomposer.serialization;

import java.lang.reflect.Type;

import org.getcomposer.entities.Autoload;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class AutoloadSerializer extends ClientEntitySerializer<Autoload> {

	public JsonElement serialize(Autoload src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		
		json.add("psr-0", context.serialize(src.getPsr0()));
		json.add("classmap", context.serialize(src.getClassMap()));
		json.add("files", context.serialize(src.getFiles()));

		return json;
	}
}
