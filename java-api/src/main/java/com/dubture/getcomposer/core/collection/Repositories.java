package com.dubture.getcomposer.core.collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	
	protected void parse(Object obj) {
		clear();
		if (obj instanceof JSONArray) {
			for (Object repo : (JSONArray) obj) {
				if (repo instanceof JSONObject && ((JSONObject)repo).containsKey("type")) {
					String type = (String)((JSONObject)repo).get("type");
					Repository r = RepositoryFactory.create(type);
					r.fromJson(repo);
					add(r);
				}
			}
		}
	}
}
