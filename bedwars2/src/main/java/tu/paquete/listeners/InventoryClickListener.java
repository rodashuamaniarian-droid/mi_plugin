package tu.paquete.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import tu.paquete.shop.ShopGUI;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (ShopGUI.isShop(e.getView().getTitle())) {
            ShopGUI.handleClick(e);
        }
    }
}
