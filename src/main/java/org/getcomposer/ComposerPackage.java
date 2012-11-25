/*******************************************************************************
 * Copyright (c) 2012 The PDT Extension Group (https://github.com/pdt-eg)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.getcomposer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.getcomposer.collection.Dependencies;
import org.getcomposer.collection.GenericArray;
import org.getcomposer.collection.License;
import org.getcomposer.collection.Persons;
import org.getcomposer.collection.Repositories;
import org.getcomposer.entities.Config;
import org.getcomposer.entities.Extra;
import org.getcomposer.entities.Support;
import org.getcomposer.serialization.ComposerPackageSerializer;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
public class ComposerPackage extends AbstractPackage {

	private Dependencies require = new Dependencies();
	private Repositories repositories = new Repositories();
	
//	private GenericArray bin = new GenericArray();

	@SerializedName("require-dev")
	private Dependencies requireDev = new Dependencies();

	private Support support = new Support();
	private License license = new License();
//	private GenericArray keywords = new GenericArray();
	
	private Persons authors = new Persons();
	
	private Extra extra = new Extra();
	private Config config = new Config();
	

	public ComposerPackage() {
		super();
	}
	
	public ComposerPackage(Object json) {
		super();
		parse(json);
	}
	
	public ComposerPackage(String json) {
		super();
		parse(JSONValue.parse(json));
	}
	
	public ComposerPackage(File file) throws IOException {
		super();
		load(file);
	}


	public String toString() {
		return getName();
	}
	
	protected void parse(Object obj) {
		if (obj instanceof JSONObject) {

			JSONObject json = (JSONObject)obj;

			parseValue(json, "bin");
			parseValue(json, "homepage");
			parseValue(json, "keywords");
			parseValue(json, "minimum-stability");
			parseValue(json, "target-dir");
			
			if (json.containsKey("require")) {
				require.load(json.get("require"));
			}
			
			if (json.containsKey("require-dev")) {
				requireDev.load(json.get("require-dev"));
			}
			
			if (json.containsKey("support")) {
				support.load(json.get("support"));
			}
			
			if (json.containsKey("license")) {
				license.load(json.get("license"));
			}
			
			if (json.containsKey("extra")) {
				extra.load(json.get("extra"));
			}
			
			if (json.containsKey("authors")) {
				authors.load(json.get("authors"));
			}
			
			if (json.containsKey("config")) {
				config.load(json.get("config"));
			}
			
			if (json.containsKey("repositories")) {
				repositories.load(json.get("repositories"));
			}
		}
		
		super.parse(obj);
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
	 * Serializes the package to json
	 * 
	 * @return the serialized json package
	 */
	public String toJson() {
		Gson gson = getBuilder();
		return gson.toJson(this, ComposerPackage.class);
	}

	/**
	 * Returns the homepage
	 * 
	 * @return the homepage
	 */
	public String getHomepage() {
		return getAsString("homepage");
	}

	/**
	 * Sets the homepage
	 * 
	 * @param homepage the homepage to set
	 */
	public void setHomepage(String homepage) {
		set("homepage", homepage);
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
	 * Returns the require-dev dependencies
	 * @return
	 */
	public Dependencies getRequireDev() {
		return requireDev;
	}

	/**
	 * Returns the target-dir
	 * 
	 * @return the target-dir
	 */
	public String getTargetDir() {
		return getAsString("target-dir");
	}
	
	/**
	 * Sets the target-dir
	 * 
	 * @param targetDir the target-dir to set
	 */
	public void setTargetDir(String targetDir) {
		set("target-dir", targetDir);
	}
	
	/**
	 * Returns the normalized version
	 * 
	 * @return the normalized version
	 */
	public String getVersionNormalized() {
		return getAsString("version_normalized");
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
	public GenericArray getKeywords() {
		return getAsArray("keywords");
	}

	/**
	 * Returns the minimum-stability property
	 * 
	 * @return the minimum-stability
	 */
	public String getMinimumStability() {
		String stabi = getAsString("minimum-stability");
		if (stabi == null) {
			return ComposerConstants.STABILITIES[0];
		} else {
			return stabi;
		}
	}
	

	/**
	 * Sets the minimum-stability property
	 * 
	 * @param minimumStability the minimum-stability to set
	 */
	public void setMinimumStability(String minimumStability) {
		set("minimum-stability", minimumStability);
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
	 * Returns the support section
	 * 
	 * @return the support section
	 */
	public Support getSupport() {
		return support;
	}

	/**
	 * @return the repositories
	 */
	public Repositories getRepositories() {
		return repositories;
	}

	/**
	 * Returns the extra entity
	 * 
	 * @return the extra
	 */
	public Extra getExtra() {
		return extra;
	}
	
	/**
	 * Returns the config entity
	 * 
	 * @return the config
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * Returns the <code>bin</code> collection.
	 * 
	 * @return the <code>bin</code> collection
	 */
	public GenericArray getBin() {
		return getAsArray("bin");
	}
	
	public static Object getSerializer() {
		return new ComposerPackageSerializer();
	}
}
