package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import tu.paquete.BedWarsPlugin;

public class SetupGradesCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public SetupGradesCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "setupgrades"; }
    @Override public String usage(){ return "setupgrades <arena> <team>"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(args.length < 2){ sender.sendMessage("Uso: /bw setupgrades <arena> <team>"); return true; }
        String arena = args[0].toLowerCase();
        String team = args[1].toLowerCase();
        plugin.getConfig().set("arenas." + arena + ".teams." + team + ".upgrades.setup", true);
        plugin.saveConfig();
        sender.sendMessage("§aModo setup de upgrades activado para " + team + " en " + arena);
        return true;
    }
}
