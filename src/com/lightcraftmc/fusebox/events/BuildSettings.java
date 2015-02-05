package com.lightcraftmc.fusebox.events;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class BuildSettings implements Listener {

	private static ArrayList<String> allowBlockBreak = new ArrayList<String>();
	private static boolean allowBreakCreative = true;
	static boolean disableHunger = false;
	
	//Excuse crappy formating/errors, on web client.
	
	@EventHandler
	public void handleLeafDecay(LeavesDecayEvent e){
		e.setCancelled(true);
	}
	
	  @EventHandler
  	public void hunger(FoodLevelChangeEvent event)
  	{
  		if(!disableHunger) return;
		Player player = (Player)event.getEntity();
		event.setCancelled(true);
		player.setFoodLevel(20);
	 }
	
	@EventHandler
	public void handleBlockFade(BlockFadeEvent e){
		e.setCancelled(true);
	}
	
	Material[] blockPhysics = {
		Material.SIGN, Material.SIGN_POST, Material.LEAVES, Material.LEAVES_2, Material.VINE
	};
	
	@EventHandler
	public void handleBlockPhysics(BlockPhysicsEvent e){
		for(Material m : blockPhysics){
			if(e.getBlock().getType().equals(m)){
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
			if(allowBreakCreative){
				return;
			}
		}
		if(allowBlockBreak.contains(e.getBlock().getWorld().getName())){
			return;
		}
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
			if(allowBreakCreative){
				return;
			}
		}
		if(allowBlockBreak.contains(e.getBlock().getWorld().getName())){
			return;
		}
		e.setCancelled(true);
	}
	
	/*
	 * The following APIs are for adding worlds for players to break blocks. This should also be used if you would like to handle the block place/break events in your
	 * own plugin.
	 */
	
	public static void allowBlockBreak(String worldName){
		if(!allowBlockBreak.contains(worldName))
		allowBlockBreak.add(worldName);
	}
	
	public static void revokeBlockBreak(String worldName){
		if(allowBlockBreak.contains(worldName))
			allowBlockBreak.remove(worldName);
	}
	
	public static void disableServerHunger(){
		disableHunger = true;
	}
	
	public static void enableServerHunger(){
		disableHunger = false;
	}
	
	public static boolean isHungerEnabled(){
		return !disableHunger;
	}
	
	public static boolean isHungerDisabled(){
		return disableHunger;
	}
	
}
