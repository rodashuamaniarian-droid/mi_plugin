package tu.paquete.commands.sub;

import org.bukkit.entity.Player;
import tu.paquete.BedWarsPlugin;
import tu.paquete.commands.SubCommand;

public class LeaveCmd implements SubCommand {

    private final BedWarsPlugin plugin;

    public LeaveCmd(BedWarsPlugin plugin) { this.plugin = plugin; }

    @Override public String getName() { return "leave"; }
    @Override public String getDescription() { return "Salir de la arena"; }
    @Override public String getSyntax() { return "leave"; }

    @Override
    public void perform(Player player, String[] args) {
        plugin.getArenaManager().leaveAllArenas(player);
        player.sendMessage("§aHas salido de la arena.");
        if (plugin.getConfig().contains("lobby")) {
            player.teleport((org.bukkit.Location) plugin.getConfig().get("lobby"));
        }
    }
}
