package net.lightcraftmc.fusebox.tools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SelectionTool extends Tool{

	private static SelectionTool instance = new SelectionTool();

	private SelectionTool(){
		super(Material.WOOD_SPADE, ChatColor.GREEN + "Selection Tool");
	}

	public static SelectionTool getInstance() {
		return instance;
	}

	@Override
	public void onClick(PlayerInteractEvent event) {

	}

	@Override
	public void onDrop(PlayerDropItemEvent event) {
		if(event.isCancelled())event.setCancelled(false);
		event.getItemDrop().remove();
	}

	@Override
	public void onInventoryClickItem(InventoryClickEvent event) {

	}

	@Override
	public void onInventoryClickCursor(InventoryClickEvent event) {

	}

}
