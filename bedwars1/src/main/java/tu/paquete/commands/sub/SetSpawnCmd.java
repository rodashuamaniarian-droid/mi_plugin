package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;

public class SetSpawnCmd implements SubCommand {

    private final BedWarsPlugin plugin;

    public SetSpawnCmd(BedWarsPlugin plugin) { this.plugin = plugin; }

    @Override public String getName() { return "setspawn"; }
    @Override public String getDescription() { return "Establecer spawn de equipo"; }
    @Override public String getSyntax() { return "setspawn <arena> <team>"; }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 3) { player.sendMessage("§cUsa: /bw setspawn <arena> <team>"); return; }
        String arenaName = args[1];
        String teamName = args[2].toUpperCase();

        Location loc = player.getLocation();
        plugin.getArenaManager().getArena(arenaName).getTeam(tu.paquete.arenas.TeamColor.valueOf(teamName)).setSpawn(loc);
        plugin.getArenaManager().saveArenas();
        player.sendMessage("§aSpawn establecido para " + teamName + " en arena " + arenaName);
    }
}
