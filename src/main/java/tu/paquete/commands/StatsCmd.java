package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.BedWarsPlayer;

public class StatsCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public StatsCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "stats"; }
    @Override public String usage(){ return "stats [player]"; }
    @Override public String permission(){ return "bedwars.player"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        BedWarsPlayer bw;
        if(args.length == 0){
            if(!(sender instanceof Player)){ sender.sendMessage("Usage: /bw stats <player>"); return true; }
            bw = plugin.getBedWarsPlayer((Player)sender);
        } else {
            // try online
            Player t = plugin.getServer().getPlayer(args[0]);
            if(t != null) bw = plugin.getBedWarsPlayer(t);
            else { sender.sendMessage("Jugador offline: usa /bw top para ver ranking."); return true; }
        }
        sender.sendMessage("§aStats de " + bw.getName());
        sender.sendMessage("Kills: " + bw.getKills());
        sender.sendMessage("Deaths: " + bw.getDeaths());
        sender.sendMessage("Wins: " + bw.getWins());
        sender.sendMessage("Final Kills: " + bw.getFinalKills());
        sender.sendMessage("BedsBroken: " + bw.getBedsBroken());
        sender.sendMessage("Coins: " + bw.getCoins());
        return true;
    }
}
