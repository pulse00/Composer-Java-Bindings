package org.getcomposer.core.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AutoloadTest.class, DependenciesTest.class, JsonArrayTest.class, JsonObjectTest.class,
		JsonParserTest.class, JsonWriterTest.class, ListenerTest.class, PersonsTest.class,
		VersionTest.class, EscapingTest.class })

public class CoreTests {

}
