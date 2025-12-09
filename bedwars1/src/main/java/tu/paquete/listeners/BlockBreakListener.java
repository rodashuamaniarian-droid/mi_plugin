package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import tu.paquete.BedWarsPlugin;
import tu.paquete.arenas.Arena;

public class BlockBreakListener implements Listener {

    private BedWarsPlugin plugin;

    public BlockBreakListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Arena arena = plugin.getArenaManager().getArenaByPlayer(e.getPlayer());
        if (arena == null) {
            e.setCancelled(true);
            return;
        }

        if (arena.isBed(e.getBlock())) {
            arena.breakBed(e.getPlayer(), e.getBlock());
        } else {
            if (!arena.isPlaceBlock(e.getBlock())) {
                e.setCancelled(true);
            }
        }
    }
}
