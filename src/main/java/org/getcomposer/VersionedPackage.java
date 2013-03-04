package org.getcomposer;

import java.util.Arrays;
import java.util.LinkedList;

import org.getcomposer.entities.Version;

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

	@Override
	public Object prepareJson(LinkedList<String> fields) {
		String[] before = new String[]{"version"};
		fields.addAll(0, new LinkedList<String>(Arrays.asList(before)));
		return super.prepareJson(fields);
	}
	
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
		return (VersionedPackage)super.clone();
	}
}
