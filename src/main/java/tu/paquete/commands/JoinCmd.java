package tu.paquete.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;

public class JoinCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public JoinCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "join"; }
    @Override public String usage(){ return "join <arena>"; }
    @Override public String permission(){ return "bedwars.player"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){ sender.sendMessage("Solo jugadores."); return true; }
        if(args.length < 1){ sender.sendMessage("Uso: /bw join <arena>"); return true; }
        Player p = (Player)sender;
        String arena = args[0].toLowerCase();
        if(!plugin.getConfig().contains("arenas." + arena)){
            p.sendMessage("§cArena no encontrada.");
            return true;
        }
        // Teletransportar al lobby de la arena o al lobby global si está definido
        String lobbyStr = plugin.getConfig().getString("lobby", null);
        if(lobbyStr != null){
            p.teleport(plugin.stringToLoc(lobbyStr));
            p.sendMessage("§aTe uniste a la arena " + arena + ". Espera al inicio.");
        } else {
            p.sendMessage("§cNo hay lobby configurado.");
        }
        // Aquí podrías añadir al equipo o cola en una estructura en memoria
        return true;
    }
}
