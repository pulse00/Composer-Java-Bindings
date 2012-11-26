package org.getcomposer.entities;

import java.util.LinkedList;

import org.json.simple.JSONValue;

/**
 * Represents a dependency entry in require or require-dev
 * 
 * @see http://getcomposer.org/doc/04-schema.md#require
 * @see http://getcomposer.org/doc/04-schema.md#require-dev
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Dependency extends GenericEntity {

	@Override
	public String prepareJson(LinkedList<String> fields) {
		return JSONValue.toJSONString(getAsString("version"));
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return getAsString("name");
	}
	
	/**
	 * Sets the name.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		set("name", name);
	}
	
	/**
	 * Returns the version.
	 * 
	 * @return the version
	 */
	public String getVersion() {
		return getAsString("version");
	}
	
	/**
	 * Sets the version.
	 * 
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		set("version", version);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Dependency clone() {
		return (Dependency)super.clone();
	}
}
