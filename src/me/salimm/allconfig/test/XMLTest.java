package me.salimm.allconfig.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import me.salimm.allconfig.core.Config;
import me.salimm.allconfig.core.types.XMLConfig;

public class XMLTest {

	@Test
	public void parseFile() throws Exception {
		Config conf = new XMLConfig("samples/sample_conf.xml");
	}

	@Test
	public void parseInputStreamXML() throws Exception {
		Config conf = new XMLConfig(new FileInputStream("samples/sample_conf.xml"));
	}

	@Test
	public void getValueTest() throws FileNotFoundException, Exception {
		Config conf = new XMLConfig(new FileInputStream("samples/sample_conf.xml"));
		conf.getBoolean("CONF.TEST.VAL_BOOLEAN");
		conf.getValue("CONF.TEST.VAL_STRING");
		conf.getInteger("CONF.TEST.VAL_INT");
		conf.getDouble("conf.test.VAL_DOUBLE");
		conf.getLong("conf.test.VAL_LONG");
	}

	@Test
	public void getValueTestDefault() throws FileNotFoundException, Exception {
		Config conf = new XMLConfig(new FileInputStream("samples/sample_conf.xml"));
		Assert.assertEquals(conf.getBoolean("CONF.TEST.X", false), false);
		Assert.assertEquals(conf.getValue("CONF.TEST.X", "test"), "test");
		Assert.assertEquals(conf.getInteger("CONF.TEST.X", 1), 1);
		Assert.assertEquals(conf.getDouble("conf.test.X", 1.2), ((double) 1.2), 0.0000001);
		Assert.assertEquals(conf.getLong("conf.test.X", 2), (long) 2);
	}
	
	@Test
	public void getMapTest() throws FileNotFoundException, Exception {
		Config conf = new XMLConfig(new FileInputStream("samples/sample_conf.xml"));
		
		Assert.assertEquals(1,conf.getMap("conf").size());
		Assert.assertEquals(11,conf.getMap("conf.test").size());
	}
}
