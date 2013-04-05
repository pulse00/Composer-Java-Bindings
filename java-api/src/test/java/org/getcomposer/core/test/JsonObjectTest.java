package org.getcomposer.core.test;

import com.dubture.getcomposer.core.objects.JsonObject;

import junit.framework.TestCase;

public class JsonObjectTest extends TestCase {

	public void testClear() {
		JsonObject obj = new JsonObject();
		obj.set("a", "val");
		obj.set("b", "val");
		obj.set("c", "val");
		
		assertEquals(3, obj.size());
		
		obj.clear();
		
		assertEquals(0, obj.size());
	}
}
