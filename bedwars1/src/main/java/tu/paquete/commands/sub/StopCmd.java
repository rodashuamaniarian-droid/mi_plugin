package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;

public class StopCmd implements SubCommand {

    private final BedWarsPlugin plugin;

    public StopCmd(BedWarsPlugin plugin) { this.plugin = plugin; }

    @Override public String getName() { return "stop"; }
    @Override public String getDescription() { return "Detener partida"; }
    @Override public String getSyntax() { return "stop <arena>"; }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) { player.sendMessage("§cUsa: /bw stop <arena>"); return; }
        String arena = args[1];
        tu.paquete.arenas.Arena a = plugin.getArenaManager().getArena(arena);
        if (a == null) { player.sendMessage("§cArena no encontrada."); return; }
        a.end(null);
        player.sendMessage("§cPartida detenida en " + arena);
    }
}
