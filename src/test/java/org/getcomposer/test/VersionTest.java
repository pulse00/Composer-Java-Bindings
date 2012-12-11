package org.getcomposer.test;

import org.getcomposer.ComposerConstants;
import org.getcomposer.DetailedVersion;
import org.junit.Test;

import junit.framework.TestCase;

public class VersionTest extends TestCase {

	@Test
	public void testParser() {
		DetailedVersion v = new DetailedVersion("1.2.3");
		
		assertEquals("1", v.getMajor());
		assertEquals("2", v.getMinor());
		assertEquals("3", v.getFix());
		
		v.setVersion(">1.2.3-rc1@beta");
		
		assertEquals(">", v.getConstraint());
		assertEquals("1", v.getSuffix());
		assertEquals(ComposerConstants.BETA, v.getStabilityModifier());
		assertEquals(ComposerConstants.RC, v.getStability());
	}
	
	@Test
	public void testBuilder() {
		DetailedVersion v = new DetailedVersion();
		
		v.setMajor("1");
		v.setMinor("2");
		v.setFix("4");
		v.setBuild("5648");
		v.setConstraint(">");
		v.setStabilityModifier("beta");
		v.setStability(ComposerConstants.ALPHA);
		v.setSuffix("2");
		
		DetailedVersion t = new DetailedVersion(v.toString());
		
		System.out.println(v.toString());
		
		assertEquals(">", t.getConstraint());
		assertEquals("1", t.getMajor());
		assertEquals("2", t.getMinor());
		assertEquals("4", t.getFix());
		assertEquals("5648", t.getBuild());
		assertEquals("2", t.getSuffix());
		assertEquals(ComposerConstants.ALPHA, t.getStability());
		assertEquals(ComposerConstants.BETA, t.getStabilityModifier());
	}
}
