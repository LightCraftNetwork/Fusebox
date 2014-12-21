package me.elliottolson.fusebox.util;

import org.bukkit.plugin.Plugin;

import java.sql.Connection;

/**
 * Copyright Elliott Olson (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public abstract class Database {

    protected Plugin plugin;

    protected Database(Plugin plugin) {
        this.plugin = plugin;
    }

    public abstract Connection openConnection();

    public abstract boolean checkConnection();

    public abstract Connection getConnection();

    public abstract void closeConnection();

}
