package org.getcomposer.core.test;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.dubture.getcomposer.core.ComposerPackage;

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
