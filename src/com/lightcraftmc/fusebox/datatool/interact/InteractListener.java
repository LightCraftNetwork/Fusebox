package com.lightcraftmc.fusebox.datatool.interact;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.lightcraftmc.fusebox.gamedata.GameData;

public class InteractListener implements Listener {
	
	@EventHandler
	public void interact(PlayerInteractEvent e){
		if(!e.getAction().toString().contains("CLICK")) return;
		ItemStack i = e.getPlayer().getItemInHand();
		if(!i.hasItemMeta()) return;
		if(!i.getItemMeta().hasDisplayName()) return;
		String s = ChatColor.stripColor(i.getItemMeta().getDisplayName());
		if(!s.contains("Edit Wand")) return;
		Player p = e.getPlayer();
		if(!p.isOp()) return;
		Location loc = e.getClickedBlock().getLocation();
		String s2 = s.split(": ")[1];
		String ls = loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();
		String s3 = s2 + ":" + ls + ";";
		GameData.init();
		GameData.addRawData(s3);
		GameData.save();
		e.setCancelled(true);
		p.sendMessage("§7Set " + s);
	}

}
