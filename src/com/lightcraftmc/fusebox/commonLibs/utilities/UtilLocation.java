package com.lightcraftmc.fusebox.commonLibs.utilities;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.lightcraftmc.fusebox.commonLibs.effects.EffectManager;
import com.lightcraftmc.fusebox.commonLibs.extra.ExtraManager;
import com.lightcraftmc.fusebox.commonLibs.particleLib.CircleParticle;
import com.lightcraftmc.fusebox.commonLibs.update.UpdateEvent;
import com.lightcraftmc.fusebox.commonLibs.update.UpdateType;

public class UtilLocation
implements Listener
{
	public static HashMap<Player, Location> locationEverySecond = new HashMap();
	public static HashMap<Player, Location> locationEvery2Second = new HashMap();

	@EventHandler
	public void OnPlayerLeft(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		if (locationEverySecond.containsKey(p)) {
			locationEverySecond.remove(p);
		}
		if (locationEvery2Second.containsKey(p))
			locationEvery2Second.remove(p);
	}

	@EventHandler
	public void onLeave(PlayerKickEvent e)
	{
		Player p = e.getPlayer();

		if (locationEverySecond.containsKey(p)) {
			locationEverySecond.remove(p);
		}
		if (locationEvery2Second.containsKey(p))
			locationEvery2Second.remove(p);
	}

	@EventHandler
	public void LocationUpdater(UpdateEvent event)
	{
		if (event.getType() == UpdateType.SEC)
		{
			for (Player p : CircleParticle.effect2.keySet()) {
				locationEverySecond.remove(p);
				locationEverySecond.put(p, p.getLocation());
			}
			for (Player p : EffectManager.effect3.keySet())
			{
				if (ExtraManager.hasExtraEffect(p)) {
					locationEvery2Second.remove(p);
					locationEvery2Second.put(p, p.getLocation());
				}
			}
		}
	}
	
	public static Location getLocation(String location){
		if(location!=null){
			String[] args = location.split(", ");
			if(args.length>3){
				if(args.length>5){
					return new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]),
							Float.parseFloat(args[4]), Float.parseFloat(args[5]));
				}else{
					return new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
				}
			}
		}
		return null;
	}

	public static String locationToString(Location location){
		if(location!=null){
			return location.getWorld().getName() + ", " + location.getX() + ", " + location.getY() + ", " + location.getZ() + ", " + location.getYaw() + ", " + location.getPitch();
		}
		return null;
	}
}