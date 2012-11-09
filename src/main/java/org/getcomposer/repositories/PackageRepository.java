package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;

import org.getcomposer.RepositoryPackage;
import org.getcomposer.internal.serialization.ExtendedClientEntitySerializer;

import com.google.gson.annotations.SerializedName;

public class PackageRepository extends Repository {

	@SerializedName("package")
	private RepositoryPackage phpPackage = new RepositoryPackage();
	
	public PackageRepository() {
		super("package");
	}
	
	public RepositoryPackage getPackage() {
		return phpPackage;
	}

	public static PackageRepository fromFile(File input) throws FileNotFoundException {
		return fromFile(input, PackageRepository.class);
	}
	
	public static PackageRepository fromJson(String json) throws FileNotFoundException {
		return fromJson(json, PackageRepository.class);
	}
	
	public static Object getSerializer() {
		return new ExtendedClientEntitySerializer<PackageRepository>();
	}
}
