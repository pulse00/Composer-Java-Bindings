package org.getcomposer.repositories;

import java.lang.reflect.Type;

public class RepositoryFactory {

	public static Repository create(String type) {
		if (type == "composer") {
			return new ComposerRepository();
		} else if (type == "vcs") {
			return new VcsRepository();
		} else if (type == "git") {
			return new GitRepository();
		} else if (type == "svn") {
			return new SubversionRepository();
		} else if (type == "hg") {
			return new MercurialRepository();
		} else if (type == "package") {
			return new PackageRepository();
		} else if (type == "pear") {
			return new PearRepository();
		}
		
		return null;
	}
	
	public static Type getType(String type) {
		if (type.equals("composer")) {
			return ComposerRepository.class;
		} else if (type.equals("vcs")) {
			return VcsRepository.class;
		} else if (type.equals("git")) {
			return GitRepository.class;
		} else if (type.equals("svn")) {
			return SubversionRepository.class;
		} else if (type.equals("hg")) {
			return MercurialRepository.class;
		} else if (type.equals("package")) {
			return PackageRepository.class;
		} else if (type.equals("pear")) {
			return PearRepository.class;
		}
		
		return null;
	}
}
