package com.lightcraftmc.fusebox.events;

import com.lightcraftmc.fusebox.bungeecord.ProxiedEconomy;
import com.lightcraftmc.fusebox.commonLibs.parties.PartyManager;


public class JacobEventSetup {
	public static void setupEvents(){
		new PartyManager();
		new ProxiedEconomy();
	}
	public static void disable(){
		
	}
}
