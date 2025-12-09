package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import tu.paquete.BedWarsPlugin;

public class DisableCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public DisableCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "disable"; }
    @Override public String usage(){ return "disable <arena>"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(args.length < 1){ sender.sendMessage("Uso: /bw disable <arena>"); return true; }
        String arena = args[0].toLowerCase();
        plugin.getConfig().set("arenas." + arena + ".enabled", false);
        plugin.saveConfig();
        sender.sendMessage("§cArena " + arena + " deshabilitada.");
        return true;
    }
}
