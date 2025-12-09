package tu.paquete.commands;

import org.bukkit.command.CommandSender;
import tu.paquete.BedWarsPlugin;

public class SaveCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public SaveCmd(BedWarsPlugin plugin){ this.plugin = plugin; }
    @Override public String name(){ return "save"; }
    @Override public String usage(){ return "save"; }
    @Override public String permission(){ return "bedwars.admin"; }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        plugin.saveConfig();
        sender.sendMessage("§aConfiguración guardada.");
        return true;
    }
}
