package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;

public class PearRepository extends AbstractRepository {

	public PearRepository() {
		super("pear");
	}
	
	public static PearRepository fromFile(File input) throws FileNotFoundException {
		return fromFile(input, PearRepository.class);
	}
	
	public static PearRepository fromJson(String json) throws FileNotFoundException {
		return fromJson(json, PearRepository.class);
	}
}
