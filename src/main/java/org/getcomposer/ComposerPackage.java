/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;

import org.getcomposer.repositories.PackageRepository;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a composer package. The source can either be a composer.json file
 * or a json response from packagist.org.
 * 
 * See {@link #fromJson} / {@link #fromPackagist} for details.
 * 
 * @see http://getcomposer.org
 * @author Robert Gruendler <r.gruendler@gmail.com>
 * @author Thomas Gossmann <gos.si>
 * 
 */
public class ComposerPackage extends IOPackage {

	private String name;
	private String type;
	private String description;
	private String homepage;
	private String minimumStability = ComposerConstants.STABILITIES[0];
	private Dependencies require = new Dependencies();

	@SerializedName("require-dev")
	private Dependencies requireDev = new Dependencies();
	private Autoload autoload;
	
	@SerializedName("target-dir")
	private String targetDir;
	private String version;
	private String versionNormalized;
	private Support support = new Support();
	private License license = new License();
	private String[] keywords;
	private Versions versions = new Versions();
	private Persons authors = new Persons();
	private Persons maintainers = new Persons();

	public ComposerPackage() {
		String[] listener = new String[] {
				"require", 
				"requireDev", 
				"versions", 
				"authors", 
				"maintainers"};

		for (String prop : listener) {
			listen(prop);
		}
	}
	
	private void listen(final String prop) {
		try {
			ObservableModel obj = (ObservableModel)this.getClass().getDeclaredField(prop).get(this);
			obj.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent e) {
					firePropertyChange(prop, e.getOldValue(), e.getNewValue());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return name;
	}

	/**
	 * Deserializes a package from a composer.json file
	 * 
	 * @param input
	 * @return the deserialized package
	 * @throws FileNotFoundException
	 */
	public static ComposerPackage fromFile(File input) throws FileNotFoundException {
		return fromFile(input, ComposerPackage.class);
	}
	
	/**
	 * Deserializes a package from a string
	 * 
	 * @param input
	 * @return the deserialized package
	 */
	public static ComposerPackage fromJson(String json) {
		return fromJson(json, ComposerPackage.class);
	}

	/**
	 * Deserializes a package from packagist.org, e.g.
	 * http://packagist.org/packages/react/react.json
	 * 
	 * @param input
	 * @return the deserialized package
	 * @throws FileNotFoundException
	 */
	public static ComposerPackage fromPackagist(File input)
			throws FileNotFoundException {
		
		PackageRepository repo = PackageRepository.fromFile(input);
		return repo.getPackage();
	}
	
	/**
	 * Serializes the package to json
	 * 
	 * @return the serialized json package
	 */
	public String toJson() {
		Gson gson = getBuilder();
		return gson.toJson(this, ComposerPackage.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.getcomposer.Versions#getDefaultVersion()
	 */
	public String getDefaultVersion() {
		return versions.getDefaultVersion();
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
					+ name);
		}

		return String.format("%s:%s", name, version);
	}


	/**
	 * Returns the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name
	 * 
	 * @param name the name to set
	 * @return this
	 */
	public ComposerPackage setName(String name) {
		firePropertyChange("name", this.name, this.name = name);
		return this;
	}


	/**
	 * Returns the type
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type
	 * 
	 * @param type the type to set
	 * @return this
	 */
	public ComposerPackage setType(String type) {
		firePropertyChange("type", this.type, this.type = type);
		return this;
	}

	
	/**
	 * Returns the description
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description
	 * 
	 * @param description the description to set
	 * @return this
	 */
	public ComposerPackage setDescription(String description) {
		firePropertyChange("description", this.description, this.description = description);
		return this;
	}

	/**
	 * Returns the homepage
	 * 
	 * @return the homepage
	 */
	public String getHomepage() {
		return homepage;
	}

	/**
	 * Sets the homepage
	 * 
	 * @param homepage the homepage to set
	 * @return this
	 */
	public ComposerPackage setHomepage(String homepage) {
		firePropertyChange("homepage", this.homepage, this.homepage = homepage);
		return this;
	}

	/**
	 * Returns the require dependencies
	 * 
	 * @return
	 */
	public Dependencies getRequire() {
		return require;
	}
	
	/**
	 * Adds a dependency to the require section
	 * 
	 * @param dependency
	 * @return this
	 */
	public ComposerPackage addRequire(Dependency dependency) {
		require.add(dependency);
		return this;
	}
	
	/**
	 * Removes a dependency from the require section
	 * 
	 * @param dependency
	 * @return this
	 */
	public ComposerPackage removeRequire(Dependency dependency) {
		require.remove(dependency);
		return this;
	}

	/**
	 * Returns the require-dev dependencies
	 * @return
	 */
	public Dependencies getRequireDev() {
		return requireDev;
	}
	
	/**
	 * Adds a dependency to the require-dev section
	 * 
	 * @param dependency
	 * @return this
	 */
	public ComposerPackage addRequireDev(Dependency dependency) {
		requireDev.add(dependency);
		return this;
	}
	
	/**
	 * Removes a dependency from the require-dev section
	 * 
	 * @param dependency
	 * @return this
	 */
	public ComposerPackage removeRequireDev(Dependency dependency) {
		requireDev.remove(dependency);
		return this;
	}


	/**
	 * Returns the autoload section
	 * 
	 * @return the autoload section
	 */
	public Autoload getAutoload() {
		return autoload;
	}

	/**
	 * Returns the target-dir
	 * 
	 * @return the target-dir
	 */
	public String getTargetDir() {
		return targetDir;
	}
	
	/**
	 * Sets the target-dir
	 * 
	 * @param targetDir the target-dir to set
	 * @return this
	 */
	public ComposerPackage setTargetDir(String targetDir) {
		firePropertyChange("targetDir", this.targetDir, this.targetDir = targetDir);
		return this;
	}

	/**
	 * Returns the version
	 * 
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Sets the version
	 * 
	 * @return this
	 */
	public ComposerPackage setVersion(String version) {
		firePropertyChange("version", this.version, this.version = version);
		return this;
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
	 * Returns the normalized version
	 * 
	 * @return the normalized version
	 */
	public String getVersionNormalized() {
		return versionNormalized;
	}

	/**
	 * Returns the license section
	 * 
	 * @return the license section
	 */
	public License getLicense() {
		return license;
	}

	/**
	 * Returns the keywords 
	 *
	 * @return the keywords
	 */
	public String[] getKeywords() {
		return keywords;
	}
	
	
	/**
	 * Sets the keywords
	 * 
	 * @param keywords the keywords to set
	 * @return this
	 */
	public ComposerPackage setKeywords(String[] keywords) {
		firePropertyChange("keywords", this.keywords, this.keywords = keywords);
		return this;
	}


	/**
	 * Returns the minimum-stability property
	 * 
	 * @return the minimum-stability
	 */
	public String getMinimumStability() {
		return minimumStability;
	}
	

	/**
	 * Sets the minimum-stability property
	 * 
	 * @param minimumStability the minimum-stability to set
	 * @return this
	 */
	public ComposerPackage setMinimumStability(String minimumStability) {
		firePropertyChange("minimumStability", this.minimumStability, this.minimumStability = minimumStability);
		return this;
	}
	
	/**
	 * Returns the authors
	 * 
	 * @return the authors
	 */
	public Persons getAuthors() {
		return authors;
	}
	
	/**
	 * Adds an author
	 * 
	 * @param author
	 * @return this
	 */
	public ComposerPackage addAuthor(Person author) {
		authors.add(author);
		return this;
	}
	
	/**
	 * Removes an author
	 * 
	 * @param author
	 * @return this
	 */
	public ComposerPackage removeAuthor(Person author) {
		authors.remove(author);
		return this;
	}

	/**
	 * Sets the authors
	 * 
	 * @param authors
	 * @return this
	 */
	public ComposerPackage setAuthors(Persons authors) {
		firePropertyChange("authors", this.authors, this.authors = authors);
		return this;
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
	public ComposerPackage addMaintainer(Person maintainer) {
		maintainers.add(maintainer);
		return this;
	}
	
	/**
	 * Removes an maintainer
	 * 
	 * @param maintainer
	 * @return this
	 */
	public ComposerPackage removeMaintainer(Person maintainer) {
		maintainers.remove(maintainer);
		return this;
	}

	/**
	 * Sets the maintainers
	 * 
	 * @param maintainers
	 * @return this
	 */
	public ComposerPackage setMaintainers(Persons maintainers) {
		firePropertyChange("maintainers", this.maintainers, this.maintainers = maintainers);
		return this;
	}
	

	/**
	 * Sets the support section
	 * 
	 * @param support the support to set
	 * @return this
	 */
	public ComposerPackage setSupport(Support support) {
		firePropertyChange("support", this.support, this.support = support);
		return this;
	}

	/**
	 * Returns the support section
	 * 
	 * @return the support section
	 */
	public Support getSupport() {
		return support;
	}

}
