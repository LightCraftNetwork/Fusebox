package com.lightcraftmc.fusebox.tools.areaSelectionTool.areas;

import org.bukkit.Location;

public class RadiusArea extends Area{

	private String location;
	private int radius;
	
	public RadiusArea(Location l1, int radius){
		this.location = locationToString(l1);
		this.radius = radius;
	}
	
	@Override
	public boolean isInArea(Location location) {
		Location l = location.clone();
		Location loc = getLocation();
		
		l.setY(location.getY());
		return((int)loc.distanceSquared(l) <= Math.pow(getRadius(), 2));
	}
	
	public Location getLocation(){
		return getLocation(location);
	}
	
	public int getRadius(){
		return radius;
	}

	@Override
	public AreaType getType() {
		return AreaType.RADIUS;
	}
	
}
