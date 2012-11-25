package org.getcomposer.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.getcomposer.entities.Dependency;
import org.getcomposer.serialization.DependenciesSerializer;
import org.json.simple.JSONObject;

/**
 * Represents a dependencies collection of a composer package, either require or
 * require-dev
 * 
 * @see http://getcomposer.org/doc/04-schema.md#require
 * @see http://getcomposer.org/doc/04-schema.md#require-dev
 * @author Thomas Gossmann <gos.si>
 */
public class Dependencies extends JsonMap<Dependencies, Dependency> implements Iterable<Dependency> {
	
	public Dependencies() {
		super(Dependency.class);
	}
	
	@SuppressWarnings("unchecked")
	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {
			for (Entry<String, Object> entry : ((Map<String, Object>)((JSONObject)obj)).entrySet()) {
				Dependency dep = new Dependency();
				dep.setName(entry.getKey());
				dep.setVersion((String)entry.getValue());
				add(dep);
			}
		}
	}
	
	/**
	 * Adds a new dependency.
	 * 
	 * @param dependency the new dependency
	 * @return this
	 */
	public void add(Dependency dependency) {
		super.set(dependency.getName(), dependency);
	}

	/**
	 * Removes a dependency.
	 * 
	 * @param dependency the dependency to remove
	 */
	public void remove(Dependency dependency) {
		super.remove(dependency.getName());
	}


	public Iterator<Dependency> iterator() {
		return (Iterator<Dependency>)properties.values().iterator();
	}

	public static Object getSerializer() {
		return new DependenciesSerializer();
	}
}
