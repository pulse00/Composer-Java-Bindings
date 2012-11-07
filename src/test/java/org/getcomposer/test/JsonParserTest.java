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
import org.getcomposer.entities.Config;
import org.getcomposer.entities.Dependency;
import org.getcomposer.entities.Distribution;
import org.getcomposer.entities.GenericEntity;
import org.getcomposer.entities.Source;
import org.getcomposer.entities.Support;
import org.getcomposer.repositories.ComposerRepository;
import org.getcomposer.repositories.PackageRepository;
import org.getcomposer.repositories.PearRepository;
import org.getcomposer.repositories.SubversionRepository;
import org.getcomposer.repositories.VcsRepository;
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
			assertEquals("http://github.com/gossi/test/issues", support.getForum());
			assertEquals("http://github.com/gossi/test/wiki", support.getWiki());
			assertEquals("http://github.com/gossi/test", support.getSource());
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
			ComposerPackage phpPackage = ComposerPackage.fromFile(loadFile("repositories.json"));
			Repositories repos = phpPackage.getRepositories();

			assertNotNull(repos);
			assertTrue(repos.get(0) instanceof ComposerRepository);
			assertTrue(repos.get(1) instanceof SubversionRepository);
			assertTrue(repos.get(2) instanceof PearRepository);
			assertTrue(repos.get(3) instanceof PackageRepository);
			assertTrue(repos.get(4) instanceof VcsRepository);
			
			// composer repository
			ComposerRepository composer = (ComposerRepository)repos.get(0);
			assertTrue(composer.getOptions() instanceof GenericEntity);
			
			GenericEntity options = composer.getOptions();
			assertTrue(options.has("ssl"));
			assertTrue(options.isEntity("ssl"));
			
			GenericEntity ssl = options.getAsEntity("ssl");
			assertTrue(ssl.has("verify_peer"));
			assertTrue(ssl.getAsBoolean("verify_peer"));
			
			// subversion repository
			SubversionRepository subversion = (SubversionRepository)repos.get(1);
			assertNotNull(subversion.getTrunkPath());
			assertNotNull(subversion.getBranchesPath());
			assertNotNull(subversion.getTagsPath());
			
			// pear repository
			PearRepository pear = (PearRepository)repos.get(2);
			assertNotNull(pear.getVendorAlias());
			assertEquals("foobar", pear.getVendorAlias());
			
			// package repository
			PackageRepository pkgRepo = (PackageRepository)repos.get(3);
			assertNotNull(pkgRepo);
			
			RepositoryPackage pkg = pkgRepo.getPackage();
			assertNotNull(pkg);
			
			assertEquals("smarty/smarty", pkg.getName());
			assertEquals("3.1.7", pkg.getVersion());
			
			assertNotNull(pkg.getDist());
			assertTrue(pkg.getDist() instanceof Distribution);
			assertEquals("http://www.smarty.net/files/Smarty-3.1.7.zip", pkg.getDist().getUrl());
			assertEquals("zip", pkg.getDist().getType());
			
			assertNotNull(pkg.getSource());
			assertTrue(pkg.getSource() instanceof Source);
			assertEquals("http://smarty-php.googlecode.com/svn/", pkg.getSource().getUrl());
			assertEquals("svn", pkg.getSource().getType());
			assertEquals("tags/Smarty_3_1_7/distribution/", pkg.getSource().getReference());
			
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
