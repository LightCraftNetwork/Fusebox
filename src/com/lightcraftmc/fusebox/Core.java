package com.lightcraftmc.fusebox;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.lightcraftmc.fusebox.build.listener.BuildSettings;
import com.lightcraftmc.fusebox.bungeecord.BCHooks;
import com.lightcraftmc.fusebox.commands.Commands;
import com.lightcraftmc.fusebox.commands.ExampleCommand;
import com.lightcraftmc.fusebox.commands.ToolCommand;
import com.lightcraftmc.fusebox.datatool.interact.InteractListener;
import com.lightcraftmc.fusebox.event.ServerTickScheduler;
import com.lightcraftmc.fusebox.gameapi.GameData;
import com.lightcraftmc.fusebox.menu.MenuListener;
import com.lightcraftmc.fusebox.player.PlayerManager;
import com.lightcraftmc.fusebox.tools.ToolManager;
import com.lightcraftmc.fusebox.util.effects.EffectManager;
import com.lightcraftmc.fusebox.util.extra.ExtraManager;
import com.lightcraftmc.fusebox.util.item.ItemTools;
import com.lightcraftmc.fusebox.util.particle.ParticleManager;

public class Core extends JavaPlugin implements PluginMessageListener{

	private static Core main;

	@Override
	public void onEnable(){
		main = this;
		init();
		setupBungee();
		try{
			GameData.init();
		}catch(Exception ex){
			System.out.println("Map Data file missing/not game server.");
		}
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
		Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), this);
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

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(label.equalsIgnoreCase("datatool")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(!p.isOp()){
					p.sendMessage("§cYou do not have permission to do that.");
					return false;
				}
			}else{
				return false;
			}
			if(args.length == 0){
				sender.sendMessage("§c/datatool tag");
				return false;
			}
			String data = args[0].toLowerCase();
			try{
				GameData.init();
			}catch(Exception ex){
				sender.sendMessage("Something failed. world-data.txt does not exist in the main world folder.");
				return false;
			}
			Player p = (Player)sender;
			Material m = Material.IRON_AXE;
			if(p.getItemInHand() != null){
				m = p.getItemInHand().getType();
			}
			p.setItemInHand(ItemTools.setName(new ItemStack(m), "§7Edit Wand: §b" + data));
			
		}
		
		
		if(label.equalsIgnoreCase("rlgamedata")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(!p.isOp()){
					p.sendMessage("§cYou do not have permission to do that.");
					return false;
				}
			}
			try{
				GameData.init();
			}catch(Exception ex){
				sender.sendMessage("Something failed. world-data.txt does not exist in the main world folder.");
			}
			
		}
		return false;
	}


}
