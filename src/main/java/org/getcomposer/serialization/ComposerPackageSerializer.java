package org.getcomposer.serialization;

import java.lang.reflect.Type;

import org.getcomposer.ComposerPackage;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class ComposerPackageSerializer extends ClientEntitySerializer<ComposerPackage> {

	public JsonElement serialize(ComposerPackage src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		
		writeProperties(json, src, context);
		
		
		add(json, "authors", context.serialize(src.getAuthors()) );
		add(json, "license", context.serialize(src.getLicense()));
		add(json, "autoload", context.serialize(src.getAutoload()));
		add(json, "require", context.serialize(src.getRequire()));
		add(json, "require-dev", context.serialize(src.getRequireDev()));
		
		add(json, "repositories", context.serialize(src.getRepositories()));
		
		add(json, "support", context.serialize(src.getSupport()));
		add(json, "config", context.serialize(src.getConfig()));
		add(json, "extra", context.serialize(src.getExtra()));

		return json;
	}
	
	private void add(JsonObject json, String key, JsonElement val) {
		if (val == null 
				|| val.isJsonNull() 
				|| (val.isJsonArray() && ((JsonArray)val).size() == 0)
				|| (val.isJsonObject() && ((JsonObject)val).entrySet().size() == 0)) {
			return;
		}
		
		json.add(key, val);
	}
}
