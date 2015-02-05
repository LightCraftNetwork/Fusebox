package com.lightcraftmc.fusebox.minigames.gameapi;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Copyright Elliott Olson (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class Team {

    public String name;
    public String shortName;
    public ChatColor color;

    public boolean joinable;
    public boolean hidden;
    public boolean canViewMembers;

    public Integer maximumSize;
    public Integer minimumSize;

    public List<Player> members = new ArrayList<Player>();

    public Team(String name, String shortName, ChatColor color){

        this.color = color;
        this.name = name;
        this.shortName = shortName;

    }


    public void addMember(Player player){

        members.add(player);

        Data data = new Data();
            String name1 = data.getName();
            ChatColor color1 = data.getColor();
            PreventionSet set1 = data.getSet();

        GameManager gameManager = new GameManager(name1, color1, set1);

        //Finish

    }

    public void removeMember(Player player){

        members.remove(player);

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public boolean isJoinable() {
        return joinable;
    }

    public void setJoinable(boolean joinable) {
        this.joinable = joinable;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isCanViewMembers() {
        return canViewMembers;
    }

    public void setCanViewMembers(boolean canViewMembers) {
        this.canViewMembers = canViewMembers;
    }

    public Integer getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(Integer maximumSize) {
        this.maximumSize = maximumSize;
    }

    public Integer getMinimumSize() {
        return minimumSize;
    }

    public void setMinimumSize(Integer minimumSize) {
        this.minimumSize = minimumSize;
    }

    public List<Player> getMembers() {
        return members;
    }

    public void setMembers(List<Player> members) {
        this.members = members;
    }
}
