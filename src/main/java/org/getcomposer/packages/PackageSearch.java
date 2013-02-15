/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.packages;

import java.util.ArrayList;
import java.util.List;

import org.getcomposer.MinimalPackage;

public class PackageSearch extends DownloadClient {
	
	private int pageLimit = 3;
	
	public PackageSearch(String baseUrl) {
		super(baseUrl, true);
	}

	public List<MinimalPackage> search(String query) throws Exception {
		List<MinimalPackage> packages = new ArrayList<MinimalPackage>();
		SearchResult result = loadPackages(createUrl(query));
		
		if (result != null && result.results != null) {
			packages.addAll(result.results);
		}

		int current = 0;

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
	
	private SearchResult loadPackages(String url) throws Exception {
		downloader.setUrl(url);
		return PackageHelper.getSearchResult(downloader.download());
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
