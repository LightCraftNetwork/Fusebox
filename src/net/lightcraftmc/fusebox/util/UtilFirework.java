package net.lightcraftmc.fusebox.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.lightcraftmc.fusebox.Core;
import net.minecraft.server.v1_8_R1.EntityFireworks;
import net.minecraft.server.v1_8_R1.PacketPlayOutEntityStatus;
import net.minecraft.server.v1_8_R1.World;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class UtilFirework extends EntityFireworks
{
  private static final Random rng = new Random();

  static Player[] players = UtilServer.getPlayers();

  boolean gone = false;

  public static void fireFirework(Location loc)
  {
    final Firework fw = (Firework)loc.getWorld().spawn(loc, Firework.class);
    FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(new Color[] { Color.YELLOW, Color.PURPLE }).with(FireworkEffect.Type.BURST).build();
    FireworkMeta fwm = fw.getFireworkMeta();
    fwm.clearEffects();
    fwm.addEffect(effect);
    fwm.setPower(0);
    fw.setFireworkMeta(fwm);
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
      public void run() { fw.detonate(); }

    }
    , 4L);
  }

  public UtilFirework(World world, Player[] p)
  {
    super(world);
    players = p;
    a(0.25F, 0.25F);
  }

  public void h()
  {
    if (this.gone) {
      return;
    }

    if (!this.world.isStatic) {
      this.gone = true;

      if ((players != null) && 
        (players.length > 0)) {
        for (Player player : players) {
          ((CraftPlayer)player).getHandle().playerConnection
            .sendPacket(new PacketPlayOutEntityStatus(this, 
            (byte)17));
        }

        die();
        return;
      }

      this.world.broadcastEntityEffect(this, (byte)17);
      die();
    }
  }

  public static void spawn(Location location, FireworkEffect effect, Player[] players)
  {
    try {
      UtilFirework firework = new UtilFirework(
        ((CraftWorld)location.getWorld()).getHandle(), players);
      FireworkMeta meta = ((Firework)firework.getBukkitEntity())
        .getFireworkMeta();
      meta.addEffect(effect);
      ((Firework)firework.getBukkitEntity()).setFireworkMeta(meta);
      firework.setPosition(location.getX(), location.getY(), 
        location.getZ());

      if (((CraftWorld)location.getWorld()).getHandle()
        .addEntity(firework))
        firework.setInvisible(true);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void launchRandomFirework(Location loc, Player players)
  {
    List types = new ArrayList();
    types.add(FireworkEffect.Type.BALL);
    types.add(FireworkEffect.Type.BALL_LARGE);
    types.add(FireworkEffect.Type.BURST);
    types.add(FireworkEffect.Type.CREEPER);
    types.add(FireworkEffect.Type.STAR);
    Color c1 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    Color c2 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    Color fade = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    try {
      UtilFirework fireworker = new UtilFirework(
        ((CraftWorld)loc.getWorld()).getHandle(), new Player[] { players });
      Firework firework = (Firework)fireworker.getBukkitEntity();
      FireworkMeta meta = firework.getFireworkMeta();
      FireworkEffect.Builder effect = FireworkEffect.builder();
      effect.withColor(new Color[] { c1, c2 });
      effect.withFade(fade);

      effect.with((FireworkEffect.Type)types.get(rng.nextInt(types.size())));

      if (rng.nextBoolean()) {
        effect.withFlicker();
      }
      if (rng.nextBoolean())
        effect.withTrail();
      meta.addEffect(effect.build());
      if (meta == null)
        return;
      firework.setFireworkMeta(meta);

      fireworker.setPosition(loc.getX(), loc.getY(), loc.getZ());

      if (((CraftWorld)loc.getWorld()).getHandle()
        .addEntity(fireworker))
        fireworker.setInvisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void launchRandomFirework(Location loc)
  {
    List types = new ArrayList();
    types.add(FireworkEffect.Type.BALL);
    types.add(FireworkEffect.Type.BALL_LARGE);
    types.add(FireworkEffect.Type.BURST);
    types.add(FireworkEffect.Type.CREEPER);
    types.add(FireworkEffect.Type.STAR);
    Color c1 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    Color c2 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    Color fade = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    try {
      UtilFirework fireworker = new UtilFirework(
        ((CraftWorld)loc.getWorld()).getHandle(), players);
      Firework firework = (Firework)fireworker.getBukkitEntity();
      FireworkMeta meta = firework.getFireworkMeta();
      FireworkEffect.Builder effect = FireworkEffect.builder();
      effect.withColor(new Color[] { c1, c2 });
      effect.withFade(fade);

      effect.with((FireworkEffect.Type)types.get(rng.nextInt(types.size())));

      if (rng.nextBoolean()) {
        effect.withFlicker();
      }
      if (rng.nextBoolean())
        effect.withTrail();
      meta.addEffect(effect.build());
      if (meta == null)
        return;
      firework.setFireworkMeta(meta);

      fireworker.setPosition(loc.getX(), loc.getY(), loc.getZ());

      if (((CraftWorld)loc.getWorld()).getHandle()
        .addEntity(fireworker))
        fireworker.setInvisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void launchRandomBurstFirework(Location loc)
  {
    List types = new ArrayList();

    types.add(FireworkEffect.Type.BURST);

    Color c1 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    Color c2 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    Color fade = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    try {
      UtilFirework fireworker = new UtilFirework(
        ((CraftWorld)loc.getWorld()).getHandle(), players);
      Firework firework = (Firework)fireworker.getBukkitEntity();
      FireworkMeta meta = firework.getFireworkMeta();
      FireworkEffect.Builder effect = FireworkEffect.builder();
      effect.withColor(new Color[] { c1, c2 });
      effect.withFade(fade);

      effect.with((FireworkEffect.Type)types.get(rng.nextInt(types.size())));

      if (rng.nextBoolean()) {
        effect.withFlicker();
      }
      if (rng.nextBoolean())
        effect.withTrail();
      meta.addEffect(effect.build());
      if (meta == null)
        return;
      firework.setFireworkMeta(meta);

      fireworker.setPosition(loc.getX(), loc.getY(), loc.getZ());

      if (((CraftWorld)loc.getWorld()).getHandle()
        .addEntity(fireworker))
        fireworker.setInvisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void launchRandomBurstFirework(Location loc, Player players)
  {
    List types = new ArrayList();

    types.add(FireworkEffect.Type.BURST);

    Color c1 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    Color c2 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    Color fade = Color.fromRGB(rng.nextInt(255), rng.nextInt(255), 
      rng.nextInt(255));
    try {
      UtilFirework fireworker = new UtilFirework(
        ((CraftWorld)loc.getWorld()).getHandle(), new Player[] { players });
      Firework firework = (Firework)fireworker.getBukkitEntity();
      FireworkMeta meta = firework.getFireworkMeta();
      FireworkEffect.Builder effect = FireworkEffect.builder();
      effect.withColor(new Color[] { c1, c2 });
      effect.withFade(fade);

      effect.with((FireworkEffect.Type)types.get(rng.nextInt(types.size())));

      if (rng.nextBoolean()) {
        effect.withFlicker();
      }
      if (rng.nextBoolean())
        effect.withTrail();
      meta.addEffect(effect.build());
      if (meta == null)
        return;
      firework.setFireworkMeta(meta);

      fireworker.setPosition(loc.getX(), loc.getY(), loc.getZ());

      if (((CraftWorld)loc.getWorld()).getHandle()
        .addEntity(fireworker))
        fireworker.setInvisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}