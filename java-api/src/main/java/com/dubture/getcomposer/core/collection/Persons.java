package com.dubture.getcomposer.core.collection;

import java.util.LinkedHashMap;
import java.util.LinkedList;

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
	
	@SuppressWarnings("rawtypes")
	protected void doParse(Object obj) {
		clear();
		if (obj instanceof LinkedList) {
			for (Object pObj : (LinkedList)obj) {
				if (pObj instanceof LinkedHashMap) {
					LinkedHashMap p = (LinkedHashMap)pObj;
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
