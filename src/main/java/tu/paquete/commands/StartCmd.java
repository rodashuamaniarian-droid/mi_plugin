package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import tu.paquete.BedWarsPlugin;

public class StartCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public StartCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "start"; }
    @Override public String usage(){ return "start <arena>"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(args.length < 1){ sender.sendMessage("Uso: /bw start <arena>"); return true; }
        String arena = args[0].toLowerCase();
        plugin.getConfig().set("arenas." + arena + ".running", true);
        plugin.saveConfig();
        plugin.getServer().broadcastMessage("§aPartida de " + arena + " iniciada por " + sender.getName());
        return true;
    }
}
