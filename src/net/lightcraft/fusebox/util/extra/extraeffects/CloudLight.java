package net.lightcraft.fusebox.util.extra.extraeffects;

import net.lightcraft.fusebox.util.effects.EffectManager;
import net.lightcraft.fusebox.util.extra.ExtraManager;
import net.lightcraft.fusebox.util.particles18.ParticleLib18;
import net.lightcraft.fusebox.util.update.UpdateType;
import net.lightcraft.fusebox.util.update.event.UpdateEvent;
import net.lightcraft.fusebox.util.UtilLocation;
import net.lightcraft.fusebox.util.UtilRandoms;
import net.lightcraft.fusebox.util.UtilityMath;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class CloudLight
implements Listener
{
@EventHandler
public void LocationUpdater(UpdateEvent event)
{
  if (event.getType() == UpdateType.TICK)
  {
    for (Player p : EffectManager.effect3.keySet())
    {
      if (EffectManager.getEffect(p) == EffectManager.EffectType.CloudLight)
      {
        if (p.isValid())
        {
          if (ExtraManager.isMoving(p))
          {
            Location l = p.getLocation();
            l.setY(p.getLocation().getY() + 3.700000047683716D);
 		   	  ParticleLib18 water = new ParticleLib18(net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.DRIP_WATER, 0.0D, 1, 0.0D);
 		   	  ParticleLib18 cloud = new ParticleLib18(net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.CLOUD, 0.0D, 3, 0.0D);
 		   	  ParticleLib18 cloud2 = new ParticleLib18(net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.CLOUD, 0.0D, 3, 0.0D);
 		   	  water.sendToLocation(l.add(UtilityMath.randomRangeFloat(-0.4F, 0.4F), 0.0D, UtilityMath.randomRangeFloat(-0.5F, 0.5F)));
 		   	  cloud.sendToLocation(l.add(UtilityMath.randomRangeFloat(-0.5F, 0.5F), 0.0D, UtilityMath.randomRangeFloat(-0.5F, 0.5F)));
 		   	  cloud2.sendToLocation(l.add(UtilityMath.randomRangeFloat(-0.5F, 0.5F), 0.0D, UtilityMath.randomRangeFloat(-0.5F, 0.5F)));
          }
          else if (!p.isInsideVehicle()) {
 		   	  ParticleLib18 waters = new ParticleLib18(net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.WATER_SPLASH, 0.1000000014901161D, 4, 0.300000011920929D);
 		   	  waters.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
          }
        }
      }
    }
  }
  if (event.getType() == UpdateType.SEC)
  {
    for (Player p : EffectManager.effect3.keySet())
    {
      if (EffectManager.getEffect(p) == EffectManager.EffectType.CloudLight)
      {
        if (p.isValid())
        {
          Vector direction = UtilRandoms.getRandomVector();

          Location loc = p.getLocation();
          if (UtilLocation.locationEvery2Second.containsKey(p)) {
            loc = (Location)UtilLocation.locationEvery2Second.get(p);
          }
          Location l2 = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
          l2.setY(p.getLocation().getY() + 3.700000047683716D);

          direction.setY(-Math.abs(direction.getY() - 2.0D));
          for (int i = 0; i < 28; i++) {
            float ratio = i * 0.06F / 28.0F;
            Vector v = direction.clone().multiply(ratio);
            l2.add(v);
		   	ParticleLib18 lava = new ParticleLib18(net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.DRIP_LAVA, 0.0D, 1, 0.0D);
		   	lava.sendToLocation(l2.add(v));
            if (i == 20) {
              l2.add(0.0D, 0.0D, 0.1000000014901161D);
            }
            l2.subtract(v);
          }
        }
      }
    }
  }
 }
}