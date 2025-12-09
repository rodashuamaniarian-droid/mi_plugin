package tu.paquete.data;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import tu.paquete.BedWarsPlugin;

public class PlayerDataListener implements Listener {

    private BedWarsPlugin plugin;

    public PlayerDataListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        plugin.getPlayerDataManager().get(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        plugin.getPlayerDataManager().save(
                plugin.getPlayerDataManager().get(e.getPlayer().getUniqueId())
        );
    }
}
