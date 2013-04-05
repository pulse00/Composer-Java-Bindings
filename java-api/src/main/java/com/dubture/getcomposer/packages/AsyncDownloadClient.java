package com.dubture.getcomposer.packages;

public class AsyncDownloadClient extends AbstractDownloadClient {

	protected AsyncDownloader downloader = new AsyncDownloader();
	protected DownloadListenerInterface downloadListener = null;
	
	public AsyncDownloadClient() {
		
	}
	
	public AsyncDownloadClient(String baseUrl) {
		super(baseUrl);
	}
	

	public AsyncDownloadClient(String baseUrl, boolean baseUrlParamEncoding) {
		super(baseUrl, baseUrlParamEncoding);
	}
	
	public void abort() {
		downloader.abort();
	}
	
	public void abort(int slot) {
		downloader.abort(slot);
	}
}
