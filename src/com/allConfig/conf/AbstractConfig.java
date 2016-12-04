package com.allConfig.conf;

import com.allConfig.errors.UnkownValueFormatException;

/**
 * AbstractConfig includes trivial implementations of config reader. It can be
 * from a dtabase
 * 
 * @author salimm
 *
 */
public abstract class AbstractConfig {

	private String address;

	public AbstractConfig(String address) throws Exception {
		this.setAddress(address);
		init(address);
	}

	protected abstract void init(String address) throws Exception;

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
	public abstract boolean getBoolean(String key) throws UnkownValueFormatException ;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
