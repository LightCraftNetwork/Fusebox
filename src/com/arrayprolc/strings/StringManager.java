package com.arrayprolc.strings;

import org.apache.commons.lang.WordUtils;

public class StringManager {

	public static String getMessage(String s, MessageType m){
		return getFormatAndColor(m) + "§7 > " + s;
	}
	
	public static String capitalize(String s){
		return WordUtils.capitalize(s.toLowerCase().replace("_", " "));
	}
	
	public static String getPrefix(MessageType m){
		return getFormatAndColor(m) + "§7 > ";
	}
	
	public static String getFormatAndColor(MessageType m){
		switch(m) {
		case CONDITION: return "§9Condition";
		case DEATH: return "§cDeath";
		case ERROR: return "§cError";
		case INFO: return "§eInfo";
		case KICK: return "§cKick";
		case PURCHASE: return "§2Purchase";
		case STATS: return "§bStats";
		case SUCCESS: return "§aSuccess";
		case TRANSACTION: return "§aTransaction";
		case GADGETS: return "§eGadgets";
		case PARKOUR: return "§dParkour";
		case TREASURE: return "§b§lTreasure";
		default: return "§eInfo";
		
		}
	}
	
}
