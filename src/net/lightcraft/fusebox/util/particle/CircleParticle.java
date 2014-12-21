package net.lightcraft.fusebox.util.particle;

import java.util.HashMap;

import net.lightcraft.fusebox.util.effects.EffectManager;
import net.lightcraft.fusebox.util.extra.ExtraManager;
import net.lightcraft.fusebox.util.particles18.ParticleLib18;
import net.lightcraft.fusebox.util.update.UpdateType;
import net.lightcraft.fusebox.util.update.event.UpdateEvent;
import net.lightcraft.fusebox.util.UtilLocation;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class CircleParticle implements Listener {
	double radialsPerStep = 0.1963495408493621D;
	float radius = 0.4F;
	float step = 0.0F;

	public static HashMap<Player, ParticleManager.ParticleType> effect2 = new HashMap();

	public static void Activate(Player p, ParticleManager.ParticleType effect) {
		if (!EffectManager.hasEffect(p)) {
			EffectManager.effect3.put(p, EffectManager.EffectType.CircleEffect);

			if (!ParticleManager.hasCircleEffect(p)) {
				effect2.put(p, effect);
				UtilLocation.locationEverySecond.put(p, p.getLocation());
			} else {
				effect2.remove(p);
				effect2.put(p, effect);
				UtilLocation.locationEverySecond.put(p, p.getLocation());
			}
		} else {
			if (ParticleManager.hasCircleEffect(p)) {
				effect2.remove(p);
				effect2.put(p, effect);
				UtilLocation.locationEverySecond.put(p, p.getLocation());
			}
			if (ExtraManager.hasExtraEffect(p))
				p.sendMessage("Too many effects.");
		}
	}

	@EventHandler
	public void ParticleAura(UpdateEvent event) {
		if (event.getType() == UpdateType.TICK) {
			for (Player p : effect2.keySet()) {
				if (EffectManager.getEffect(p) == EffectManager.EffectType.CircleEffect) {
					if (p.isValid()) {
						ParticleManager.ParticleType effect = (ParticleManager.ParticleType) effect2
								.get(p);

						Vector v = new Vector(Math.cos(this.radialsPerStep
								* this.step)
								* this.radius, 0.0D,
								Math.sin(this.radialsPerStep * this.step)
										* this.radius);
						Location l = (Location) UtilLocation.locationEverySecond
								.get(p);
						Location loc = new Location(p.getWorld(), l.getX(),
								l.getY() + 2.0D, l.getZ());

						loc.add(v);

						if (effect == ParticleManager.ParticleType.Heart) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.HEART,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;
						}

						if (effect == ParticleManager.ParticleType.Music) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.NOTE,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.Flame) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.FLAME,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.Water) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.DRIP_WATER,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.Lava) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.DRIP_LAVA,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.Spark) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.FIREWORKS_SPARK,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.Witch) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.SPELL_WITCH,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.Enchantement) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.ENCHANTMENT_TABLE,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.AngryVillager) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.VILLAGER_ANGRY,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.MagicCrit) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.CRIT_MAGIC,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.Portal) {
							loc.setY(loc.getY() - 0.4000000059604645D);
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.PORTAL,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;
						}
						if (effect == ParticleManager.ParticleType.Rainbow) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.REDSTONE,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.Slime) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.SLIME,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.Snow) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.SNOWBALL,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}
						if (effect == ParticleManager.ParticleType.SnowShovel) {
							ParticleLib18 snowshovel = new ParticleLib18(
									net.lightcraft.fusebox.util.particles18.ParticleLib18.ParticleType.SNOW_SHOVEL,
									0.0D, 1, 0.0001D);
							snowshovel.sendToLocation(loc);
							break;

						}

						loc.subtract(v);
					}

				}

			}

		}
		this.step += 1.0F;
	}

}