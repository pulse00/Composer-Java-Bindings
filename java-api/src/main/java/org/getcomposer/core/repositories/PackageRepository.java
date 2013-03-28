package org.getcomposer.core.repositories;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;

import org.getcomposer.core.RepositoryPackage;
import org.getcomposer.core.annotation.Name;
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
		listen();
	}
	
	public PackageRepository(String json) {
		super("package");
		fromJson(json);
		listen();
	}
	
	public PackageRepository(File file) throws IOException {
		super("package");
		fromJson(file);
		listen();
	}
	
	public PackageRepository(Reader reader) throws IOException {
		super("package");
		fromJson(reader);
		listen();
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
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public PackageRepository clone() {
		PackageRepository clone = new PackageRepository();
		cloneProperties(clone);
		return clone;
	}
}
