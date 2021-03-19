package br.com.alsupremeandrey.PDEK;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import pdek.org.bstats.bukkit.Metrics;

public class PDEK extends JavaPlugin{

	@Override
	public void onEnable() {
		@SuppressWarnings("unused")
		Metrics metrics = new Metrics(this, 10709);
		
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c|-------------PD&aEK--------------"));
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6|&3  PDEK Has Been loaded     "));
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6|&3  Author: &5" + this.getDescription().getAuthors()));
		this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6|&3  Current Version: &c" + this.getDescription().getVersion()));
		if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6|  &bVault has detected"));
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6|  &bEnabling Economy kit"));
		}else {
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6|  &cVault has not detected"));
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6|  &cConsider use Vault to use Economy kit"));
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6|  &chttps://www.spigotmc.org/resources/vault.34315/"));
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6|  &cAll the economy method return 0 when using Economy Kit without Vault"));
		}
		
		VersionManager.getVersion(this, 88025, Version ->{
			if (this.getDescription().getVersion().equalsIgnoreCase(Version)){
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&No update avaible for PDEK, Current version: &f " + Version));
		}else {
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&New update avaible for PDEK, new version: &f" + Version));
		}
		});
		

		
		super.onEnable();
	}
	
}
