package org.getcomposer.packages;

import java.io.InputStream;

public class DownloadListenerAdapater implements DownloadListenerInterface {

	@Override
	public void aborted() {
	}

	@Override
	public void dataReceived(InputStream content, String url) {
	}

	@Override
	public void errorOccured(Exception e) {
	}

}
