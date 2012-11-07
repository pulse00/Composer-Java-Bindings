package org.getcomposer;

import java.io.File;
import java.io.FileNotFoundException;

import org.getcomposer.collection.Persons;
import org.getcomposer.collection.Versions;
import org.getcomposer.entities.Person;
import org.getcomposer.internal.serialization.ClientEntitySerializer;
import org.getcomposer.repositories.PackageRepository;

public class RepositoryPackage extends AbstractPackage {
	
	private Versions versions = new Versions();
	private Persons maintainers = new Persons();
	
	public RepositoryPackage() {
		super();
	}
	
	/**
	 * Deserializes packages from packagist.org, e.g.
	 * http://packagist.org/packages/react/react.json
	 * 
	 * @param input
	 * @return the deserialized package
	 * @throws FileNotFoundException
	 */
	public static RepositoryPackage fromPackageRepository(File input)
			throws FileNotFoundException {
		
		PackageRepository repo = PackageRepository.fromFile(input);
		return repo.getPackage();
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
	 * Adds an maintainer
	 * 
	 * @param maintainer
	 * @return this
	 */
	public RepositoryPackage addMaintainer(Person maintainer) {
		maintainers.add(maintainer);
		return this;
	}
	
	/**
	 * Removes an maintainer
	 * 
	 * @param maintainer
	 * @return this
	 */
	public RepositoryPackage removeMaintainer(Person maintainer) {
		maintainers.remove(maintainer);
		return this;
	}

	/**
	 * Sets the maintainers
	 * 
	 * @param maintainers
	 * @return this
	 */
	public RepositoryPackage setMaintainers(Persons maintainers) {
		firePropertyChange("maintainers", this.maintainers, this.maintainers = maintainers);
		return this;
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
	public RepositoryPackage setRepository(String repository) {
		set("repository", repository);
		return this;
	}
	
	
	public static Object getSerializer() {
		return new ClientEntitySerializer<RepositoryPackage>();
	}
}
