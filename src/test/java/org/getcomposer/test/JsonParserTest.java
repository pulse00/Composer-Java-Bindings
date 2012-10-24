/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.test;

import java.util.Iterator;

import org.getcomposer.Dependencies;
import org.getcomposer.ComposerPackage;
import org.getcomposer.Dependency;
import org.getcomposer.Support;
import org.getcomposer.repositories.PackageRepository;
import org.junit.Test;

public class JsonParserTest extends ComposertTestCase {

	@Test
	public void testComposerJson() {

		try {

			ComposerPackage phpPackage = ComposerPackage
					.fromFile(loadFile("composer.json"));

			assertNotNull(phpPackage);
			assertNotNull("Authors null", phpPackage.getAuthors());
			assertEquals(3, phpPackage.getAuthors().size());
			assertEquals(1, phpPackage.getLicense().size());
			assertEquals(1, phpPackage.getKeywords().length);
			assertEquals(3, phpPackage.getRequire().size());
			
			Dependencies require = phpPackage.getRequire();
			
			for (Dependency dep : require) {
				assertNotNull(dep.getName());
				assertNotNull(dep.getVersion());
			}

			assertNotNull(phpPackage.getAutoload());
			assertEquals("FOS\\UserBundle", phpPackage.getAutoload().getPsr0()
					.keySet().iterator().next());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSupport() {
		
		try {
			ComposerPackage phpPackage = ComposerPackage.fromFile(loadFile("support.json"));
			Support support = phpPackage.getSupport();

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
			ComposerPackage phpPackage = ComposerPackage.fromFile(loadFile("empty.json"));
			assertNotNull(phpPackage);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testPackageJson() {

		try {
			ComposerPackage phpPackage = ComposerPackage.fromPackagist(loadFile("packagist.json"));
			assertNotNull(phpPackage);

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
			
			ComposerPackage phpPackage = repo.getPackage();

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
