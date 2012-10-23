package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import org.getcomposer.Versions;

public class ComposerRepository extends AbstractRepository {

	private Map<String, Versions> packages;
	
	public ComposerRepository() {
		super("composer");
	}
	
	public Versions getVersions(String name) {
		if (packages.containsKey(name)) {
			return packages.get(name);
		}
		return null;
	}

	public static ComposerRepository fromFile(File input) throws FileNotFoundException {
		return fromFile(input, ComposerRepository.class);
	}
	
	public static ComposerRepository fromJson(String json) throws FileNotFoundException {
		return fromJson(json, ComposerRepository.class);
	}
}
