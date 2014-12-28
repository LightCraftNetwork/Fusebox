package com.lightcraftmc.fusebox.player;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import com.lightcraftmc.fusebox.Core;
import com.lightcraftmc.fusebox.configuration.Configuration;

public class Players {

	private String uuid;
	private Configuration config;
	private PermissionAttachment permissions;
	
	public Players(Player player){
		this.uuid = player.getUniqueId().toString();
		config = new Configuration(Core.getInstance(), "/players/" + uuid + ".yml");
	}
	
	public String getUUIDString(){
		return uuid;
	}
	
	public UUID getUUID(){
		return UUID.fromString(uuid);
	}
	
	public Configuration getConfig(){
		return config;
	}
	
	public PermissionAttachment getPermissionAttachment(){
		return permissions;
	}
	
	public void setPermissionAttachment(PermissionAttachment pa){
		this.permissions = pa;
	}
	
}
