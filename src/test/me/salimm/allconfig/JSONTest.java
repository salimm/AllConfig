package test.me.salimm.allconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import me.salimm.allconfig.core.Config;
import me.salimm.allconfig.core.types.JSONConfig;

public class JSONTest {

	@Test
	public void parseFile() throws Exception {
		Config conf = new JSONConfig("samples/sample_conf.json");
		assertNotNull(conf);
	}

	@Test
	public void parseInputStreamJSON() throws Exception {
		Config conf = new JSONConfig(new FileInputStream("samples/sample_conf.json"));
		assertNotNull(conf);
	}

	@Test
	public void getValueTest() throws FileNotFoundException, Exception {
		Config conf = new JSONConfig(new FileInputStream("samples/sample_conf.json"));
		assertEquals(false, conf.getBoolean("conf.test.args.useSSL"));
		assertEquals(1,conf.getInteger("conf.test.TEST.X"));
		assertEquals(2,conf.getInteger("conf.test.TEST.x"));
		assertEquals("TEST_USER", conf.getString("conf.test.DB_USER"));
	}

	@Test
	public void getValueTestDefault() throws FileNotFoundException, Exception {
		Config conf = new JSONConfig(new FileInputStream("samples/sample_conf.json"));
		assertEquals(false, conf.getBoolean("conf.test.args.useSSL", true));
		assertEquals(conf.getBoolean("conf.test.args.NOT_FOUND", true), true);
		assertEquals(conf.getInteger("conf.test.TEST.X", 1), 1);
		assertEquals(conf.getInteger("conf.test.TEST.Y", 3), 3);
	}

	@Test
	public void getMapTest() throws FileNotFoundException, Exception {
		Config conf = new JSONConfig(new FileInputStream("samples/sample_conf.json"));
		assertEquals(conf.getBoolean("conf.test.args.useSSL", true), false);
	}
}
