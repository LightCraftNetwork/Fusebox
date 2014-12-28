package com.lightcraftmc.fusebox.bungeecord;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;	

public class BCHooks {

	public static HashMap<String, Integer> players = new HashMap<String, Integer>();

	public static void hook(String channel, Player player, byte[] message){
		checkOnline(channel, player, message);
	}

	public static void checkOnline(String channel, Player player, byte[] message){
		try{
			ByteArrayDataInput in = ByteStreams.newDataInput(message);
			
			if (!channel.equals("BungeeCord")) return;
			
			if(!in.readUTF().equalsIgnoreCase("PlayerList")) return;
			
			String server = in.readUTF();
			String builder = in.readUTF();
			
			boolean removeOne = false;
			
			for(String s : builder.split(", ")){
				if(s.equalsIgnoreCase("")){
					removeOne = true;
				}
			}
			
			int r = builder.split(", ").length;
			
			players.remove(server);
			
			if(removeOne) r = r - 1;
			
			players.put(server, r);
			
		}catch(Exception ex){
			
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void requestPlayerList(){
		if(Bukkit.getOnlinePlayers().length > 0){
		for(String srv : Servers.getServers()){
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("PlayerList");  
			out.writeUTF(srv);
			Bukkit.getOnlinePlayers()[0].sendPluginMessage(Bukkit.getServer().getPluginManager().getPlugin("HubPlugin"), "BungeeCord", out.toByteArray());
		}
		}
	}
}
