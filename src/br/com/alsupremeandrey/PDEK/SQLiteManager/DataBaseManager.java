package br.com.alsupremeandrey.PDEK.SQLiteManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import javax.annotation.Nullable;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class DataBaseManager {

	Plugin plugin;
    Connection connection;
    
    public DataBaseManager(JavaPlugin instance){
        plugin = instance;
    }

    public abstract Connection getSQLConnection();


    public void createTables(String tablename, String columns) {
    	Connection con = null;
    	String sql = "CREATE TABLE IF NOT EXISTS " + tablename + " (" + columns + ");";
		Statement S = null;
    	try {
    		con = getSQLConnection();
    		S = con.createStatement();
			S.executeUpdate(sql);

		} catch (SQLException e) {

			plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", e);
		} finally {
			try {
				if(S != null) {
					S.close();
				}if(con != null) {
					con.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
    }
    
    public void insertInTables(String tableName, String values, @Nullable String columnsName) {
    	Connection con = getSQLConnection();
    	try {
    		PreparedStatement PS;
    		if(columnsName == null)
    			PS = con.prepareStatement("INSERT INTO " + tableName + " VALUES (" + values + ")");
    		else
    			PS = con.prepareStatement("INSERT INTO " + tableName + "(" + columnsName + ")" + " VALUES (" + values + ")");
			PS.executeUpdate();
			PS.close();
			con.close();
    	}catch (Exception e) {
    		plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", e);
		}
    }
    
    public void updateData(String tableName, String columnName, String value, String ConditionColumn, String logicalOperator, String conditionValue) {
    	Connection con = getSQLConnection();
    	try {
    		PreparedStatement PS = con.prepareStatement("UPDATE " + tableName + " SET " 
    				+ columnName + " = " + value + " Where " + ConditionColumn + logicalOperator + conditionValue);
			PS.executeUpdate();
			PS.close();
			con.close();
    	}catch (Exception e) {
    		plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", e);
		}
    }
    
    public void runCustomSQL(String sqlCommand) {
    	try {
        	Connection con = getSQLConnection();
        	PreparedStatement PS = con.prepareStatement(sqlCommand);
			PS.executeUpdate();
			PS.close();
			con.close();
		} catch (SQLException e) {
			plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", e);
		}
    }
    
    public void replaceInTable(String table, String columns, String data) {
    	Connection con = getSQLConnection();
    	try {
			PreparedStatement ps = con.prepareStatement("REPLACE INTO " + table + " (" +columns +")"+ " VALUES(" + data + ");");
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", e);
		}
    	
    }
    public float getFloatTableColumn(String playerName, String columnName, String table) {
    	connection = getSQLConnection();
    	try {
    		
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE playername = '"+playerName+"' ;");
			ResultSet rs = ps.executeQuery();
			float ret = rs.getFloat(columnName);
			close(ps,rs);
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return 0;
    }
    public int getIntTableColumn(String playerName, String columnname, String table) {
    	connection = getSQLConnection();
    	try {
    		
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE playername = '"+playerName+"' ;");
			ResultSet rs = ps.executeQuery();
			int ret = rs.getInt(columnname);
			close(ps,rs);
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return 0;
    }
    
    public String getStringTableColumn(String playerName, String columnname, String table) {
    	connection = getSQLConnection();
    	try {
    		
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE playername = '"+playerName+"' ;");
			ResultSet rs = ps.executeQuery();
			String ret = rs.getString(columnname);
			close(ps,rs);
			return ret;
			
		} catch (SQLException e) {

		}
    	return null;
    }
    
    public Boolean getBooleanTableColumn(String playerName, String columnname, String table) {
    	connection = getSQLConnection();
    	try {
    		
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE playername = '"+playerName+"' ;");
			ResultSet rs = ps.executeQuery();
			Boolean ret = rs.getBoolean(columnname);
			close(ps,rs);
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public void close(PreparedStatement ps,ResultSet rs){
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            Error.close(plugin, ex);
        }
    }
}
