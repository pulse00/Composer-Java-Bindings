package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;

import org.getcomposer.internal.serialization.ExtendedClientEntitySerializer;

import com.google.gson.annotations.SerializedName;

public class PearRepository extends Repository {

	@SerializedName("vendor-alias")
	private String vendorAlias;
	
	public PearRepository() {
		super("pear");
	}
	
	/**
	 * Returns the vendor-alias
	 * @return the vendor-alias
	 */
	public String getVendorAlias() {
		return vendorAlias;
	}

	/**
	 * Sets the vendor-alias
	 * 
	 * @param vendorAlias the vendor-alias to set
	 * @return this
	 */
	public PearRepository setVendorAlias(String vendorAlias) {
		this.vendorAlias = vendorAlias;
		return this;
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
