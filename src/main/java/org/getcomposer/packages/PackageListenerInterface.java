package org.getcomposer.packages;

import org.getcomposer.RepositoryPackage;

public interface PackageListenerInterface extends TransferListenerInterface {
	public void packageLoaded(RepositoryPackage repositoryPackage);
}
