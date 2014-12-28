package com.lightcraftmc.fusebox.tools;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Tool {
	
	protected ItemStack tool;
	protected String name;
	
	public Tool(Material m, String name){
		tool = new ItemStack(m);
		ItemMeta im = tool.getItemMeta();
		im.setDisplayName(name);
		tool.setItemMeta(im);
		this.name = name;
	}
	
	public final ItemStack getTool(){
		return tool;
	}
	
	public final String getName(){
		return name;
	}
	
	public abstract void onClick(PlayerInteractEvent event);
	public abstract void onDrop(PlayerDropItemEvent event);
	public abstract void onInventoryClickItem(InventoryClickEvent event);
	public abstract void onInventoryClickCursor(InventoryClickEvent event);
	public abstract void onPlayerKick(PlayerKickEvent event);
	public abstract void onPlayerQuit(PlayerQuitEvent event);
	public abstract void onPlayerJoin(PlayerJoinEvent event);
	
}
