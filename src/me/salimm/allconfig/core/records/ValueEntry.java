package me.salimm.allconfig.core.records;

public class ValueEntry implements ConfigEntry {

	private String value;
	private String name;

	public ValueEntry(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "{ name: " + getName() + ", value: " + getValue() + "}";
	}

}
