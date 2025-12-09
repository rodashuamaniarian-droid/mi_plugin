package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;

public class SetBedCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public SetBedCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "setbed"; }
    @Override public String usage(){ return "setbed <arena> <team>"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){ sender.sendMessage("Solo jugadores."); return true; }
        if(args.length < 2){ sender.sendMessage("Uso: /bw setbed <arena> <team>"); return true; }
        Player p = (Player) sender;
        String arena = args[0].toLowerCase();
        String team = args[1].toLowerCase();
        plugin.getConfig().set("arenas." + arena + ".teams." + team + ".bed", plugin.locToString(p.getLocation()));
        plugin.saveConfig();
        p.sendMessage("§aCama guardada para " + team + " en arena " + arena);
        return true;
    }
}
