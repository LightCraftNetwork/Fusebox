package net.lightcraftmc.fusebox.area;

import org.bukkit.Location;

public class SphereArea extends Area{

	private String location;
	private int radius;
	
	public SphereArea(Location l1, int radius){
		this.location = locationToString(l1);
		this.radius = radius;
	}
	
	@Override
	public boolean isInArea(Location location) {
		return ((int)location.distanceSquared(getLocation()) <= Math.pow(getRadius(), 2));
	}
	
	public Location getLocation(){
		return getLocation(location);
	}
	
	public int getRadius(){
		return radius;
	}

	@Override
	public AreaType getType() {
		return AreaType.SPHERE;
	}
	
}
