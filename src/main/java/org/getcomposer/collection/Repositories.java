package org.getcomposer.collection;

import org.getcomposer.repositories.Repository;
import org.getcomposer.repositories.RepositoryFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
