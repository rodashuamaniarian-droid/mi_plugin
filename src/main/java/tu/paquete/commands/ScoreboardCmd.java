package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import tu.paquete.BedWarsPlugin;

public class ScoreboardCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public ScoreboardCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "scoreboard"; }
    @Override public String usage(){ return "scoreboard <enable|disable>"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(args.length < 1){ sender.sendMessage("Uso: /bw scoreboard <enable|disable>"); return true; }
        String op = args[0].toLowerCase();
        if(op.equals("enable")) plugin.getConfig().set("scoreboard.enabled", true);
        else plugin.getConfig().set("scoreboard.enabled", false);
        plugin.saveConfig();
        sender.sendMessage("§aScoreboard " + op + "d.");
        return true;
    }
}
