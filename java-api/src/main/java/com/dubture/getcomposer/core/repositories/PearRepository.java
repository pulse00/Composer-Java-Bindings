package com.dubture.getcomposer.core.repositories;

public class PearRepository extends Repository implements Cloneable {

	public PearRepository() {
		super("pear");
	}
	
	/**
	 * Returns the vendor-alias
	 * @return the vendor-alias
	 */
	public String getVendorAlias() {
		return getAsString("vendor-alias");
	}

	/**
	 * Sets the vendor-alias
	 * 
	 * @param vendorAlias the vendor-alias to set
	 */
	public void setVendorAlias(String vendorAlias) {
		set("vendor-alias", vendorAlias);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public PearRepository clone() {
		PearRepository clone = new PearRepository();
		cloneProperties(clone);
		return clone;
	}
}
