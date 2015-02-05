package com.lightcraftmc.fusebox.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ServerTickEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	

}
