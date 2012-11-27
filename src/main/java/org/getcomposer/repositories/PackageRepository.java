package org.getcomposer.repositories;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;

import org.getcomposer.RepositoryPackage;
import org.getcomposer.annotation.Name;
import org.json.simple.JSONObject;

public class PackageRepository extends Repository {

	@Name("package")
	private RepositoryPackage repositoryPackage = new RepositoryPackage();
	
	public PackageRepository() {
		super("package");
		listen();
	}
	
	
	public PackageRepository(Object json) {
		super("package");
		fromJson(json);
	}
	
	public PackageRepository(String json) {
		super("package");
		fromJson(json);
	}
	
	public PackageRepository(File file) throws IOException {
		super("package");
		fromJson(file);
	}
	
	public PackageRepository(Reader reader) throws IOException {
		super("package");
		fromJson(reader);
	}
	
	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {
			JSONObject json = (JSONObject) obj;
			
			if (json.containsKey("package")) {
				repositoryPackage.fromJson(json.get("package"));
			}
		}
		
		super.parse(obj);
	}
	
	@Override
	public Object prepareJson(LinkedList<String> fields) {
		String[] order = new String[]{"package"};
		return super.prepareJson(new LinkedList<String>(Arrays.asList(order)));
	}
	
	public RepositoryPackage getPackage() {
		return repositoryPackage;
	}
}
