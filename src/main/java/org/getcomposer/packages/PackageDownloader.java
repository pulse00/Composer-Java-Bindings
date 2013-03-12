/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.packages;

import org.getcomposer.core.RepositoryPackage;

public class PackageDownloader extends DownloadClient {

	public PackageDownloader(String baseUrl) {
		super(baseUrl);
	}

	public RepositoryPackage loadPackage(String packageName) throws Exception {
		downloader.setUrl(createUrl(packageName));
		
		if (!downloader.getUrl().endsWith(".json")) {
			downloader.setUrl(downloader.getUrl() + ".json");
		}

		return PackageHelper.getPackage(downloader.download());
	}

}
