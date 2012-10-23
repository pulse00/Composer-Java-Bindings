package org.getcomposer;

import java.util.List;
import java.util.Map;

public interface PackageInterface {

	/**
	 * Returns the first available version by default. TODO: check the specs
	 * about how to resolve this ...
	 * 
	 * @return String the default version
	 */
	public abstract String getDefaultVersion();

	/**
	 * 
	 * Returns the package name suitable for passing it to
	 * "composer.phar require"
	 * 
	 * @param version
	 * @return String the package/version combination
	 * @throws Exception
	 */
	public abstract String getPackageName(String version) throws Exception;

	public abstract String getName();

	public abstract String getType();

	public abstract String getDescription();

	public abstract String getHomepage();

	public abstract String getUrl();
	
	public abstract String getMinimumStability();

	public abstract String getFullPath();

	public abstract Map<String, String> getRequire();

	public abstract Map<String, String> getRequireDev();

	public abstract Autoload getAutoload();

	public abstract String getTargetDir();

	public abstract String getVersion();

	public abstract License getLicense();

	public abstract String[] getKeywords();

	public abstract Versions getVersions();

	public abstract List<Person> getAuthors();
	
	public abstract SupportInterface getSupport();

}