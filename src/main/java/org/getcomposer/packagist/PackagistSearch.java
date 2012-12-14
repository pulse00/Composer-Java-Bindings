/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.packagist;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.getcomposer.ComposerConstants;
import org.getcomposer.ComposerPackage;
import org.json.simple.JSONValue;

public class PackagistSearch extends Downloader {
	private int pageLimit = 5;
	protected List<PackageSearchListenerInterface> pkgListeners = new ArrayList<PackageSearchListenerInterface>();
	
	public PackagistSearch() {
		super(ComposerConstants.SEARCH_URL);
	}
	
	protected String createQueryUrl(String query) {
		try {
			return String.format(ComposerConstants.SEARCH_URL, URLEncoder.encode(query, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addPackageSearchListener(PackageSearchListenerInterface listener) {
		if (!pkgListeners.contains(listener)) {
			pkgListeners.add(listener);
		}
	}
	
	public void removePackageSearchListener(PackageSearchListenerInterface listener) {
		pkgListeners.remove(listener);
	}

	public List<ComposerPackage> searchPackages(String query) throws Exception {
		List<ComposerPackage> packages = new ArrayList<ComposerPackage>();
		setUrl(createQueryUrl(query));

		SearchResult result = loadPackages(getUrl());
		
		if (result != null && result.results != null) {
			packages.addAll(result.results);
		}

		int current = 0;

		// TODO: implement paging results
		while (result.next != null && result.next.length() > 0) {
			result = loadPackages(result.next);

			if ((result.results != null && result.results.size() == 0)
					|| result.next == null || current++ > pageLimit) {
				break;
			}
			packages.addAll(result.results);
		}

		return packages;
	}
	
	public void searchPackagesAsync(final String query) {
		addDownloadListener(new DownloadListenerAdapater() {
			private int counter = 1;
			public void dataReceived(InputStream content) {
				SearchResult result = getPackages(content);
				
				if (result != null && result.results != null) {
					for (PackageSearchListenerInterface listener : pkgListeners) {
						listener.packagesFound(result.results, query, result);
					}
				}
				
				if (result != null && result.next != null 
						&& result.next.length() > 0 && counter < pageLimit) {
					setUrl(result.next);
					downloadAsync();
					counter++;
				}
			}
		});
		setUrl(createQueryUrl(query));
		downloadAsync();
	}
	
	protected SearchResult getPackages(InputStream resource) {
		InputStreamReader reader = new InputStreamReader(resource);

		return new SearchResult(JSONValue.parse(reader));
	}

	protected SearchResult loadPackages(String url) throws Exception {
		setUrl(url);
		return getPackages(download());
	}

	/**
	 * @return the pageLimit
	 */
	public int getPageLimit() {
		return pageLimit;
	}

	/**
	 * @param pageLimit the pageLimit to set
	 */
	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
}
