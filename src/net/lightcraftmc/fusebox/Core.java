package net.lightcraftmc.fusebox;

import net.lightcraftmc.fusebox.anticheat.AntiCheat;
import net.lightcraftmc.fusebox.build.listener.BuildSettings;
import net.lightcraftmc.fusebox.commands.Commands;
import net.lightcraftmc.fusebox.commands.ExampleCommand;
import net.lightcraftmc.fusebox.commands.ToolCommand;
import net.lightcraftmc.fusebox.event.ServerTickScheduler;
import net.lightcraftmc.fusebox.menu.MenuListener;
import net.lightcraftmc.fusebox.player.PlayerManager;
import net.lightcraftmc.fusebox.tools.ToolManager;
import net.lightcraftmc.fusebox.util.effects.EffectManager;
import net.lightcraftmc.fusebox.util.extra.ExtraManager;
import net.lightcraftmc.fusebox.util.particle.ParticleManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
	    Bukkit.getServer().getPluginManager().registerEvents(new BuildSettings(), this);
	    AntiCheat.init();
	    ToolManager.getInstance();
	    ServerTickScheduler.schedule();
    	new ToolCommand("tools").register();

        //Setup Commands
        //setupCommands();
        //getCommand("test").setExecutor(new CommandProcessor());

    }
    
    public static Core getInstance(){
    	return main;
    }


    @Override
    public void onDisable(){
    	main = null;
    }


    public void setupCommands(){
        Commands.getCommandList().add(new ExampleCommand());
    }


}
