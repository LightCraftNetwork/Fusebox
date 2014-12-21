package com.arrayprolc.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerMenuEvent extends Event {
	Player p;
	Inventory i;
	int slot;
	ItemStack item;

	public PlayerMenuEvent(Player p, Inventory i, int slot, ItemStack item) {
		this.p = p;
		this.i = i;
		this.slot = slot;
		this.item = item;
	}

	public Player getPlayer() {
		return p;
	}
	
	public ItemStack getItem() {
		return item;
	}

	public int getSlot() {
		return slot;
	}

	public Inventory getInventory() {
		return i;
	}

	public String getUniqueId() {
		String s = i.getName() + "-" + i.getSize();

		return s;
	}

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
