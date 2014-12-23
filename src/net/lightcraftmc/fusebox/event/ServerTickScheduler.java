package net.lightcraftmc.fusebox.event;

import net.lightcraftmc.fusebox.Core;

import org.bukkit.Bukkit;

public class ServerTickScheduler {

	public static void schedule(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable(){
			public void run(){
			Bukkit.getServer().getPluginManager().callEvent(new ServerTickEvent());
			}
		}, 0, 1);
	}
	
}
