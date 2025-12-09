package tu.paquete.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import tu.paquete.BedWarsPlugin;
import tu.paquete.arena.Arena;

public class VoidListener implements Listener {

    private final BedWarsPlugin plugin;

    public VoidListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onVoid(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof org.bukkit.entity.Player)) return;

        if (e.getCause() != EntityDamageEvent.DamageCause.VOID) return;

        org.bukkit.entity.Player p = (org.bukkit.entity.Player) e.getEntity();

        for (Arena a : plugin.getArenaManager().getArenas().values()) {

            if (!a.getPlayers().contains(p)) continue;

            e.setCancelled(true);
            a.removePlayer(p);

            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(a.getSpectatorLocation());
        }
    }
}
