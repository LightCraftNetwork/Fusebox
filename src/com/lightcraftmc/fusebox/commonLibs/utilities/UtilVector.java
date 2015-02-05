package com.lightcraftmc.fusebox.commonLibs.utilities;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class UtilVector
{
  public static void velocity(Entity ent, double str, double yAdd, double yMax)
  {
    velocity(ent, ent.getLocation().getDirection(), str, false, 0.0D, yAdd, yMax);
  }

  public static void velocity(Entity ent, Vector vec, double str, boolean ySet, double yBase, double yAdd, double yMax)
  {
    if ((Double.isNaN(vec.getX())) || (Double.isNaN(vec.getY())) || (Double.isNaN(vec.getZ())) || (vec.length() == 0.0D)) {
      return;
    }
    if (ySet) {
      vec.setY(yBase);
    }
    vec.normalize();
    vec.multiply(str);

    vec.setY(vec.getY() + yAdd);
    if (vec.getY() > yMax) {
      vec.setY(yMax);
    }
    ent.setFallDistance(0.0F);
    ent.setVelocity(vec);
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

  public static final double angleToXAxis(Vector vector) {
    return Math.atan2(vector.getX(), vector.getY());
  }
}