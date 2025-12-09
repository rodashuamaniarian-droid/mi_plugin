package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import tu.paquete.BedWarsPlugin;

public class SpectatorProtectionListener implements Listener {

    private final BedWarsPlugin plugin;

    public SpectatorProtectionListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof org.bukkit.entity.Player) {

            org.bukkit.entity.Player p = (org.bukkit.entity.Player) e.getEntity();

            if (p.getGameMode() == org.bukkit.GameMode.SPECTATOR) {
                e.setCancelled(true);
            }
        }
    }
}
