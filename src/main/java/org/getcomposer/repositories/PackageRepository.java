package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;

import org.getcomposer.ComposerPackage;

import com.google.gson.annotations.SerializedName;

public class PackageRepository extends AbstractRepository {

	@SerializedName("package")
	private ComposerPackage phpPackage;
	
	public PackageRepository() {
		super("package");
	}
	
	public ComposerPackage getPackage() {
		return phpPackage;
	}

	public static PackageRepository fromFile(File input) throws FileNotFoundException {
		return fromFile(input, PackageRepository.class);
	}
	
	public static PackageRepository fromJson(String json) throws FileNotFoundException {
		return fromJson(json, PackageRepository.class);
	}
}
