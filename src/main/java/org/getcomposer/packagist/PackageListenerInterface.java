package org.getcomposer.packagist;

import org.getcomposer.RepositoryPackage;

public interface PackageListenerInterface {
	public void packageLoaded(RepositoryPackage repositoryPackage);
}
