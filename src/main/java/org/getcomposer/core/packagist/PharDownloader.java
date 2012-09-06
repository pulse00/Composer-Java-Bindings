package com.dubture.composer.core.packagist;

import com.dubture.composer.ComposerConstants;

public class PharDownloader extends Downloader {

	public PharDownloader() {
		super(ComposerConstants.pharURL);
	}
}
