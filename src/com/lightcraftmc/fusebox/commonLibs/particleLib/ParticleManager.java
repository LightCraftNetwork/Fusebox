package com.lightcraftmc.fusebox.commonLibs.particleLib;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import com.lightcraftmc.fusebox.Core;
import com.lightcraftmc.fusebox.commonLibs.effects.EffectManager;
import com.lightcraftmc.fusebox.commonLibs.utilities.UtilLocation;

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