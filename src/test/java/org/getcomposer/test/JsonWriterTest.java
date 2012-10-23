package org.getcomposer.test;

import org.getcomposer.PHPPackage;
import org.junit.Test;

public class JsonWriterTest extends ComposertTestCase {

	@Test
	public void testToJson() {
		PHPPackage phpPackage = createDummyPackage();

		String json = phpPackage.toJson();
		assertNotNull(json);
		System.out.println(json);
	}
}
