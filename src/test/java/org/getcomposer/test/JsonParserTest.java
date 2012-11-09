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
import org.getcomposer.collection.Dependencies;
import org.getcomposer.entities.Config;
import org.getcomposer.entities.Dependency;
import org.junit.Test;

public class JsonParserTest extends ComposertTestCase {

	@Test
	public void testComposerPackage() {
		try {
			ComposerPackage phpPackage = ComposerPackage.fromFile(loadFile("composer.json"));
			
			assertNotNull(phpPackage);
			
			assertEquals("friendsofsymfony/user-bundle", phpPackage.getName());
			assertEquals("symfony-bundle", phpPackage.getType());
			assertEquals("Symfony FOSUserBundle", phpPackage.getDescription());
			
			assertEquals("User management", phpPackage.getKeywords().get(0));
			assertEquals("http://friendsofsymfony.github.com", phpPackage.getHomepage());
			
			assertEquals("FOS/UserBundle", phpPackage.getTargetDir());
			assertNotNull(phpPackage.getMinimumStability());

			assertNotNull("Authors not NULL", phpPackage.getAuthors());
			assertEquals(3, phpPackage.getAuthors().size());
			
			assertEquals(1, phpPackage.getLicense().size());
			assertEquals("MIT", phpPackage.getLicense().get(0));
			
			assertEquals(1, phpPackage.getKeywords().size());
			
			assertNotNull(phpPackage.getRequire());
			assertEquals(3, phpPackage.getRequire().size());
		
			Dependencies require = phpPackage.getRequire();
			
			for (Dependency dep : require) {
				assertNotNull(dep.getName());
				assertNotNull(dep.getVersion());
			}
			
			assertNotNull(phpPackage.getRequireDev());
			assertNotNull(phpPackage.getAutoload());
			
			assertEquals("PSR-0 Namespace paths count", 1, phpPackage.getAutoload().getPsr0().get("FOS\\UserBundle").getAll().size());
			
		} catch (Exception e) {
			e.printStackTrace();
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
			ComposerPackage phpPackage = ComposerPackage.fromFile(loadFile("config.json"));
			Config config = phpPackage.getConfig();

			assertNotNull(config);
			assertEquals("vend0r", config.getVendorDir());
			assertEquals("b1n", config.getBinDir());
			assertEquals(3000, config.getProcessTimeout());
			assertEquals(2, config.getGithubProtocols().size());
			assertTrue(config.getNotifyOnInstall());
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
