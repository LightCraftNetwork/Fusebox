package net.lightcraftmc.fusebox.anticheat;


import net.lightcraftmc.fusebox.strings.MessageType;
import net.lightcraftmc.fusebox.strings.StringManager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class HackAction {

	public static void warnPlayer(Player p, HackType h){
		p.sendMessage(StringManager.getMessage("§c§lTurn off your " + h.toString().toLowerCase() + " cheats immediately or you will be banned.", MessageType.ERROR));
		teleportToLowestPosition(p);
	}
	
	public static void teleportToLowestPosition(Entity e){
		Location loc = e.getLocation();
		for(int i = 0; i < 256; i++){
			if(loc.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR)){
				loc = loc.add(0, -1, 0);

			}
		}
		loc.setPitch(0);
		loc.setYaw(90);
		e.teleport(loc);
	}
	
}
