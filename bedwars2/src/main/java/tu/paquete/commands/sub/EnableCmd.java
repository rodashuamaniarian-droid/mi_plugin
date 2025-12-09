package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;

public class EnableCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public EnableCmd(BedWarsPlugin plugin){ this.plugin = plugin; }

    @Override public String getName(){ return "enable"; }
    @Override public String getDescription(){ return "Habilitar arena"; }
    @Override public String getSyntax(){ return "enable <arena>"; }

    @Override
    public void perform(Player player, String[] args){
        if (args.length < 2) { player.sendMessage("§cUsa: /bw enable <arena>"); return; }
        plugin.getArenaManager().getArena(args[1]).setEnabled(true);
        plugin.getArenaManager().saveArenas();
        player.sendMessage("§aArena habilitada.");
    }
}
