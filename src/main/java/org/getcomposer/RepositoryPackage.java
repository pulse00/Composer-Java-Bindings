package org.getcomposer;

import java.io.File;
import java.io.IOException;

import org.getcomposer.collection.Persons;
import org.getcomposer.collection.Versions;
import org.getcomposer.packagist.PackagistDownloader;
import org.getcomposer.repositories.PackageRepository;
import org.getcomposer.serialization.ExtendedClientEntitySerializer;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class RepositoryPackage extends AbstractPackage {
	
	private Versions versions = new Versions();
	private Persons maintainers = new Persons();
	
	public RepositoryPackage() {
		super();
	}
	
	public RepositoryPackage(Object json) {
		super();
		parse(json);
	}
	
	public RepositoryPackage(String json) {
		super();
		parse(JSONValue.parse(json));
	}
	
	public RepositoryPackage(File file) throws IOException {
		super();
		load(file);
	}
	
	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {

			JSONObject json = (JSONObject)obj;

			parseValue(json, "repository");
			
			if (json.containsKey("versions")) {
				versions.load(json.get("versions"));
			}
			
			if (json.containsKey("maintainers")) {
				maintainers.load(json.get("maintainers"));
			}

		}
		
		super.parse(obj);
	}
	
	/**
	 * Deserializes packages from packagist.org, e.g.
	 * http://packagist.org/packages/react/react.json
	 * 
	 * @param input
	 * @return the deserialized package
	 * @throws IOException 
	 */
	public static RepositoryPackage fromPackageRepository(File input) throws IOException {
		PackageRepository repo = new PackageRepository(input);
		return repo.getPackage();
	}
	
//	/**
//	 * Deserializes packages from packagist.org, e.g.
//	 * http://packagist.org/packages/react/react.json
//	 * 
//	 * @param input
//	 * @return the deserialized package
//	 * @throws FileNotFoundException
//	 */
//	public static RepositoryPackage fromPackageRepository(Reader input)
//			throws FileNotFoundException {
//		
//		PackageRepository repo = new PackageRepository(input);
//		return repo.getPackage();
//	}
	
	/**
	 * Deserializes packages from packagist.org, e.g.
	 * http://packagist.org/packages/Symfony/Router.json
	 * 
	 * @param name the package name, such as Symfony/Router
	 * @return the deserialized package
	 * @throws IOException
	 */
	public static RepositoryPackage fromPackagist(String name) throws IOException {
		
		PackagistDownloader downloader = new PackagistDownloader(name);
		return downloader.getPackage();
	}
	
	
	/**
	 * Returns the versions
	 * 
	 * @return the versions
	 */
	public Versions getVersions() {
		return versions;
	}

	/**
	 *
	 * Returns the package name suitable for passing it to
	 * "composer.phar require"
	 *
	 * @param version
	 * @return String the package/version combination
	 * @throws Exception
	 */
	public String getPackageName(String version) throws Exception {
		if (!this.versions.has(version)) {
			throw new Exception("Invalid version " + version + " for package "
					+ getName());
		}

		return String.format("%s:%s", getName(), version);
	}
	
	/**
	 * Returns the maintainers
	 * 
	 * @return the maintainers
	 */
	public Persons getMaintainers() {
		return maintainers;
	}

	/**
	 * Returns the repository url
	 * 
	 * @return the repository
	 */
	public String getRepository() {
		return getAsString("repository");
	}

	/**
	 * @param repository the repository to set
	 */
	public void setRepository(String repository) {
		set("repository", repository);
	}
	
	
	public static Object getSerializer() {
		return new ExtendedClientEntitySerializer<RepositoryPackage>();
	}
}
