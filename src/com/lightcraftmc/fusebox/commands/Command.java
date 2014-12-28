package com.lightcraftmc.fusebox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright Elliott Olson (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public abstract class Command {


    /*
    *
    * You will also need to add the base command as usual in your plugin.yml.
    *
    */

    protected String name;
    protected String usage;
    protected String usagePrior;
    protected List<String> args = new ArrayList<String>();
    protected String alias;
    protected CommandSender sender;
    protected boolean bePlayer;
    protected Player player;
    protected String permission;

    public CommandResult run(CommandSender sender, String command, String[] preArgs){

        setSender(sender);
        setName(command);

        getArgs().clear();

        for (String string : preArgs){
            getArgs().add(string);
        }

        if (isBePlayer()){

            if (!(sender instanceof Player)){
                return CommandResult.NOT_PLAYER;
            }

        }

        if (!(sender.hasPermission(getPermission()))){

            return CommandResult.NO_PERMISSION;

        }

        return execute();

    }

    public abstract CommandResult execute();


    public String getDisplayUsage(){
        return ChatColor.DARK_AQUA + "/" + usagePrior + " " + ChatColor.GREEN + getName() + (hasAlias() ? ChatColor.GRAY + "/" + ChatColor.GREEN + getAlias() : "") + " " + ChatColor.RED + getUsage();
    }

    public boolean hasAlias(){
        return getAlias()!=null;
    }

    public String getUsagePrior() {
        return usagePrior;
    }

    public void setUsagePrior(String usagePrior) {
        this.usagePrior = usagePrior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public CommandSender getSender() {
        return sender;
    }

    public void setSender(CommandSender sender) {
        this.sender = sender;
    }

    public boolean isBePlayer() {
        return bePlayer;
    }

    public void setBePlayer(boolean bePlayer) {
        this.bePlayer = bePlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
