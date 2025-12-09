package tu.paquete.shop;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import java.util.List;

public class ShopGUI {

    private final String title;
    private final int size;
    private final List<ItemStack> items;

    public ShopGUI(String title, int size, List<ItemStack> items){
        this.title = title;
        this.size = size;
        this.items = items;
    }

    public void open(Player player){
        Inventory inv = Bukkit.createInventory(null, size, title);
        int i = 0;
        for(ItemStack it : items){
            if(i >= size) break;
            inv.setItem(i++, it);
        }
        player.openInventory(inv);
    }

}
