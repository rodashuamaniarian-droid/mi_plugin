package tu.paquete.shop;

import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopGUI {

    public static boolean isShop(String title) {
        return title != null && title.toLowerCase().contains("shop");
    }

    public static void handleClick(InventoryClickEvent e) {
        e.setCancelled(true);
        // simple placeholder: close inventory
        if (e.getWhoClicked() != null) e.getWhoClicked().closeInventory();
    }
}
