package net.lightcraftmc.fusebox.commands;

import org.bukkit.ChatColor;

/**
 * Copyright Elliott Olson (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class ExampleCommand extends Command{

    public ExampleCommand(){

        setAlias("e");
        setPermission("example.permission.example");
        setUsage("");
        setName("example");
        setBePlayer(true);
        setUsagePrior("test");

    }


    @Override
    public CommandResult execute() {

        if (getArgs().size() == 1){

            player.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + ">> " + ChatColor.GRAY + "This command successfully works!");

        }

        return null;
    }
}
