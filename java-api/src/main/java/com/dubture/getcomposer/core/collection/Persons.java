package com.dubture.getcomposer.core.collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dubture.getcomposer.core.entities.AbstractJsonArray;
import com.dubture.getcomposer.core.objects.Person;


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
		clear();
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
	
	@Override
	public boolean has(Person value) {
		if (super.has(value)) {
			return true;
		}
		
		for (Person p : this) {
			if (p.equals(value)) {
				return true;
			}
		}
		return false;
	}
	
}
