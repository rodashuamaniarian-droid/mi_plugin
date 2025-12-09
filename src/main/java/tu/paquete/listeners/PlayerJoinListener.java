package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tu.paquete.BedWarsPlugin;

public class PlayerJoinListener implements Listener {

    private final BedWarsPlugin plugin;

    public PlayerJoinListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        // Evitar spam
        e.setJoinMessage(null);

        // Scoreboard global
        plugin.getScoreboardManager().applyLobbyScoreboard(e.getPlayer());
    }
}
