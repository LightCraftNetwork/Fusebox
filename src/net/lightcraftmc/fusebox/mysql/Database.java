package net.lightcraftmc.fusebox.mysql;

import net.lightcraftmc.fusebox.Core;

import org.bukkit.plugin.Plugin;

import java.sql.Connection;


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
