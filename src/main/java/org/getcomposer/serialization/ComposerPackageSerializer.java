package org.getcomposer.serialization;

import java.lang.reflect.Type;

import org.getcomposer.ComposerPackage;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class ComposerPackageSerializer extends ClientEntitySerializer<ComposerPackage> {

	public JsonElement serialize(ComposerPackage src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		
		writeProperties(json, src, context);
		
		json.add("authors", context.serialize(src.getAuthors()));
		json.add("license", context.serialize(src.getLicense()));
		json.add("autoload", context.serialize(src.getAutoload()));
		json.add("require", context.serialize(src.getRequire()));
		json.add("require-dev", context.serialize(src.getRequireDev()));
		
		json.add("repositories", context.serialize(src.getRepositories()));
		
		json.add("support", context.serialize(src.getSupport()));
		json.add("config", context.serialize(src.getConfig()));
		json.add("extra", context.serialize(src.getExtra()));

		return json;
	}
}
