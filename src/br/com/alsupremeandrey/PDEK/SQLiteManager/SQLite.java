package br.com.alsupremeandrey.PDEK.SQLiteManager;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class SQLite extends DataBaseManager{
	String dbname;
	
    public SQLite(JavaPlugin instance, String dataBaseName){
        super(instance);
        dbname = dataBaseName;
    }

    public Connection getSQLConnection() {
        File dataFolder = new File(plugin.getServer().getPluginManager().getPlugin(plugin.getName()).getDataFolder(), dbname+".db");
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: "+dbname+".db");
                plugin.getLogger().log(Level.SEVERE, String.valueOf(e));
            }
        }
        try {
            if(connection!=null&&!connection.isClosed()){
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = (Connection) DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE,"SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }


}
