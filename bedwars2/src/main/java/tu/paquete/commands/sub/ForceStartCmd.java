package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;

public class ForceStartCmd implements SubCommand {

    private final BedWarsPlugin plugin;

    public ForceStartCmd(BedWarsPlugin plugin) { this.plugin = plugin; }

    @Override public String getName() { return "forcestart"; }
    @Override public String getDescription() { return "Forzar inicio de partida"; }
    @Override public String getSyntax() { return "forcestart <arena>"; }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) { player.sendMessage("§cUsa: /bw forcestart <arena>"); return; }
        String arena = args[1];
        tu.paquete.arenas.Arena a = plugin.getArenaManager().getArena(arena);
        if (a == null) { player.sendMessage("§cArena no encontrada."); return; }
        a.start();
        player.sendMessage("§aPartida iniciada en " + arena);
    }
}
