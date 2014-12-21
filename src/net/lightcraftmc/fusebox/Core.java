package net.lightcraftmc.fusebox;

import net.lightcraftmc.fusebox.player.PlayerManager;
import net.lightcraftmc.fusebox.util.effects.EffectManager;
import net.lightcraftmc.fusebox.util.extra.ExtraManager;
import net.lightcraftmc.fusebox.util.particle.ParticleManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.arrayprolc.menu.MenuListener;

public class Core extends JavaPlugin{

	private static Core main;
	
    @Override
    public void onEnable(){
    	main = this;
    	init();
    }
    
    private void init(){
    	PlayerManager.getInstance();
    	EffectManager.registerEvents(this);
    	ExtraManager.registerEvents(this);
	    ParticleManager.registerEvents(this);
	    Bukkit.getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }
    
    public static Core getInstance(){
    	return main;
    }


    @Override
    public void onDisable(){
    	main = null;
    }

}
