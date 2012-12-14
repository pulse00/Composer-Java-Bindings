package org.getcomposer.packagist;

import java.io.InputStream;

public interface DownloadListenerInterface {

	void aborted();
	
	void progressChanged(int worked);

	void setTotalWork(int total);

	void dataReceived(InputStream content);

	void errorOccured(Exception e);
}
