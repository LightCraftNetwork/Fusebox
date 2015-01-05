package com.lightcraftmc.fusebox.pet;

import java.util.UUID;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.lightcraftmc.fusebox.Core;
import com.lightcraftmc.fusebox.util.UtilEnt;

public class Pet implements Listener {
	EntityType e;
	Player p;
	OfflinePlayer owner;
	public boolean stop = false;
	Entity en;

	public Pet(EntityType entity, OfflinePlayer owner){
		PetList.addPet(this);
		e = entity;
		this.owner = owner;
		if(owner.isOnline()){
			p = owner.getPlayer();
			spawn();
			setupRunnable();
			
		}
	}
	public void setupRunnable(){
		new BukkitRunnable() {
			public void run() {
				if(!stop){
					updateEvent();
				}else{
					this.cancel();
				}
			}
		}.runTaskTimer(Core.getInstance(), 0, 1);
		Pet pet = this;
		new BukkitRunnable() {
			public void run() {
				if(!stop){
					UtilEnt.CreatureMove(en, pet.getLocationBehind(p, 3), 2f);
				}else{
					this.cancel();
				}
			}
		}.runTaskTimer(Core.getInstance(), 0, 1);
	}
	
	public void despawn(){
		PetList.pets.remove(this);
		en.remove();
		stop = true;
	}
	public void spawn(){
		if(stop) return;
		try{
		if(en != null || !en.isDead()){
			en.remove();
		}
		}catch(Exception ex){}
		
		
		
		en = p.getWorld().spawnEntity(p.getLocation(), e);
		en.setCustomName("§b" + owner.getName() + "§7's §a" + WordUtils.capitalize(e.toString().replace("_", " ").toLowerCase()));
	}
	
	public UUID getUniqueId(){
		return en.getUniqueId();
	}
	
	public Location getLocationBehind(Player player, int distance){
		World world = player.getWorld();
		Location loc = player.getLocation();
		Block behind = loc.getBlock();
		int direction = (int)loc.getYaw();
		 
		if(direction < 0) {
		    direction += 360;
		    direction = (direction + 45) / 90;
		}else {
		    direction = (direction + 45) / 90;
		}
		 
		switch (direction) {
		    case 1:
		        behind = world.getBlockAt(behind.getX() + distance, behind.getY(), behind.getZ());
		        break;
		    case 2:
		        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() + distance);
		        break;
		    case 3:
		        behind = world.getBlockAt(behind.getX() - distance, behind.getY(), behind.getZ());
		        break;
		    case 4:
		        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() - distance);
		        break;
		    case 0:
		        behind = world.getBlockAt(behind.getX(), behind.getY(), behind.getZ() - distance);
		        break;
		    default:
		        break;
		}
		return behind.getLocation();
	}

	public Entity getEntity(){
		return en;
	}
	
	public void updateEvent(){
		if(stop) return;
	//	if(en == null) return;
		if(en.isDead()) { spawn(); }
		if(en instanceof LivingEntity){
			LivingEntity e2 = (LivingEntity)en;
			e2.setHealth(5D);
			if(!e2.getWorld().getName().equals(p.getWorld().getName())){
				en.teleport(p);
			}
			if(e2.getLocation().distance(p.getLocation()) > 10){
				en.teleport(p);
			}
		}
		if(!owner.isOnline()){
			stop = true;
			en.remove();
			return;
		}

	}
	


}
