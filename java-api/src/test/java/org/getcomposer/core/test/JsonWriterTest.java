package org.getcomposer.core.test;

import org.junit.Test;

import com.dubture.getcomposer.core.ComposerPackage;
import com.dubture.getcomposer.json.ParseException;

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
	}
	
	@Test
	public void testEmptyPackage() {
		ComposerPackage pkg = new ComposerPackage();
		assertEquals("{}", pkg.toJson());
	}
	
	@Test
	public void testKeywords() {
		ComposerPackage pkg = new ComposerPackage();

		pkg.getKeywords().add("bla");
		assertNotSame("{\n\t\"keywords\" : \"bla\"\n}", pkg.toJson());
		
		pkg.getKeywords().add("blubb");
		assertEquals("{\n\t\"keywords\" : [\n\t\t\"bla\",\n\t\t\"blubb\"\n\t]\n}", pkg.toJson());
	}
	
	@Test
	public void testLicense() {
		ComposerPackage pkg = new ComposerPackage();

		pkg.getLicense().add("MIT");
		assertEquals("{\n\t\"license\" : \"MIT\"\n}", pkg.toJson());
		
		pkg.getLicense().add("EPL");
		assertEquals("{\n\t\"license\" : [\n\t\t\"MIT\",\n\t\t\"EPL\"\n\t]\n}", pkg.toJson());
	}
	
	@Test
	public void testComposerPackage() {
		try {
			doTestComposerPackage(new ComposerPackage(json));
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testDependencies() {
		try {
			doTestDependencies(new ComposerPackage(json));
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testAutoload() {
		try {
			doTestAutoload(new ComposerPackage(json));
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testConfig() {
		try {
			doTestConfig(new ComposerPackage(json));
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testScripts() {
		try {
			doTestScripts(new ComposerPackage(json));
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSupport() {
		try {
			doTestSupport(new ComposerPackage(json));
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testRepositories() {
		try {
			doTestRepositories(new ComposerPackage(json));
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test 
	public void testSimplePackage() {
		ComposerPackage pkg = new ComposerPackage();
		pkg.setName("test/package");
		pkg.setType("");
		
//		System.out.println(pkg.toJson());
	}
}
