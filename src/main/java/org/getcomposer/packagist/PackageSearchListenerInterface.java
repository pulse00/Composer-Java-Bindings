package org.getcomposer.packagist;

import java.util.List;

import org.getcomposer.ComposerPackage;

public interface PackageSearchListenerInterface {
	public void packagesFound(List<ComposerPackage> packages, String query, SearchResult result);
}
