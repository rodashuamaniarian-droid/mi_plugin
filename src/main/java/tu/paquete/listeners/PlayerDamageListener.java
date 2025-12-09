package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import tu.paquete.BedWarsPlugin;
import tu.paquete.arena.Arena;

public class PlayerDamageListener implements Listener {

    private final BedWarsPlugin plugin;

    public PlayerDamageListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof org.bukkit.entity.Player)) return;

        org.bukkit.entity.Player p = (org.bukkit.entity.Player) e.getEntity();

        for (Arena a : plugin.getArenaManager().getArenas().values()) {
            if (a.getPlayers().contains(p) && a.getState().name().equals("WAITING")) {
                e.setCancelled(true);
            }
        }
    }
}
