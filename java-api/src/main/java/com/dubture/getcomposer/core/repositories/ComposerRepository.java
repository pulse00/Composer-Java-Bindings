package com.dubture.getcomposer.core.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

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
	
	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {
			JSONObject json = (JSONObject) obj;
			
			if (json.containsKey("options")) {
				options.fromJson(json.get("options"));
			}
		}
		
		super.parse(obj);
	}
	
	@Override
	public Object prepareJson(LinkedList<String> fields) {
		String[] order = new String[]{"packages", "options"};
		return super.prepareJson(new LinkedList<String>(Arrays.asList(order)));
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
