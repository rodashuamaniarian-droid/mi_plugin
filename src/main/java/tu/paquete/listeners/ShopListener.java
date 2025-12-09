package tu.paquete.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import tu.paquete.BedWarsPlugin;

public class ShopListener implements Listener {

    private final BedWarsPlugin plugin;

    public ShopListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if (e.getClickedBlock() == null) return;

        if (e.getClickedBlock().getType() == Material.VILLAGER_SPAWN_EGG
                || e.getClickedBlock().getType() == Material.EMERALD_BLOCK) {

            plugin.getShopGUI().openShop(e.getPlayer());
        }
    }
}
