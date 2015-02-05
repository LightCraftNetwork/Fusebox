package com.lightcraftmc.fusebox.commonLibs.utilities;

import org.bukkit.Location;
import org.bukkit.World;

public class UtilData {

	public static Location parseLocationString(World w, String s){
		String[] work = s.split(",");
		double x = Double.parseDouble(work[0]); double y = Double.parseDouble(work[1]); double z = Double.parseDouble(work[2]); 
		float yaw = Float.parseFloat(work[3]); float pitch = Float.parseFloat(work[4]);
		return new Location(w, x, y, z, pitch, yaw);
	}
	
}
