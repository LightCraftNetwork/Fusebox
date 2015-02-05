package com.lightcraftmc.fusebox.commonLibs.utilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import com.lightcraftmc.fusebox.Core;
import com.lightcraftmc.fusebox.commonLibs.particleLib.ParticleLib18;

public class UtilEffect
{
  public static void playFlameBonus(Location l)
  {
    final int i2 = Bukkit.getScheduler().runTaskTimer(Core.getInstance(), new Runnable() { private int particles;
      private int particlesPerIteration;
      private float size;
      private float xFactor;
      private float yFactor;
      private float zFactor;
      private float yOffset;
      private double xRotation;
      private double yRotation;
      private double zRotation;
      private int step;
      Location location;

      public void run() { Vector vector = new Vector();

        for (int i = 0; i < this.particlesPerIteration; i++) {
          this.step += 1;

          float t = 3.141593F / this.particles * this.step;
          float r = MathUtils.sin(t * 2.718282F * this.particlesPerIteration / this.particles) * this.size;
          float s = r * 3.141593F * t;

          vector.setX(this.xFactor * r * -MathUtils.cos(s));
          vector.setZ(this.zFactor * r * -MathUtils.sin(s));
          vector.setY(this.yFactor + this.yOffset - 1.0F);

          UtilVector.rotateVector(vector, this.xRotation, this.yRotation, this.zRotation);
      	  ParticleLib18 res = new ParticleLib18(com.lightcraftmc.fusebox.commonLibs.particleLib.ParticleLib18.ParticleType.FLAME,  0.0D, 1, 0.0D);
      	  res.sendToLocation(this.location.add(vector));

          this.location.subtract(vector);
        }
      }
    }
    , 1L, 1L).getTaskId();

    Bukkit.getServer().getScheduler()
      .runTaskLater(Core.getInstance(), new Runnable()
    {
      public void run() {
        Bukkit.getScheduler().cancelTask(i2);
      }
    }
    , 80L);
  }

  public static void playFlameThing(final Location l)
  {
    final int i = Bukkit.getScheduler().runTaskTimer(Core.getInstance(), new Runnable()
    {
      float LineNumber = 1.0F;
      float j = 0.0F;
      float radius = 0.05F;
      float heightEcart = 0.01F;

      public void run()
      {
        for (int k = 0; k < this.LineNumber; k++)
        {
          l.add(Math.cos(this.j) * this.radius, this.j * this.heightEcart, 
          Math.sin(this.j) * this.radius);
      	  ParticleLib18 res = new ParticleLib18(com.lightcraftmc.fusebox.commonLibs.particleLib.ParticleLib18.ParticleType.HEART,  0.0D, 1, 0.0D);
      	  res.sendToLocation(l);

          
        }

        this.j += 0.2F;
        if (this.j > 50.0F)
        {
          this.j = 0.0F;
        }
      }
    }
    , 1L, 1L).getTaskId();

    Bukkit.getServer().getScheduler()
      .runTaskLater(Core.getInstance(), new Runnable()
    {
      public void run() {
        Bukkit.getScheduler().cancelTask(i);
      }
    }
    , 40L);
  }
}