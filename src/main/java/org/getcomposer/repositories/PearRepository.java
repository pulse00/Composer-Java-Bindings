package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;

import org.getcomposer.serialization.ExtendedClientEntitySerializer;


public class PearRepository extends Repository {

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

	public static PearRepository fromFile(File input) throws FileNotFoundException {
		return fromFile(input, PearRepository.class);
	}
	
	public static PearRepository fromJson(String json) throws FileNotFoundException {
		return fromJson(json, PearRepository.class);
	}
	
	public static Object getSerializer() {
		return new ExtendedClientEntitySerializer<PearRepository>();
	}
}
