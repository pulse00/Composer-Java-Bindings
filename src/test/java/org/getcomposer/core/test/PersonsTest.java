package org.getcomposer.core.test;

import org.getcomposer.core.collection.Persons;
import org.getcomposer.core.objects.Person;

import junit.framework.TestCase;

public class PersonsTest extends TestCase {

	public void testIndexO() {
		Persons persons = new Persons();
		
		Person p = new Person();
		persons.add(p);
		
		assertEquals(1, persons.size());
		assertEquals(p, persons.get(0));
		assertTrue(persons.has(p));
		assertEquals(0, persons.indexOf(p));
	}
}
