package org.getcomposer.test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.getcomposer.ComposerPackage;
import org.getcomposer.Person;

import junit.framework.TestCase;

public class ComposertTestCase extends TestCase {
	protected File loadFile(String name) throws URISyntaxException {

		ClassLoader loader = getClass().getClassLoader();
		URL resource = loader.getResource(name);
		return new File(resource.toURI());
	}
	
	protected ComposerPackage createDummyPackage() {
		ComposerPackage phpPackage = new ComposerPackage();
		
		phpPackage.setName("gossi/test");
		
		Person author = new Person();
		author.setName("tester");
		
		phpPackage.addAuthor(author);
		
		return phpPackage;
	}
}
