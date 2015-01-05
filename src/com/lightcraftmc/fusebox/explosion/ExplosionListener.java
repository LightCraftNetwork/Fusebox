package com.lightcraftmc.fusebox.explosion;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

import com.lightcraftmc.fusebox.Core;

public class ExplosionListener implements Listener {
	ArrayList<UUID> notouch = new ArrayList<UUID>();
	public ExplosionListener(){
		super();
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable(){
			public void run(){
				for(World w : Bukkit.getWorlds()) for(Entity e : w.getEntities()){
					if(notouch.contains(e.getUniqueId())){
						for(int i = 0; i < 5; i++){
							if(!e.getLocation().clone().add(0, -i, 0).getBlock().getType().equals(Material.AIR) && e.getVelocity().getY() < 0 || e.getTicksLived() > 25){
								e.remove();
								notouch.remove(e.getUniqueId());
							}
						}
					}
				}
			}
		}, 0, 1);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		event.setCancelled(true);
		int i = 0;
		for (Block block : event.blockList()) {
			final Block b = block;
			final Material m = b.getType();
			final byte d = b.getData();
			for(Player all : Bukkit.getOnlinePlayers())	sendBlockBreakParticles(all, b.getType(), b.getLocation());
			for(Player p : Bukkit.getOnlinePlayers()){
				if(p.getLocation().getWorld().getName().equals(b.getLocation().getWorld().getName())){
					p.sendBlockChange(b.getLocation(), Material.AIR, b.getData());
				}
			}
			/*	float x = -2 + (float) (Math.random() * ((2 - -2) + 1));
				float y = -3 + (float) (Math.random() * ((3 - -3) + 1));
				float z = (float) -2.5
						+ (float) (Math.random() * ((2.5 - -2.5) + 1));
				FallingBlock fall = b.getWorld().spawnFallingBlock(
						b.getLocation(), b.getType(), b.getData());
				float drop = 2 + (float) (Math.random() * ((0 - 1) + 1));
				boolean itemDrop = false;
				if ((int) drop == 1) {
					itemDrop = true;
				} else {
					itemDrop = false;
				}
				fall.setDropItem(false);

				fall.setVelocity(new Vector(x - 2, y - 2, z - 2));

				notouch.add(fall.getUniqueId());*/
			i = i + 1;
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable(){
				public void run(){
					for(Player p : Bukkit.getOnlinePlayers()){
						if(p.getLocation().getWorld().getName().equals(b.getLocation().getWorld().getName())){
							p.sendBlockChange(b.getLocation(), b.getType(), b.getData());
						}
					}
				}
			}, (20*2) + i);
		}

	}

	@SuppressWarnings("deprecation")
	public static void sendBlockBreakParticles(Player p, Material m,
			Location loc) {
		p.getWorld().playEffect(loc, Effect.STEP_SOUND, m.getId());
	}


}
