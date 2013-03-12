package org.getcomposer.core.repositories;

public class VcsRepository extends Repository {

	public VcsRepository() {
		super("vcs");
	}
	
	public VcsRepository(String type) {
		super(type);
	}
}
