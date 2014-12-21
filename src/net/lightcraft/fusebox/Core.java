package net.lightcraft.fusebox;

import java.util.ArrayList;

import net.lightcraft.fusebox.util.effects.EffectManager;
import net.lightcraft.fusebox.util.extra.ExtraManager;
import net.lightcraft.fusebox.util.particle.ParticleManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin{

	private static Core main;
	
    @Override
    public void onEnable(){
    	main = this;
    }
    
    private void init(){
    	EffectManager.registerEvents(this);
    	ExtraManager.registerEvents(this);
	    ParticleManager.registerEvents(this);
    }
    
    public static Core getInstance(){
    	return main;
    }


    @Override
    public void onDisable(){
    	main = null;
    }

}
