package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;

public class GitRepository extends VcsRepository {

	public GitRepository() {
		super("git");
	}
	
	public static GitRepository fromJson(File input) throws FileNotFoundException {
		return fromFile(input, GitRepository.class);
	}
	
	public static GitRepository fromJson(String json) throws FileNotFoundException {
		return fromJson(json, GitRepository.class);
	}
}
