package org.getcomposer.packages;

import java.io.InputStream;

import org.getcomposer.ComposerConstants;

public class PharDownloader extends DownloadClient {

	public PharDownloader() {
		super();
		downloader.setUrl(ComposerConstants.PHAR_URL);
	}
	
	public InputStream download() {
		return downloader.download();
	}
}
