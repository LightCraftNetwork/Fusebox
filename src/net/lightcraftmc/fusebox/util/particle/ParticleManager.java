package net.lightcraftmc.fusebox.util.particle;

import net.lightcraftmc.fusebox.Core;
import net.lightcraftmc.fusebox.util.effects.EffectManager;
import net.lightcraftmc.fusebox.util.UtilLocation;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class ParticleManager
  implements Listener  
{	
	
  public static boolean hasCircleEffect(Player p)
  {
    if (CircleParticle.effect2.containsKey(p)) {
      return true;
    }
    return false;
  }

  public static void removeCircleEffect(Player p)
  {
    EffectManager.effect3.remove(p);
    UtilLocation.locationEverySecond.remove(p);
    CircleParticle.effect2.remove(p);
  }
  
  public static void registerEvents(Core plugin)
  {
    PluginManager pm = plugin.getServer().getPluginManager();
    pm.registerEvents(new CircleParticle(), plugin);
  }


  public static enum ParticleType
  {
    Heart, Music, Flame, Water, Lava, Spark, Reddust, Enchantement, Witch, AngryVillager, Portal, Snow, Slime, Rainbow, SnowShovel, MagicCrit;
  }
}