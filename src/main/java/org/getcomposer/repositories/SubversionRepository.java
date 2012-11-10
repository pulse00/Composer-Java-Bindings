package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;

import org.getcomposer.serialization.ClientEntitySerializer;

public class SubversionRepository extends VcsRepository {

	public SubversionRepository() {
		super("svn");
	}

	public String getTrunkPath() {
		return getAsString("trunk-path");
	}
	
	public SubversionRepository setTrunkPath(String path) {
		set("trunk-path", path);
		return this;
	}
	
	public String getBranchesPath() {
		return getAsString("branches-path");
	}
	
	public SubversionRepository setBranchesPath(String path) {
		set("branches-path", path);
		return this;
	}
	
	public String getTagsPath() {
		return getAsString("tags-path");
	}
	
	public SubversionRepository setTagsPath(String path) {
		set("tags-path", path);
		return this;
	}
	
	public static SubversionRepository fromFile(File input) throws FileNotFoundException {
		return fromFile(input, SubversionRepository.class);
	}
	
	public static SubversionRepository fromJson(String json) throws FileNotFoundException {
		return fromJson(json, SubversionRepository.class);
	}
	
	public static Object getSerializer() {
		return new ClientEntitySerializer<SubversionRepository>();
	}
}
