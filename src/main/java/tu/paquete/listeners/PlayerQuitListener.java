package tu.paquete.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tu.paquete.BedWarsPlugin;
import tu.paquete.arena.Arena;

public class PlayerQuitListener implements Listener {

    private final BedWarsPlugin plugin;

    public PlayerQuitListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(null);

        for (Arena a : plugin.getArenaManager().getArenas().values()) {
            if (a.getPlayers().contains(p)) {
                a.removePlayer(p);
            }
        }
    }
}
