package org.getcomposer.core.test;

import junit.framework.TestCase;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.dubture.getcomposer.core.ComposerPackage;

public class EscapingTest extends TestCase {

	@Test
	public void testScriptBackslashEscaping() {
		
		String json = "{\n" +"	\"autoload\" : {\n" + 
				"		\"psr-0\" : {\n" + 
				"			\"Dubture\\\\FFmpegBundle\" : \"\"\n" + 
				"		}\n" + 
				"	}\n"+ 
				"}";
		
		try {
			ComposerPackage pkg = new ComposerPackage(json);
			assertEquals(json, pkg.toJson());
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
		
	}
}
