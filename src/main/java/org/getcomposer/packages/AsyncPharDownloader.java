package org.getcomposer.packages;

import org.getcomposer.core.ComposerConstants;


public class AsyncPharDownloader extends AsyncDownloadClient {

	public AsyncPharDownloader() {
		super();
		downloader.setUrl(ComposerConstants.PHAR_URL);
	}
	
	public void addDownloadListener(DownloadListenerInterface listener) {
		downloader.addDownloadListener(listener);
	}

	public void removeDownloadListener(DownloadListenerInterface listener) {
		downloader.removeDownloadListener(listener);
	}

	public void download() {
		downloader.download();
	}
}
