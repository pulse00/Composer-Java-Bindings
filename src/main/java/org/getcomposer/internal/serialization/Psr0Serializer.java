package org.getcomposer.internal.serialization;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.Map.Entry;

import org.getcomposer.collection.Psr0;
import org.getcomposer.entities.Namespace;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class Psr0Serializer implements JsonDeserializer<Psr0>, JsonSerializer<Psr0> {

	public JsonElement serialize(Psr0 src, Type typeOfSrc,
			JsonSerializationContext context) {
		
		JsonObject json = new JsonObject();
//		for (Dependency dep : src) {
//			json.addProperty(dep.getName(), dep.getVersion());
//		}
		return json;
	}

	public Psr0 deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		Psr0 psr0 = new Psr0();
		
		Set<Entry<String, JsonElement>> items = json.getAsJsonObject().entrySet();
		
		for (Entry<String, JsonElement> entry : items) {
			Namespace namespace = new Namespace();
			namespace.setNamespace(entry.getKey());
			
			if (entry.getValue().isJsonArray()) {
				for(JsonElement path : entry.getValue().getAsJsonArray()) {
					namespace.add(path.getAsString());
				}
			} else {
				namespace.add(entry.getValue().getAsString());
			}
			psr0.add(namespace);
		}
		
		return psr0;
	}

}
