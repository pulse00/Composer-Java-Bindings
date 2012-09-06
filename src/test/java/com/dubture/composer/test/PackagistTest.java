/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.dubture.composer.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.dubture.composer.PHPPackage;
import com.dubture.composer.core.packagist.PackageDownloader;
import com.dubture.composer.core.packagist.SearchResultDownloader;

public class PackagistTest extends TestCase {
	
	@Test
	public void testPackage() {
		
		try {
			PackageDownloader downloader = new PackageDownloader("react/react");
			PHPPackage resource = downloader.getPackage();
			
			assertTrue(resource != null);
			assertEquals("react/react", resource.name);
			assertEquals("Nuclear Reactor written in PHP.", resource.description);
			assertNotNull(resource.versions);
			assertTrue(resource.versions.size() > 1);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSearch() {
		
		try {
			SearchResultDownloader downloader = new SearchResultDownloader();
			List<? extends PHPPackage> packages = downloader.searchPackages("html");

			assertNotNull(packages);
			assertTrue(packages.size() > 0);
			
			for (PHPPackage phpPackage : packages) {
				assertNotNull(phpPackage);
				assertNotNull(phpPackage.name);
				assertNotNull(phpPackage.description);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
