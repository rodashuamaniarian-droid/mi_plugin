package tu.paquete.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import tu.paquete.data.PlayerData;

public class ScoreboardManager {

    public void apply(Player p, PlayerData data) {

        ScoreboardManager mgr = Bukkit.getScoreboardManager();
        Scoreboard board = mgr.getNewScoreboard();

        Objective obj = board.registerNewObjective("bw", "dummy", "§e§lBEDWARS");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int score = 10;

        obj.getScore("§f").setScore(score--);
        obj.getScore("§bKills: §f" + data.getKills()).setScore(score--);
        obj.getScore("§bDeaths: §f" + data.getDeaths()).setScore(score--);
        obj.getScore("§bWins: §f" + data.getWins()).setScore(score--);
        obj.getScore("§bLosses: §f" + data.getLosses()).setScore(score--);
        obj.getScore("§bBeds: §f" + data.getBedsBroken()).setScore(score--);
        obj.getScore("§bLevel: §f" + data.getLevel()).setScore(score--);
        obj.getScore("§bXP: §f" + data.getXp()).setScore(score--);
        obj.getScore("§f ").setScore(score--);
        obj.getScore("§7play.server.net").setScore(score--);

        p.setScoreboard(board);
    }
}
