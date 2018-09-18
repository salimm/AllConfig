package me.salimm.allconfig.core.types;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import me.salimm.allconfig.core.Config;
import me.salimm.allconfig.core.records.ConfigEntry;
import me.salimm.allconfig.core.records.ValueEntry;

public class JSONConfig extends Config {
	/**
	 * Hashmap including all key value pairs of properties in XML config file
	 * 
	 * @throws Exception
	 * @throws FileNotFoundException
	 */
	public JSONConfig(String address) throws FileNotFoundException, Exception {
		this(new FileInputStream(new File(address)));
	}

	public JSONConfig(InputStream in) throws Exception {
		init(in);
	}

	protected void init(InputStream in) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(in);
		Config root = (Config) processNode(node, null);
		for (ConfigEntry entry : root.children()) {
			this.map.put(entry.getName(), entry);
		}
		

	}

	private ConfigEntry processNode(JsonNode root, String name) {
		// if(!((node instanceof Element) || (node instanceof Document)))
		// return null;

		if (hasChildren(root)) {
			Config conf = new Config(name);

			Entry<String, JsonNode> child = null;
			for (Iterator<Entry<String, JsonNode>> childNodes = root.fields(); childNodes.hasNext();) {
				child = childNodes.next();
				ConfigEntry childEntry = processNode(child.getValue(), child.getKey());
				if (childEntry == null) {
					continue;
				}
				conf.add(childEntry);
			}
			return conf;
		} else {
			 return new ValueEntry(name, root.asText());
		}
	}

	private boolean hasChildren(JsonNode node) {
		return node.isObject() && (((ObjectNode) node).size()) > 0;
	}

}
