package me.salimm.allconfig.core.types;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import me.salimm.allconfig.core.Config;
import me.salimm.allconfig.core.records.ConfigEntry;
import me.salimm.allconfig.core.records.ValueEntry;

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
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(in);
		doc.getDocumentElement().normalize();
		ConfigEntry root = processNode(doc.getFirstChild());
		this.map.put(root.getName(), root);

	}

	private ConfigEntry processNode(Node node) {
		if(!((node instanceof Element) || (node instanceof Document)))
			return null;

		String nodeName = node.getNodeName().trim();

		if ( hasChildren(node)) {
			Config conf = new Config(nodeName);

			NodeList childNodes = node.getChildNodes();
			for (int n = 0; n < childNodes.getLength(); n++) {
				Node childNode = childNodes.item(n);
				ConfigEntry childEntry = processNode(childNode);
				if(childEntry==null) {
					continue;
				}
				conf.add(childEntry);
			}

			return conf;
		} else {
			return new ValueEntry(nodeName, node.getTextContent());
		}

	}

	private boolean hasChildren(Node node) {
		boolean hasChild = false;
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			hasChild |= ((node.getChildNodes().item(i) instanceof Element) || (node.getChildNodes().item(i) instanceof Document));
		}
		return  hasChild;
	}

}
