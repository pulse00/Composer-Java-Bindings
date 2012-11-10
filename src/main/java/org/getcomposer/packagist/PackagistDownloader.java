package org.getcomposer.packagist;

import org.getcomposer.ComposerConstants;

public class PackagistDownloader extends PackageDownloader {

	public PackagistDownloader(String name) {
		super(String.format(ComposerConstants.PACKAGE_URL, name));
	}
}
