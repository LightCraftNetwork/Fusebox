package net.lightcraftmc.fusebox.area;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public abstract class Area {

	public abstract boolean isInArea(Location location);
	public abstract AreaType getType();
	
	public Location getLocation(String location){
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

	public String locationToString(Location location){
		if(location!=null){
			return location.getWorld().getName() + ", " + location.getX() + ", " + location.getY() + ", " + location.getZ() + ", " + location.getYaw() + ", " + location.getPitch();
		}
		return null;
	}
	
}
