package com.dubture.getcomposer.core.collection;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dubture.getcomposer.core.ComposerPackage;
import com.dubture.getcomposer.core.entities.AbstractJsonArray;

/**
 * A collection to manage multiple composer packages.
 * 
 * Can be parsed from e.g. composer/installed.json or composer/installed_dev.json file
 * 
 * @author Thomas Gossmann <gos.si>
 */
public class ComposerPackages extends AbstractJsonArray<ComposerPackage> {
	
	public ComposerPackages() {
	}
	
	public ComposerPackages(Object json) {
		fromJson(json);
	}
	
	public ComposerPackages(String json) {
		fromJson(json);
	}
	
	public ComposerPackages(File file) throws IOException {
		fromJson(file);
	}
	
	public ComposerPackages(Reader reader) throws IOException {
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
	
	public void addAll(ComposerPackages packages) {
		for (ComposerPackage pkg : packages) {
			add(pkg);
		}
	}
	
	public void removeAll(ComposerPackages packages) {
		for (ComposerPackage pkg : packages) {
			remove(pkg);
		}
	}
}
