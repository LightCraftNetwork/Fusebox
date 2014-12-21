package net.lightcraft.fusebox;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin{

	private static Core main;
	
    @Override
    public void onEnable(){
    	main = this;
    }
    
    public static Core getInstance(){
    	return main;
    }


    @Override
    public void onDisable(){
    	main = null;
    }

}
