package com.dubture.getcomposer.core.collection;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import com.dubture.getcomposer.core.entities.AbstractUniqueJsonArray;
import com.dubture.getcomposer.json.ParseException;

public class UniqueJsonArray extends AbstractUniqueJsonArray<Object> {

	public UniqueJsonArray() {
	}
	
	public UniqueJsonArray(Object json) {
		fromJson(json);
	}
	
	public UniqueJsonArray(String json) throws ParseException {
		fromJson(json);
	}
	
	public UniqueJsonArray(File file) throws IOException, ParseException {
		fromJson(file);
	}
	
	public UniqueJsonArray(Reader reader) throws IOException, ParseException {
		fromJson(reader);
	}
}
