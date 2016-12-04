package com.allConfig.conf;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.allConfig.errors.UnkownValueFormatException;

public class XMLConfig extends AbstractConfig {
	/**
	 * Hashmap including all key value pairs of properties in XML config file
	 */
	private HashMap<String, String> map;

	public XMLConfig(String address) throws Exception {
		super(address);
	}

	@Override
	protected void init(String address) throws Exception {
		// reading config file into hash map here
		// initialize hashmap
		map = new HashMap<String, String>();
		// read xml file into hashmap

		File fXmlFile = new File(address);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		// reading list of property nodes
		NodeList properties = doc.getElementsByTagName("property");

		// iterating over them
		for (int i = 0; i < properties.getLength(); i++) {
			// read each property
			Node prop = properties.item(i);
			String name = ((Element) prop).getElementsByTagName("name").item(0)
					.getTextContent();
			String value = ((Element) prop).getElementsByTagName("value")
					.item(0).getTextContent();

			// inserting into hash map
			getMap().put(name, value);
		}
	}

	@Override
	public String getValue(String key) {
		return getMap().get(key);
	}

	@Override
	public int getInteger(String key) {
		return Integer.parseInt(getValue(key));
	}

	@Override
	public double getDouble(String key) {
		return Double.parseDouble(getValue(key));
	}

	@Override
	public long getLong(String key) {
		return Long.parseLong(getValue(key));
	}

	public HashMap<String, String> getMap() {
		return map;
	}

	@Override
	public String getValue(String key, String def) {
		String val = getValue(key);
		if (val == null)
			return def;
		return val;
	}

	@Override
	public int getInteger(String key, int def) {
		int val = 0;
		try {
			val = getInteger(key);
		} catch (NumberFormatException e) {
			return def;
		}
		return val;
	}

	@Override
	public double getDouble(String key, double def) {
		double val = 0;
		try {
			val = getDouble(key);
		} catch (NumberFormatException e) {
			return def;
		}
		return val;
	}

	@Override
	public long getLong(String key, long def) {
		long val = 0;
		try {
			val = getLong(key);
		} catch (NumberFormatException e) {
			return def;
		}
		return val;
	}

	@Override
	public boolean getBoolean(String key, boolean def) {
		boolean val = false;
		try {
			val = getBoolean(key);
		} catch (Exception e) {
			e.printStackTrace();
			return def;
		}
		return val;
	}

	@Override
	public boolean getBoolean(String key) throws UnkownValueFormatException {
		if (key.equalsIgnoreCase("true") || key.equalsIgnoreCase("false")) {
			return Boolean.valueOf(getValue(key));
		} else {
			throw new UnkownValueFormatException();
		}
	}

}
