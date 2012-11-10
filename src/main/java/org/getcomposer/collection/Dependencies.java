package org.getcomposer.collection;

import java.util.Iterator;

import org.getcomposer.entities.Dependency;
import org.getcomposer.serialization.DependenciesSerializer;

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
	
	/**
	 * Adds a new dependency.
	 * 
	 * @param dependency the new dependency
	 * @return this
	 */
	public Dependencies add(Dependency dependency) {
		return super.set(dependency.getName(), dependency);
	}

	/**
	 * Removes a dependency.
	 * 
	 * @param dependency the dependency to remove
	 */
	public Dependencies remove(Dependency dependency) {
		return super.remove(dependency.getName());
	}


	public Iterator<Dependency> iterator() {
		return (Iterator<Dependency>)properties.values().iterator();
	}

	public static Object getSerializer() {
		return new DependenciesSerializer();
	}
}
