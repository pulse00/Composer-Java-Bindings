package org.getcomposer.core.test;

import org.getcomposer.core.VersionedPackage;
import org.getcomposer.core.collection.Dependencies;
import org.junit.Test;

import junit.framework.TestCase;

public class DependenciesTest extends TestCase {

	@Test
	public Dependencies testAdd() {
		Dependencies deps = new Dependencies();
		
		VersionedPackage pkg = new VersionedPackage();
		pkg.setName("gossi/ldap");
		pkg.setVersion("1.2.3");
		
		deps.add(pkg);
		
		assertEquals(1, deps.size());
		
		return deps;
	}
	
	@Test
	public void testAddAll() {
		Dependencies deps1 = testAdd();
		Dependencies deps2 = new Dependencies();
		
		VersionedPackage pkg = new VersionedPackage();
		pkg.setName("phpunit/phpunit");
		pkg.setVersion("1.2.4");
		
		deps2.add(pkg);
		
		pkg = new VersionedPackage();
		pkg.setName("symfony/symfony");
		pkg.setVersion("2.2.2");
		
		deps2.add(pkg);
		
		assertEquals(2, deps2.size());
		
		deps1.addAll(deps2);
		
		assertEquals(3, deps1.size());
	}
}
