package org.getcomposer.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.getcomposer.ComposerConstants;
import org.getcomposer.ComposerPackage;
import org.getcomposer.DetailedVersion;
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

	private Map<String, DetailedVersion> detailedVersions = null;
	private SortedMap<String, List<String>> majors = new TreeMap<String, List<String>>();

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

	public Set<String> toSet() {
		return properties.keySet();
	}

	public String[] toArray() {
		return properties.keySet().toArray(new String[0]);
	}

	private void compileDetailedVersions() {
		if (detailedVersions == null) {
			detailedVersions = new HashMap<String, DetailedVersion>();
			
			for (String version : toArray()) {
				compileDetailedVersion(version);
			}
		}
	}

	private void compileDetailedVersion(String version) {
		DetailedVersion v = new DetailedVersion(version);
		detailedVersions.put(version, v);
		
		// hierarchy
		if (v.getStability() == ComposerConstants.STABLE) {
			String major = v.getMajor();
			if (major != null) {
				if (!majors.containsKey(major)) {
					majors.put(major, new ArrayList<String>());
				}
				
				List<String> majorList = majors.get(major);
				
				String minor = v.getMinor();
				if (minor != null) {
					majorList.add(minor);
					Collections.sort(majorList);
				}
			}
		}
	}
	
	private void prepareDetailedVersions() {
		if (detailedVersions == null) {
			compileDetailedVersions();
		}
	}
	
	public List<DetailedVersion> getDetailedVersions() {
		prepareDetailedVersions();
		
		return (List<DetailedVersion>)detailedVersions.values();
	}
	
	public String[] getMajors() {
		prepareDetailedVersions();
		
		return majors.keySet().toArray(new String[]{});
	}
	
	public String getRecentMajor() {
		prepareDetailedVersions();
		
		if (majors.size() > 0) {
			return majors.firstKey();
		}
		
		return null;
	}
	
	/**
	 * Returns all minor versions for the given major version or null
	 * if major version does not exist.
	 * 
	 * @param major
	 * @return
	 */
	public String[] getMinors(String major) {
		prepareDetailedVersions();
		
		if (majors.containsKey(major)) {
			return majors.get(major).toArray(new String[]{});
		}
		
		return null;
	}
	
	/**
	 * Returns the recent minor version for the given major version or null
	 * if neither major version or no minor version exists.
	 *  
	 * @param major
	 * @return
	 */
	public String getRecentMinor(String major) {
		prepareDetailedVersions();
		
		if (majors.containsKey(major) && majors.get(major).size() > 0) {
			return majors.get(major).get(0);
		}
		
		return null;
	}
	
	
	public void set(String version, ComposerPackage composerPackage) {
		if (detailedVersions != null) {
			compileDetailedVersion(version);
		}
		
		super.set(version, composerPackage);
	}
	
	public void remove(String version) {
		if (detailedVersions != null) {
			DetailedVersion v = getDetailedVersion(version);
			detailedVersions.remove(version);
			
			// remove hierarchy
			if (v.getStability() == ComposerConstants.STABLE) {
				String major = v.getMajor();
				if (major != null) {
					if (majors.containsKey(major)) {
						List<String> majorList = majors.get(major);
						
						String minor = v.getMinor();
						if (minor != null && majorList.contains(minor)) {
							majorList.remove(minor);
							Collections.sort(majorList);
						}
						
						if (majorList.size() == 0) {
							majors.remove(major);
						}
					}
				}
			}
		}
		
		super.remove(version);
	}
	
	/**
	 * Returns the detailed version for a given string version or null
	 * if the version doesn't exist in this version collection
	 * 
	 * @param version
	 * @return
	 */
	public DetailedVersion getDetailedVersion(String version) {
		prepareDetailedVersions();

		if (detailedVersions.containsKey(version)) {
			return detailedVersions.get(version);
		}
		
		return null;
	}
}
