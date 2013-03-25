package org.getcomposer.core.test;

import org.getcomposer.core.collection.JsonArray;

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
}
