package org.getcomposer.core.repositories;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

import org.getcomposer.core.collection.Versions;
import org.getcomposer.core.objects.JsonObject;
import org.json.simple.JSONObject;

/**
 * Represents a composer repository
 * 
 * @author Thomas Gossmann <gos.si>
 *
 */
public class ComposerRepository extends Repository {

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
}
