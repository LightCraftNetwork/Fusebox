package com.lightcraftmc.fusebox.gameapi;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.lightcraftmc.fusebox.gameapi.Functionality.EndGameExecutor;
import com.lightcraftmc.fusebox.gameapi.Functionality.StartGameExecutor;
import com.lightcraftmc.fusebox.gameapi.Functionality.TeamExecutor;

import java.util.List;

/**
 * Copyright Elliott Olson (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class GameManager {

    public String name;
    public ChatColor color;
    public GameState state;
    public PreventionSet set;

    public boolean teamBased;
    public List<Team> teams;

    public boolean enforcePlayableArea;

    public String subTitle;
    public List<String> description;

    public Location lobby;

    public boolean kitsEnabled;

    public boolean useMotd;

    public StartGameExecutor startGameExecutor;
    public EndGameExecutor endGameExecutor;
    public TeamExecutor teamExecutor;

    public GameManager(String name, ChatColor color, PreventionSet set){

        this.name = name;
        this.color = color;
        this.set = set;

    }

    public void setup(){

        Data data = new Data();

        data.setName(this.name);
        data.setColor(this.color);
        data.setSet(this.set);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public PreventionSet getSet() {
        return set;
    }

    public void setSet(PreventionSet set) {
        this.set = set;
    }

    public boolean isTeamBased() {
        return teamBased;
    }

    public void setTeamBased(boolean teamBased) {
        this.teamBased = teamBased;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public boolean isEnforcePlayableArea() {
        return enforcePlayableArea;
    }

    public void setEnforcePlayableArea(boolean enforcePlayableArea) {
        this.enforcePlayableArea = enforcePlayableArea;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public Location getLobby() {
        return lobby;
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    public boolean isKitsEnabled() {
        return kitsEnabled;
    }

    public void setKitsEnabled(boolean kitsEnabled) {
        this.kitsEnabled = kitsEnabled;
    }

    public boolean isUseMotd() {
        return useMotd;
    }

    public void setUseMotd(boolean useMotd) {
        this.useMotd = useMotd;
    }

    public StartGameExecutor getStartGameExecutor() {
        return startGameExecutor;
    }

    public void setStartGameExecutor(StartGameExecutor startGameExecutor) {
        this.startGameExecutor = startGameExecutor;
    }

    public EndGameExecutor getEndGameExecutor() {
        return endGameExecutor;
    }

    public void setEndGameExecutor(EndGameExecutor endGameExecutor) {
        this.endGameExecutor = endGameExecutor;
    }


    public TeamExecutor getTeamExecutor() {
        return teamExecutor;
    }

    public void setTeamExecutor(TeamExecutor teamExecutor) {
        this.teamExecutor = teamExecutor;
    }
}
