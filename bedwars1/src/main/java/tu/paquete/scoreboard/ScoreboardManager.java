package tu.paquete.scoreboard;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.data.BedWarsPlayer;

public class ScoreboardManager {

    private static boolean enabled = true;
    private static BedWarsPlugin plugin;

    public static void initialize(BedWarsPlugin p) { plugin = p; }

    public static void setEnabled(boolean b) { enabled = b; }

    public static void updateLobbyScoreboard(Player p, BedWarsPlayer bw) {
        if (!enabled) return;
        p.setPlayerListName("§a" + p.getName());
    }
}
