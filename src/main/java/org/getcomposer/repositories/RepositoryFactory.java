package org.getcomposer.repositories;

import java.lang.reflect.Type;

public class RepositoryFactory {

	public static Repository create(String type) {
		if (type.equalsIgnoreCase("composer")) {
			return new ComposerRepository();
		} else if (type.equalsIgnoreCase("vcs")) {
			return new VcsRepository();
		} else if (type.equalsIgnoreCase("git")) {
			return new GitRepository();
		} else if (type.equalsIgnoreCase("svn")) {
			return new SubversionRepository();
		} else if (type.equalsIgnoreCase("hg")) {
			return new MercurialRepository();
		} else if (type.equalsIgnoreCase("package")) {
			return new PackageRepository();
		} else if (type.equalsIgnoreCase("pear")) {
			return new PearRepository();
		}
		
		return null;
	}
	
	public static Type getType(String type) {
		if (type.equalsIgnoreCase("composer")) {
			return ComposerRepository.class;
		} else if (type.equalsIgnoreCase("vcs")) {
			return VcsRepository.class;
		} else if (type.equalsIgnoreCase("git")) {
			return GitRepository.class;
		} else if (type.equalsIgnoreCase("svn")) {
			return SubversionRepository.class;
		} else if (type.equalsIgnoreCase("hg")) {
			return MercurialRepository.class;
		} else if (type.equalsIgnoreCase("package")) {
			return PackageRepository.class;
		} else if (type.equalsIgnoreCase("pear")) {
			return PearRepository.class;
		}
		
		return null;
	}
}
