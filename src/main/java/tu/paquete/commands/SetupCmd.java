package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;

public class SetupCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public SetupCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "setup"; }
    @Override public String usage(){ return "setup <arena>"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){ sender.sendMessage("Solo jugadores pueden usar /bw setup"); return true; }
        if(args.length < 1){ sender.sendMessage("Uso: /bw setup <arena>"); return true; }
        Player p = (Player) sender;
        String arena = args[0].toLowerCase();
        plugin.getConfig().set("setup." + p.getUniqueId().toString(), arena);
        plugin.saveConfig();
        p.sendMessage("§aModo setup activado para arena: " + arena + ". Usa los comandos /bw setspawn, /bw setbed, /bw setshop.");
        return true;
    }
}
