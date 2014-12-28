package com.lightcraftmc.fusebox.util.strings;

import com.lightcraftmc.fusebox.util.strings.WordUtils;

public class UtilString {

	static String chatPattern = "[a-zA-Z,a-zA-Z\\\\d\\\\p{Blank}?!.,:()+-/*{}\\[\\]'\";<>\\d]";
	
	public static String enumToString(String input){
		return WordUtils.capitalize(input.toLowerCase().replace("_", " "));
	}
	
	public static ChatResult convertToChat(String input){
		String violations = input.replaceAll(chatPattern, "");
		int vi = violations.length();
		for(char c : violations.toCharArray()){
			input = input.replaceAll(c + "", "");
		}
		return new ChatResult(input, vi);
	}
	

	
}
