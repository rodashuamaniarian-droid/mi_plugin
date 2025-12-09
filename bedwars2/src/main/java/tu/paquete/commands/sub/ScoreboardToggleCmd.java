package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;
import tu.paquete.scoreboard.ScoreboardManager;

public class ScoreboardToggleCmd implements SubCommand {

    private final BedWarsPlugin plugin;

    public ScoreboardToggleCmd(BedWarsPlugin plugin) { this.plugin = plugin; }

    @Override public String getName() { return "scoreboard"; }
    @Override public String getDescription() { return "Activar/desactivar scoreboard global"; }
    @Override public String getSyntax() { return "scoreboard <enable|disable>"; }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) { player.sendMessage("§cUsa: /bw scoreboard <enable|disable>"); return; }

        String m = args[1].toLowerCase();
        if (m.equals("enable")) {
            ScoreboardManager.setEnabled(true);
            player.sendMessage("§aScoreboard activado.");
        } else if (m.equals("disable")) {
            ScoreboardManager.setEnabled(false);
            player.sendMessage("§cScoreboard desactivado.");
        } else {
            player.sendMessage("§cUsa: /bw scoreboard <enable|disable>");
        }
    }
}
