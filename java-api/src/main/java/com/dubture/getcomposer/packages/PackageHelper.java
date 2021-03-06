package com.dubture.getcomposer.packages;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.dubture.getcomposer.core.RepositoryPackage;
import com.dubture.getcomposer.core.repositories.PackageRepository;
import com.dubture.getcomposer.json.JsonParser;
import com.dubture.getcomposer.json.ParseException;

class PackageHelper {
	
	static RepositoryPackage getPackage(InputStream resource) throws Exception {
		InputStreamReader reader = new InputStreamReader(resource);

		PackageRepository repo = new PackageRepository(reader);
		return repo.getPackage();
	}
	
	static SearchResult getSearchResult(InputStream resource) {
		InputStreamReader reader = new InputStreamReader(resource);

		JsonParser parser = new JsonParser();
		try {
			return new SearchResult(parser.parse(reader));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new SearchResult(new Object());
	}
}
