package org.getcomposer.collection;

import org.getcomposer.repositories.Repository;
import org.getcomposer.serialization.RepositoriesSerializer;

/**
 * Represents a repositories collection of a composer package
 * 
 * @see http://getcomposer.org/doc/04-schema.md#repositories
 * @author Thomas Gossmann <gos.si>
 */
public class Repositories extends JsonList<Repository> {
	
	public Repositories() {
		super(Repository.class);
	}
	

	public static Object getSerializer() {
		return new RepositoriesSerializer();
	}
}
