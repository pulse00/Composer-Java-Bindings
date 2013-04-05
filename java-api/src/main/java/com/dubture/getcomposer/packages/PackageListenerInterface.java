package com.dubture.getcomposer.packages;

import com.dubture.getcomposer.core.RepositoryPackage;

public interface PackageListenerInterface extends TransferListenerInterface {
	public void packageLoaded(RepositoryPackage repositoryPackage);
}
