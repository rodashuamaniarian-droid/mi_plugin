package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import tu.paquete.BedWarsPlugin;

public class StopCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public StopCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "stop"; }
    @Override public String usage(){ return "stop <arena>"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(args.length < 1){ sender.sendMessage("Uso: /bw stop <arena>"); return true; }
        String arena = args[0].toLowerCase();
        plugin.getConfig().set("arenas." + arena + ".running", false);
        plugin.saveConfig();
        plugin.getServer().broadcastMessage("§cPartida de " + arena + " detenida por " + sender.getName());
        return true;
    }
}
