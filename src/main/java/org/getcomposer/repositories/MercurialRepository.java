package org.getcomposer.repositories;

import java.io.File;
import java.io.FileNotFoundException;

import org.getcomposer.internal.serialization.ClientEntitySerializer;

public class MercurialRepository extends VcsRepository {

	public MercurialRepository() {
		super("hg");
	}
	
	public static MercurialRepository fromFile(File input) throws FileNotFoundException {
		return fromFile(input, MercurialRepository.class);
	}
	
	public static MercurialRepository fromJson(String json) throws FileNotFoundException {
		return fromJson(json, MercurialRepository.class);
	}

	public static Object getSerializer() {
		return new ClientEntitySerializer<MercurialRepository>();
	}
}
