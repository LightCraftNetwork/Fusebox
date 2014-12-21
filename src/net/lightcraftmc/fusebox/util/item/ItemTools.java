package net.lightcraftmc.fusebox.util.item;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemTools {

	public static ItemStack setName(ItemStack is, String name,
			String[] lore2) {
		ItemMeta im = is.getItemMeta();
		if (name != null) {
			im.setDisplayName(name);
		}
		List<String> lore = Arrays.asList(lore2);
		if (lore != null) {
			im.setLore(lore);
		}
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack setLore(ItemStack is, String lore3) {
		try{
		return setName(is, WordUtils.capitalize(is.getType().toString().toLowerCase().replace("_", " ")), lore3);
		}catch(Exception e){ return is; }
	}
	public static ItemStack setName(ItemStack is, String name,
			String lore3) {
		String[] lore2 = { lore3 + "" };
		ItemMeta im = is.getItemMeta();
		if (name != null) {
			im.setDisplayName(name);
		}
		List<String> lore = Arrays.asList(lore2);
		if (lore != null) {
			im.setLore(lore);
		}
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack setName(ItemStack is, String name) {
		ItemMeta im = is.getItemMeta();
		if (name != null) {
			im.setDisplayName(name);
		}
		is.setItemMeta(im);
		return is;
	}
}
