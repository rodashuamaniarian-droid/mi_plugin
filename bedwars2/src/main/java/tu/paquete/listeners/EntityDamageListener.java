package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import tu.paquete.BedWarsPlugin;
import tu.paquete.arenas.Arena;

public class EntityDamageListener implements Listener {

    private BedWarsPlugin plugin;

    public EntityDamageListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof org.bukkit.entity.Player)) return;

        Arena arena = plugin.getArenaManager().getArenaByPlayer((org.bukkit.entity.Player) e.getEntity());
        if (arena == null) return;

        if (arena.isWaiting() || arena.isStarting()) {
            e.setCancelled(true);
        }
    }
}
