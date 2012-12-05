package org.getcomposer.entities;

import org.getcomposer.collection.GenericArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Represents the scripts entry in a composer package
 * 
 * @see http://getcomposer.org/doc/articles/scripts.md
 * @see http://getcomposer.org/doc/04-schema.md#scripts
 * @author Thomas Gossmann <gos.si>
 */
public class Scripts extends GenericEntity {

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
	
	private void parseScripts(JSONObject json, String property) {
		if (json.containsKey(property)) {
			GenericArray values;
			Object value = json.get(property);
			
			if (value instanceof JSONArray) {
				values = new GenericArray(value);
			} else {
				values = new GenericArray();
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
	public GenericArray getPreInstallCmd() {
		return getAsArray("pre-install-cmd");
	}
	
	/**
	 * Gets scripts that will occur after the 
	 * <pre>install</pre> command is executed.
	 * 
	 * @return the scripts
	 */
	public GenericArray getPostInstallCmd() {
		return getAsArray("post-install-cmd");
	}
	
	/**
	 * Gets scripts that will occur before the 
	 * <pre>update</pre> command is executed.
	 * 
	 * @return the scripts
	 */
	public GenericArray getPreUpdateCmd() {
		return getAsArray("pre-update-cmd");
	}
	
	/**
	 * Gets scripts that will occur after the 
	 * <pre>update</pre> command is executed.
	 * 
	 * @return the scripts
	 */
	public GenericArray getPostUpdateCmd() {
		return getAsArray("post-update-cmd");
	}
	
	/**
	 * Gets scripts that will occur before a package is installed.
	 * 
	 * @return the scripts
	 */
	public GenericArray getPrePackageInstall() {
		return getAsArray("pre-package-install");
	}
	
	/**
	 * Gets scripts that will occur after a package is installed.
	 * 
	 * @return the scripts
	 */
	public GenericArray getPostPackageInstall() {
		return getAsArray("post-package-install");
	}
	
	/**
	 * Gets scripts that will occur before a package is updateed.
	 * 
	 * @return the scripts
	 */
	public GenericArray getPrePackageUpdate() {
		return getAsArray("pre-package-update");
	}
	
	/**
	 * Gets scripts that will occur after a package is updateed.
	 * 
	 * @return the scripts
	 */
	public GenericArray getPostPackageUpdate() {
		return getAsArray("post-package-update");
	}
	
	/**
	 * Gets scripts that will occur before a package is uninstalled.
	 * 
	 * @return the scripts
	 */
	public GenericArray getPrePackageUninstall() {
		return getAsArray("pre-package-uninstall");
	}
	
	/**
	 * Gets scripts that will occur after a package is uninstalled.
	 * 
	 * @return the scripts
	 */
	public GenericArray getPostPackageUninstall() {
		return getAsArray("post-package-uninstall");
	}
}
