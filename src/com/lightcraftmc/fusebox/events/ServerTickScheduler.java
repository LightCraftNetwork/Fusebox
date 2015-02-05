package com.lightcraftmc.fusebox.events;

import org.bukkit.Bukkit;

import com.lightcraftmc.fusebox.Core;

public class ServerTickScheduler {

	public static void schedule(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable(){
			public void run(){
			Bukkit.getServer().getPluginManager().callEvent(new ServerTickEvent());
			}
		}, 0, 1);
	}
	
}
