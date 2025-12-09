package tu.paquete.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import tu.paquete.scoreboard.ScoreboardManager;

public class PlayerDataListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        ScoreboardManager.set(e.getPlayer());
    }
}
