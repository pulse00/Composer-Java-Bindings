/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.test;

import org.getcomposer.ComposerPackage;
import org.getcomposer.RepositoryPackage;
import org.junit.Test;

public class JsonParserTest extends ComposertTestCase {

	@Test
	public void testComposerPackage() {
		try {
			doTestComposerPackage(ComposerPackage.fromFile(loadFile("composer.json")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDependencies() {
		try {
			doTestDependencies(ComposerPackage.fromFile(loadFile("dependencies.json")));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSupport() {
		try {
			doTestSupport(ComposerPackage.fromFile(loadFile("support.json")));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testConfig() {
		try {
			doTestConfig(ComposerPackage.fromFile(loadFile("config.json")));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testRepositories() {
		try {
			doTestRepositories(ComposerPackage.fromFile(loadFile("repositories.json")));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testAutoload() {
		try {
			doTestAutoload(ComposerPackage.fromFile(loadFile("autoload.json")));
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
			RepositoryPackage phpPackage = RepositoryPackage.fromPackageRepository(loadFile("packagist.json"));
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
			RepositoryPackage phpPackage = RepositoryPackage.fromPackageRepository(loadFile("react.json"));
			assertNotNull(phpPackage);

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
