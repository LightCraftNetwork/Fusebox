package net.lightcraftmc.fusebox.tools;

import java.util.ArrayList;
import java.util.List;

import net.lightcraftmc.fusebox.Core;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ToolManager implements Listener{

	private static ToolManager manager = new ToolManager();
	private List<Tool> tools = new ArrayList<Tool>();
	
	private ToolManager(){
		Bukkit.getPluginManager().registerEvents(this, Core.getInstance());
	}
	
	public void addTool(Tool t){
		if(getTool(t.getClass())!=null){
			System.out.print("[Core] Adding duplicate tool " + t.getClass());
			System.out.print("[Core] This in not reccomended");
		}
		tools.add(t);
	}
	
	public Tool getTool(Class<? extends Tool> tool){
		for(Tool t : tools){
			if(t.getClass().getName().equals(tool.getName())){
				return t;
			}
		}
		return null;
	}
	
	public void removeTool(Tool t){
		if(tools.contains(t)){
			tools.remove(t);
		}
	}
	
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		ItemStack is = event.getCursor();
		ItemMeta im;
		if((im=is.getItemMeta())!=null){
			String name = im.getDisplayName();
			for(Tool t : tools){
				if(t.getTool().getType()==is.getType()&&t.getName().equals(name)){
					t.onInventoryClickCursor(event);
				}
			}
		}
		is = event.getCurrentItem();
		if((im=is.getItemMeta())!=null){
			String name = im.getDisplayName();
			for(Tool t : tools){
				if(t.getTool().getType()==is.getType()&&t.getName().equals(name)){
					t.onInventoryClickItem(event);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		ItemStack is = event.getPlayer().getItemInHand();
		ItemMeta im;
		if((im=is.getItemMeta())!=null){
			String name = im.getDisplayName();
			for(Tool t : tools){
				if(t.getTool().getType()==is.getType()&&t.getName().equals(name)){
					t.onClick(event);
				}
			}
		}
	}
}
