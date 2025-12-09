package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import tu.paquete.BedWarsPlugin;
import tu.paquete.data.BedWarsPlayer;
import tu.paquete.arenas.Arena;

public class PlayerDeathListener implements Listener {

    private BedWarsPlugin plugin;

    public PlayerDeathListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);

        Arena arena = plugin.getArenaManager().getArenaByPlayer(e.getEntity());
        if (arena == null) return;

        BedWarsPlayer bw = plugin.getPlayerData().get(e.getEntity());
        bw.addDeath();

        if (e.getEntity().getKiller() != null) {
            BedWarsPlayer killer = plugin.getPlayerData().get(e.getEntity().getKiller());
            killer.addKill();
        }

        arena.handlePlayerDeath(e.getEntity());
    }
}
