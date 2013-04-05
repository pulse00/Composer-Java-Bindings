package com.dubture.getcomposer.packages;

import java.io.InputStream;

import com.dubture.getcomposer.core.ComposerConstants;

public class PharDownloader extends DownloadClient {

	public PharDownloader() {
		super();
		downloader.setUrl(ComposerConstants.PHAR_URL);
	}
	
	public InputStream download() {
		return downloader.download();
	}
}
