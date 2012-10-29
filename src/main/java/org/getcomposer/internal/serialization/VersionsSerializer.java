package org.getcomposer.internal.serialization;

import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.Set;

import org.getcomposer.ComposerPackage;
import org.getcomposer.collection.Versions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class VersionsSerializer implements JsonDeserializer<Versions> {

	public Versions deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		Versions versions = new Versions();
		
		Set<Entry<String, JsonElement>> jsonVersions = json.getAsJsonObject().entrySet();
		
		for (Entry<String, JsonElement> entry : jsonVersions) {
			versions.set(entry.getKey(), (ComposerPackage) context.deserialize(entry.getValue(), ComposerPackage.class));
		}
		
		return versions;
	}

}
