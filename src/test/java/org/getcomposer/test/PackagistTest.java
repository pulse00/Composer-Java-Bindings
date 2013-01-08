/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.test;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.getcomposer.MinimalPackage;
import org.getcomposer.RepositoryPackage;
import org.getcomposer.packagist.DownloadListenerAdapater;
import org.getcomposer.packagist.PackageListenerInterface;
import org.getcomposer.packagist.PackageSearchListenerInterface;
import org.getcomposer.packagist.PackagistDownloader;
import org.getcomposer.packagist.PharDownloader;
import org.getcomposer.packagist.PackagistSearch;
import org.getcomposer.packagist.SearchResult;
import org.junit.Test;


public class PackagistTest extends TestCase {
	
	private CountDownLatch counter = new CountDownLatch(1);
	private Object asyncResult;
	private String asyncQuery;
	private int asyncCounter;
	
	public void setUp() {
		asyncResult = null;
		asyncQuery = "";
		asyncCounter = 0;
	}
	
	public void testComposerDownload() {
		try {
			PharDownloader downloader = new PharDownloader();
			InputStream content = downloader.download(); 
			assertNotNull(content);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testAsyncComposerDownload() {
		try {
			PharDownloader downloader = new PharDownloader();
			downloader.addDownloadListener(new DownloadListenerAdapater() {
				@SuppressWarnings("unused")
				public void dataReceived(InputStream content) {
					asyncResult = content;
					counter.countDown();
				}
				
				public void errorOccured(Exception e) {
					e.printStackTrace();
				}
			});
			downloader.downloadAsync();

			counter.await(10, TimeUnit.SECONDS);

			assertNotNull(asyncResult);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testPackageDownloader() {
		try {
			PackagistDownloader downloader = new PackagistDownloader("gossi/ldap");
			RepositoryPackage pkg = downloader.loadPackage();
			assertNotNull(pkg);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testAsyncPackageDownloader() {
		try {
			PackagistDownloader downloader = new PackagistDownloader("gossi/ldap");
			downloader.addPackageListener(new PackageListenerInterface() {
				public void packageLoaded(RepositoryPackage repositoryPackage) {
					asyncResult = repositoryPackage;
					counter.countDown();
				}
			});
			downloader.loadPackageAsync();
			
			counter.await(10, TimeUnit.SECONDS);

			assertNotNull(asyncResult);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testPackage() {
		
		try {
			RepositoryPackage resource = RepositoryPackage.fromPackagist("react/react");
			
			assertTrue(resource != null);
			assertEquals("react/react", resource.getName());
			assertEquals("Nuclear Reactor written in PHP.", resource.getDescription());
			assertNotNull(resource.getVersions());
			assertTrue(resource.getVersions().size() > 1);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSearch() {
		
		try {
			assertSearchResult("html");
			assertSearchResult("react");
			assertSearchResult("foo bar");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	protected void assertSearchResult(String query) throws Exception {
		
		PackagistSearch downloader = new PackagistSearch();
		List<MinimalPackage> packages = downloader.searchPackages(query);

		assertNotNull(packages);
		assertTrue(packages.size() > 0);
		
		for (MinimalPackage phpPackage : packages) {
			if (query.equals("foo bar")) {
//				System.err.println(phpPackage.getName());
			}
			assertNotNull(phpPackage);
			assertNotNull(phpPackage.getName());
			assertNotNull(phpPackage.getDescription());
		}
	}
	
	@Test
	public void testAsyncSearch() {
		try {
			PackagistSearch downloader = new PackagistSearch();
			downloader.addPackageSearchListener(new PackageSearchListenerInterface() {
				public void packagesFound(List<MinimalPackage> packages, String query, SearchResult result) {
					asyncResult = packages;
					asyncQuery = query;
					asyncCounter++;
					
					counter.countDown();
				}
			});
			String query = "gossi/ldap";
			downloader.searchPackagesAsync(query);
			
			counter.await(10, TimeUnit.SECONDS);
			
			assertNotNull(asyncResult);
			assertEquals(query, asyncQuery);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testAsyncSearchWithPages() {
		try {
			PackagistSearch downloader = new PackagistSearch();
			downloader.addPackageSearchListener(new PackageSearchListenerInterface() {
				public void packagesFound(List<MinimalPackage> packages, String query, SearchResult result) {
					asyncResult = packages;
					asyncQuery = query;
					asyncCounter++;
				}
			});
			String query = "test";
			downloader.setPageLimit(2);
			downloader.searchPackagesAsync(query);
			
			counter.await(10, TimeUnit.SECONDS);
			
			assertNotNull(asyncResult);
			assertEquals(query, asyncQuery);
			assertEquals(2, asyncCounter);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
