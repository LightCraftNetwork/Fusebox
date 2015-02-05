package com.lightcraftmc.fusebox.commonLibs.extra.extraeffects;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.lightcraftmc.fusebox.commonLibs.effects.EffectManager;
import com.lightcraftmc.fusebox.commonLibs.extra.ExtraManager;
import com.lightcraftmc.fusebox.commonLibs.particleLib.ParticleLib18;
import com.lightcraftmc.fusebox.commonLibs.update.UpdateEvent;
import com.lightcraftmc.fusebox.commonLibs.update.UpdateType;

public class Helix
  implements Listener
{
  float rayonDistance = 0.6F;
  float particleRotation = 0.2F;

  double count = 0.0D;

  @EventHandler
  public void LocationUpdater(UpdateEvent event)
  {
    if (event.getType() == UpdateType.TICK)
    {
      for (Player p : EffectManager.effect3.keySet())
      {
        if (EffectManager.getEffect(p) == EffectManager.EffectType.Helix)
        {
          if (p.isValid())
            if (ExtraManager.isMoving(p)) {
              Location l = p.getLocation();

              double count2 = this.count;
              double hauteure2 = -1.0D;
              double rayon2 = 1.5D;

              ArrayList<Location> points2 = new ArrayList<Location>();
              ArrayList<Location> points = new ArrayList<Location>();
              while (true)
              {
                double nombre2 = 3.141592653589793D + count2 * 3.141592653589793D / 6.0D;
                Location loc2 = new Location(p.getWorld(), l.getX() + 
                  Math.cos(nombre2 * this.rayonDistance) * 
                  rayon2, l.getY() + 
                  hauteure2, l.getZ() + 
                  Math.sin(nombre2 * this.rayonDistance) * 
                  rayon2);
                Location loc3 = new Location(p.getWorld(), l.getX() + 
                  Math.cos(nombre2 * this.rayonDistance) * 
                  -rayon2, l.getY() + 
                  hauteure2, l.getZ() + 
                  Math.sin(nombre2 * this.rayonDistance) * 
                  -rayon2);

                points2.add(loc2);
                points.add(loc3);

                if (count2 >= 36.0D + this.count)
                  break;
                rayon2 -= 0.04D;
                hauteure2 += 0.11D;
                count2 += 1.0D;
              }

              for (Location l4 : points2)
              {
            	  ParticleLib18 rd1 = new ParticleLib18(com.lightcraftmc.fusebox.commonLibs.particleLib.ParticleLib18.ParticleType.REDSTONE, 0.0D, 1, 0.0D);
       		   		rd1.sendToLocation(l4);
              }

              for (Location l3 : points)
              {
            	  ParticleLib18 rd1 = new ParticleLib18(com.lightcraftmc.fusebox.commonLibs.particleLib.ParticleLib18.ParticleType.REDSTONE, 0.0D, 1, 0.0D);
          		   rd1.sendToLocation(l3);              }

              if (this.count >= 36.0D) {
                this.count = 0.0D;
              }

              this.count += this.particleRotation;
            }
            else if (!p.isInsideVehicle()) {
              ParticleLib18 rd = new ParticleLib18(com.lightcraftmc.fusebox.commonLibs.particleLib.ParticleLib18.ParticleType.REDSTONE, 0.0D, 2, 0.2000000029802322D);
      		   rd.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
            }
        }
      }
    }
  }
}