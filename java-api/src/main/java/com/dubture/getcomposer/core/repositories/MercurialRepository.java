package com.dubture.getcomposer.core.repositories;

public class MercurialRepository extends VcsRepository implements Cloneable {

	public MercurialRepository() {
		super("hg");
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public MercurialRepository clone() {
		MercurialRepository clone = new MercurialRepository();
		cloneProperties(clone);
		return clone;
	}
}
