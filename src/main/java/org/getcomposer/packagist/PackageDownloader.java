/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.packagist;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.getcomposer.RepositoryPackage;
import org.getcomposer.repositories.PackageRepository;

public class PackageDownloader extends Downloader {
	
	private List<PackageListenerInterface> pkgListeners = new ArrayList<PackageListenerInterface>();
	
	public PackageDownloader(String packageName) {
		super(packageName);
	}
	
	public void addPackageListener(PackageListenerInterface listener) {
		if (!pkgListeners.contains(listener)) {
			pkgListeners.add(listener);
		}
	}
	
	public void removePackageListener(PackageListenerInterface listener) {
		pkgListeners.remove(listener);
	}
	
	protected void notifyPackageListener(RepositoryPackage pkg) {
		for (PackageListenerInterface listener : pkgListeners) {
			listener.packageLoaded(pkg);
		}
	}
	
	private RepositoryPackage getPackage(InputStream resource) throws Exception {
		InputStreamReader reader = new InputStreamReader(resource);

		PackageRepository repo = new PackageRepository(reader);
		return repo.getPackage();
	}

	public RepositoryPackage loadPackage() throws Exception {
		if (!url.endsWith(".json")) {
			url += ".json";
		}

		return getPackage(download());
	}
	
	public void loadPackageAsync() {
		addDownloadListener(new DownloadListenerAdapater() {
			@Override
			public void dataReceived(InputStream content, String url) {
				try {
					notifyPackageListener(getPackage(content));
				} catch (Exception e) {
					// oupsi, not catched, its the ioexception, we can ignore that here?
				}
			}
		});
		downloadAsync();
	}
}
