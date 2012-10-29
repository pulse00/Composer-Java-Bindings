package org.getcomposer.entities;

/**
 * Represents a dependency entry in require or require-dev
 * 
 * @see http://getcomposer.org/doc/04-schema.md#require
 * @see http://getcomposer.org/doc/04-schema.md#require-dev
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Dependency extends GenericEntity {

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
	 * @return this
	 */
	public Dependency setName(String name) {
		set("name", name);
		return this;
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
	 * @return this
	 */
	public Dependency setVersion(String version) {
		set("version", version);
		return this;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Dependency clone() {
		return (Dependency)super.clone();
	}
}
