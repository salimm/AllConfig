package me.salimm.allconfig.core.records;

import java.util.HashMap;

import me.salimm.allconfig.core.Config;


public class MapEntry extends Config implements ConfigEntry {

	public MapEntry(HashMap<String, ConfigEntry> map){
		super(map);
	}


	
}
