package com.lightcraftmc.fusebox.commonLibs.utilities;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class UtilServer {
	
	public static Player[] getPlayers() {
		return getServer().getOnlinePlayers().toArray(new Player[getServer().getOnlinePlayers().size()]);
	}
	
	public static Server getServer() {
		return Bukkit.getServer();
	}

}
