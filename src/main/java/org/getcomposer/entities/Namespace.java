package org.getcomposer.entities;

import org.getcomposer.collection.GenericArray;

/**
 * Represents a namespace entry in the psr0 entity of a composer package.
 * 
 * @see http://getcomposer.org/doc/04-schema.md#psr-0
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Namespace extends GenericEntity {

	private transient GenericArray paths = new GenericArray();
	
	public Namespace() {
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return the name
	 */
	public String getNamespace() {
		return getAsString("namespace");
	}
	
	/**
	 * Sets the name.
	 * 
	 * @param name the name to set
	 * @return this
	 */
	public Namespace setNamespace(String namespace) {
		set("namespace", namespace);
		return this;
	}
	
	public Namespace add(String path) {
		paths.add(path);
		return this;
	}
	
	/**
	 * Returns the path and if there are more than one, returns the first one.
	 * 
	 * @return the version
	 */
	public String get() {
		return (String) paths.get(0);
	}
	
	public GenericArray getAll() {
		return paths;
	}
	
	public int size() {
		return paths.size();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Namespace clone() {
		return (Namespace)super.clone();
	}
}
