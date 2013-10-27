package com.dubture.getcomposer.core.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dubture.getcomposer.core.collection.Versions;
import com.dubture.getcomposer.core.objects.JsonObject;

/**
 * Represents a composer repository
 * 
 * @author Thomas Gossmann <gos.si>
 *
 */
public class ComposerRepository extends Repository implements Cloneable {

	private Map<String, Versions> packages;
	private JsonObject options = new JsonObject();
	
	public ComposerRepository() {
		super("composer");
		listen();
	}
	
	@Override
	protected List<String> getOwnProperties() {
		String[] props = new String[]{"options"};
		List<String> list = new ArrayList<String>(Arrays.asList(props));
		list.addAll(super.getOwnProperties());
		return list;
	}

	public Versions getVersions(String packageName) {
		if (packages.containsKey(packageName)) {
			return packages.get(packageName);
		}
		return null;
	}
	
	/**
	 * Returns the options entity
	 * 
	 * @return the options
	 */
	public JsonObject getOptions() {
		return options;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public ComposerRepository clone() {
		ComposerRepository clone = new ComposerRepository();
		cloneProperties(clone);
		return clone;
	}
}
