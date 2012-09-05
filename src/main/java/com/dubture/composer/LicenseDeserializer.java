package com.dubture.composer;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 
 * @see http://getcomposer.org/doc/04-schema.md#license
 * @author Robert Gruendler <r.gruendler@gmail.com>
 *
 */
public class LicenseDeserializer implements JsonDeserializer<License> {

	public License deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		
		License license = new License();
		
		if (type instanceof GenericArrayType) {
		
			JsonArray jsonArray = element.getAsJsonArray();
			String[] licenses = new String[jsonArray.size()];
			int i = 0;
			
			for (JsonElement child : jsonArray) {
				licenses[i++] = child.getAsString();
			}
			license.setNames(licenses);
		} else {
			license.setNames(new String[]{element.getAsString()});
		}
		
		return license;
	}
}
