package com.lightcraftmc.fusebox.commonLibs;

import org.bukkit.Bukkit;

import com.lightcraftmc.fusebox.Core;

public class Cooldown {
	double t;
	String n;
	boolean over = false;
	double count = 0;

	public Cooldown(double ticksCooldown, String title, boolean shouldStart){
		t = ticksCooldown; n = title;
		if(shouldStart){
			schedule();
		}
	}

	public Cooldown(double ticksCooldown, String title){
		new Cooldown(ticksCooldown, title, true);
	}

	public void schedule(){
		doRepeat();
	}

	public boolean isOver() { if(count >= t) over = true; return over; }

	/*
	 * This should be alright on CPU, needs a bit of testing though.
	 */

	private void doRepeat(){
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable(){
			public void run(){
				count++;
				if(!isOver()){
					doRepeat();
					return;
				}
			}
		}, 1);
	}

	public double getTimeRemaining(){
		return t - count;
	}

}
