package com.dubture.getcomposer.packages;

public class DownloadClient extends AbstractDownloadClient {

	protected Downloader downloader = new Downloader();
	
	public DownloadClient() {
		
	}
	
	public DownloadClient(String baseUrl) {
		super(baseUrl);
	}

	public DownloadClient(String baseUrl, boolean baseUrlParamEncoding) {
		super(baseUrl, baseUrlParamEncoding);
	}

	public void abort() {
		downloader.abort();
	}
}
