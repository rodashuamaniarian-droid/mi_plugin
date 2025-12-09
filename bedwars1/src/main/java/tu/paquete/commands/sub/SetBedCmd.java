package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;

public class SetBedCmd implements SubCommand {

    private final BedWarsPlugin plugin;

    public SetBedCmd(BedWarsPlugin plugin) { this.plugin = plugin; }

    @Override public String getName() { return "setbed"; }
    @Override public String getDescription() { return "Establecer cama del equipo"; }
    @Override public String getSyntax() { return "setbed <arena> <team>"; }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 3) { player.sendMessage("§cUsa: /bw setbed <arena> <team>"); return; }
        String arenaName = args[1];
        String teamName = args[2].toUpperCase();

        Location loc = player.getLocation();
        plugin.getArenaManager().getArena(arenaName).getTeam(tu.paquete.arenas.TeamColor.valueOf(teamName)).setBedLocation(loc);
        plugin.getArenaManager().saveArenas();
        player.sendMessage("§aCama establecida para " + teamName + " en arena " + arenaName);
    }
}
