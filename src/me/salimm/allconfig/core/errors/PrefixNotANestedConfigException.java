package me.salimm.allconfig.core.errors;

public class PrefixNotANestedConfigException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PrefixNotANestedConfigException(String[] path) {
		super("path: " + compilePath(path));
	}

	private static String compilePath(String[] path) {
		String tmp = "";
		for (String seg : path) {
			tmp += seg;
		}
		return tmp;
	}

}
