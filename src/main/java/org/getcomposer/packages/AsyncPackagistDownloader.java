package org.getcomposer.packages;

import org.getcomposer.ComposerConstants;

public class AsyncPackagistDownloader extends AsyncPackageDownloader {

	public AsyncPackagistDownloader() {
		super(ComposerConstants.PACKAGE_URL);
	}
}
