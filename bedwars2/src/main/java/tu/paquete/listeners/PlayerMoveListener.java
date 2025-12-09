package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tu.paquete.BedWarsPlugin;
import tu.paquete.arenas.Arena;

public class PlayerMoveListener implements Listener {

    private BedWarsPlugin plugin;

    public PlayerMoveListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Arena arena = plugin.getArenaManager().getArenaByPlayer(e.getPlayer());
        if (arena == null) return;

        if (e.getPlayer().getLocation().getY() <= 0) {
            arena.handleVoidFall(e.getPlayer());
        }
    }
}
