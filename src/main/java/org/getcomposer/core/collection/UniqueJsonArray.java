package org.getcomposer.core.collection;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.getcomposer.core.entities.AbstractUniqueJsonArray;

public class UniqueJsonArray extends AbstractUniqueJsonArray<Object> {

	public UniqueJsonArray() {
	}
	
	public UniqueJsonArray(Object json) {
		fromJson(json);
	}
	
	public UniqueJsonArray(String json) {
		fromJson(json);
	}
	
	public UniqueJsonArray(File file) throws IOException {
		fromJson(file);
	}
	
	public UniqueJsonArray(Reader reader) throws IOException {
		fromJson(reader);
	}
}
