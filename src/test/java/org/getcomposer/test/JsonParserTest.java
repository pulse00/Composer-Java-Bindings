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
import org.getcomposer.collection.Repositories;
import org.getcomposer.entities.Dependency;
import org.getcomposer.entities.GenericEntity;
import org.getcomposer.entities.Support;
import org.getcomposer.repositories.ComposerRepository;
import org.getcomposer.repositories.PackageRepository;
import org.getcomposer.repositories.PearRepository;
import org.getcomposer.repositories.SubversionRepository;
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
			
			assertEquals("User management", phpPackage.getKeywords()[0]);
			assertEquals("http://friendsofsymfony.github.com", phpPackage.getHomepage());
			
			assertEquals("FOS/UserBundle", phpPackage.getTargetDir());
			assertNotNull(phpPackage.getMinimumStability());

			assertNotNull("Authors not NULL", phpPackage.getAuthors());
			assertEquals(3, phpPackage.getAuthors().size());
			
			assertEquals(1, phpPackage.getLicense().size());
			assertEquals("MIT", phpPackage.getLicense().get(0));
			
			assertEquals(1, phpPackage.getKeywords().length);
			assertEquals(1, phpPackage.getKeywords().length);
			
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
	
	public void testRepositories() {
		try {
			ComposerPackage phpPackage = ComposerPackage.fromFile(loadFile("repositories.json"));
			Repositories repos = phpPackage.getRepositories();

			assertNotNull(repos);
			assertTrue(repos.get(0) instanceof ComposerRepository);
			assertTrue(repos.get(1) instanceof SubversionRepository);
			assertTrue(repos.get(2) instanceof PearRepository);
			assertTrue(repos.get(3) instanceof PackageRepository);
			
			ComposerRepository composer = (ComposerRepository)repos.get(0);
			assertTrue(composer.getOptions() instanceof GenericEntity);
			assertTrue(composer.getOptions().has("ssl"));
			assertTrue(composer.getOptions().isEntity("ssl"));
			
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
			RepositoryPackage phpPackage = RepositoryPackage.fromPackagist(loadFile("packagist.json"));
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
			
			RepositoryPackage phpPackage = repo.getPackage();

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
