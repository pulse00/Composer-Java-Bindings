package com.dubture.getcomposer.core.repositories;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.parser.ParseException;

import com.dubture.getcomposer.core.RepositoryPackage;
import com.dubture.getcomposer.core.annotation.Name;

public class PackageRepository extends Repository {

	@Name("package")
	private RepositoryPackage repositoryPackage = new RepositoryPackage();
	
	public PackageRepository() {
		super("package");
		listen();
	}
	
	public PackageRepository(Object json) {
		this();
		fromJson(json);
	}
	
	public PackageRepository(String json) throws ParseException {
		this();
		fromJson(json);
	}
	
	public PackageRepository(File file) throws IOException, ParseException {
		this();
		fromJson(file);
	}
	
	public PackageRepository(Reader reader) throws IOException, ParseException {
		this();
		fromJson(reader);
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
