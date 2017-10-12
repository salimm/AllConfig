package me.salimm.allConfig.records;

public class ValueEntry implements ConfigEntry{
	
	private String value;
	
	public ValueEntry(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	

}
