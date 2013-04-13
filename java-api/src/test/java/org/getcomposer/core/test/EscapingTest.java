package org.getcomposer.core.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.dubture.getcomposer.core.ComposerPackage;

public class EscapingTest extends TestCase {

	@Test
	public void testScriptBackslashEscaping() {
		
		String json = "{\n" + 
				"	\"name\" : \"pulse00/ffmpeg-bundle\",\n" + 
				"	\"description\" : \"Symfony bundle to provide PHP-FFmpeg as a Symfony service (https://github.com/alchemy-fr/PHP-FFmpeg)\",\n" + 
				"	\"type\" : \"symfony-bundle\",\n" + 
				"	\"authors\" : [{\n" + 
				"			\"name\" : \"Robert Gruendler\",\n" + 
				"			\"email\" : \"r.gruendler@gmail.com\"\n" + 
				"		}\n" + 
				"	],\n" + 
				"	\"keywords\" : [\n" + 
				"		\"ffmpeg\",\n" + 
				"		\"multimedia\"\n" + 
				"	],\n" + 
				"	\"license\" : [\n" + 
				"		\"MIT\"\n" + 
				"	],\n" + 
				"	\"require\" : {\n" + 
				"		\"php-ffmpeg/php-ffmpeg\" : \"0.2.1\"\n" + 
				"	},\n" + 
				"	\"autoload\" : {\n" + 
				"		\"psr-0\" : {\n" + 
				"			\"Dubture\\\\FFmpegBundle\" : \"\"\n" + 
				"		}\n" + 
				"	},\n" + 
				"	\"target-dir\" : \"Dubture/FFmpegBundle\",\n" + 
				"	\"minimum-stability\" : \"dev\"\n" + 
				"}";
		
		ComposerPackage pkg = new ComposerPackage();
		pkg.fromJson(json);
		assertEquals(json, pkg.toJson());
	}
}
