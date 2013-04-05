package com.dubture.getcomposer.core.repositories;

public class VcsRepository extends Repository implements Cloneable {

	public VcsRepository() {
		super("vcs");
	}
	
	public VcsRepository(String type) {
		super(type);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public VcsRepository clone() {
		VcsRepository clone = new VcsRepository();
		cloneProperties(clone);
		return clone;
	}
}
