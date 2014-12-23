package net.lightcraftmc.fusebox.player;

import java.util.UUID;

import net.lightcraftmc.fusebox.Core;
import net.lightcraftmc.fusebox.configuration.Configuration;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

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
