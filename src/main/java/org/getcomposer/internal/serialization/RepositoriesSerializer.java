package org.getcomposer.internal.serialization;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import org.getcomposer.collection.Repositories;
import org.getcomposer.entities.GenericEntity;
import org.getcomposer.repositories.Repository;
import org.getcomposer.repositories.RepositoryFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class RepositoriesSerializer implements JsonDeserializer<Repositories>, JsonSerializer<Repositories> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JsonElement serialize(Repositories src, Type typeOfSrc,
			JsonSerializationContext context) {
		
		ClientEntitySerializer clientSerializer = new ClientEntitySerializer<GenericEntity>();
		GenericEntitySerializer genericSerializer = new GenericEntitySerializer();
		
		JsonArray repos = new JsonArray();
		for (Repository repo : src) {
			JsonObject json = new JsonObject();

			List<Field>fields = clientSerializer.getFields(repo);

			for (Field field : fields) {
				String name = clientSerializer.getFieldName(field);
				field.setAccessible(true);

				try {
					json.add(name, context.serialize(field.get(repo)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			genericSerializer.writeProperties(json, repo, context);
			
			repos.add(json);
		}
		
		return repos;
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
