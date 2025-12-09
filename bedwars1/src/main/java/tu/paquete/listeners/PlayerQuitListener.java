package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tu.paquete.BedWarsPlugin;
import tu.paquete.data.BedWarsPlayer;
import tu.paquete.arenas.Arena;

public class PlayerQuitListener implements Listener {

    private BedWarsPlugin plugin;

    public PlayerQuitListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        BedWarsPlayer bw = plugin.getPlayerData().get(e.getPlayer());
        if (bw != null) {
            bw.save();
        }

        Arena arena = plugin.getArenaManager().getArenaByPlayer(e.getPlayer());
        if (arena != null) {
            arena.removePlayer(e.getPlayer());
        }

        e.setQuitMessage(null);
    }
}
