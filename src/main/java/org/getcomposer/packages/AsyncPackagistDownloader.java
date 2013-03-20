package org.getcomposer.packages;

import org.getcomposer.core.ComposerConstants;

public class AsyncPackagistDownloader extends AsyncPackageDownloader {

	public AsyncPackagistDownloader() {
		super(ComposerConstants.PACKAGE_URL);
	}
}
