package com.lightcraftmc.fusebox.commonLibs;

public class DataSet {

	String k, v;
	
	public DataSet(String key, String value){
		k = key; v = value;
	}
	
	public String getKey(){ return k; }
	
	public String getValue() { return v; }
	
}
