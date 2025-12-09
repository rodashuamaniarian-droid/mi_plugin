package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;

public class LeaveCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public LeaveCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "leave"; }
    @Override public String usage(){ return "leave"; }
    @Override public String permission(){ return "bedwars.player"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){ sender.sendMessage("Solo jugadores."); return true; }
        Player p = (Player)sender;
        String lobbyStr = plugin.getConfig().getString("lobby", null);
        if(lobbyStr != null) p.teleport(plugin.stringToLoc(lobbyStr));
        p.getInventory().clear();
        p.sendMessage("§aSaliste de la partida.");
        return true;
    }
}
