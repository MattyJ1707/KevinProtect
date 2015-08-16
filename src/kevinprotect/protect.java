package kevinprotect;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class protect implements CommandExecutor {

	public main plugin;
	
	public protect(main instance) {
		plugin = instance;
	}


	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (label.equalsIgnoreCase("protect")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;

				if (p.hasPermission("kevinprotect.use")) {

					String chunkid = p.getLocation().getChunk().toString();
					if (args.length == 0) {
						protectHelp(p);
					} else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("help")) {
							protectHelp(p);
						} else if (args[0].equalsIgnoreCase("claim")) {
							protectClaim(p, chunkid);
						} else if (args[0].equalsIgnoreCase("unclaim")) {
							protectUnclaim(p, chunkid);
						}
					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("add")) {
							protectAdd(p, chunkid);
						} else if (args[0].equalsIgnoreCase("remove")) {
							protectRemove(p, chunkid);
						}
					} else {
						protectHelp(p);
					}

				} else {

					plugin.message(
							p,
							"You do not have permission to use KevinProtect. Contact an administrator if you believe this is an error.");
				}

			} else {
				sender.sendMessage("You must be playing ingame to use this command!");
			}
		}

		return false;
	}

	public void protectRemove(Player p, String chunkid) {
		// protect remove TO BE ADDED
	}

	public void protectAdd(Player p, String chunkid) {
		// protect add TO BE ADDED
	}

	public void protectClaim(Player p, String chunkid) {
		if((plugin.getConfig().contains("protected chunks." + chunkid)) && (!plugin.getConfig().get("protected chunks." + chunkid).equals("no player"))) {
			plugin.errorMessage(p, "Sorry, this chunk has already been claimed, see if you can find another one :)");
		} else if((!p.hasPermission("kevinprotect.override")) && (plugin.getConfig().contains("players with chunks." + p.getUniqueId().toString()))) {
			plugin.errorMessage(p, "Sorry, you cannot claim more than one chunk at the current time!");
		} else {
			plugin.getConfig().set("players with chunks." + p.getUniqueId().toString(),"true");
			plugin.getConfig().set("protected chunks." + chunkid, p.getUniqueId().toString());
			plugin.message(p, "You have successfully claimed this chunk as your own! No one else can build on it!");
		}
	}

	public void protectUnclaim(Player p, String chunkid) {
		if(plugin.getConfig().contains("protected chunks." + chunkid + "." + p.getUniqueId().toString())) {
			plugin.getConfig().set("protected chunks." + chunkid, "no player");
		}
	}

	public void protectHelp(Player p) {
		// protect help
		plugin.message(
				p,
				"KevinProtect - Custom protection plugin for the KevinKraft server by MattyJ1707");
		plugin.message(p,
				"/protect claim - Claims the chunk you are standing in for yourself!");
		plugin.message(
				p,
				"/protect unclaim - Unclaims the chunk you are standing in, meaning it can be claimed by someone else");
		plugin.message(
				p,
				"/protect add <playername> - Adds another player to be able to edit your claimed chunk!");
		plugin.message(
				p,
				"/protect remove <playername> - Removes a player from being able to edit your claimed chunk!");
		plugin.message(p, "/protect help - Brings up this help screen");
		plugin.message(p,
				"Contact MattyJ1707 for any bugs or suggestions for future updates");
	}

}
