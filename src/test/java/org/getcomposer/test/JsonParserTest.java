/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.test;

import java.util.Iterator;
import java.util.Map;

import org.getcomposer.PHPPackage;
import org.getcomposer.PackageInterface;
import org.getcomposer.SupportInterface;
import org.getcomposer.repositories.PackageRepository;
import org.junit.Test;

public class JsonParserTest extends ComposertTestCase {

	@Test
	@SuppressWarnings("rawtypes")
	public void testComposerJson() {

		try {

			PHPPackage phpPackage = PHPPackage
					.fromFile(loadFile("composer.json"));

			assertNotNull(phpPackage);
			assertEquals(3, phpPackage.authors.size());
			assertEquals(1, phpPackage.license.size());
			assertEquals(1, phpPackage.keywords.length);
			assertEquals(3, phpPackage.require.size());
			
			Map<String, String> require = phpPackage.require;
			Iterator it = require.keySet().iterator();

			while (it.hasNext()) {
				String key = (String) it.next();
				String value = require.get(key);
				assertNotNull(key);
				assertNotNull(value);
			}

			assertNotNull(phpPackage.autoload);
			assertEquals("FOS\\UserBundle", phpPackage.autoload.getPsr_0()
					.keySet().iterator().next());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSupport() {
		
		try {
			PHPPackage phpPackage = PHPPackage.fromFile(loadFile("support.json"));
			SupportInterface support = phpPackage.getSupport();
					
			assertEquals("test@mail.com", support.getEmail());
			assertEquals("irc://freenode.org/test", support.getIrc());
			assertEquals("http://github.com/gossi/test/issues", support.getIssues());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void testEmptyJson() {
		try {
			PHPPackage phpPackage = PHPPackage.fromFile(loadFile("empty.json"));
			assertNotNull(phpPackage);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testPackageJson() {

		try {
			PackageRepository repo = PackageRepository.fromFile(loadFile("packagist.json"));
			assertNotNull(repo);
			
			PackageInterface phpPackage = repo.getPackage();

			assertEquals("friendsofsymfony/user-bundle", phpPackage.getName());
			assertEquals("Symfony FOSUserBundle", phpPackage.getDescription());
			assertNotNull(phpPackage.getVersions());
			assertTrue(phpPackage.getVersions().size() > 0);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testReactJson() {

		try {
			PackageRepository repo = PackageRepository.fromFile(loadFile("react.json"));
			assertNotNull(repo);
			
			PackageInterface phpPackage = repo.getPackage();

			assertEquals("react/react", phpPackage.getName());
			assertEquals("Nuclear Reactor written in PHP.",
					phpPackage.getDescription());
			assertNotNull(phpPackage.getVersions());
			assertTrue(phpPackage.getVersions().size() > 0);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
