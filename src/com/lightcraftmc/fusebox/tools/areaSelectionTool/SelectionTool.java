package com.lightcraftmc.fusebox.tools.areaSelectionTool;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.lightcraftmc.fusebox.commonLibs.utilities.UtilLocation;
import com.lightcraftmc.fusebox.tools.Tool;

public class SelectionTool extends Tool{

	private static SelectionTool instance = new SelectionTool();
	private Map<String, String> location1 = new HashMap<String, String>();
	private Map<String, String> location2 = new HashMap<String, String>();

	private SelectionTool(){
		super(Material.WOOD_SPADE, ChatColor.GREEN + "Selection Tool");
	}

	public static SelectionTool getInstance() {
		return instance;
	}

	@Override
	public void onClick(PlayerInteractEvent event) {
		event.setCancelled(true);
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR)||event.getAction().equals(Action.RIGHT_CLICK_BLOCK)&&event.getClickedBlock()!=null&&event.getClickedBlock().getLocation()!=null){
			location2.put(event.getPlayer().getUniqueId().toString(), UtilLocation.locationToString(event.getClickedBlock().getLocation()));
			event.getPlayer().sendMessage("Location 2 set");
			return;
		} else if(event.getAction().equals(Action.LEFT_CLICK_AIR)||event.getAction().equals(Action.LEFT_CLICK_BLOCK)&&event.getClickedBlock()!=null&&event.getClickedBlock().getLocation()!=null){
			location1.put(event.getPlayer().getUniqueId().toString(), UtilLocation.locationToString(event.getClickedBlock().getLocation()));
			event.getPlayer().sendMessage("Location 1 set");
			return;
		}
	}

	@Override
	public void onDrop(PlayerDropItemEvent event) {
		if(event.isCancelled())event.setCancelled(false);
		event.getItemDrop().remove();
	}

	@Override
	public void onInventoryClickItem(InventoryClickEvent event) {
		if(event.isCancelled())event.setCancelled(false);
	}

	@Override
	public void onInventoryClickCursor(InventoryClickEvent event) {
		if(event.isCancelled())event.setCancelled(false);
	}

	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {

	}

	@Override
	public void onPlayerKick(PlayerKickEvent event) {
		String uuid = event.getPlayer().getUniqueId().toString();
		if(location1.containsKey(uuid)){
			location1.remove(uuid);
		}
		if(location2.containsKey(uuid)){
			location2.remove(uuid);
		}
	}

	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		String uuid = event.getPlayer().getUniqueId().toString();
		if(location1.containsKey(uuid)){
			location1.remove(uuid);
		}
		if(location2.containsKey(uuid)){
			location2.remove(uuid);
		}
	}

	public boolean hasLocation1(Player player){
		return location1.containsKey(player.getUniqueId().toString());
	}
	
	public boolean hasLocation2(Player player){
		return location2.containsKey(player.getUniqueId().toString());
	}

	public Location getLocation1(Player player){
		return UtilLocation.getLocation(location1.get(player.getUniqueId().toString()));
	}
	
	public Location getLocation2(Player player){
		return UtilLocation.getLocation(location2.get(player.getUniqueId().toString()));
	}
	
}
