package me.salimm.allConfig;

import java.util.Arrays;


public class ConfigKey {

	/**
	 * contains the the prefix to the nested map
	 */
	private String[] prefix;

	/**
	 * final key to the value in nested map
	 */
	private String valKey;

	public ConfigKey(String[] prefix, String valKey) {
		this.prefix = prefix;
		this.valKey = valKey;
	}

	public String getValKey() {
		return valKey;
	}

	public void setValKey(String valKey) {
		this.valKey = valKey;
	}

	public String[] getPrefix() {
		return prefix;
	}

	public void setPrefix(String[] prefix) {
		this.prefix = prefix;
	}

	/**
	 * creates a config key instance out of the full key string
	 * 
	 * @param key
	 * @return
	 */
	public static ConfigKey splitKey(String key) {
		String[] parts = key.split("\\.");

		String[] prefix = null;
		String valKey = null;

		if (parts.length == 1) {
			valKey = key;
		} else {
			prefix = Arrays.copyOf(parts, parts.length - 1);
			valKey = parts[parts.length - 1];
		}

		return new ConfigKey(prefix, valKey);
	}

}