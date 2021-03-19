package br.com.alsupremeandrey.PDEK;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;

public abstract class VersionManager {
	

	public static void getVersion(JavaPlugin plugin, int resourceID, final Consumer<String> consumer) {
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
			try(InputStream inputstream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceID).openStream(); Scanner scanner = new Scanner(inputstream)){
				if(scanner.hasNext()) {
					consumer.accept(scanner.next());
				}
			} catch (MalformedURLException e) {
				 plugin.getLogger().info("Cannot look for updates: " + e.getMessage());
			} catch (IOException e) {
				 plugin.getLogger().info("Cannot look for updates: " + e.getMessage());
			}
		});
	}

}
