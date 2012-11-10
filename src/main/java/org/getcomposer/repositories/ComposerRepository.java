package org.getcomposer.repositories;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Map;

import org.getcomposer.collection.Versions;
import org.getcomposer.entities.GenericEntity;
import org.getcomposer.serialization.ExtendedClientEntitySerializer;

/**
 * Represents a composer repository
 * 
 * @author Thomas Gossmann <gos.si>
 *
 */
public class ComposerRepository extends Repository {

	private Map<String, Versions> packages;
	private GenericEntity options = new GenericEntity();
	
	public ComposerRepository() {
		super("composer");
		listen();
	}
	
	public Versions getVersions(String name) {
		if (packages.containsKey(name)) {
			return packages.get(name);
		}
		return null;
	}
	
	/**
	 * Returns the options entity
	 * 
	 * @return the options
	 */
	public GenericEntity getOptions() {
		return options;
	}

	public static ComposerRepository fromFile(File input) throws FileNotFoundException {
		return fromFile(input, ComposerRepository.class);
	}
	
	public static ComposerRepository fromJson(String json) throws FileNotFoundException {
		return fromJson(json, ComposerRepository.class);
	}

	public static Object getSerializer() {
		return new ExtendedClientEntitySerializer<ComposerRepository>();
	}
}
