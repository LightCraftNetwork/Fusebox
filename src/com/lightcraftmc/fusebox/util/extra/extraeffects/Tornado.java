package com.lightcraftmc.fusebox.util.extra.extraeffects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.lightcraftmc.fusebox.util.effects.EffectManager;
import com.lightcraftmc.fusebox.util.extra.ExtraManager;
import com.lightcraftmc.fusebox.util.particles18.ParticleLib18;
import com.lightcraftmc.fusebox.util.update.UpdateType;
import com.lightcraftmc.fusebox.util.update.event.UpdateEvent;

public class Tornado
  implements Listener
{
  float LineNumber = 3.0F;
  float j = 0.0F;
  float radius = 0.3F;
  float heightEcart = 0.01F;

  @EventHandler
  public void LocationUpdater(UpdateEvent event) {
    if (event.getType() == UpdateType.TICK)
    {
      for (Player p : EffectManager.effect3.keySet())
      {
        if ((EffectManager.getEffect(p) == EffectManager.EffectType.Tornado) && 
          (p.isValid()))
        {
          if (ExtraManager.isMoving(p))
          {
            Location loc = p.getLocation();

            loc.setY(loc.getY() + 2.0D);

            for (int k = 0; k < this.LineNumber; k++)
            {
              loc.add(Math.cos(this.j) * this.radius, this.j * this.heightEcart, 
                Math.sin(this.j) * this.radius);
   		   	  ParticleLib18 fw = new ParticleLib18(com.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType.FIREWORKS_SPARK, 0.0D, 1, 0.0D);
   		   	  fw.sendToLocation(loc);
            }

            this.j += 0.2F;
            if (this.j > 50.0F)
            {
              this.j = 0.0F;
            }

          }
          else if (!p.isInsideVehicle()) {
 		   ParticleLib18 fw = new ParticleLib18(com.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType.FIREWORKS_SPARK, 0.1000000014901161D, 4, 0.300000011920929D);
 		   fw.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
          }
        }
      }
    }
  }
}