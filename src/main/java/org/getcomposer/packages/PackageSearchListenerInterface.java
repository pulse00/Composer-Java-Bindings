package org.getcomposer.packages;

import java.util.List;

import org.getcomposer.MinimalPackage;

public interface PackageSearchListenerInterface extends TransferListenerInterface {
	public void packagesFound(List<MinimalPackage> packages, String query, SearchResult result);
}
