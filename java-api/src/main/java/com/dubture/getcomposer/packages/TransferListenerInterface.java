package com.dubture.getcomposer.packages;

public interface TransferListenerInterface {
	void errorOccured(Exception e);
	
	void aborted(String url);
}
