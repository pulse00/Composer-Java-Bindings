package org.getcomposer.collection;

import org.getcomposer.entities.Person;
import org.getcomposer.internal.serialization.ListSerializer;


/**
 * Represents a person collection for authors and maintainers
 * 
 * @author Thomas Gossmann <gos.si>
 *
 */
public class Persons extends JsonList<Person> implements Iterable<Person> {

	public Persons() {
		super(Person.class);
	}
	
	public static Object getSerializer() {
		return new ListSerializer<Persons, Person>();
	}
}
