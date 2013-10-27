package com.dubture.getcomposer.core.collection;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.dubture.getcomposer.core.entities.AbstractJsonArray;
import com.dubture.getcomposer.core.repositories.Repository;
import com.dubture.getcomposer.core.repositories.RepositoryFactory;

/**
 * Represents a repositories collection of a composer package
 * 
 * @see http://getcomposer.org/doc/04-schema.md#repositories
 * @author Thomas Gossmann <gos.si>
 */
public class Repositories extends AbstractJsonArray<Repository> {
	
	public Repositories() {
	}
	
	@SuppressWarnings("rawtypes")
	protected void doParse(Object obj) {
		clear();
		if (obj instanceof LinkedList) {
			for (Object repo : (LinkedList) obj) {
				if (repo instanceof LinkedHashMap && ((LinkedHashMap)repo).containsKey("type")) {
					String type = (String)((LinkedHashMap)repo).get("type");
					Repository r = RepositoryFactory.create(type);
					r.fromJson(repo);
					add(r);
				}
			}
		}
	}
}
