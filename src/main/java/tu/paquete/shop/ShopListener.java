package tu.paquete.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tu.paquete.BedWarsPlugin;
import tu.paquete.BedWarsPlayer;

import java.util.*;

public class ShopListener implements Listener {

    private final BedWarsPlugin plugin;
    // mapa id -> ShopEntry
    private final Map<String, ShopEntry> shopEntries = new LinkedHashMap<>();

    public ShopListener(BedWarsPlugin plugin){
        this.plugin = plugin;
        // carga inicial de items (puedes reemplazar por carga desde shop.yml)
        loadDefaultShop();
    }

    private void loadDefaultShop(){
        // Bloques (ejemplo)
        addShopEntry("blocks", Material.WHITE_WOOL, "Bloques (x16)", 2, 16);
        // Armas y herramientas
        addShopEntry("wood_sword", Material.WOODEN_SWORD, "Espada de madera", 5, 1);
        addShopEntry("iron_sword", Material.IRON_SWORD, "Espada de hierro", 25, 1);
        addShopEntry("bow", Material.BOW, "Arco", 15, 1);
        addShopEntry("arrows", Material.ARROW, "Flechas x16", 3, 16);
        // Armaduras
        addShopEntry("iron_chestplate", Material.IRON_CHESTPLATE, "Peto de Hierro", 40, 1);

        // Upgrades (se manejan como entries también)
        addUpgradeEntry("sharpness1", "Afilado I", 50);
        addUpgradeEntry("protection1", "Protección I", 50);
    }

    private void addShopEntry(String id, Material mat, String displayName, int price, int amount){
        ShopEntry e = new ShopEntry(id, mat, displayName, price, amount, false);
        shopEntries.put(id, e);
    }

    private void addUpgradeEntry(String id, String name, int price){
        ShopEntry e = new ShopEntry(id, Material.DIAMOND, name + " §7(Upgrade)", price, 1, true);
        shopEntries.put(id, e);
    }

    // Abre la UI de la tienda para el jugador
    public void openShop(Player p){
        List<ItemStack> items = new ArrayList<>();
        for(ShopEntry e : shopEntries.values()){
            items.add(buildDisplayItem(e));
        }
        // tamaño: redondear al múltiplo de 9
        int size = ((items.size() + 8) / 9) * 9;
        ShopGUI gui = new ShopGUI("§6Tienda BedWars", Math.max(9, size), items);
        gui.open(p);
    }

    private ItemStack buildDisplayItem(ShopEntry e){
        ItemStack it = new ItemStack(e.material, e.amount);
        ItemMeta meta = it.getItemMeta();
        if(meta != null){
            meta.setDisplayName(e.displayName);
            List<String> lore = new ArrayList<>();
            lore.add("§7Precio: §e" + e.price + " coins");
            lore.add(e.isUpgrade ? "§6Upgrade" : "§7Item");
            meta.setLore(lore);
            it.setItemMeta(meta);
        }
        return it;
    }

    // Evento para comprar en la tienda
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getView() == null) return;
        if(!e.getView().getTitle().contains("Tienda BedWars")) return;
        e.setCancelled(true);
        if(e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta()) return;
        Player p = (Player) e.getWhoClicked();
        String display = e.getCurrentItem().getItemMeta().getDisplayName();

        // buscar ShopEntry por display name
        ShopEntry found = null;
        for(ShopEntry se : shopEntries.values()){
            if(se.displayName.equals(display)){
                found = se; break;
            }
        }
        if(found == null) return;

        BedWarsPlayer bw = plugin.getBedWarsPlayer(p);
        if(bw.getCoins() < found.price){
            p.sendMessage("§cNo tienes suficientes monedas (" + found.price + " required).");
            return;
        }

        // pagar
        bw.removeCoins(found.price);

        // si es upgrade -> añadir upgrade
        if(found.isUpgrade){
            bw.addUpgrade(new Upgrade(found.displayName, found.price));
            p.sendMessage("§aUpgrade adquirido: " + found.displayName);
        } else {
            // dar item al inventario (cantidad y material)
            p.getInventory().addItem(new ItemStack(found.material, found.amount));
            p.sendMessage("§aCompraste: " + found.displayName + " por §e" + found.price + " coins.");
        }
        // opcional: refrescar la GUI
        openShop(p);
    }

    // Clase interna para representar ítems en tienda
    private static class ShopEntry {
        final String id;
        final Material material;
        final String displayName;
        final int price;
        final int amount;
        final boolean isUpgrade;

        ShopEntry(String id, Material material, String displayName, int price, int amount, boolean isUpgrade){
            this.id = id;
            this.material = material;
            this.displayName = displayName;
            this.price = price;
            this.amount = amount;
            this.isUpgrade = isUpgrade;
        }
    }

}
