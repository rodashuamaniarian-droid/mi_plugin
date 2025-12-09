package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;

public class SaveCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public SaveCmd(BedWarsPlugin plugin){ this.plugin = plugin; }

    @Override public String getName(){ return "save"; }
    @Override public String getDescription(){ return "Guardar config/arenas"; }
    @Override public String getSyntax(){ return "save"; }

    @Override
    public void perform(Player player, String[] args){
        plugin.getArenaManager().saveArenas();
        player.sendMessage("§aConfiguración guardada.");
    }
}
