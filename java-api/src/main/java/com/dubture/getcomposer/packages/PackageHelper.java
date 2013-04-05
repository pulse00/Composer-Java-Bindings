package com.dubture.getcomposer.packages;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONValue;

import com.dubture.getcomposer.core.RepositoryPackage;
import com.dubture.getcomposer.core.repositories.PackageRepository;

class PackageHelper {
	
	static RepositoryPackage getPackage(InputStream resource) throws Exception {
		InputStreamReader reader = new InputStreamReader(resource);

		PackageRepository repo = new PackageRepository(reader);
		return repo.getPackage();
	}
	
	static SearchResult getSearchResult(InputStream resource) {
		InputStreamReader reader = new InputStreamReader(resource);

		return new SearchResult(JSONValue.parse(reader));
	}
}
