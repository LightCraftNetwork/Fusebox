
package com.arrayprolc.menu;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu {
	Inventory inv;
	String name = "";
	int size = 9;
	boolean shouldAutoDecide = false;
	String uniqueId = "";

	private HashMap<ItemStack, Integer> items = new HashMap<ItemStack, Integer>();

	public Menu(String displayName, int invSize) {
		items.clear();
		size = invSize;
		name = displayName;
		inv = Bukkit.createInventory(null, size, name);
		init();
		String uuid = UUID.randomUUID().toString();
		this.uniqueId = uuid;
	}

	public void addItem(ItemStack i, int slot) {
		items.put(i, slot);
		inv.setItem(slot, i);

	}

	public void setItems(HashMap<ItemStack, Integer> stack) {
		items.clear();
		items = stack;
	}
	
	public ItemStack getItemAtSlot(int slot){
		return inv.getItem(slot);
	}

	public void clearItems() {
		items.clear();
	}

	public HashMap<ItemStack, Integer> getItems() {
		return items;
	}

	public void setName(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public void setLength(int i) {
		if (i % 9 == 0) {
			size = i;
		} else {
			while (size < items.size()) {
				if (size < items.size()) {
					size = size + 9;
				}
			}
		}
	}

	public String getUniqueId(Inventory id) {
		String s = name + "-" + "size" + "-";
		ItemStack[] items = id.getContents();
		for (ItemStack it : items) {
			s = s + "-" + it.toString();
		}

		return s;
	}

	public String getUniqueId() {
		String s = name + "-" + size;

		return s;
	}

	public void setShouldAutoDecide(boolean b) {
		shouldAutoDecide = b;
	}

	public void getShouldAutoDecide(boolean b) {
		shouldAutoDecide = b;
	}

	public void init() {
		MenuListener.menuInventories.add(inv.getName());
	}

	public void displayMenu(Player p) {
		MenuListener.menuInventories.remove(inv.getName());
		p.openInventory(inv);
		MenuListener.menuInventories.add(inv.getName());
	}

	public Inventory getInventory() {
		inv = Bukkit.createInventory(null, size, name);
		return inv;
	}

}
