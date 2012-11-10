package org.getcomposer.serialization;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import org.getcomposer.entities.GenericEntity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class ExtendedClientEntitySerializer<T extends GenericEntity> extends ClientEntitySerializer<T> {

	public JsonElement serialize(T src, Type typeOfSrc,
			JsonSerializationContext context) {

		GenericEntitySerializer genericSerializer = new GenericEntitySerializer();
		JsonObject json = new JsonObject();
		
		genericSerializer.writeProperties(json, src, context);

		// fields
		try {
			List<Field> fields = getFields(src);
			
			for (Field field : fields) {
				String name = getFieldName(field);
				field.setAccessible(true);

				JsonElement val = context.serialize(field.get(src));
				if ((val.isJsonArray() && val.getAsJsonArray().size() > 0)
						|| (val.isJsonObject() && val.getAsJsonObject().entrySet().size() > 0)) {
					json.add(name, val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}

}
