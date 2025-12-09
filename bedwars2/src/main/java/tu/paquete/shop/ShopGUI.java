
package tu.paquete.shop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import tu.paquete.BedWarsPlugin;
import tu.paquete.data.BedWarsPlayer;
import java.util.UUID;
import java.util.Arrays;

public class ShopGUI {

    private static BedWarsPlugin plugin;
    private static UpgradeManager upgrades;

    public static void initialize(BedWarsPlugin pl, UpgradeManager um) {
        plugin = pl;
        upgrades = um;
    }

    public static boolean isShop(String title) {
        return title != null && title.toLowerCase().contains("shop");
    }

    public static void openShop(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, "Shop - Upgrades");
        inv.setItem(2, createItem(Material.IRON_SWORD, "Sharpness Upgrade", Arrays.asList("Mejora el daño", "Costo: 10")));
        inv.setItem(4, createItem(Material.DIAMOND_CHESTPLATE, "Armor Upgrade", Arrays.asList("Mejora la armadura", "Costo: 15")));
        inv.setItem(6, createItem(Material.GOLD_INGOT, "Resources Upgrade", Arrays.asList("Mejora recolección", "Costo: 8")));
        p.openInventory(inv);
    }

    private static ItemStack createItem(Material mat, String name, java.util.List<String> lore) {
        ItemStack it = new ItemStack(mat);
        ItemMeta im = it.getItemMeta();
        im.setDisplayName(name);
        im.setLore(lore);
        it.setItemMeta(im);
        return it;
    }

    public static void handleClick(InventoryClickEvent e) {
        e.setCancelled(true);
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;
        String name = clicked.getItemMeta().getDisplayName().toLowerCase();

        UUID uuid = p.getUniqueId();
        if (name.contains("sharpness")) {
            Upgrade u = Upgrade.SHARPNESS;
            attemptUpgrade(p, uuid, u, 10);
        } else if (name.contains("armor")) {
            Upgrade u = Upgrade.REINFORCED_ARMOR;
            attemptUpgrade(p, uuid, u, 15);
        } else if (name.contains("resources")) {
            Upgrade u = Upgrade.RESOURCE_PACKER;
            attemptUpgrade(p, uuid, u, 8);
        }
    }

    private static void attemptUpgrade(Player p, UUID uuid, Upgrade up, int cost) {
        BedWarsPlayer bw = plugin.getPlayerData().get(uuid);
        if (bw == null) {
            p.sendMessage("§cError: datos no cargados.");
            return;
        }
        // for demo: treat 'level' as currency (not ideal) - you can replace with real coins
        if (bw.getLevel() < cost) {
            p.sendMessage("§cNo tienes suficiente nivel/moneda. Necesitas: " + cost);
            return;
        }
        if (!upgrades.canUpgrade(uuid, up)) {
            p.sendMessage("§c¡Upgrade ya al máximo!");
            return;
        }
        int cur = upgrades.getLevel(uuid, up);
        upgrades.setLevel(uuid, up, cur + 1);
        bw.setLevel(bw.getLevel() - cost); // pay with level for demo
        plugin.getLogger().info("Player " + p.getName() + " upgraded " + up.name() + " to " + (cur+1));
        p.sendMessage("§aUpgrade comprado: " + up.getName() + " nivel " + (cur+1));
        p.closeInventory();
    }
}
