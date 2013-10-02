package com.dubture.getcomposer.core.collection;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import com.dubture.getcomposer.core.entities.AbstractJsonArray;
import com.dubture.getcomposer.json.ParseException;

public class JsonArray extends AbstractJsonArray<Object> {

	public JsonArray() {
	}
	
	public JsonArray(Object json) {
		fromJson(json);
	}
	
	public JsonArray(String json) throws ParseException {
		fromJson(json);
	}
	
	public JsonArray(File file) throws IOException, ParseException {
		fromJson(file);
	}
	
	public JsonArray(Reader reader) throws IOException, ParseException {
		fromJson(reader);
	}
}
