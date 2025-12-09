
package tu.paquete.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import tu.paquete.BedWarsPlugin;
import tu.paquete.data.BedWarsPlayer;
import tu.paquete.data.MySQLManager;

public class ScoreboardManager {

    private static BedWarsPlugin plugin;
    private static MySQLManager mysql;
    private static boolean enabled = true;

    public static void initialize(BedWarsPlugin p, MySQLManager my) {
        plugin = p;
        mysql = my;
        startUpdater();
    }

    public static void setEnabled(boolean b) { enabled = b; }

    private static void startUpdater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!enabled) return;
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    BedWarsPlayer bw = plugin.getPlayerData().get(pl.getUniqueId());
                    if (bw == null) continue;
                    updatePlayerScoreboard(pl, bw);
                }
            }
        }.runTaskTimerAsynchronously(plugin, 20L, 40L);
    }

    public static void updateLobbyScoreboard(Player p, BedWarsPlayer bw) {
        updatePlayerScoreboard(p, bw);
    }

    private static void updatePlayerScoreboard(Player p, BedWarsPlayer bw) {
        if (!enabled) return;
        // create a simple sidebar scoreboard
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("bedwars", "dummy", "§6BedWars");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int line = 6;
        Score s1 = obj.getScore("Jugadores: " + Bukkit.getOnlinePlayers().size());
        s1.setScore(line--);
        Score s2 = obj.getScore("Kills: " + bw.getKills());
        s2.setScore(line--);
        Score s3 = obj.getScore("Deaths: " + bw.getDeaths());
        s3.setScore(line--);
        Score s4 = obj.getScore("Wins: " + bw.getWins());
        s4.setScore(line--);
        Score s5 = obj.getScore("Nivel: " + bw.getLevel());
        s5.setScore(line--);

        p.setScoreboard(board);
    }
}
