package com.lightcraftmc.fusebox.area;

import org.bukkit.Location;

public class RectangleArea extends Area{

	private String location1, location2;
	
	public RectangleArea(Location l1, Location l2){
		this.location1 = locationToString(l1);
		this.location2 = locationToString(l2);
	}
	
	@Override
	public boolean isInArea(Location location) {
		Location l1 = getLocation1();
		Location l2 = getLocation2();
		return (isXIn(location.getBlockX(), l1.getBlockX(), l2.getBlockX())&&
				isZIn(location.getBlockZ(), l1.getBlockZ(), l2.getBlockZ()));
	}

	public boolean isXIn(int x, int x1, int x2){
		if(x1 >= x2){
			if(x<=x1&&x>=x2){
				return true;
			}
		}else{
			if(x>=x1&&x<=x2){
				return true;
			}
		}
		return false;
	}

	public boolean isZIn(int z, int z1, int z2){
		if(z1 >= z2){
			if(z<=z1&&z>=z2){
				return true;
			}
		}else{
			if(z>=z1&&z<=z2){
				return true;
			}
		}
		return false;
	}
	
	public Location getLocation1(){
		return getLocation(location1);
	}
	
	public Location getLocation2(){
		return getLocation(location2);
	}

	@Override
	public AreaType getType() {
		return AreaType.RECTANGLE;
	}
	
}
