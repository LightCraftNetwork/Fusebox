package com.lightcraftmc.fusebox.commonLibs.strings;

public class ChatResult {
	String string;
	int vi;
	public ChatResult(String s, int violations){
		string = s; vi = violations;
	}
	
	public int getViolations(){
		return vi;
	}
	
	public String getString(){
		return string;
	}
	
	public String toString(){
		return string;
	}
}
