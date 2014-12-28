package com.lightcraftmc.fusebox.menu;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.lightcraftmc.fusebox.Core;



public class MenuListener implements Listener {

	Core plugin;

	public MenuListener() {
		plugin = Core.getInstance();
	}

	public static ArrayList<String> menuInventories = new ArrayList<String>();
	public static HashMap<String, String> commands = new HashMap<String, String>();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (menuInventories.contains(e.getInventory().getName())) {
			e.setCancelled(true);
			Bukkit.getServer()
			.getPluginManager()
			.callEvent(
					new PlayerMenuEvent((Player) e.getWhoClicked(), e
							.getInventory(), e.getSlot(), e.getCursor()));
			try {
				((Player) e.getWhoClicked()).updateInventory();
				e.getWhoClicked().openInventory(e.getInventory());

			} catch (Exception ex) {
			}

		}
	}

}
