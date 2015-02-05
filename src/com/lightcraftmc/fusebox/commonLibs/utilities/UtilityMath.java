package com.lightcraftmc.fusebox.commonLibs.utilities;

import java.text.DecimalFormat;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class UtilityMath
{
  public static final Random random = new Random(System.nanoTime());

  public static int getRandomNumberBetween(int min, int max)
  {
    int randomNumber = random.nextInt(max - min) + min;
    if (randomNumber == min)
    {
      return min + 1;
    }

    return randomNumber;
  }

  public static int randRange(int min, int max)
  {
    Random r = new Random();
    return min + r.nextInt() * (max - min);
  }

  public static double randomRange(double min, double max) {
    return Math.random() < 0.5D ? (1.0D - Math.random()) * (max - min) + min : Math.random() * (max - min) + min;
  }

  public static float randomRangeFloat(float min, float max) {
    return (float)(Math.random() < 0.5D ? (1.0D - Math.random()) * (max - min) + min : Math.random() * (max - min) + min);
  }

  public static int randomRangeInt(int min, int max) {
    return (int)(Math.random() < 0.5D ? (1.0D - Math.random()) * (max - min) + min : Math.random() * (max - min) + min);
  }

  public static Vector getRandomVector()
  {
    double x = random.nextDouble() * 2.0D - 1.0D;
    double y = random.nextDouble() * 2.0D - 1.0D;
    double z = random.nextDouble() * 2.0D - 1.0D;

    return new Vector(x, y, z).normalize();
  }

  public static Vector getRandomVectorline()
  {
    int minz = 1;
    int maxz = 3;
    int rz = (int)(Math.random() * (maxz - minz) + minz);

    double miny = -1.0D;
    double maxy = 1.0D;
    double ry = Math.random() * (maxy - miny) + miny;

    double x = -5.0D;
    double y = ry;
    double z = rz;

    return new Vector(x, y, z).normalize();
  }

  public static final Vector rotateAroundAxisX(Vector v, double angle)
  {
    double cos = Math.cos(angle);
    double sin = Math.sin(angle);
    double y = v.getY() * cos - v.getZ() * sin;
    double z = v.getY() * sin + v.getZ() * cos;
    return v.setY(y).setZ(z);
  }

  public static final Vector rotateAroundAxisY(Vector v, double angle)
  {
    double cos = Math.cos(angle);
    double sin = Math.sin(angle);
    double x = v.getX() * cos + v.getZ() * sin;
    double z = v.getX() * -sin + v.getZ() * cos;
    return v.setX(x).setZ(z);
  }

  public static final Vector rotateAroundAxisZ(Vector v, double angle)
  {
    double cos = Math.cos(angle);
    double sin = Math.sin(angle);
    double x = v.getX() * cos - v.getY() * sin;
    double y = v.getX() * sin + v.getY() * cos;
    return v.setX(x).setY(y);
  }

  public static final Vector rotateVector(Vector v, double angleX, double angleY, double angleZ)
  {
    rotateAroundAxisX(v, angleX);
    rotateAroundAxisY(v, angleY);
    rotateAroundAxisZ(v, angleZ);
    return v;
  }

  public static Vector calculateVelocity(Player p, Entity e) {
    Location ploc = p.getLocation();
    Location eloc = e.getLocation();

    double px = ploc.getX();
    double py = ploc.getY();
    double pz = ploc.getZ();
    double ex = eloc.getX();
    double ey = eloc.getY();
    double ez = eloc.getZ();

    double x = 0.0D;
    double y = 0.0D;
    double z = 0.0D;
    if (px < ex)
      x = 2.0D;
    else if (px > ex) {
      x = -2.0D;
    }
    if (py < ey)
      y = 1.0D;
    else if (py > ey) {
      y = 1.0D;
    }
    if (pz < ez)
      z = 2.0D;
    else if (pz > ez) {
      z = -2.0D;
    }
    return new Vector(x, y, z);
  }

  public static Vector calculateVelocity(Location l, Entity e) {
    Location ploc = l;
    Location eloc = e.getLocation();

    double px = ploc.getX();
    double py = ploc.getY();
    double pz = ploc.getZ();
    double ex = eloc.getX();
    double ey = eloc.getY();
    double ez = eloc.getZ();

    double x = 0.0D;
    double y = 0.0D;
    double z = 0.0D;
    if (px < ex)
      x = 2.0D;
    else if (px > ex) {
      x = -2.0D;
    }
    if (py < ey)
      y = 1.0D;
    else if (py > ey) {
      y = 1.0D;
    }
    if (pz < ez)
      z = 2.0D;
    else if (pz > ez) {
      z = -2.0D;
    }
    return new Vector(x, y, z);
  }

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
}