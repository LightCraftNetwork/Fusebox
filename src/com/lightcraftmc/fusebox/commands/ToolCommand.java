package com.lightcraftmc.fusebox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.lightcraftmc.fusebox.util.AbstractCommand;

public class ToolCommand extends AbstractCommand {

	public ToolCommand(String command) {
		super(command);
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String Label,
			String[] args) {
		if(!s.hasPermission("fusebox.tools"))return false;
		if(args.length==0){
			s.sendMessage(ChatColor.GREEN + "-=-=-=-=-=-=-[Tools]-=-=-=-=-=-=-");
			s.sendMessage(ChatColor.AQUA + "/tools selection");
			return true;
		}else{
			/*switch(args[0].toLowerCase()){
			case "selection":{
				if(!(s instanceof Player)){
					s.sendMessage("Command must be run by player");
					return false;
				}
				Tool t = SelectionTool.getInstance();
				Player player = (Player)s;
				if(ToolManager.getInstance().contains(player.getInventory(), t)){
					s.sendMessage(ChatColor.AQUA + "You already have that tool in your inventory");
					return false;
				}
				ToolManager.getInstance().givePlayerTool(player, t);
				return true;
			}
			default:{
				s.sendMessage(ChatColor.RED + "Unknown argument '" + args[0] + "', for command /" + cmd.getLabel() + "");
				return false;
			}
			}*/
		}
        return false;
	}

}
