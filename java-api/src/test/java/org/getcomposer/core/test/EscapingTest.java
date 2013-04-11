package org.getcomposer.core.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.dubture.getcomposer.core.ComposerPackage;

public class EscapingTest extends TestCase {

	@Test
	public void testScriptBackslashEscaping() {
		
		String json = "{\n" + 
				"	\"keywords\" : [],\n" + 
				"	\"require\" : {\n" + 
				"		\"php\" : \">=5.4\"\n" + 
				"	},\n" + 
				"	\"autoload\" : {\n" + 
				"		\"psr-0\" : {\n" + 
				"			\"asdfasdfadsf\" : \"src\"\n" + 
				"		}\n" + 
				"	},\n" + 
				"	\"scripts\" : {\n" + 
				"		\"post-install-cmd\" : [\n" + 
				"			\"Sensio\\\\Bundle\\\\DistributionBundle\\\\Composer\\\\ScriptHandler::buildBootstrap\"\n" + 
				"		]\n" + 
				"	}\n" + 
				"}";
		
		ComposerPackage pkg = new ComposerPackage();
		pkg.fromJson(json);
		assertEquals(json, pkg.toJson());
	}
}
