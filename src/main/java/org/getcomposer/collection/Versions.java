package org.getcomposer.collection;

import org.getcomposer.ComposerPackage;
import org.getcomposer.serialization.MapSerializer;


/**
 * Represents a version property in a composer package or version collection
 * in a composer repository or packagist package.
 * 
 * @see http://getcomposer.org/doc/04-schema.md#version
 * @see http://getcomposer.org/doc/05-repositories.md#packages
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Versions extends IterableJsonMap<Versions, ComposerPackage> {

	public Versions() {
		super(ComposerPackage.class);
	}
	
	/**
	 * Returns the most recent version
	 * @return
	 */
	public String getDefaultVersion() {
		return (String)properties.entrySet().iterator().next().getKey();
	}

	public static Object getSerializer() {
		return new MapSerializer<Versions, ComposerPackage>();
	}
}
