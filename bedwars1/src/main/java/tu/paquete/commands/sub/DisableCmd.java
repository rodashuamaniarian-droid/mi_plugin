package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;

public class DisableCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public DisableCmd(BedWarsPlugin plugin){ this.plugin = plugin; }

    @Override public String getName(){ return "disable"; }
    @Override public String getDescription(){ return "Deshabilitar arena"; }
    @Override public String getSyntax(){ return "disable <arena>"; }

    @Override
    public void perform(Player player, String[] args){
        if (args.length < 2) { player.sendMessage("§cUsa: /bw disable <arena>"); return; }
        plugin.getArenaManager().getArena(args[1]).setEnabled(false);
        plugin.getArenaManager().saveArenas();
        player.sendMessage("§cArena deshabilitada.");
    }
}
