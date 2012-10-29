package org.getcomposer.internal.serialization;

import java.lang.reflect.Type;

import org.getcomposer.collection.Repositories;
import org.getcomposer.repositories.Repository;
import org.getcomposer.repositories.RepositoryFactory;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class RepositoriesSerializer implements JsonDeserializer<Repositories>, JsonSerializer<Repositories> {

	public JsonElement serialize(Repositories src, Type typeOfSrc,
			JsonSerializationContext context) {
		
//		JsonObject json = new JsonObject();
//		for (Dependency dep : src) {
//			json.addProperty(dep.getName(), dep.getVersion());
//		}
//		return json;
		return null;
	}

	public Repositories deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		Repositories repos = new Repositories();

		for (JsonElement entry : json.getAsJsonArray()) {
			JsonObject item = entry.getAsJsonObject();
			Type type = RepositoryFactory.getType(item.get("type").getAsString());
			repos.add((Repository)context.deserialize(item, type));
		}
		
		return repos;
	}

}
