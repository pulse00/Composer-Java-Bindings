/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
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
			
			assertNotNull(phpPackage);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testPackagistJson() {

		try {
			PHPPackage phpPackage = PHPPackage.fromPackagist(loadFile("packagist.json"));
			assertNotNull(phpPackage);
			
			System.err.println(phpPackage.name);
			assertEquals("friendsofsymfony/user-bundle", phpPackage.name);
			
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
