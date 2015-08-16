package kevinprotect;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;

public class listener implements Listener {

	public main plugin;

	public listener(main instance) {
		plugin = instance;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if ((plugin.getConfig()
				.contains("protected chunks." +event.getBlock().getLocation().getChunk().toString()) && (!plugin
				.getConfig()
				.get("protected chunks."
						+ event.getBlock().getLocation().getChunk().toString()
						+ ".")
				.equals(event.getPlayer().getUniqueId().toString())))) {
			event.setCancelled(true);
			plugin.errorMessage(event.getPlayer(), "You cannot break blocks here, someone else has claimed this chunk!");

		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if ((plugin.getConfig()
				.contains("protected chunks." +event.getClickedBlock().getLocation().getChunk().toString()) && (!plugin
				.getConfig()
				.get("protected chunks."
						+ event.getClickedBlock().getLocation().getChunk().toString()
						+ ".")
				.equals(event.getPlayer().getUniqueId().toString())))) {
			event.setCancelled(true);
			plugin.errorMessage(event.getPlayer(), "You cannot do that here, someone else has claimed this chunk!");

		}
		

	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if ((plugin.getConfig()
				.contains("protected chunks." +event.getBlock().getLocation().getChunk().toString()) && (!plugin
				.getConfig()
				.get("protected chunks."
						+ event.getBlock().getLocation().getChunk().toString()
						+ ".")
				.equals(event.getPlayer().getUniqueId().toString())))) {
			event.setCancelled(true);
			plugin.errorMessage(event.getPlayer(), "You cannot place that here, someone else has claimed this chunk!");

		}
	}
}
