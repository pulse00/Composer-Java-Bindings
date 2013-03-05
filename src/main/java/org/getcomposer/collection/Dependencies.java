package org.getcomposer.collection;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.getcomposer.VersionedPackage;
import org.json.simple.JSONObject;

/**
 * Represents a dependencies collection of a composer package, either require or
 * require-dev
 * 
 * @see http://getcomposer.org/doc/04-schema.md#require
 * @see http://getcomposer.org/doc/04-schema.md#require-dev
 * @author Thomas Gossmann <gos.si>
 */
public class Dependencies extends JsonMap<Dependencies, VersionedPackage> implements Iterable<VersionedPackage> {
	
	public Dependencies() {
		super(VersionedPackage.class);
	}
	
	@SuppressWarnings("unchecked")
	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {
			for (Entry<String, Object> entry : ((Map<String, Object>)((JSONObject)obj)).entrySet()) {
				VersionedPackage dep = new VersionedPackage();
				dep.setName(entry.getKey());
				dep.setVersion((String)entry.getValue());
				add(dep);
			}
		}
	}
	
	public Object prepareJson(LinkedList<String> fields) {
		LinkedHashMap<String, Object> out = new LinkedHashMap<String, Object>();
		for (VersionedPackage dep : this) {
			out.put(dep.getName(), dep.getVersion());
		}
		return out;
	}
	
	/**
	 * Adds a new dependency.
	 * 
	 * @param dependency the new dependency
	 * @return this
	 */
	public void add(VersionedPackage dependency) {
		set(dependency.getName(), dependency);
	}
	
	public void addAll(Dependencies dependencies) {
		for (VersionedPackage pkg : dependencies) {
			add(pkg);
		}
	}

	/**
	 * Removes a dependency.
	 * 
	 * @param dependency the dependency to remove
	 */
	public void remove(VersionedPackage dependency) {
		super.remove(dependency.getName());
	}
	
	public VersionedPackage[] toArray() {
		return properties.values().toArray(new VersionedPackage[]{});
	}


	public Iterator<VersionedPackage> iterator() {
		return (Iterator<VersionedPackage>)properties.values().iterator();
	}
}
