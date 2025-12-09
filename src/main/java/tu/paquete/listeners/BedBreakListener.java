package tu.paquete.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import tu.paquete.BedWarsPlugin;
import tu.paquete.arena.Arena;
import tu.paquete.arena.Team;
import tu.paquete.arena.TeamColor;

public class BedBreakListener implements Listener {

    private final BedWarsPlugin plugin;

    public BedBreakListener(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBedBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Block b = e.getBlock();

        if (b.getType() != Material.BED_BLOCK) return;

        for (Arena a : plugin.getArenaManager().getArenas().values()) {

            if (!a.getPlayers().contains(p)) continue;

            for (Team t : a.getTeams()) {

                if (t.getBedLocation() == null) continue;

                if (t.getBedLocation().getBlockX() == b.getX()
                        && t.getBedLocation().getBlockY() == b.getY()
                        && t.getBedLocation().getBlockZ() == b.getZ()) {

                    t.setBedAlive(false);

                    for (Player pl : a.getPlayers()) {
                        pl.sendMessage("§cLa cama del equipo " + t.getColor().getDisplayName() + " fue destruida por §e" + p.getName());
                    }
                }
            }
        }
    }
}
