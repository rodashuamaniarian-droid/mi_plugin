package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;

public class SetSpecCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public SetSpecCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "setspec"; }
    @Override public String usage(){ return "setspec"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){ sender.sendMessage("Solo jugadores."); return true; }
        Player p = (Player) sender;
        plugin.getConfig().set("spectator", plugin.locToString(p.getLocation()));
        plugin.saveConfig();
        p.sendMessage("§aUbicación de espectador guardada.");
        return true;
    }
}
