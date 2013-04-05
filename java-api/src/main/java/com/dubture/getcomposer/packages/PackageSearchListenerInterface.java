package com.dubture.getcomposer.packages;

import java.util.List;

import com.dubture.getcomposer.core.MinimalPackage;

public interface PackageSearchListenerInterface extends TransferListenerInterface {
	public void packagesFound(List<MinimalPackage> packages, String query, SearchResult result);
}
