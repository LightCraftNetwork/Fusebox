package net.lightcraft.fusebox.util.extra.extraeffects;

import net.lightcraft.fusebox.util.effects.EffectManager;
import net.lightcraft.fusebox.util.extra.ExtraManager;
import net.lightcraft.fusebox.util.particles18.ParticleLib18;
import net.lightcraft.fusebox.util.update.UpdateType;
import net.lightcraft.fusebox.util.update.event.UpdateEvent;
import net.lightcraft.fusebox.util.MathUtils;
import net.lightcraft.fusebox.util.UtilVector;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class Shield
  implements Listener
{
  private int particles = 120;
  private int particlesPerIteration = 8;
  private float size = 1.0F;
  private float xFactor = 1.4F;
  private float yFactor = 1.4F;
  private float zFactor = 1.4F;
  private float yOffset = 1.0F;
  private double xRotation;
  private double yRotation;
  private double zRotation = 0.0D;
  private int step;

  @EventHandler
  public void LocationUpdater(UpdateEvent event)
  {
    if (event.getType() == UpdateType.TICK)
    {
      for (Player p : EffectManager.effect3.keySet())
      {
        if (EffectManager.getEffect(p) == EffectManager.EffectType.Shield)
        {
          if (p.isValid())
          {
            if (ExtraManager.isMoving(p))
            {
              Location location = p.getLocation();
              Vector vector = new Vector();

              for (int i = 0; i < this.particlesPerIteration; i++) {
                this.step += 1;

                float t = 3.141593F / this.particles * this.step;
                float r = MathUtils.sin(t) * this.size;
                float s = r * 3.141593F * t;

                vector.setX(this.xFactor * r * MathUtils.cos(s));
                vector.setZ(this.zFactor * r * MathUtils.sin(s));
                vector.setY(this.yFactor * MathUtils.cos(t) + this.yOffset);

                UtilVector.rotateVector(vector, this.xRotation, this.yRotation, this.zRotation);

     		    ParticleLib18 crit = new ParticleLib18(net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.CRIT_MAGIC, 0.0D, 1, 0.0D);
     		    crit.sendToLocation(location.add(vector));

     		    location.subtract(vector);
              }

            }
            else if (!p.isInsideVehicle()) {
   		   	  ParticleLib18 crit = new ParticleLib18(net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.CRIT_MAGIC, 0.1000000014901161D, 4, 0.300000011920929D);
   		   	  crit.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
            }
          }
        }
      }
    }
  }
}