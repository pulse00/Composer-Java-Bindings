package org.getcomposer.packagist;

import java.util.List;

import org.getcomposer.MinimalPackage;

public interface PackageSearchListenerInterface {
	public void packagesFound(List<MinimalPackage> packages, String query, SearchResult result);
}
