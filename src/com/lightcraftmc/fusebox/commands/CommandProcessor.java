package com.lightcraftmc.fusebox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Copyright Elliott Olson (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class CommandProcessor implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        String cmd = "help";

        if (args.length > 0){
            cmd = args[0];
        }

        for (Command c : Commands.getCommandList()){

            if (c.getName().equalsIgnoreCase(cmd) || (c.hasAlias() && c.getAlias().equalsIgnoreCase(cmd))){

                CommandResult result = c.run(sender, c.getName(), args);
                if (result == CommandResult.INVALID_USAGE){

                    sender.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + ">> " + ChatColor.GRAY + "Usage: " + ChatColor.DARK_AQUA + c.getDisplayUsage());

                } else if (result == CommandResult.NO_PERMISSION){

                    sender.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + ">> " + ChatColor.GRAY + "You don't have permission to do that.");

                } else if (result == CommandResult.NOT_PLAYER){

                    sender.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + ">> /" + c.getUsagePrior() + ChatColor.GRAY + " You must be a player to do that.");

                }

                return true;

            }

        }

        sender.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + ">> " + ChatColor.GRAY + "Unknown sub-command. Try " + ChatColor.DARK_AQUA + "/bs help");
        return true;
    }

}
