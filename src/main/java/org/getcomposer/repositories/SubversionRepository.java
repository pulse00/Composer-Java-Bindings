package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;

public class SubversionRepository extends VcsRepository {

	public SubversionRepository() {
		super("svn");
	}

	public static SubversionRepository fromFile(File input) throws FileNotFoundException {
		return fromFile(input, SubversionRepository.class);
	}
	
	public static SubversionRepository fromJson(String json) throws FileNotFoundException {
		return fromJson(json, SubversionRepository.class);
	}
}
