package com.allConfig.conf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.allConfig.errors.UnkownValueFormatException;

/**
 * AbstractConfig includes trivial implementations of config reader. It can be
 * from a dtabase
 * 
 * @author salimm
 *
 */
public abstract class AbstractConfig {

	public AbstractConfig(String address) throws Exception {
		init(new FileInputStream(new File(address)));
	}

	public AbstractConfig(InputStream in) throws Exception {
		init(in);
	}

	protected abstract void init(InputStream in) throws Exception;

	/**
	 * 
	 * Get a string value
	 * 
	 * @param key
	 * @return
	 */
	public abstract String getValue(String key);

	/**
	 * 
	 * Read an integer value
	 * 
	 * @param key
	 * @return
	 */
	public abstract int getInteger(String key);

	/**
	 * 
	 * Read an double value
	 * 
	 * @param key
	 * @return
	 */
	public abstract double getDouble(String key);

	/**
	 * 
	 * Read an long value
	 * 
	 * @param key
	 * @return
	 */
	public abstract long getLong(String key);

	/**
	 * 
	 * Get a string value
	 * 
	 * @param key
	 * @return
	 */
	public abstract String getValue(String key, String def);

	/**
	 * 
	 * Read an integer value
	 * 
	 * @param key
	 * @return
	 */
	public abstract int getInteger(String key, int def);

	/**
	 * 
	 * Read an double value
	 * 
	 * @param key
	 * @return
	 */
	public abstract double getDouble(String key, double def);

	/**
	 * 
	 * Read an long value
	 * 
	 * @param key
	 * @return
	 */
	public abstract long getLong(String key, long def);

	/**
	 * Read a boolean value
	 * 
	 * @param key
	 * @param def
	 * @return
	 */
	public abstract boolean getBoolean(String key, boolean def);

	/**
	 * Read a boolean value
	 * 
	 * @param key
	 * @param def
	 * @return
	 */
	public abstract boolean getBoolean(String key) throws UnkownValueFormatException;

	public String prepareKey(String prefix, String key) {
		return prefix.toUpperCase() + "." + key.toUpperCase();
	}

	public InputStream createInputStream() {
		String tmp = "";
		return new ByteArrayInputStream(tmp.getBytes());
	}

}
