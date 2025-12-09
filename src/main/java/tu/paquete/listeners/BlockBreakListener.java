package tu.paquete.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        // Permitir romper bloques colocados por jugadores
        if (e.getBlock().getType() == Material.WOOL) return;

        // Evitar romper mapa
        e.setCancelled(true);
    }
}
