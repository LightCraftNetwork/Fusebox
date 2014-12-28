package com.lightcraftmc.fusebox.util;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.util.Vector;

public final class UtilRandoms
{
  public static final Random random = new Random(System.nanoTime());

  public static Vector getRandomVector()
  {
    double x = random.nextDouble() * 10.0D - 1.0D;
    double y = random.nextDouble() * 10.0D - 1.0D;
    double z = random.nextDouble() * 10.0D - 1.0D;

    return new Vector(x, y, z).normalize();
  }

  public static Vector getRandomVectorline(int direction)
  {
    int minz = 1;
    int maxz = 3;
    int rz = (int)(Math.random() * (maxz - minz) + minz);

    double miny = -1.0D;
    double maxy = 1.0D;
    double ry = Math.random() * (maxy - miny) + miny;

    double x = direction;
    double y = ry;
    double z = rz;

    return new Vector(x, y, z).normalize();
  }

  public static Vector getRandomVectorlineZ(int direction)
  {
    int minz = 1;
    int maxz = 3;
    int rz = (int)(Math.random() * (maxz - minz) + minz);

    double miny = -1.0D;
    double maxy = 1.0D;
    double ry = Math.random() * (maxy - miny) + miny;

    double x = direction;
    double y = ry;
    double z = rz;

    return new Vector(z, y, x).normalize();
  }

  public static Vector getRandomCircleVector()
  {
    double rnd = random.nextDouble() * 2.0D * 3.141592653589793D;
    double x = Math.cos(rnd);
    double z = Math.sin(rnd);

    return new Vector(x, 0.0D, z);
  }

  public static Vector getRandomCircleVectorLine()
  {
    double rnd = -10.0D + random.nextInt(10) * 2 * 3.141592653589793D;
    double y = -2 + random.nextInt(1);
    double x = Math.cos(rnd);
    double z = Math.sin(rnd);

    return new Vector(x, y, z);
  }

  public static Material getRandomMaterial(Material[] materials) {
    return materials[random.nextInt(materials.length)];
  }

  public static double getRandomAngle() {
    return random.nextDouble() * 2.0D * 3.141592653589793D;
  }
}