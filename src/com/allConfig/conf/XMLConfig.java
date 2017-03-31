package com.allConfig.conf;

import java.io.File;
import java.io.InputStream;
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
	public XMLConfig(InputStream in) throws Exception {
		super(in);
	}

	@Override
	protected void init(InputStream in) throws Exception {
		// reading config file into hash map here
		// initialize hashmap
		map = new HashMap<String, String>();
		// read xml file into hashmap

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(in);
		doc.getDocumentElement().normalize();

		NodeList childNodes = doc.getChildNodes();

		for (int n = 0; n < childNodes.getLength(); n++) {
			Node node = childNodes.item(n);
			processChildNodes(node, "");
		}
	}

	private void processChildNodes(Node node, String prefix) {
		if (!(node instanceof Element))
			return;
		if (node.getNodeName().toUpperCase().equals("PROPERTY")) {
			String name = ((Element) node).getElementsByTagName("name").item(0).getTextContent();
			String value = ((Element) node).getElementsByTagName("value").item(0).getTextContent();
			// inserting into hash map
			getMap().put(prepareKey(prefix, name), value);
		} else {
			NodeList childNodes = node.getChildNodes();
			for (int n = 0; n < childNodes.getLength(); n++) {
				Node child = childNodes.item(n);
				if (!(child instanceof Element))
					continue;
				String prefixTmp = "";
				if (prefix.length() == 0)
					prefixTmp = node.getNodeName();
				else
					prefixTmp = prepareKey(prefix, node.getNodeName());
				processChildNodes(child, prefixTmp);
			}
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
