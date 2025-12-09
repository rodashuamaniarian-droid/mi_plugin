package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;
import org.bukkit.Location;

public class SetLobbyCmd implements SubCommand {
    private final BedWarsPlugin plugin;
    public SetLobbyCmd(BedWarsPlugin plugin){ this.plugin = plugin; }

    @Override public String getName(){ return "setlobby"; }
    @Override public String getDescription(){ return "Establecer lobby principal"; }
    @Override public String getSyntax(){ return "setlobby"; }

    @Override
    public void perform(Player player, String[] args){
        Location loc = player.getLocation();
        plugin.getConfig().set("lobby", loc);
        plugin.saveConfig();
        player.sendMessage("§aLobby establecido.");
    }
}
