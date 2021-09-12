package br.com.alsupremeandrey.PDEK;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Configurations {
	
	private File file;
	private FileConfiguration FConfig;
	private Plugin plugin;
	private String name;
	
	public Configurations(String configName, Plugin plugin) {
		this.plugin = plugin;
		this.name = configName;
		file = new File(Bukkit.getServer().getPluginManager().getPlugin(plugin.getName()).getDataFolder(), configName + ".yml");
		genFileConfig();
		reloadConfig();
	}
	
	public void reloadConfig() {
		FConfig = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileConfiguration getConfig() {
		FConfig = YamlConfiguration.loadConfiguration(file);
		return FConfig;
	}
	
	public void saveConfig() {
		try {
			FConfig.save(file);
			reloadConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void genFileConfig() {
		if(!(file.exists())){
			plugin.saveResource(name + ".yml", false);
		}
	}

}
