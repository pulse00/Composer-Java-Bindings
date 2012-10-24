package org.getcomposer;

import org.getcomposer.collection.JsonList;
import org.getcomposer.serialization.ListSerializer;


/**
 * Represents a person collection for authors and maintainers
 * 
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Persons extends JsonList<Person> {

	public Persons() {
		super(Person.class);
	}
	
	public static Object getSerializer() {
		return new ListSerializer<Persons, Person>();
	}
}
