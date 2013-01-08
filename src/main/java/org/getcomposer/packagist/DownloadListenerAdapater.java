package org.getcomposer.packagist;

import java.io.InputStream;

public class DownloadListenerAdapater implements DownloadListenerInterface {

	@Override
	public void aborted() {
	}

	@Override
	public void progressChanged(int worked) {
	}

	@Override
	public void setTotalWork(int total) {
	}

	@Override
	public void dataReceived(InputStream content, String url) {
	}

	@Override
	public void errorOccured(Exception e) {
	}

}
