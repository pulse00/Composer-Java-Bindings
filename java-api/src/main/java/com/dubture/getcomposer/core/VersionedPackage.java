package com.dubture.getcomposer.core;

import com.dubture.getcomposer.core.entities.Version;

/**
 * Represents a dependency entry in require or require-dev
 * 
 * @see http://getcomposer.org/doc/04-schema.md#require
 * @see http://getcomposer.org/doc/04-schema.md#require-dev
 * @author Thomas Gossmann <gos.si>
 *
 */
public class VersionedPackage extends MinimalPackage {

	protected transient Version detailedVersion = null;
	
	public Version getDetailedVersion() {
		if (detailedVersion == null) {
			detailedVersion = new Version(getVersion());
		}
		return detailedVersion;
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
	public VersionedPackage clone() {
		VersionedPackage clone = new VersionedPackage();
		cloneProperties(clone);
		return clone;
	}
}
