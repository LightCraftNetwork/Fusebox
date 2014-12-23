package net.lightcraftmc.fusebox.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.lightcraftmc.fusebox.Core;
import net.lightcraftmc.fusebox.util.particles18.ParticleLib18;
import net.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType;

public class UtilPlayParticle {

	public static void playTreasureAnimation(final Location l,
			final ParticleType particle) {
		final int i = Bukkit.getScheduler()
				.runTaskTimer(Core.getInstance(), new Runnable() {
					float LineNumber = 1.0F;
					float j = 0.0F;
					float radius = 0.05F;
					float heightEcart = 0.01F;

					public void run() {
						for (int k = 0; k < this.LineNumber; k++) {
							l.add(Math.cos(this.j) * this.radius, this.j
									* this.heightEcart, Math.sin(this.j)
									* this.radius);
							ParticleLib18 res = new ParticleLib18(particle,
									0.0D, 1, 0.0D);
							res.sendToLocation(l);
						}

						this.j += 0.2F;
						if (this.j > 50.0F) {
							this.j = 0.0F;
						}
					}
				}, 1L, 1L).getTaskId();

		Bukkit.getServer().getScheduler()
				.runTaskLater(Core.getInstance(), new Runnable() {
					public void run() {
						Bukkit.getScheduler().cancelTask(i);
					}
				}, 40L);
	}
}
