package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.getcomposer.RepositoryPackage;
import org.getcomposer.serialization.ExtendedClientEntitySerializer;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.annotations.SerializedName;

public class PackageRepository extends Repository {

	@SerializedName("package")
	private RepositoryPackage repositoryPackage = new RepositoryPackage();
	
	public PackageRepository() {
		super("package");
	}
	
	
	public PackageRepository(Object json) {
		super("package");
		parse(json);
	}
	
	public PackageRepository(String json) {
		super("package");
		parse(JSONValue.parse(json));
	}
	
	public PackageRepository(File file) throws IOException {
		super("package");
		load(file);
	}
	
	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {
			JSONObject json = (JSONObject) obj;
			
			if (json.containsKey("package")) {
				repositoryPackage.load(json.get("package"));
			}
		}
		
		super.parse(obj);
	}
	
	public RepositoryPackage getPackage() {
		return repositoryPackage;
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
