package org.getcomposer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailedVersion {

	public final int BEGIN = 0;
	public final int END = 1;
	
	private List<DetailedVersion> versions = new ArrayList<DetailedVersion>();
	
	/**
	 * Passed version string
	 */
	private String version;
	
	private String constraint;
	private String stabilityModifier;
	private String major;
	private String minor;
	private String fix;
	private String build;
	private String stability;
	private String suffix;
	private int devPosition = BEGIN;
	
	public DetailedVersion() {
		
	}
	
	public DetailedVersion(String version) {
		parse(version);
	}
	
	private void parse(String version) {
		// reset
		versions.clear();
		constraint = null;
		stabilityModifier = null;
		major = null;
		minor = null;
		fix = null;
		build = null;
		stability = null;
		suffix = null;
		
		// start parsing
		if (version.matches(",")) {
			String parts[] = version.split(",");
			
			// lowest = this
			versions.add(this);
			parseVersion(parts[0]);
			
			// all higher ones
			for (int i = 1; i < parts.length; i++) {
				versions.add(new DetailedVersion(parts[i]));
			}
			
			// reset
			// TODO add listeners on them to reset on changes on the versions
			this.version = null;
		} else {
			parseVersion(version);
		}
	}
	
	private void parseVersion(String version) {
		this.version = version;
		String parts[];
		
		// constraint
		String constraintPattern = "^(<>|!=|>=?|<=?|==?)?(.+)";
		if (version.matches(constraintPattern)) {
			constraint = version.replaceAll(constraintPattern, "$1");
			version = version.replaceAll(constraintPattern, "$2");
		}
		
		// stability modifier
		if (version.matches(".+@.+")) {
			parts = version.split("@");
			version = parts[0];
			stabilityModifier = normalizeStability(parts[1]);
		}
		
		// dev version?
		if (version.startsWith("dev-")) {
			stability = ComposerConstants.DEV;
			version = version.substring(4);
		}
		
		parts = version.split("-");
		int len = parts.length;
		
		if (len > 0) {
			parseMain(parts[0]);
		}
		
		if (len > 1) {
			parseTail(parts[1]);
		}
		
		if (stability == null) {
			stability = ComposerConstants.STABLE;
		}
	}
	
	private void parseMain(String main) {
		String parts[] = main.split("\\.");
		int len = parts.length;
		
		if (len > 0) {
			major = parts[0];
		}
		
		if (len > 1) {
			minor = parts[1];
		}
		
		if (len > 2) {
			fix = parts[2];
		}
		
		if (len > 3) {
			build = parts[3];
		}
	}
	
	private void parseTail(String tail) {
		Pattern pattern = Pattern.compile("[._-]?(?:(stable|beta|b|RC|alpha|a|patch|pl|p)(?:[.-]?(\\d+))?)?([.-]?dev)?", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(tail);

		matcher.find();
		
		if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
			suffix = matcher.group(2);
		}

		// stability 
		if (stability == null) {
			// -dev present?
			if (matcher.group(3) != null && !matcher.group(3).isEmpty()) {
				stability = ComposerConstants.DEV;
			} 
			
			// stability
			else if (matcher.group(1) != null && !matcher.group(1).isEmpty()) {
				stability = normalizeStability(matcher.group(1));
			}
		}
	}
	
	private String normalizeStability(String stabi) {
		if (stabi.equalsIgnoreCase("dev")) {
    		return ComposerConstants.DEV;
    	}
		
		if (stabi.equalsIgnoreCase("beta") || stabi.equalsIgnoreCase("b")) {
    		return ComposerConstants.BETA;
    	}
    	
    	if (stabi.equalsIgnoreCase("alpha") || stabi.equalsIgnoreCase("a")) {
    		return ComposerConstants.ALPHA;
    	}
    	
    	if (stabi.equalsIgnoreCase("rc")) {
    		return ComposerConstants.RC;
    	}
    	
    	if (stabi.equalsIgnoreCase("stable")) {
    		return ComposerConstants.STABLE;
    	}
    	
    	return "";
	}
	
	private String build() {
		StringBuilder sb = new StringBuilder();
		
		if (constraint != null) {
			sb.append(constraint);
		}
		
		if (devPosition == BEGIN && stability == ComposerConstants.DEV) {
			sb.append("dev-");
		}
		
		sb.append(major);
		
		if (minor != null) {
			sb.append(".");
			sb.append(minor);
		}
		
		if (fix != null) {
			sb.append(".");
			sb.append(fix);
		}
		
		if (build != null) {
			sb.append(".");
			sb.append(build);
		}
		
		StringBuilder sx = new StringBuilder();
		
		if (stability != null 
				&& (stability == ComposerConstants.DEV ? devPosition != BEGIN : true)) {
			sx.append(stability);
		}

		if (suffix != null) {
			sx.append(suffix);
		}
		
		if (stabilityModifier != null) {
			sx.append("@");
			sx.append(stabilityModifier);
		}
		
		if (sx.length() > 0) {
			sb.append("-");
			sb.append(sx);
		}
		
		if (versions.size() > 0) {
			int i = 1;
			for (DetailedVersion v : versions) {
				sb.append(v.toString());
				if (i < versions.size()) {
					sb.append(",");
				}
				i++;
			}
		}
		
		return sb.toString();
	}
	
	public boolean hasRange() {
		return versions.size() > 0;
	}
	
	public DetailedVersion getLowest() {
		return this;
	}
	
	public DetailedVersion getHighest() {
		if (versions == null) {
			return null;
		}
		
		return versions.get(versions.size() - 1);
	}
	
	public DetailedVersion getVerison(int index) {
		if (versions.size() > index) {
			return versions.get(index);
		}
		return null;
	}
	
	public void add(DetailedVersion version) {
		versions.add(versions.size(), version);
	}
	
	public void add(int index, DetailedVersion version) {
		versions.add(index, version);
		reset();
	}
	
	private void reset() {
		version = "";
	}

	/**
	 * @return the version
	 */
	public String toString() {
		if (version == "") {
			version = build();
		}
		return version;
	}

	/**
	 * @return the constraint
	 */
	public String getConstraint() {
		return constraint;
	}

	/**
	 * @return the stabilityModifier
	 */
	public String getStabilityModifier() {
		return stabilityModifier;
	}

	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @return the minor
	 */
	public String getMinor() {
		return minor;
	}

	/**
	 * @return the fix
	 */
	public String getFix() {
		return fix;
	}

	/**
	 * @return the build
	 */
	public String getBuild() {
		return build;
	}

	/**
	 * @return the stability
	 */
	public String getStability() {
		return stability;
	}
	
	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
		versions.clear();
		parse(version);
	}

	/**
	 * @param constraint the constraint to set
	 */
	public void setConstraint(String constraint) {
		this.constraint = constraint;
		reset();
	}

	/**
	 * @param stabilityModifier the stabilityModifier to set
	 */
	public void setStabilityModifier(String stabilityModifier) {
		this.stabilityModifier = stabilityModifier;
		reset();
	}

	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
		reset();
	}

	/**
	 * @param minor the minor to set
	 */
	public void setMinor(String minor) {
		this.minor = minor;
		reset();
	}

	/**
	 * @param fix the fix to set
	 */
	public void setFix(String fix) {
		this.fix = fix;
		reset();
	}

	/**
	 * @param build the build to set
	 */
	public void setBuild(String build) {
		this.build = build;
		reset();
	}

	/**
	 * @param stability the stability to set
	 */
	public void setStability(String stability) {
		this.stability = stability;
		reset();
	}
	
	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
		reset();
	}

	public int getDevPosition() {
		return devPosition;
	}

	public void setDevPosition(int devPosition) {
		this.devPosition = devPosition;
		reset();
	}
}
