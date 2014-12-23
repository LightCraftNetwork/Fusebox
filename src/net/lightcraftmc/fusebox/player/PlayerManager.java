package net.lightcraftmc.fusebox.player;

import java.util.ArrayList;
import java.util.List;

import net.lightcraftmc.fusebox.Core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerManager implements Listener{

	private List<Players> players = new ArrayList<Players>();
	private static PlayerManager manager = new PlayerManager();
	
	private PlayerManager(){
		Bukkit.getPluginManager().registerEvents(this, Core.getInstance());
		registerOnlinePlayers();
	}
	
	public static PlayerManager getInstance(){
		return manager;
	}
	
	public void registerOnlinePlayers(){
		for(Player player : Bukkit.getOnlinePlayers()){
			addPlayer(player);
		}
	}
	
	private Players addPlayer(Player player){
		Players pl;
		if((pl=getPlayer(player))!=null)return pl;
		pl = new Players(player);
		players.add(pl);
		return pl;
	}
	
	public Players getPlayer(Player player){
		for(Players pl : players){
			if(pl.getUUIDString().equals(player.getUniqueId().toString())){
				return pl;
			}
		}
		return null;
	}
	
	private void removePlayer(Player player){
		Players pl;
		if((pl=getPlayer(player))!=null){
			players.remove(pl);
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		addPlayer(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerKickEvent event){
		removePlayer(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		removePlayer(event.getPlayer());
	}
	
}
