package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;

public class JoinCmd implements SubCommand {

    private final BedWarsPlugin plugin;

    public JoinCmd(BedWarsPlugin plugin) { this.plugin = plugin; }

    @Override public String getName() { return "join"; }
    @Override public String getDescription() { return "Unirse a una arena"; }
    @Override public String getSyntax() { return "join <arena>"; }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) { player.sendMessage("§cUsa: /bw join <arena>"); return; }
        String arena = args[1];
        plugin.getArenaManager().joinArena(player, arena);
    }
}
