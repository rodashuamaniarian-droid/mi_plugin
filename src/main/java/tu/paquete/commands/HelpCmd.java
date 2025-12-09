package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import tu.paquete.BedWarsPlugin;

public class HelpCmd implements SubCommand {

    private final BedWarsPlugin plugin;
    private final BWCommand parent;

    public HelpCmd(BedWarsPlugin plugin, BWCommand parent){
        this.plugin = plugin;
        this.parent = parent;
    }

    @Override public String name(){ return "help"; }
    @Override public String usage(){ return "/bw help"; }
    @Override public String permission(){ return null; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§a---- BedWars Help v5 ----");
        for(SubCommand sc : parent.getSubCommands().values()){
            if(sc.permission() == null || sender.hasPermission(sc.permission())){
                sender.sendMessage("§e/" + sc.usage());
            }
        }
        sender.sendMessage("§a-------------------------");
        return true;
    }
}
