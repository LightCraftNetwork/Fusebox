package com.lightcraftmc.fusebox.commands;

import org.bukkit.ChatColor;

public class LcConstants {

	public static final String MOUNT_METADATA_STRING = "LCMount";
	public static final String MOUNT_INVENTORY_TITLE = color("&a&oMounts!");
	
	
	private static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
