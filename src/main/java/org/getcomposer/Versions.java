package org.getcomposer;

import java.util.HashMap;
import java.util.Map;

public class Versions extends ObservableModel {
	
	private Map<String, PackageInterface> versions;
	
	public Versions() {
		versions = new HashMap<String, PackageInterface>();
	}
	
	public PackageInterface getVersion(String version) {
		if (versions.containsKey(version)) {
			return versions.get(version);
		}
		return null;
	}
	
	public String getDefaultVersion() {
		return versions.keySet().iterator().next();
	}
	
	public boolean has(String version) {
		return versions.containsKey(version);
	}
	
	public int size() {
		return versions.size();
	}
	
	public void add(String version, PackageInterface phpPackage) {
		versions.put(version, phpPackage);
	}
}
