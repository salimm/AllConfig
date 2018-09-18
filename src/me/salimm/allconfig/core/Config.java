package me.salimm.allconfig.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;

import me.salimm.allconfig.core.errors.PrefixNotANestedConfigException;
import me.salimm.allconfig.core.errors.UnkownValueFormatException;
import me.salimm.allconfig.core.records.ConfigEntry;
import me.salimm.allconfig.core.records.ValueEntry;

/**
 * AbstractConfig includes trivial implementations of config reader. It can be
 * from a dtabase
 * 
 * @author salimm
 *
 */
public class Config implements ConfigEntry {

	protected HashMap<String, ConfigEntry> map;
	private String name;

	public Config(String name, HashMap<String, ConfigEntry> map) {
		this.name = name;
		this.map = map;
	}

	public Config(String name) {
		this(name, new HashMap<String, ConfigEntry>());
	}

	public Config() {
		this(null, new HashMap<String, ConfigEntry>());
	}

	public ConfigEntry get(String path) throws PrefixNotANestedConfigException {
		return get(path.trim().split("\\."));
	}

	public ConfigEntry get(String[] path) throws PrefixNotANestedConfigException {
		return get(path, 0);
	}

	protected ConfigEntry get(String[] path, int idx) throws PrefixNotANestedConfigException {
		ConfigEntry entry = map.get(path[idx]);

		if (entry == null) {
			throw new PrefixNotANestedConfigException();
		}

		if (idx == path.length - 1) {
			return entry;
		} else {
			if (entry instanceof Config) {
				return ((Config) entry).get(path, idx + 1);
			} else {
				throw new PrefixNotANestedConfigException();
			}
		}
	}
	
	public void add(ConfigEntry entry) {
		this.map.put(entry.getName(), entry);
	}

	public Config getConfig(String path) throws PrefixNotANestedConfigException {
		return (Config) get(path.trim().split("\\."), 0);
	}

	public String getString(String keyStr) throws PrefixNotANestedConfigException {
		ValueEntry entry = (ValueEntry) get(keyStr);
		return entry.getValue();
	}

	public int getInteger(String key) throws NumberFormatException, PrefixNotANestedConfigException {
		return Integer.parseInt(getString(key));
	}

	public double getDouble(String key) throws NumberFormatException, PrefixNotANestedConfigException {
		return Double.parseDouble(getString(key));
	}

	public long getLong(String key) throws NumberFormatException, PrefixNotANestedConfigException {
		return Long.parseLong(getString(key));
	}

	public String getString(String key, String def) throws PrefixNotANestedConfigException {
		try {
			String val = getString(key);
			if (val == null)
				return def;

			return val;
		} catch (Exception e) {
			return def;
		}
	}

	public int getInteger(String key, int def) {
		int val = 0;
		try {
			val = getInteger(key);
		} catch (Exception e) {
			return def;
		}
		return val;
	}

	public double getDouble(String key, double def) {
		double val = 0;
		try {
			val = getDouble(key);
		} catch (Exception e) {
			return def;
		}
		return val;
	}

	public long getLong(String key, long def) {
		long val = 0;
		try {
			val = getLong(key);
		} catch (Exception e) {
			return def;
		}
		return val;
	}

	public boolean getBoolean(String key, boolean def) {
		boolean val = false;
		try {
			val = getBoolean(key);
		} catch (Exception e) {
			return def;
		}
		return val;
	}

	public boolean getBoolean(String key) throws UnkownValueFormatException, PrefixNotANestedConfigException {
		String val = getString(key);
		if (val != null && (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false"))) {
			return Boolean.valueOf(val);
		} else {
			throw new UnkownValueFormatException();
		}
	}

	@Override
	public String toString() {
		return map.toString();
	}

	public String prepareKey(String prefix, String key) {
		return prefix.toLowerCase() + "." + key.toLowerCase();
	}

	public InputStream createInputStream() {
		String tmp = "";
		return new ByteArrayInputStream(tmp.getBytes());
	}

	public int size() {
		return map.size();
	}

	public Collection<ConfigEntry> children() {
		return map.values();
	}

	public Collection<String> childNames() {
		return map.keySet();
	}

	/**
	 * This is null it is the root Config
	 */
	public String getName() {
		return name;
	}

}
