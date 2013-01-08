package org.getcomposer.packagist;

import org.getcomposer.ComposerConstants;

public class PharDownloader extends Downloader {

	public PharDownloader() {
		super(ComposerConstants.PHAR_URL);
	}
}
