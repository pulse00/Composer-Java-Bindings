package org.getcomposer.core.test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import org.getcomposer.core.ComposerPackage;
import org.getcomposer.core.collection.Psr0;
import org.getcomposer.core.objects.Autoload;
import org.getcomposer.core.objects.Namespace;
import org.junit.Test;

import junit.framework.TestCase;

public class ListenerTest extends TestCase {

	private ComposerPackage composerPackage;
	private HashMap<String, Integer> listenerCounter;
	
	protected void setUp() {
		listenerCounter = new HashMap<String, Integer>();
		composerPackage = new ComposerPackage();
		composerPackage.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String prop = evt.getPropertyName();
				
				if (!listenerCounter.containsKey(prop)) {
					listenerCounter.put(prop, 0);
				}
				
				listenerCounter.put(prop, listenerCounter.get(prop) + 1);
				
				// debug output
//				System.out.println("Prop Change: " + evt.getPropertyName() + ", old Value: " + evt.getOldValue() + ", new Value: " + evt.getNewValue());
			}
		});
	}
	
	private int getCounter(String key) {
		if (!listenerCounter.containsKey(key)) {
			return 0;
		}
		
		return (int)listenerCounter.get(key);
	}
	
	@Test
	public void testLicense() {
		composerPackage.getLicense().add("MIT");

		assertEquals(1, getCounter("license"));
		
		composerPackage.getLicense().add("EPL");
		
		assertEquals(2, getCounter("license"));
	}
	
	@Test
	public void testKeywords() {
		composerPackage.getKeywords().add("foo");

		assertEquals(1, getCounter("keywords"));
		
		composerPackage.getKeywords().add("bar");
		
		assertEquals(2, getCounter("keywords"));
	}
	
	@Test
	public void testAutoload() {
		Autoload al = composerPackage.getAutoload();
		Psr0 psr = al.getPsr0();
		
		// psr
		Namespace ns1 = new Namespace();
		ns1.setNamespace("test");
		psr.add(ns1);
		
		assertEquals(1, getCounter("autoload"));
		
		ns1.add("new/path");
		
		assertEquals(2, getCounter("autoload"));
		
		// classmap
		al.getClassMap().add("file/to/path.php");
		
		assertEquals(3, getCounter("autoload"));
		
		// files
		al.getFiles().add("another/file/to/path.php");
		
		assertEquals(4, getCounter("autoload"));
	}
}
