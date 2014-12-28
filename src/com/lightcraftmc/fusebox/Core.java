package com.lightcraftmc.fusebox;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.lightcraftmc.fusebox.build.listener.BuildSettings;
import com.lightcraftmc.fusebox.bungeecord.BCHooks;
import com.lightcraftmc.fusebox.commands.Commands;
import com.lightcraftmc.fusebox.commands.ExampleCommand;
import com.lightcraftmc.fusebox.commands.ToolCommand;
import com.lightcraftmc.fusebox.event.ServerTickScheduler;
import com.lightcraftmc.fusebox.menu.MenuListener;
import com.lightcraftmc.fusebox.player.PlayerManager;
import com.lightcraftmc.fusebox.tools.ToolManager;
import com.lightcraftmc.fusebox.util.effects.EffectManager;
import com.lightcraftmc.fusebox.util.extra.ExtraManager;
import com.lightcraftmc.fusebox.util.particle.ParticleManager;

public class Core extends JavaPlugin implements PluginMessageListener{

	private static Core main;

	@Override
	public void onEnable(){
		main = this;
		init();
		setupBungee();
	}

	private void setupBungee(){
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
	}

	private void init(){
		PlayerManager.getInstance();
		EffectManager.registerEvents(this);
		ExtraManager.registerEvents(this);
		ParticleManager.registerEvents(this);
		Bukkit.getServer().getPluginManager().registerEvents(new MenuListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BuildSettings(), this);
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

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		BCHooks.hook(channel, player, message);

	}


}
