package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;
import tu.paquete.arenas.Arena;

public class CreateCmd implements SubCommand {

    private final BedWarsPlugin plugin;

    public CreateCmd(BedWarsPlugin plugin) { this.plugin = plugin; }

    @Override public String getName() { return "arena"; }
    @Override public String getDescription() { return "Crear o administrar arenas (uso: /bw arena create <name>)"; }
    @Override public String getSyntax() { return "arena create <name>"; }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 3 || !args[1].equalsIgnoreCase("create")) {
            player.sendMessage("§cUsa: /bw arena create <name>");
            return;
        }
        String name = args[2].toLowerCase();
        Arena a = plugin.getArenaManager().createArena(name);
        plugin.getArenaManager().saveArenas();
        player.sendMessage("§aArena creada: §e" + name);
    }
}
