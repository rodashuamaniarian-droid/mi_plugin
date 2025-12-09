package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.scoreboard.ScoreboardManager;

public class ScoreboardToggleCmd extends SubCommand {

    private final BedWarsPlugin plugin;

    public ScoreboardToggleCmd(BedWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "scoreboard";
    }

    @Override
    public String getDescription() {
        return "Activa o desactiva el scoreboard";
    }

    @Override
    public String getSyntax() {
        return "/bw scoreboard <enable|disable>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (!player.hasPermission("bedwars.admin")) {
            player.sendMessage("§cNo tienes permiso.");
            return;
        }

        if (args.length < 2) {
            player.sendMessage("§cUsa: /bw scoreboard <enable|disable>");
            return;
        }

        if (args[1].equalsIgnoreCase("enable")) {
            ScoreboardManager.setScoreboardEnabled(true);
            player.sendMessage("§aScoreboard ACTIVADO.");
        } else if (args[1].equalsIgnoreCase("disable")) {
            ScoreboardManager.setScoreboardEnabled(false);
            player.sendMessage("§cScoreboard DESACTIVADO.");
        } else {
            player.sendMessage("§cUsa: /bw scoreboard <enable|disable>");
        }
    }
}
