package com.lightcraftmc.fusebox.util.extra.extraeffects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.lightcraftmc.fusebox.util.UtilityMath;
import com.lightcraftmc.fusebox.util.effects.EffectManager;
import com.lightcraftmc.fusebox.util.extra.ExtraManager;
import com.lightcraftmc.fusebox.util.particles18.ParticleLib18;
import com.lightcraftmc.fusebox.util.update.UpdateType;
import com.lightcraftmc.fusebox.util.update.event.UpdateEvent;

public class Cloud
  implements Listener
{
  @EventHandler
  public void LocationUpdater(UpdateEvent event)
  {
    if (event.getType() == UpdateType.TICK)
    {
      for (Player p : EffectManager.effect3.keySet())
      {
        if (EffectManager.getEffect(p) == EffectManager.EffectType.Cloud)
        {
          if (p.isValid())
          {
            if (ExtraManager.isMoving(p))
            {
              Location l = p.getLocation();
              l.setY(p.getLocation().getY() + 3.700000047683716D);
   		   	  ParticleLib18 water = new ParticleLib18(com.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType.DRIP_WATER, 0.0D, 1, 0.0D);
   		   	  ParticleLib18 cloud = new ParticleLib18(com.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType.CLOUD, 0.0D, 3, 0.0D);
   		   	  ParticleLib18 cloud2 = new ParticleLib18(com.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType.CLOUD, 0.0D, 3, 0.0D);
   		   	  water.sendToLocation(l.add(UtilityMath.randomRangeFloat(-0.4F, 0.4F), 0.0D, UtilityMath.randomRangeFloat(-0.5F, 0.5F)));
   		   	  cloud.sendToLocation(l.add(UtilityMath.randomRangeFloat(-0.4F, 0.4F), 0.0D, UtilityMath.randomRangeFloat(-0.5F, 0.5F)));
   		   	  cloud2.sendToLocation(l.add(UtilityMath.randomRangeFloat(-0.5F, 0.5F), 0.0D, UtilityMath.randomRangeFloat(-0.5F, 0.5F)));
              l.subtract(l);
            }
            else if (!p.isInsideVehicle()) {
   		   	  ParticleLib18 waters = new ParticleLib18(com.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType.WATER_SPLASH, 0.1000000014901161D, 4, 0.300000011920929D);
   		   	  waters.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
            }
          }
        }
      }
    }
  }
}