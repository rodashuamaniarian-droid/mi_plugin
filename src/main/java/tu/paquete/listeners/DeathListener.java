package tu.paquete.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import tu.paquete.BedWarsPlugin;
import tu.paquete.arena.Arena;
import tu.paquete.arena.Team;

public class DeathListener implements Listener {

    private final BedWarsPlugin plugin;

    public DeathListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        e.setDeathMessage(null);

        Player p = e.getEntity();

        for (Arena a : plugin.getArenaManager().getArenas().values()) {

            if (!a.getPlayers().contains(p)) continue;

            Team t = plugin.getTeamManager().getPlayerTeam(p);

            if (t == null) return;

            if (!t.isBedAlive()) {
                // muerte definitiva
                p.sendMessage(ChatColor.RED + "¡Has sido eliminado!");
                a.removePlayer(p);
            } else {
                // respawn normal
                p.spigot().respawn();
                p.teleport(t.getSpawn());
            }
        }
    }
}
