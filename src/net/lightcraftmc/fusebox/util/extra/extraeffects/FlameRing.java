package net.lightcraftmc.fusebox.util.extra.extraeffects;

import net.lightcraftmc.fusebox.util.effects.EffectManager;
import net.lightcraftmc.fusebox.util.extra.ExtraManager;
import net.lightcraftmc.fusebox.util.particles18.ParticleLib18;
import net.lightcraftmc.fusebox.util.update.UpdateType;
import net.lightcraftmc.fusebox.util.update.event.UpdateEvent;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FlameRing
  implements Listener
{
  private double i = 0.0D;

  @EventHandler
  public void LocationUpdater(UpdateEvent event) { if (event.getType() == UpdateType.TICK)
    {
      for (Player p : EffectManager.effect3.keySet())
      {
        if (EffectManager.getEffect(p) == EffectManager.EffectType.FlameRing)
        {
          if (p.isValid())
          {
            if (ExtraManager.isMoving(p))
            {
              double slice = 0.1570796326794897D;
              double radius = 1.0D;

              Location ploca = p.getLocation();
              double angle = slice * this.i;
              double angle2 = slice * this.i;
              angle2 -= angle2 * 2.0D;

              double newX = ploca.getX() - radius * Math.cos(angle);
              double newZ = ploca.getZ() - radius * Math.sin(angle);
              double newY2 = ploca.getY() + 1.0D + radius * Math.cos(angle);
              double newX2 = ploca.getX() + radius * Math.cos(angle2);
              double newZ2 = ploca.getZ() + radius * Math.sin(angle2);
              Location p1 = new Location(p.getWorld(), newX, newY2, newZ);
              Location p2 = new Location(p.getWorld(), newX2, newY2, newZ2);

   		   	  ParticleLib18 flame1 = new ParticleLib18(net.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType.FLAME, 0.0D, 1, 0.0D);
   		   	  ParticleLib18 flame2 = new ParticleLib18(net.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType.FLAME, 0.0D, 1, 0.0D);
   		   	  flame1.sendToLocation(p1);
   		   	  flame2.sendToLocation(p2);
            }
            else if (!p.isInsideVehicle()) {
   		   	  ParticleLib18 flame = new ParticleLib18(net.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType.FLAME, 0.1000000014901161D, 4, 0.300000011920929D);
   		   	  flame.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
            }

          }

          this.i += 1.0D;
        }
      }
    }
  }
}