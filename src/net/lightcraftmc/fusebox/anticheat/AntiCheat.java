package net.lightcraftmc.fusebox.anticheat;

import java.util.HashMap;
import java.util.UUID;

import net.lightcraftmc.fusebox.Core;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AntiCheat implements Listener {

	private static AntiCheat instance;

	public static void init() {
		instance = new AntiCheat();
		Bukkit.getServer().getPluginManager().registerEvents(instance, Core.getInstance());
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable(){
			public void run(){
				for(Player p : Bukkit.getOnlinePlayers()){
					if(p.isFlying()) return;
					if(p.isSneaking()) return;
					Location nl = p.getLocation();// nl.setPitch(0); nl.setYaw(0);
					/*if(p.getLocation().getX() == e.getTo().getX()){
						if(p.getLocation().getY() == e.getTo().getY()){
							if(p.getLocation().getZ() == e.getTo().getZ()){
								return;
							}
						}	
					}*/
					/*
					if(moving.containsKey(p.getUniqueId())){
						if(moving.get(p.getUniqueId()).distance(nl) == 0){
							Bukkit.broadcastMessage(p.getVelocity().getY() + "");
							//if(p.getVelocity().getY() > -0.1 && p.getVelocity().getY() < 0.1)
							if(p.getLocation().add(0, -1, 0).getBlock().getType() == Material.AIR){
								Bukkit.broadcastMessage("hi");
							}
						}
					}*/
					if(p.isOnGround() && p.getLocation().add(0, -1, 0).getBlock().getType() == Material.AIR){
						teleportToLowestPosition(p);
					}
					
					Bukkit.broadcastMessage(p.isOnGround() + "");
					if(!moving.containsKey(p.getUniqueId())){
						moving.put(p.getUniqueId(), nl);
					}
					if(moving.get(p.getUniqueId()).getY() == p.getLocation().getY() && !hasBlockNearby(p.getLocation())){
						if(p.getLocation().add(0, -1, 0).getBlock().getType() == Material.AIR && (p.getLocation().add(0, -2, 0).getBlock().getType() == Material.AIR)){
							teleportToLowestPosition(p);
							moving.remove(p.getUniqueId());
						}
					}else{
						moving.remove(p.getUniqueId());
						moving.put(p.getUniqueId(), nl);
					}
				}
			}
		}, 0, 1);
	}
	public static boolean hasBlockNearby(Location loc){
		for(int x = -1; x < 1; x++){
			for(int y = -1; y < 1; y++){
				for(int z = -1; z < 1; z++){
					Location loc2 = loc;
					loc2 = loc2.add(x, y, z);
					if(loc2.getBlock().getType() != Material.AIR){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static AntiCheat getInstance(){
		return instance;
	}

	static HashMap<UUID, Location> moving = new HashMap<UUID, Location>();
	HashMap<UUID, Integer> test = new HashMap<UUID, Integer>();


	public static void teleportToLowestPosition(Entity e){
		Location loc = e.getLocation();
		for(int i = 0; i < 256; i++){
			if(loc.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR)){
				loc = loc.add(0, -1, 0);
			}
		}
		e.teleport(loc);
	}

}
