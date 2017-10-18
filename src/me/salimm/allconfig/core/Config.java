package me.salimm.allconfig.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

import me.salimm.allconfig.core.errors.PrefixNotANestedConfigException;
import me.salimm.allconfig.core.errors.UnkownValueFormatException;
import me.salimm.allconfig.core.records.ConfigEntry;
import me.salimm.allconfig.core.records.MapEntry;
import me.salimm.allconfig.core.records.ValueEntry;

/**
 * AbstractConfig includes trivial implementations of config reader. It can be
 * from a dtabase
 * 
 * @author salimm
 *
 */
public class Config {

	protected HashMap<String, ConfigEntry> map;

	public Config(HashMap<String, ConfigEntry> map) {
		this.map = map;
	}

	public Config() {
		this.map = new HashMap<String, ConfigEntry>();
	}

	public Config getMap(String prefix) throws PrefixNotANestedConfigException {
		return getMap(prefix.trim().toLowerCase().split("\\."), 0);
	}

	protected MapEntry getMap(String[] prefix, int idx) throws PrefixNotANestedConfigException {
		if (prefix == null)
			return new MapEntry(this.map);

		ConfigEntry entry = map.get(prefix[idx]);
		if (idx == prefix.length - 1) {
			if (entry instanceof MapEntry) {
				return ((MapEntry) entry);
			} else {
				throw new PrefixNotANestedConfigException();
			}
		} else {
			if (entry instanceof MapEntry) {
				return ((MapEntry) entry).getMap(prefix, idx + 1);
			} else {
				throw new PrefixNotANestedConfigException();
			}
		}
	}

	public Config getMap(ConfigKey key) throws PrefixNotANestedConfigException {
		return getMap(key.getPrefix(), 0);
	}

	public String getValue(String keyStr) throws PrefixNotANestedConfigException {
		ConfigKey key = ConfigKey.splitKey(keyStr.toLowerCase());
		if (key.getPrefix() != null) {
			Config conf = getMap(key);
			return conf.getValue(key.getValKey());
		} else {
			return ((ValueEntry) map.get(key.getValKey())).getValue();
		}
	}

	public int getInteger(String key) throws NumberFormatException, PrefixNotANestedConfigException {
		return Integer.parseInt(getValue(key));
	}

	public double getDouble(String key) throws NumberFormatException, PrefixNotANestedConfigException {
		return Double.parseDouble(getValue(key));
	}

	public long getLong(String key) throws NumberFormatException, PrefixNotANestedConfigException {
		return Long.parseLong(getValue(key));
	}

	public String getValue(String key, String def) throws PrefixNotANestedConfigException {
		try {
			String val = getValue(key);
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
		String val = getValue(key);
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

	public int size(){
		return map.size();
	}
	
}
