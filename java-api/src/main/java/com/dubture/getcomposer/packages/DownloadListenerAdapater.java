package com.dubture.getcomposer.packages;

import java.io.InputStream;

public class DownloadListenerAdapater implements DownloadListenerInterface {

	@Override
	public void aborted(String url) {
	}

	@Override
	public void dataReceived(InputStream content, String url) {
	}

	@Override
	public void errorOccured(Exception e) {
	}

}
