package org.getcomposer.packages;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.getcomposer.RepositoryPackage;
import org.getcomposer.repositories.PackageRepository;
import org.json.simple.JSONValue;

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
