package org.getcomposer.core.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.getcomposer.core.collection.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Represents the scripts entry in a composer package
 * 
 * @see http://getcomposer.org/doc/articles/scripts.md
 * @see http://getcomposer.org/doc/04-schema.md#scripts
 * @author Thomas Gossmann <gos.si>
 */
public class Scripts extends JsonObject {

	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {

			JSONObject json = (JSONObject)obj;

			parseScripts(json, "pre-install-cmd");
			parseScripts(json, "post-install-cmd");
			parseScripts(json, "pre-update-cmd");
			parseScripts(json, "post-update-cmd");
			parseScripts(json, "pre-package-install");
			parseScripts(json, "post-package-install");
			parseScripts(json, "pre-package-update");
			parseScripts(json, "post-package-update");
			parseScripts(json, "pre-package-uninstall");
			parseScripts(json, "post-package-uninstall");
		}
	}
	
	public static String[] getEvents() {
		return new String[] {"pre-install-cmd", "post-install-cmd", "pre-update-cmd", "post-update-cmd",
				"pre-package-install", "post-package-install", "pre-package-update",
				"post-package-update", "pre-package-uninstall", "post-package-uninstall"};
	}
	
	@Override
	protected List<String> getOwnProperties() {
		String[] props = new String[]{"pre-install-cmd", "post-install-cmd", "pre-update-cmd", "post-update-cmd",
				"pre-package-install", "post-package-install", "pre-package-update",
				"post-package-update", "pre-package-uninstall", "post-package-uninstall"};
		List<String> list = new ArrayList<String>(Arrays.asList(props));
		list.addAll(super.getOwnProperties());
		return list;
	}
	
	private void parseScripts(JSONObject json, String property) {
		if (json.containsKey(property)) {
			JsonArray values;
			Object value = json.get(property);
			
			if (value instanceof JSONArray) {
				values = new JsonArray(value);
			} else {
				values = new JsonArray();
				values.add(value);
			}
			
			set(property, values);
			json.remove(property);
		}
	}
	
	
	/**
	 * Gets scripts that will occur before the 
	 * <pre>install</pre> command is executed.
	 * 
	 * @return the scripts
	 */
	public JsonArray getPreInstallCmd() {
		return getAsArray("pre-install-cmd");
	}
	
	/**
	 * Gets scripts that will occur after the 
	 * <pre>install</pre> command is executed.
	 * 
	 * @return the scripts
	 */
	public JsonArray getPostInstallCmd() {
		return getAsArray("post-install-cmd");
	}
	
	/**
	 * Gets scripts that will occur before the 
	 * <pre>update</pre> command is executed.
	 * 
	 * @return the scripts
	 */
	public JsonArray getPreUpdateCmd() {
		return getAsArray("pre-update-cmd");
	}
	
	/**
	 * Gets scripts that will occur after the 
	 * <pre>update</pre> command is executed.
	 * 
	 * @return the scripts
	 */
	public JsonArray getPostUpdateCmd() {
		return getAsArray("post-update-cmd");
	}
	
	/**
	 * Gets scripts that will occur before a package is installed.
	 * 
	 * @return the scripts
	 */
	public JsonArray getPrePackageInstall() {
		return getAsArray("pre-package-install");
	}
	
	/**
	 * Gets scripts that will occur after a package is installed.
	 * 
	 * @return the scripts
	 */
	public JsonArray getPostPackageInstall() {
		return getAsArray("post-package-install");
	}
	
	/**
	 * Gets scripts that will occur before a package is updateed.
	 * 
	 * @return the scripts
	 */
	public JsonArray getPrePackageUpdate() {
		return getAsArray("pre-package-update");
	}
	
	/**
	 * Gets scripts that will occur after a package is updateed.
	 * 
	 * @return the scripts
	 */
	public JsonArray getPostPackageUpdate() {
		return getAsArray("post-package-update");
	}
	
	/**
	 * Gets scripts that will occur before a package is uninstalled.
	 * 
	 * @return the scripts
	 */
	public JsonArray getPrePackageUninstall() {
		return getAsArray("pre-package-uninstall");
	}
	
	/**
	 * Gets scripts that will occur after a package is uninstalled.
	 * 
	 * @return the scripts
	 */
	public JsonArray getPostPackageUninstall() {
		return getAsArray("post-package-uninstall");
	}
}
