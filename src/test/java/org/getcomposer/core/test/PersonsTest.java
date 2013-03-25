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
	
	public void testEquals() {
		Person p;
		Person p2;
		
		// positives
		p = new Person();
		p2 = p;
		assertTrue(p.equals(p2));
		
		p = new Person();
		p.setName("hans");
		p2 = p.clone();
		assertTrue(p.equals(p2));
		
		p = new Person();
		p.setEmail("hans@wurst.de");
		p2 = p.clone();
		assertTrue(p.equals(p2));
		
		p = new Person();
		p.setHomepage("http://wurst.de");
		p2 = p.clone();
		assertTrue(p.equals(p2));
		
		p = new Person();
		p.setRole("Voll de grasse scheff boss ey!");
		p2 = p.clone();
		assertTrue(p.equals(p2));
		
		p = new Person();
		p.setName("hans");
		p.setHomepage("http://wurst.de");
		p2 = p.clone();
		assertTrue(p.equals(p2));
		
		
		// negatives
		p = new Person();
		p.setName("hans");
		p2 = p.clone();
		p2.setName("erwin");
		assertFalse(p.equals(p2));
		
		p = new Person();
		p.setEmail("hans@wurst.de");
		p2 = p.clone();
		p2.setEmail("erwin@test.de");
		assertFalse(p.equals(p2));
		
		p = new Person();
		p.setHomepage("http://wurst.de");
		p2 = p.clone();
		p2.setHomepage("http://erwinrockt.de");
		assertFalse(p.equals(p2));
		
		p = new Person();
		p.setRole("Voll de grasse scheff boss ey!");
		p2 = p.clone();
		p2.setRole("krasser voll nich de boss typ");
		assertFalse(p.equals(p2));
		
		p = new Person();
		p.setName("hans");
		p.setHomepage("http://wurst.de");
		p2 = p.clone();
		p2.setEmail("jemand@microsoft.com");
		assertFalse(p.equals(p2));
	}
}
