package net.lightcraftmc.fusebox.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class UtilVelocity
{
  public static Vector getBumpVector(Entity entity, Location from, double power)
  {
    Vector bump = entity.getLocation().toVector().subtract(from.toVector()).normalize();
    bump.multiply(power);
    return bump;
  }

  public static Vector getPullVector(Entity entity, Location to, double power)
  {
    Vector pull = to.toVector().subtract(entity.getLocation().toVector()).normalize();
    pull.multiply(power);
    return pull;
  }

  public static void bumpEntity(Entity entity, Location from, double power)
  {
    entity.setVelocity(getBumpVector(entity, from, power));
  }

  public static void bumpEntity(Entity entity, Location from, double power, double fixedY)
  {
    Vector vector = getBumpVector(entity, from, power);
    vector.setY(fixedY);
    entity.setVelocity(vector);
  }

  public static void pullEntity(Entity entity, Location to, double power)
  {
    entity.setVelocity(getPullVector(entity, to, power));
  }

  public static void pullEntity(Entity entity, Location from, double power, double fixedY)
  {
    Vector vector = getPullVector(entity, from, power);
    vector.setY(fixedY);
    entity.setVelocity(vector);
  }
}