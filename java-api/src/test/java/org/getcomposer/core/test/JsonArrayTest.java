package org.getcomposer.core.test;

import com.dubture.getcomposer.core.collection.JsonArray;

import junit.framework.TestCase;

public class JsonArrayTest extends TestCase {

	public void testClear() {
		JsonArray array = new JsonArray();
		array.add("a");
		array.add("b");
		array.add("c");
		assertEquals(3, array.size());
		
		array.clear();
		assertEquals(0, array.size());
	}
	
	public void testEquals() {
		JsonArray a1, a2;
		
		a1 = new JsonArray();
		a1.add("a");
		a1.add("b");
		a1.add("c");
		
		a2 = new JsonArray();
		a2.add("a");
		a2.add("b");
		a2.add("c");
		
		assertTrue(a1.equals(a2));
	}
}
