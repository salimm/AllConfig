package me.salimm.allconfig.core;

public class ConfigKey {

	public static final String PATH_SEPARATOR = "\\.";

	/**
	 * creates a config key instance out of the full key string
	 * 
	 * @param key
	 * @return
	 */
	public static String[] splitKey(String key) {
		String[] parts = key.split(PATH_SEPARATOR);
		return parts;
	}
	
	public static String prepareKey(String prefix, String key) {
		return prefix + "." + key;
	}

}