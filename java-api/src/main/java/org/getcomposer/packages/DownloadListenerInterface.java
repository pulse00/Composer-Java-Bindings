package org.getcomposer.packages;

import java.io.InputStream;

public interface DownloadListenerInterface extends TransferListenerInterface {

	void dataReceived(InputStream resource, String url);
}
