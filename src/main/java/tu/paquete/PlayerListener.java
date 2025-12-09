package tu.paquete;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final BedWarsPlugin plugin;

    public PlayerListener(BedWarsPlugin plugin){ this.plugin = plugin; }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        BedWarsPlayer bwPlayer = plugin.getBedWarsPlayer(e.getPlayer());
        // Lógica adicional al unirse, si quieres
    }
}
