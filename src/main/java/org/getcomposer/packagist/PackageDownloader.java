/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer.packagist;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.getcomposer.RepositoryPackage;
import org.getcomposer.repositories.PackageRepository;

public class PackageDownloader extends Downloader {
	
	public PackageDownloader(String packageName) {
		super(packageName);
	}

	public RepositoryPackage getPackage() throws IOException {
		if (!url.endsWith(".json")) {
			url += ".json";
		}

		InputStream resource = downloadResource();
		InputStreamReader reader = new InputStreamReader(resource);

		PackageRepository repo = new PackageRepository(reader);
		return repo.getPackage();

	}
}
