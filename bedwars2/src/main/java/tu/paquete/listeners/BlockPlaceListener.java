package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import tu.paquete.BedWarsPlugin;
import tu.paquete.arenas.Arena;

public class BlockPlaceListener implements Listener {

    private BedWarsPlugin plugin;

    public BlockPlaceListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Arena arena = plugin.getArenaManager().getArenaByPlayer(e.getPlayer());
        if (arena == null) {
            e.setCancelled(true);
            return;
        }

        arena.addPlacedBlock(e.getBlock());
    }
}
