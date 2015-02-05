package com.lightcraftmc.fusebox.commonLibs.utilities;

import java.text.DecimalFormat;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class UtilMath
{
  public static Random random = new Random();

  public static double trim(int degree, double d)
  {
    String format = "#.#";

    for (int i = 1; i < degree; i++) {
      format = format + "#";
    }
    DecimalFormat twoDForm = new DecimalFormat(format);
    return Double.valueOf(twoDForm.format(d)).doubleValue();
  }

  public static int r(int i)
  {
    return random.nextInt(i);
  }

  public static double offset2d(Entity a, Entity b)
  {
    return offset2d(a.getLocation().toVector(), b.getLocation().toVector());
  }

  public static double offset2d(Location a, Location b)
  {
    return offset2d(a.toVector(), b.toVector());
  }

  public static double offset2d(Vector a, Vector b)
  {
    a.setY(0);
    b.setY(0);
    return a.subtract(b).length();
  }

  public static double offset(Entity a, Entity b)
  {
    return offset(a.getLocation().toVector(), b.getLocation().toVector());
  }

  public static double offset(Location a, Location b)
  {
    return offset(a.toVector(), b.toVector());
  }

  public static double offset(Vector a, Vector b)
  {
    return a.subtract(b).length();
  }
  
	public static int getClosestTo(int j, int rejex){
		int working = rejex; for(int i = 0; i < 9*7; i++) if(j > working) working = working + rejex; return working;
	}
	
	public static long getClosestTo(long j, long rejex){
		long working = rejex; for(long i = 0; i < 9*7; i++) if(j > working) working = working + rejex; return working;
	}
	
	public static double getClosestTo(double j, double rejex){
		double working = rejex; for(double i = 0; i < 9*7; i++) if(j > working) working = working + rejex; return working;
	}
	
	public static int round(double input){
		return (int) Math.round(input);
	}
	
	public static long roundLong(double input){
		return Math.round(input);
	}
	
	public static int randInt(int min, int max) {
	    return random.nextInt((max - min) + 1) + min;
	}
	
	public static double getAverage(double[] values){
		double w = 0; for(double d : values) w = w + d; return w / values.length;
	}
	
	public static double addAll(double[] values){
		double w = 0; for(double d : values) w = w + d; return w;
	}
	
	public static int addAll(int[] values){
		int w = 0; for(int d : values) w = w + d; return w;
	}
	
	public static long addAll(long[] values){
		long w = 0; for(long d : values) w = w + d; return w;
	}
}