package kevinprotect;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

	public List<String> inchunk;
	
	public void onEnable() {
		final FileConfiguration config = this.getConfig();
		getServer().getPluginManager().registerEvents(new listener(this), this);
		getLogger().info("KevinProtect has been enabled!");
		if (getConfig().contains("protected chunks")) {

		} else {
			getConfig().addDefault("protected chunks." + "description",
					"chunk id of protected chunks ");
			getConfig().addDefault("players with chunks." + "description",
					"players with their chunks");
			config.options().copyDefaults(true);
		}
		saveConfig();
		getCommand("protect").setExecutor(new protect(this));
	}

	public void onDisable() {
		getLogger().info("KevinProtect has been disabled!");
		saveConfig();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (label.equalsIgnoreCase("protect")) {

		}
		return false;

	}

	public void message(Player p, String message) {
		p.sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN
				+ "KevinProtect" + ChatColor.GREEN + "]" + ChatColor.DARK_AQUA
				+ " " + message);
	}
	
	public void errorMessage(Player p, String message) {
		p.sendMessage(ChatColor.RED + "[" + ChatColor.DARK_RED
				+ "KevinProtect" + ChatColor.RED + "]" + ChatColor.DARK_RED
				+ " " + message);
	}
}
