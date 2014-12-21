package net.lightcraftmc.fusebox.player;

import java.util.UUID;

import org.bukkit.entity.Player;

public class Players {

	private String uuid;
	
	public Players(Player player){
		this.uuid = player.getUniqueId().toString();
	}
	
	public String getUUIDString(){
		return uuid;
	}
	
	public UUID getUUID(){
		return UUID.fromString(uuid);
	}
	
}
