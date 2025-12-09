package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import tu.paquete.BedWarsPlugin;

public class ArenaCreateCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public ArenaCreateCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "arena"; }
    @Override public String usage(){ return "arena create <nombre>"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(args.length < 2 || !args[0].equalsIgnoreCase("create")){
            sender.sendMessage("Uso: /bw arena create <nombre>");
            return true;
        }
        String name = args[1].toLowerCase();
        plugin.getConfig().set("arenas." + name + ".enabled", false);
        plugin.saveConfig();
        sender.sendMessage("§aArena '" + name + "' creada. Editala con /bw setup " + name);
        return true;
    }
}
