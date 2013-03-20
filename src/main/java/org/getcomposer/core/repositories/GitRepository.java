package org.getcomposer.core.repositories;

public class GitRepository extends VcsRepository implements Cloneable {

	public GitRepository() {
		super("git");
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public GitRepository clone() {
		GitRepository clone = new GitRepository();
		cloneProperties(clone);
		return clone;
	}
}
