package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tu.paquete.BedWarsPlugin;
import tu.paquete.data.BedWarsPlayer;
import tu.paquete.scoreboard.ScoreboardManager;

public class PlayerJoinListener implements Listener {

    private BedWarsPlugin plugin;

    public PlayerJoinListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        BedWarsPlayer bw = plugin.getPlayerData().getOrCreate(e.getPlayer());
        bw.load();

        ScoreboardManager.updateLobbyScoreboard(e.getPlayer(), bw);
        e.getPlayer().sendMessage("§a¡Bienvenido al servidor BedWars!");
        e.setJoinMessage(null);
    }
}
