package com.lightcraftmc.fusebox.tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.lightcraftmc.fusebox.Core;
import com.lightcraftmc.fusebox.tools.areaSelectionTool.SelectionTool;

public class ToolManager implements Listener{

	private static ToolManager manager = new ToolManager();
	private List<Tool> tools = new ArrayList<Tool>();

	private ToolManager(){
		Bukkit.getPluginManager().registerEvents(this, Core.getInstance());
		addTool(SelectionTool.getInstance());
	}

	public static ToolManager getInstance(){
		return manager;
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


	@EventHandler(priority=EventPriority.HIGHEST)
	public void onInventoryClick(InventoryClickEvent event){
		ItemStack is = event.getCursor();
		if(is!=null){
			ItemMeta im;
			if((im=is.getItemMeta())!=null){
				String name = im.getDisplayName();
				if(name!=null){
					for(Tool t : tools){
						if(t.getTool().getType()==is.getType()&&t.getName().equals(name)){
							t.onInventoryClickCursor(event);
						}
					}
				}
			}
		}
		is = event.getCurrentItem();
		if(is!=null){
			ItemMeta im;
			if((im=is.getItemMeta())!=null){
				String name = im.getDisplayName();
				if(name!=null){
					for(Tool t : tools){
						if(t.getTool().getType()==is.getType()&&t.getName().equals(name)){
							t.onInventoryClickItem(event);
						}
					}
				}
			}
		}
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event){
		ItemStack is = event.getPlayer().getItemInHand();
		if(is!=null){
			ItemMeta im;
			if((im=is.getItemMeta())!=null){
				String name = im.getDisplayName();
				if(name!=null){
					for(Tool t : tools){
						if(t.getTool().getType()==is.getType()&&t.getName().equals(name)){
							t.onClick(event);
						}
					}
				}
			}
		}
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerDropItem(PlayerDropItemEvent event){
		ItemStack is = event.getItemDrop().getItemStack();
		if(is!=null){
			ItemMeta im;
			if((im=is.getItemMeta())!=null){
				String name = im.getDisplayName();
				if(name!=null){
					for(Tool t : tools){
						if(t.getTool().getType()==is.getType()&&t.getName().equals(name)){
							t.onDrop(event);
						}
					}
				}
			}
		}
	}

	public void givePlayerTool(Player p, Tool t){
		p.getInventory().addItem(t.getTool().clone());
	}

	public boolean contains(Inventory inv, Tool t){
		for(int i = 0; i < inv.getSize(); i++){
			ItemStack is = inv.getItem(i);
			if(is!=null){
				ItemMeta im;
				if((im=is.getItemMeta())!=null){
					String name = im.getDisplayName();
					if(name!=null){
						if(t.getTool().getType()==is.getType()&&t.getName().equals(name)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		for(Tool t : tools)t.onPlayerJoin(event);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		for(Tool t : tools)t.onPlayerQuit(event);
	}

	@EventHandler
	public void onPlayerJoin(PlayerKickEvent event){
		for(Tool t : tools)t.onPlayerKick(event);
	}
}
