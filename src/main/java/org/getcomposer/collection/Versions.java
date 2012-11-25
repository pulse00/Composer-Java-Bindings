package org.getcomposer.collection;

import java.util.Map;
import java.util.Map.Entry;

import org.getcomposer.ComposerPackage;
import org.getcomposer.serialization.MapSerializer;
import org.json.simple.JSONObject;


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
	
	@SuppressWarnings("unchecked")
	protected void parse(Object obj) {
		clear();
		if (obj instanceof JSONObject) {
			for (Entry<String, Object> entry : ((Map<String, Object>)obj).entrySet()) {
				ComposerPackage pkg = new ComposerPackage(entry.getValue());
				set(entry.getKey(), pkg);
			}
		}
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
