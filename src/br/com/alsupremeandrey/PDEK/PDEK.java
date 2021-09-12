package br.com.alsupremeandrey.PDEK;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.md_5.bungee.api.ChatColor;
import pdek.org.bstats.bukkit.Metrics;

public class PDEK extends JavaPlugin implements Listener{

	@Override
	public void onEnable() {

		this.getConfig().set("useBSTATS", true);
		this.getConfig().options().copyDefaults();
		this.saveConfig();
		this.reloadConfig();
		
		if(this.getConfig().getBoolean("useBSTATS")) {
			@SuppressWarnings("unused")
			Metrics metrics = new Metrics(this, 10709);
		}
		
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

		VersionManager.getVersion(this, 90557, Version ->{
			if (this.getDescription().getVersion().equalsIgnoreCase(Version)){
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo update avaible for PDEK, Current version: &f " + Version));
		}else {
			this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNew update avaible for PDEK, new version: &f" + Version));
		}
		});

		this.getServer().getPluginManager().registerEvents(this, this);
		
		super.onEnable();
	}
	
	@EventHandler
	public void teste(PlayerJoinEvent e) {
		ScoreboardCreator teste = new ScoreboardCreator(this, e.getPlayer());
		teste.setObj("dsadsa", "dummy", "title");
		teste.setScore("lol");
		teste.genScoreBoard();
		
	}
	@EventHandler
	public void ue(PlayerMoveEvent e) {
		e.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR).unregister();
	}
	
}
