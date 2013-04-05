package org.getcomposer.core.test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.dubture.getcomposer.core.ComposerPackage;
import com.dubture.getcomposer.core.collection.Psr0;
import com.dubture.getcomposer.core.objects.Namespace;

public class AutoloadTest extends ComposertTestCase {
	
	@Test
	public void testPsr0() throws IOException, URISyntaxException {
		
		ComposerPackage composerPackage = new ComposerPackage(loadFile("autoload.json"));
		composerPackage.getAutoload().getPsr0().clear();
		Namespace ns = new Namespace();
		ns.setNamespace("foo");
		ns.add("bar");
		composerPackage.getAutoload().getPsr0().add(ns);
		assertEquals(1, composerPackage.getAutoload().getPsr0().size());
		Namespace namespace = composerPackage.getAutoload().getPsr0().get("foo");
		assertNotNull(namespace);
		assertEquals("bar", namespace.getPaths().get(0));
	}
	
	@Test
	public void testPsr0Escaping() throws ParseException {
		
		Psr0 psr0 = new Psr0();
		Namespace ns = new Namespace("Symfony\\Component\\Yaml", "symfony/yaml");
		psr0.add(ns);
		
		String json = psr0.toJson();
		psr0 = new Psr0(json);
		assertEquals(1, psr0.getNamespaces().size());
		
	}
	
	@Test
	public void testFromString() throws ParseException {
		Psr0 psr0 = new Psr0("{ \"Foo\" : \"Bar\", \"What\" : \"Ever\"}");
		assertEquals(2, psr0.size());
		assertEquals("Foo", psr0.getFirst().getNamespace());
	}
	
	@Test
	public void testNamespaceEquals() {
		Namespace n1, n2;
		
		// positives
		n1 = new Namespace();
		n2 = n1;
		assertTrue(n1.equals(n2));
		
		n1.setNamespace("test");
		n2 = n1.clone();
		assertTrue(n1.equals(n2));
		
		n1 = new Namespace();
		n1.add("bla");
		n2 = n1.clone();
		assertTrue(n1.equals(n2));
		
		n1.add("boink");
		n2 = n1.clone();
		assertTrue(n1.equals(n2));
		
		// negatives
		n1 = new Namespace();
		n1.setNamespace("test");
		n2 = new Namespace();
		n2.setNamespace("wurst");
		assertFalse(n1.equals(n2));
		
		n1 = new Namespace();
		n1.add("bla");
		n2 = new Namespace();
		n2.add("boink");
		assertFalse(n1.equals(n2));
		
		n1 = new Namespace();
		n1.setNamespace("test");
		n2 = new Namespace();
		n2.add("boink");
		assertFalse(n1.equals(n2));
		
		n1 = new Namespace();
		n1.add("boink");
		n2 = new Namespace();
		n2.setNamespace("test");
		assertFalse(n1.equals(n2));
		
		n1 = new Namespace();
		n1.setNamespace("test");
		n1.add("bla");
		n2 = new Namespace();
		n2.setNamespace("test");
		n2.add("boink");
		assertFalse(n1.equals(n2));
	}
}
