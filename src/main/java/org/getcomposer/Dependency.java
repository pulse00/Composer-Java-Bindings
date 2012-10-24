package org.getcomposer;

/**
 * Represents a dependency entry in require or require-dev
 * 
 * @see http://getcomposer.org/doc/04-schema.md#require
 * @see http://getcomposer.org/doc/04-schema.md#require-dev
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Dependency extends ObservableModel {

	private String name;
	private String version;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 * @return this
	 */
	public Dependency setName(String name) {
		firePropertyChange("name", this.name, this.name = name);
		return this;
	}
	
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * @param version the version to set
	 * @return this
	 */
	public Dependency setVersion(String version) {
		firePropertyChange("version", this.version, this.version = version);
		return this;
	}
	
	
}
