package net.lightcraftmc.fusebox.util;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class UtilServer {
	
	@SuppressWarnings("deprecation")
	public static Player[] getPlayers() {
		return getServer().getOnlinePlayers();
	}
	
	public static Server getServer() {
		return Bukkit.getServer();
	}

}
