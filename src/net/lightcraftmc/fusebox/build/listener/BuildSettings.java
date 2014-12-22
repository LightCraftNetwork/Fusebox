package net.lightcraftmc.fusebox.build.listener;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;

public class BuildSettings implements Listener {

	private static ArrayList<String> allowBlockBreak = new ArrayList<String>();
	private static boolean allowBreakCreative = true;
	@EventHandler
	public void handleLeafDecay(LeavesDecayEvent e){
		e.setCancelled(true);
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
	
}
