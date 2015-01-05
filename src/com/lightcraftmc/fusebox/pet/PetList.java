package com.lightcraftmc.fusebox.pet;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.lightcraftmc.fusebox.Core;
import com.lightcraftmc.fusebox.menu.Menu;
import com.lightcraftmc.fusebox.menu.UtilMenu;
import com.lightcraftmc.fusebox.util.item.ItemTools;
import com.lightcraftmc.fusebox.util.strings.UtilString;

public class PetList implements Listener {

	static ArrayList<Pet> pets = new ArrayList<Pet>();
	private static boolean isInit = false;

	private static void init(){
		isInit = true;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable(){
			public void run(){
				for(Pet pet : pets){

				}
			}
		}, 0, 1);
	}

	public static void addPet(Pet pet){
		if(!pets.contains(pet)){
			pets.add(pet);
		}
		if(!isInit){
			init();
		}
	}

	private static boolean isExclusion(EntityType e, EntityType[] types){
		for(EntityType e2 : types){
			if(e == e2){
				return true;
			}
		}
		return false;
	}

	public static void openMenu(Player p){
		EntityType[] exclusions = { EntityType.ARMOR_STAND, EntityType.DROPPED_ITEM, EntityType.EGG, EntityType.ARROW, EntityType.SNOWBALL, EntityType.ENDER_CRYSTAL,
				EntityType.ENDER_DRAGON, EntityType.ITEM_FRAME, EntityType.LEASH_HITCH, EntityType.COMPLEX_PART, EntityType.GUARDIAN, EntityType.ENDER_PEARL,
				EntityType.ENDER_SIGNAL, EntityType.EXPERIENCE_ORB, EntityType.FIREBALL, EntityType.SMALL_FIREBALL, EntityType.LIGHTNING, EntityType.PAINTING,
				EntityType.MINECART, EntityType.MINECART_CHEST, EntityType.MINECART_COMMAND, EntityType.MINECART_FURNACE, EntityType.MINECART_HOPPER, EntityType.MINECART_MOB_SPAWNER,
				EntityType.MINECART_TNT, EntityType.THROWN_EXP_BOTTLE, EntityType.WITHER_SKULL, EntityType.PRIMED_TNT, EntityType.FALLING_BLOCK, EntityType.FIREWORK,
				EntityType.BOAT, EntityType.GIANT, EntityType.GHAST, EntityType.WITHER, EntityType.SPLASH_POTION, EntityType.FISHING_HOOK,
				EntityType.WEATHER, EntityType.PLAYER, EntityType.UNKNOWN
		};
		Menu menu = new Menu("Pets", 9*6); HashMap<EntityType, Integer> allowed = new HashMap<EntityType, Integer>(); int current = 0; int[] allow = UtilMenu.getAllowedSlots();
		
		for(EntityType e : EntityType.values()){ if(!isExclusion(e, exclusions)){
			try{
				int slot = allow[current];
				allowed.put(e, slot);
				current++;
			}catch(Exception ex){ }
		} }
		
		for(EntityType e : allowed.keySet()){ int slot = allowed.get(e).intValue();	menu.addItem(ItemTools.setName(new ItemStack(Material.INK_SACK, 1, (byte)8), "§7" + UtilString.enumToString(e.toString())), slot); }
		
		menu.addItem(ItemTools.setName(new ItemStack(Material.NAME_TAG, 1), "§bName §7your pet"), 43);
		
		menu.displayMenu(p); return;
	}
	
	@EventHandler
	public void click(InventoryClickEvent e){
		if(!e.getInventory().getTitle().contains("Pets")) return;
		if(e.getCurrentItem().getType().equals(Material.NAME_TAG)){
			
		}
	}
	
	public boolean hasPurchased(String key, OfflinePlayer p){
		return true;
	}

}
