package org.getcomposer.core.collection;

import org.getcomposer.core.entities.AbstractJsonArray;
import org.getcomposer.core.objects.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * Represents a person collection for authors and maintainers
 * 
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Persons extends AbstractJsonArray<Person> implements Iterable<Person> {

	public Persons() {
	}
	
	protected void parse(Object obj) {
		if (obj instanceof JSONArray) {
			for (Object pObj : (JSONArray)obj) {
				if (pObj instanceof JSONObject) {
					JSONObject p = (JSONObject)pObj;
					Person person = new Person(p);
					add(person);
				}
			}
		}
	}
	
}
