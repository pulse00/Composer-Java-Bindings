package com.dubture.getcomposer.packages;

import com.dubture.getcomposer.core.ComposerConstants;

public class AsyncPackagistDownloader extends AsyncPackageDownloader {

	public AsyncPackagistDownloader() {
		super(ComposerConstants.PACKAGE_URL);
	}
}
