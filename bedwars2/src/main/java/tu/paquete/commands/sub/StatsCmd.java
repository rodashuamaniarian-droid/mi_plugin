package tu.paquete.commands.sub;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;
import tu.paquete.data.BedWarsPlayer;

public class StatsCmd implements SubCommand {

    private final BedWarsPlugin plugin;

    public StatsCmd(BedWarsPlugin plugin) { this.plugin = plugin; }

    @Override public String getName() { return "stats"; }
    @Override public String getDescription() { return "Mostrar stats"; }
    @Override public String getSyntax() { return "stats [player]"; }

    @Override
    public void perform(Player sender, String[] args) {

        BedWarsPlayer bw;
        if (args.length >= 2) {
            Player t = Bukkit.getPlayer(args[1]);
            if (t == null) {
                sender.sendMessage("§cJugador no encontrado.");
                return;
            }
            bw = plugin.getPlayerData().get(t.getUniqueId());
        } else {
            bw = plugin.getPlayerData().get(sender.getUniqueId());
        }

        sender.sendMessage("§6-- Stats de " + (args.length>=2?args[1]:sender.getName()) + " --");
        sender.sendMessage("§eKills: §f" + bw.getKills());
        sender.sendMessage("§eDeaths: §f" + bw.getDeaths());
        sender.sendMessage("§eWins: §f" + bw.getWins());
        sender.sendMessage("§eLosses: §f" + bw.getLosses());
        sender.sendMessage("§eLevel: §f" + bw.getLevel());
    }
}
