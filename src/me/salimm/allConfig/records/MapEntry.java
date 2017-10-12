package me.salimm.allConfig.records;

import java.util.HashMap;

import me.salimm.allConfig.Config;

public class MapEntry extends Config implements ConfigEntry {

	public MapEntry(HashMap<String, ConfigEntry> map){
		super(map);
	}


	
}
