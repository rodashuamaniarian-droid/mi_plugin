package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;

public class SetLobbyCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public SetLobbyCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "setlobby"; }
    @Override public String usage(){ return "setlobby"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){ sender.sendMessage("Solo jugadores."); return true; }
        Player p = (Player) sender;
        String loc = plugin.locToString(p.getLocation());
        plugin.getConfig().set("lobby", loc);
        plugin.saveConfig();
        p.sendMessage("§aLobby guardado.");
        return true;
    }
}
