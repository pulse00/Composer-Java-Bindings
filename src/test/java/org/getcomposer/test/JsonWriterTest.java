package org.getcomposer.test;

import org.getcomposer.ComposerPackage;
import org.junit.Test;

public class JsonWriterTest extends ComposertTestCase {

	private ComposerPackage phpPackage;
	private String json;

	
	
	/*
	 * would be better in a @BeforeClass as so, this does not work for me
	 */
	public void setUp() {
		phpPackage = createDummyPackage();
		json = phpPackage.toJson();
	}
	
	@Test
	public void testToJson() {
		assertNotNull(json);
//		System.out.println(json);
	}
	
	@Test
	public void testComposerPackage() {
		doTestComposerPackage(ComposerPackage.fromJson(json));
	}
	
	@Test
	public void testDependencies() {
		doTestDependencies(ComposerPackage.fromJson(json));
	}
	
	@Test
	public void testAutoload() {
		doTestAutoload(ComposerPackage.fromJson(json));
	}
	
	@Test
	public void testConfig() {
		doTestConfig(ComposerPackage.fromJson(json));
	}
	
	@Test
	public void testSupport() {
		doTestSupport(ComposerPackage.fromJson(json));
	}

	@Test
	public void testRepositories() {
		doTestRepositories(ComposerPackage.fromJson(json));
	}
	
	@Test public void testSimplePackage() {
		ComposerPackage pkg = new ComposerPackage();
		pkg.setName("test/package");
		pkg.setType("");
		
		System.out.println(pkg.toJson());
	}
}
