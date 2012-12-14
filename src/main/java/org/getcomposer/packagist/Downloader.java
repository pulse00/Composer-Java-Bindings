/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.packagist;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Robert Gruendler <r.gruendler@gmail.com>
 * 
 */
public class Downloader {
	protected String url;

	protected List<DownloadListenerInterface> listeners;
	protected DownloadThread downloadThread;
	
	private InputStream downloadContent;
	private Exception downloadError;

	public Downloader(String url) {
		this.setUrl(url);
		listeners = new ArrayList<DownloadListenerInterface>();
	}

	public void addDownloadListener(DownloadListenerInterface listener) {
		listeners.add(listener);
	}

	public void removeDownloadListener(DownloadListenerInterface listener) {
		listeners.remove(listener);
	}
	
	/*package*/ List<DownloadListenerInterface> getListeners() {
		return listeners;
	}
	
	public InputStream download() throws Exception {
		downloadContent = null;
		downloadError = null;
		addDownloadListener(new DownloadListenerAdapater() {
			public void dataReceived(InputStream resource) {
				downloadContent = resource;
			}
			
			public void errorOccured(Exception e) {
				downloadError = e;
			}
		});
		
		downloadThread = new DownloadThread(this);
		downloadThread.run();
		
		if (downloadError != null) {
			throw downloadError;
		}
		
		return downloadContent;
	}

	public void downloadAsync() {
		downloadThread = new DownloadThread(this);
		downloadThread.start();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void abort() {
		if (downloadThread != null) {
			downloadThread.abort();
		}
	}

}
