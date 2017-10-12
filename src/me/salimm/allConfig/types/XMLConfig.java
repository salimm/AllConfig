package me.salimm.allConfig.types;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import me.salimm.allConfig.Config;
import me.salimm.allConfig.records.ConfigEntry;
import me.salimm.allConfig.records.MapEntry;
import me.salimm.allConfig.records.ValueEntry;

public class XMLConfig extends Config {
	/**
	 * Hashmap including all key value pairs of properties in XML config file
	 * 
	 * @throws Exception
	 * @throws FileNotFoundException
	 */
	public XMLConfig(String address) throws FileNotFoundException, Exception {
		this(new FileInputStream(new File(address)));
	}

	public XMLConfig(InputStream in) throws Exception {
		init(in);
	}

	protected void init(InputStream in) throws Exception {
		// reading config file into hash map here
		// initialize hashmap
		// read xml file into hashmap

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(in);
		doc.getDocumentElement().normalize();

		this.map = processNode(doc);

		// NodeList childNodes = doc.getChildNodes();
		//
		// for (int n = 0; n < childNodes.getLength(); n++) {
		// Node node = childNodes.item(n);
		// processChildNodes(node);
		// }
	}

	private HashMap<String, ConfigEntry> processNode(Node node) {
		if (!(node instanceof Element) && !(node instanceof Document))
			return null;
		
		

		HashMap<String, ConfigEntry> map = new HashMap<String, ConfigEntry>();

		NodeList childNodes = node.getChildNodes();
		for (int n = 0; n < childNodes.getLength(); n++) {
			Node childNode = childNodes.item(n);

			String nodeName = childNode.getNodeName().trim().toLowerCase();

			if (!(childNode instanceof Element)) {
				continue;
			} else if (nodeName.equals("property")) {
				String name = ((Element) childNode).getElementsByTagName("name").item(0).getTextContent().trim()
						.toLowerCase();
				String value = ((Element) childNode).getElementsByTagName("value").item(0).getTextContent();
				// inserting into hash map
				map.put(name, new ValueEntry(value));
			} else {
				map.put(nodeName, new MapEntry(processNode(childNode)));
			}
		}

		return map;

	}

}
