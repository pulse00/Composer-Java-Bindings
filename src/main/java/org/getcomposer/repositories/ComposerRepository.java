package org.getcomposer.repositories;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

import org.getcomposer.collection.Versions;
import org.getcomposer.entities.GenericEntity;
import org.json.simple.JSONObject;

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
	public GenericEntity getOptions() {
		return options;
	}
}
