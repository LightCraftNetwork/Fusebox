package net.lightcraftmc.fusebox.util;

import net.minecraft.server.v1_8_R1.EntityPlayer;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingUtils {

	public static int getPing(Player p) {
		CraftPlayer cp = (CraftPlayer) p; 
		EntityPlayer ep = cp.getHandle(); 
		return ep.ping; 
	}

}
