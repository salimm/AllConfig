package test.me.salimm.allconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import me.salimm.allconfig.core.Config;
import me.salimm.allconfig.core.types.XMLConfig;

public class XMLTest {

	@Test
	public void parseFile() throws Exception {
		Config conf = new XMLConfig("samples/sample_conf.xml");
		assertNotNull(conf);
	}

	@Test
	public void parseInputStreamXML() throws Exception {
		Config conf = new XMLConfig(new FileInputStream("samples/sample_conf.xml"));
		assertNotNull(conf);
	}

	@Test
	public void getValueTest() throws FileNotFoundException, Exception {
		Config conf = new XMLConfig(new FileInputStream("samples/sample_conf.xml"));
		assertEquals(conf.getBoolean("conf.test.args.useSSL"), false);
		assertEquals(conf.getInteger("conf.test.TEST.X"), 1);
		assertEquals(conf.getInteger("conf.test.TEST.x"), 2);
		assertEquals(conf.getString("conf.test.DB_USER"), "TEST_USER");
	}

	@Test
	public void getValueTestDefault() throws FileNotFoundException, Exception {
		Config conf = new XMLConfig(new FileInputStream("samples/sample_conf.xml"));
		assertEquals(conf.getBoolean("conf.test.args.useSSL",true), false);
		assertEquals(conf.getBoolean("conf.test.args.NOT_FOUND",true), true);
		assertEquals(conf.getInteger("conf.test.TEST.X",1), 1);
		assertEquals(conf.getInteger("conf.test.TEST.Y",3), 3);
	}

	@Test
	public void getMapTest() throws FileNotFoundException, Exception {
		Config conf = new XMLConfig(new FileInputStream("samples/sample_conf.xml"));
		assertEquals(conf.getBoolean("conf.test.args.useSSL",true), false);
	}
}
