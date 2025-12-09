package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;

public class StartCmd implements SubCommand {

    private final BedWarsPlugin plugin;

    public StartCmd(BedWarsPlugin plugin) { this.plugin = plugin; }

    @Override public String getName() { return "start"; }
    @Override public String getDescription() { return "Iniciar partida manualmente"; }
    @Override public String getSyntax() { return "start <arena>"; }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) { player.sendMessage("§cUsa: /bw start <arena>"); return; }
        String arena = args[1];
        tu.paquete.arenas.Arena a = plugin.getArenaManager().getArena(arena);
        if (a == null) { player.sendMessage("§cArena no encontrada."); return; }
        a.start();
        player.sendMessage("§aPartida iniciada en " + arena);
    }
}
