package com.dubture.composer.test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.TestCase;

import org.junit.Test;

import com.dubture.composer.PHPPackage;

public class JsonParserTest extends TestCase {

	@Test
	public void testComposerJson() {

		try {
			PHPPackage phpPackage = PHPPackage.fromJson(loadFile("composer.json"));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testPackagistJson() {

		try {
			PHPPackage phpPackage = PHPPackage.fromJson(loadFile("packagist.json"));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} 
	}
	
	protected File loadFile(String name) throws URISyntaxException {

		ClassLoader loader = getClass().getClassLoader();
		URL resource = loader.getResource(name);
		return new File(resource.toURI());
	}
}
