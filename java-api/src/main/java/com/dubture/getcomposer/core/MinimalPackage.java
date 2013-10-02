package com.dubture.getcomposer.core;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import com.dubture.getcomposer.core.objects.JsonObject;
import com.dubture.getcomposer.json.ParseException;

/**
 * A package that can be read from a file or string and also dump to the latter one.
 * 
 * @author Thomas Gossmann <gos.si>
 * 
 */
public class MinimalPackage extends JsonObject {
	
	public MinimalPackage() {
		super();
		listen();
	}
	
	public MinimalPackage(Object json) {
		this();
		fromJson(json);
	}
	
	public MinimalPackage(String json) throws ParseException {
		this();
		fromJson(json);
	}
	
	public MinimalPackage(File file) throws IOException, ParseException {
		this();
		fromJson(file);
	}
	
	public MinimalPackage(Reader reader) throws IOException, ParseException {
		this();
		fromJson(reader);
	}
	
	/**
	 * Returns the <code>name</code> property.
	 * 
	 * @return the <code>name</code> value
	 */
	public String getName() {
		return getAsString("name");
	}
	
	/**
	 * Sets the <code>name</code> property.
	 * 
	 * @param name the new <code>name</code> value
	 */
	public void setName(String name) {
		set("name", name);
	}
	
	/**
	 * Returns the <code>description</code> property.
	 * @return
	 */
	public String getDescription() {
		return getAsString("description");
	}
	
	/**
	 * Sets the <code>description</code> property.
	 * 
	 * @param description the new <code>description</code> value
	 */
	public void setDescription(String description) {
		set("description", description);
	}
	
	
}
