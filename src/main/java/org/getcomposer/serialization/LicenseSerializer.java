/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.serialization;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

import org.getcomposer.License;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 * @see http://getcomposer.org/doc/04-schema.md#license
 * @author Robert Gruendler <r.gruendler@gmail.com>
 * 
 */
public class LicenseSerializer implements JsonDeserializer<License>, JsonSerializer<License> {

	public License deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {

		License license = new License();

		if (element instanceof JsonArray) {
			for (JsonElement item : (JsonArray)element) {
				license.add(item.getAsString());
			}
			
//			JsonArray array = (JsonArray) element;
//			String[] licences  = new String[array.size()];
//			int i=0;
//			for (Object o : array) {
//				if (o instanceof String) {
//					licences[i++] = (String) o;
//				} else if (o instanceof JsonPrimitive) {
//					JsonPrimitive prim = (JsonPrimitive) o;
//					licences[i++] = prim.getAsString();
//				}
//			}
//			license.names = licences;
		} else if (type instanceof GenericArrayType) { 
			JsonArray jsonArray = element.getAsJsonArray();

			for (JsonElement item : jsonArray) {
				license.add(item.getAsString());
			}
		} else {
			license.add(element.getAsString());
		}

		return license;
	}

	public JsonElement serialize(License src, Type typeOfSrc,
			JsonSerializationContext context) {
		
		System.out.println("Serialize License: " + src);
		if (src.size() == 1) {
			return new JsonPrimitive(src.get(0));
		} else {
			JsonArray licenses = new JsonArray();
			for (String license : src) {
				licenses.add(new JsonPrimitive(license));
			}
			return licenses;
		}
	}
	
	
}
