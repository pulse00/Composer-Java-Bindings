package org.getcomposer.core.collection;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.getcomposer.core.ComposerPackage;
import org.getcomposer.core.entities.AbstractJsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class InstalledPackages extends AbstractJsonArray<ComposerPackage> {
	
	public InstalledPackages() {
	}
	
	public InstalledPackages(Object json) {
		fromJson(json);
	}
	
	public InstalledPackages(String json) {
		fromJson(json);
	}
	
	public InstalledPackages(File file) throws IOException {
		fromJson(file);
	}
	
	public InstalledPackages(Reader reader) throws IOException {
		fromJson(reader);
	}
	
	protected void parse(Object obj) {
		clear();
		if (obj instanceof JSONObject) {
			add(new ComposerPackage(obj));
		} else if (obj instanceof JSONArray) {
			JSONArray array = (JSONArray) obj;
			for (Object entry : array) {
				if (entry instanceof JSONObject) {
					add(new ComposerPackage(entry));
				}
			}
		}
	}
}
