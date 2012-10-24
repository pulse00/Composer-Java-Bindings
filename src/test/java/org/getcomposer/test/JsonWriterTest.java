package org.getcomposer.test;

import org.getcomposer.ComposerPackage;
import org.junit.Test;

public class JsonWriterTest extends ComposertTestCase {

	@Test
	public void testToJson() {
		ComposerPackage phpPackage = createDummyPackage();

		String json = phpPackage.toJson();
		assertNotNull(json);
		System.out.println(json);
	}
}
