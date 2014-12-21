package net.lightcraftmc.fusebox.mysql;

import net.lightcraftmc.fusebox.configuration.Configuration;

import org.bukkit.plugin.Plugin;

import java.sql.*;
import java.util.logging.Level;

public class MySQL extends Database{

    private final String user;
    private final String database;
    private final String password;
    private final String port;
    private final String hostname;
    private Connection connection;

    public MySQL(Plugin plugin, Configuration file){
        super(plugin);
        if(!file.GetConfig().isSet("MySQL.user")){
            file.GetConfig().set("MySQL.user", "user");
            file.setChanged();
        }
        if(!file.GetConfig().isSet("MySQL.password")){
            file.GetConfig().set("MySQL.password", "password");
            file.setChanged();
        }
        if(!file.GetConfig().isSet("MySQL.host")){
            file.GetConfig().set("MySQL.host", "localhost");
            file.setChanged();
        }
        if(!file.GetConfig().isSet("MySQL.port")){
            file.GetConfig().set("MySQL.port", "3306");
            file.setChanged();
        }
        if(!file.GetConfig().isSet("MySQL.database")){
            file.GetConfig().set("MySQL.database", "db");
            file.setChanged();
        }
        if(file.getChanged()){
            file.SaveConfig();
        }
        this.user = file.GetConfig().getString("MySQL.user");
        this.password = file.GetConfig().getString("MySQL.password");
        this.hostname = file.GetConfig().getString("MySQL.host");
        this.port = file.GetConfig().getString("MySQL.port");
        this.database = file.GetConfig().getString("MySQL.database");
    }

    public MySQL(Plugin plugin, String hostname, String port, String database, String username, String password)
    {
        super(plugin);
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
        this.connection = null;
    }

    public Connection openConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
        }
        catch (SQLException e)
        {
            System.out.print(e.getMessage());
            this.plugin.getLogger().log(Level.SEVERE, "Can't connect to MySQL server");
            closeConnection();
        }
        catch (ClassNotFoundException e)
        {
            this.plugin.getLogger().log(Level.SEVERE, "JDBC Driver not found!");
        }
        return this.connection;
    }

    public boolean checkConnection()
    {
        return this.connection != null;
    }

    public Connection getConnection()
    {
        return this.connection;
    }

    public void closeConnection()
    {
        if (this.connection != null) {
            try
            {
                this.connection.close();
            }
            catch (SQLException e)
            {
                this.plugin.getLogger().log(Level.SEVERE, "Error closing the MySQL Connection!");
                e.printStackTrace();
            }
        }
    }

    public ResultSet querySQL(String query)
    {
        Connection c = null;
        if (checkConnection()) {
            c = getConnection();
        } else {
            c = openConnection();
        }
        Statement s = null;
        try
        {
            s = c.createStatement();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
        ResultSet ret = null;
        try
        {
            ret = s.executeQuery(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    public void updateSQL(String update)
    {
        Connection c = null;
        if (checkConnection()) {
            c = getConnection();
        } else {
            c = openConnection();
        }
        Statement s = null;
        try
        {
            s = c.createStatement();
            s.executeUpdate(update);
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }

}
