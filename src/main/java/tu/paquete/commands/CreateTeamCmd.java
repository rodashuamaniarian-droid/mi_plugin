package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import tu.paquete.BedWarsPlugin;

public class CreateTeamCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public CreateTeamCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "create"; }
    @Override public String usage(){ return "create team <arena> <team>"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(args.length < 3 || !args[0].equalsIgnoreCase("team")){ sender.sendMessage("Uso: /bw create team <arena> <team>"); return true; }
        String arena = args[1].toLowerCase();
        String team = args[2].toLowerCase();
        plugin.getConfig().set("arenas." + arena + ".teams." + team + ".enabled", true);
        plugin.saveConfig();
        sender.sendMessage("§aEquipo " + team + " creado en arena " + arena);
        return true;
    }
}
