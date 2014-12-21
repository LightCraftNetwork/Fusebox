package net.lightcraftmc.fusebox.util;

import java.util.HashMap;

import net.lightcraftmc.fusebox.util.effects.EffectManager;
import net.lightcraftmc.fusebox.util.extra.ExtraManager;
import net.lightcraftmc.fusebox.util.particle.CircleParticle;
import net.lightcraftmc.fusebox.util.update.UpdateType;
import net.lightcraftmc.fusebox.util.update.event.UpdateEvent;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UtilLocation
implements Listener
{
public static HashMap<Player, Location> locationEverySecond = new HashMap();
public static HashMap<Player, Location> locationEvery2Second = new HashMap();

@EventHandler
public void OnPlayerLeft(PlayerQuitEvent e)
{
  Player p = e.getPlayer();
  if (locationEverySecond.containsKey(p)) {
    locationEverySecond.remove(p);
  }
  if (locationEvery2Second.containsKey(p))
    locationEvery2Second.remove(p);
}

@EventHandler
public void onLeave(PlayerKickEvent e)
{
  Player p = e.getPlayer();

  if (locationEverySecond.containsKey(p)) {
    locationEverySecond.remove(p);
  }
  if (locationEvery2Second.containsKey(p))
    locationEvery2Second.remove(p);
}

@EventHandler
public void LocationUpdater(UpdateEvent event)
{
  if (event.getType() == UpdateType.SEC)
  {
    for (Player p : CircleParticle.effect2.keySet()) {
      locationEverySecond.remove(p);
      locationEverySecond.put(p, p.getLocation());
    }
    for (Player p : EffectManager.effect3.keySet())
    {
      if (ExtraManager.hasExtraEffect(p)) {
        locationEvery2Second.remove(p);
        locationEvery2Second.put(p, p.getLocation());
      }
    }
  }
}
}