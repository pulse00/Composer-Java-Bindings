package org.getcomposer.internal.serialization;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.getcomposer.entities.GenericEntity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;

public class ClientEntitySerializer<T extends GenericEntity> implements JsonSerializer<T>, JsonDeserializer<T> {

	public JsonElement serialize(T src, Type typeOfSrc,
			JsonSerializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		try {
			Class<T> cls = (Class<T>) typeOfT;
			T entity = cls.newInstance();
			
			JsonObject obj = json.getAsJsonObject();
			
			// collecting fields
			ArrayList<Field> fields = new ArrayList<Field>();
			Class superClass = entity.getClass();
			
			while (superClass != null) {
				for (Field field : superClass.getDeclaredFields()) {
					if (!((field.getModifiers() & Modifier.TRANSIENT) == Modifier.TRANSIENT)) {
						fields.add(field);
					}
				}
				superClass = superClass.getSuperclass();		
			}
			
			// setting values
			for (Field field : fields) {
				String name = field.getName();
				for (Annotation anno : field.getAnnotations()) {
					if (anno.annotationType() == SerializedName.class) {
						name = ((SerializedName) anno).value();
					}
				}
				
				if (obj.has(name)) {
					field.setAccessible(true);

					Object instance;
					if (new GenericEntity().getClass().isAssignableFrom(field.getType())) {
						ClientEntitySerializer<?> clientSerializer = ClientEntitySerializer.class.newInstance();
						instance = clientSerializer.deserialize(obj.get(name), field.getType(), context);
					} else {
						instance = context.deserialize(obj.get(name), field.getType());
					}
					field.set(entity, instance);
				}
			}
			
			GenericEntitySerializer genericSerializer = new GenericEntitySerializer();
			genericSerializer.setProperties(entity, json, context);
			
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
}
